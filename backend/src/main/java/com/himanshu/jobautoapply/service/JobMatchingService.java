package com.himanshu.jobautoapply.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobMatchingService {

    private static final Map<String, List<String>> SKILL_MAPPINGS = Map.of(
        "java", Arrays.asList("spring", "spring boot", "hibernate", "jpa", "maven", "gradle"),
        "javascript", Arrays.asList("js", "node.js", "nodejs", "react", "vue", "angular"),
        "python", Arrays.asList("django", "flask", "fastapi", "pandas", "numpy"),
        "database", Arrays.asList("sql", "mysql", "postgresql", "mongodb", "redis"),
        "cloud", Arrays.asList("aws", "azure", "gcp", "google cloud", "docker", "kubernetes"),
        "frontend", Arrays.asList("html", "css", "react", "vue", "angular", "bootstrap"),
        "backend", Arrays.asList("spring boot", "node.js", "express", "django", "flask")
    );

    public JobMatchResult calculateSkillMatch(List<String> userSkills, List<String> jobRequirements, double minMatchThreshold) {
        if (jobRequirements == null || jobRequirements.isEmpty()) {
            return new JobMatchResult(0.0, Collections.emptyList(), Collections.emptyList(), false);
        }

        List<String> normalizedUserSkills = userSkills.stream()
            .map(skill -> skill.toLowerCase().trim())
            .collect(Collectors.toList());

        List<String> normalizedJobRequirements = jobRequirements.stream()
            .map(req -> req.toLowerCase().trim())
            .collect(Collectors.toList());

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String requirement : normalizedJobRequirements) {
            boolean isMatched = normalizedUserSkills.stream()
                .anyMatch(userSkill -> 
                    userSkill.contains(requirement) || 
                    requirement.contains(userSkill) ||
                    areSimilarSkills(userSkill, requirement)
                );

            if (isMatched) {
                matchedSkills.add(requirement);
            } else {
                missingSkills.add(requirement);
            }
        }

        double skillMatch = (double) matchedSkills.size() / normalizedJobRequirements.size() * 100;
        boolean isQualified = skillMatch >= minMatchThreshold;

        return new JobMatchResult(
            Math.round(skillMatch * 100.0) / 100.0,
            matchedSkills,
            missingSkills,
            isQualified
        );
    }

    private boolean areSimilarSkills(String skill1, String skill2) {
        for (Map.Entry<String, List<String>> entry : SKILL_MAPPINGS.entrySet()) {
            String category = entry.getKey();
            List<String> skills = entry.getValue();

            boolean skill1InCategory = skills.contains(skill1) || skill1.contains(category);
            boolean skill2InCategory = skills.contains(skill2) || skill2.contains(category);

            if (skill1InCategory && skill2InCategory) {
                return true;
            }
        }
        return false;
    }

    public List<String> extractSkillsFromJobDescription(String description) {
        List<String> commonSkills = Arrays.asList(
            "Java", "Spring Boot", "Spring", "Hibernate", "JavaScript", "TypeScript", 
            "React", "Angular", "Vue", "Node.js", "Python", "Django", "Flask",
            "HTML", "CSS", "MySQL", "PostgreSQL", "MongoDB", "AWS", "Azure", "GCP",
            "Docker", "Kubernetes", "Git", "REST API", "GraphQL", "Microservices"
        );

        List<String> foundSkills = new ArrayList<>();
        String lowerDescription = description.toLowerCase();

        for (String skill : commonSkills) {
            if (lowerDescription.contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        return foundSkills.stream().distinct().collect(Collectors.toList());
    }

    public static class JobMatchResult {
        private final double skillMatch;
        private final List<String> matchedSkills;
        private final List<String> missingSkills;
        private final boolean isQualified;

        public JobMatchResult(double skillMatch, List<String> matchedSkills, 
                            List<String> missingSkills, boolean isQualified) {
            this.skillMatch = skillMatch;
            this.matchedSkills = matchedSkills;
            this.missingSkills = missingSkills;
            this.isQualified = isQualified;
        }

        // Getters
        public double getSkillMatch() { return skillMatch; }
        public List<String> getMatchedSkills() { return matchedSkills; }
        public List<String> getMissingSkills() { return missingSkills; }
        public boolean isQualified() { return isQualified; }
    }
}
