package com.himanshu.jobautoapply.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeParsingService {

    private static final List<String> COMMON_SKILLS = Arrays.asList(
        "Java", "Spring Boot", "Spring", "Hibernate", "JPA", "Maven", "Gradle",
        "JavaScript", "TypeScript", "React", "Angular", "Vue.js", "Node.js",
        "Python", "Django", "Flask", "FastAPI", "C++", "C#", "PHP", "Ruby", "Go", "Rust",
        "HTML", "CSS", "SASS", "SCSS", "Bootstrap", "Tailwind CSS",
        "MySQL", "PostgreSQL", "MongoDB", "Redis", "SQLite", "Oracle", "SQL Server",
        "AWS", "Azure", "GCP", "Google Cloud", "Docker", "Kubernetes", "Jenkins",
        "Git", "GitHub", "GitLab", "Jira", "Confluence", "Postman", "Swagger",
        "Machine Learning", "AI", "Data Science", "Analytics", "TensorFlow", "Keras",
        "REST API", "GraphQL", "Microservices", "DevOps", "CI/CD",
        "Linux", "Unix", "Windows", "Agile", "Scrum", "Kanban"
    );

    public ParsedResume parseResume(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are supported");
        }

        String text = extractTextFromPDF(file);
        
        return ParsedResume.builder()
            .text(text)
            .skills(extractSkills(text))
            .experience(extractExperience(text))
            .education(extractEducation(text))
            .summary(extractSummary(text))
            .build();
    }

    private String extractTextFromPDF(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    private List<String> extractSkills(String text) {
        List<String> foundSkills = new ArrayList<>();
        String lowerText = text.toLowerCase();

        for (String skill : COMMON_SKILLS) {
            if (lowerText.contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        // Extract skills from skills section
        Pattern skillsPattern = Pattern.compile(
            "(?i)(?:skills|technologies|technical skills|core competencies)[:\\s]*(.*?)(?:\\n\\n|\\n[A-Z]|$)",
            Pattern.DOTALL
        );
        
        Matcher matcher = skillsPattern.matcher(text);
        if (matcher.find()) {
            String skillsSection = matcher.group(1);
            for (String skill : COMMON_SKILLS) {
                if (skillsSection.toLowerCase().contains(skill.toLowerCase()) && 
                    !foundSkills.contains(skill)) {
                    foundSkills.add(skill);
                }
            }
        }

        return foundSkills;
    }

    private String extractExperience(String text) {
        Pattern experiencePattern = Pattern.compile(
            "(?i)(?:experience|work experience|professional experience)[:\\s]*(.*?)(?:\\n\\n[A-Z]|education|skills|$)",
            Pattern.DOTALL
        );
        
        Matcher matcher = experiencePattern.matcher(text);
        if (matcher.find()) {
            String experience = matcher.group(1).trim();
            return experience.length() > 1000 ? experience.substring(0, 1000) + "..." : experience;
        }
        return "";
    }

    private String extractEducation(String text) {
        Pattern educationPattern = Pattern.compile(
            "(?i)(?:education|academic background|qualifications)[:\\s]*(.*?)(?:\\n\\n[A-Z]|experience|skills|$)",
            Pattern.DOTALL
        );
        
        Matcher matcher = educationPattern.matcher(text);
        if (matcher.find()) {
            String education = matcher.group(1).trim();
            return education.length() > 500 ? education.substring(0, 500) + "..." : education;
        }
        return "";
    }

    private String extractSummary(String text) {
        Pattern summaryPattern = Pattern.compile(
            "(?i)(?:summary|profile|objective|about|profile summary)[:\\s]*(.*?)(?:\\n\\n[A-Z]|experience|education|$)",
            Pattern.DOTALL
        );
        
        Matcher matcher = summaryPattern.matcher(text);
        if (matcher.find()) {
            String summary = matcher.group(1).trim();
            return summary.length() > 500 ? summary.substring(0, 500) + "..." : summary;
        }
        return "";
    }

    public static class ParsedResume {
        private String text;
        private List<String> skills;
        private String experience;
        private String education;
        private String summary;

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private ParsedResume resume = new ParsedResume();

            public Builder text(String text) {
                resume.text = text;
                return this;
            }

            public Builder skills(List<String> skills) {
                resume.skills = skills;
                return this;
            }

            public Builder experience(String experience) {
                resume.experience = experience;
                return this;
            }

            public Builder education(String education) {
                resume.education = education;
                return this;
            }

            public Builder summary(String summary) {
                resume.summary = summary;
                return this;
            }

            public ParsedResume build() {
                return resume;
            }
        }

        // Getters
        public String getText() { return text; }
        public List<String> getSkills() { return skills; }
        public String getExperience() { return experience; }
        public String getEducation() { return education; }
        public String getSummary() { return summary; }
    }
}
