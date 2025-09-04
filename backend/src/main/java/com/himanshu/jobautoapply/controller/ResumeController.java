package com.himanshu.jobautoapply.controller;

import com.himanshu.jobautoapply.model.Resume;
import com.himanshu.jobautoapply.model.User;
import com.himanshu.jobautoapply.repository.ResumeRepository;
import com.himanshu.jobautoapply.repository.UserRepository;
import com.himanshu.jobautoapply.service.ResumeParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/resumes")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeParsingService resumeParsingService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "userId", defaultValue = "1") Long userId) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "No file uploaded"));
            }

            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Only PDF files are allowed"));
            }

            // Parse resume
            ResumeParsingService.ParsedResume parsedResume = resumeParsingService.parseResume(file);

            // Get or create demo user
            User user = userRepository.findById(userId)
                .orElseGet(() -> {
                    User newUser = new User("demo@example.com", "password", "Demo User");
                    return userRepository.save(newUser);
                });

            // Save resume to database
            Resume resume = new Resume();
            resume.setUser(user);
            resume.setFilename(file.getOriginalFilename());
            resume.setFilePath("/uploads/" + file.getOriginalFilename()); // In production, save to cloud storage
            resume.setSkills(parsedResume.getSkills());
            resume.setExperience(parsedResume.getExperience());
            resume.setEducation(parsedResume.getEducation());
            resume.setSummary(parsedResume.getSummary());

            Resume savedResume = resumeRepository.save(resume);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("resume", Map.of(
                "id", savedResume.getId(),
                "skills", savedResume.getSkills(),
                "experience", savedResume.getExperience(),
                "education", savedResume.getEducation(),
                "summary", savedResume.getSummary()
            ));

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Failed to process resume: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResume(@PathVariable Long id) {
        Optional<Resume> resume = resumeRepository.findById(id);
        
        if (resume.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Resume r = resume.get();
        Map<String, Object> response = Map.of(
            "id", r.getId(),
            "filename", r.getFilename(),
            "skills", r.getSkills(),
            "experience", r.getExperience() != null ? r.getExperience() : "",
            "education", r.getEducation() != null ? r.getEducation() : "",
            "summary", r.getSummary() != null ? r.getSummary() : "",
            "createdAt", r.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserResumes(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of(
            "success", true,
            "resumes", user.getResumes().stream().map(r -> Map.of(
                "id", r.getId(),
                "filename", r.getFilename(),
                "skills", r.getSkills(),
                "createdAt", r.getCreatedAt()
            )).toList()
        ));
    }
}
