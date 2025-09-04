package com.himanshu.jobautoapply.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @DecimalMin(value = "0.0", message = "Minimum skill match must be at least 0")
    @DecimalMax(value = "100.0", message = "Minimum skill match must be at most 100")
    @Column(name = "min_skill_match", nullable = false)
    private Double minSkillMatch = 80.0;

    @Min(value = 1, message = "Max applications per day must be at least 1")
    @Column(name = "max_applications_per_day", nullable = false)
    private Integer maxApplicationsPerDay = 10;

    @ElementCollection
    @CollectionTable(name = "user_preferred_locations", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "location")
    private List<String> preferredLocations;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "experience_level")
    private String experienceLevel;

    @ElementCollection
    @CollectionTable(name = "user_job_types", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "job_type")
    private List<String> jobTypes;

    @Column(name = "auto_apply_enabled", nullable = false)
    private Boolean autoApplyEnabled = false;

    @Column(name = "email_outreach_enabled", nullable = false)
    private Boolean emailOutreachEnabled = true;

    @Column(name = "linkedin_enabled", nullable = false)
    private Boolean linkedinEnabled = true;

    @Column(name = "naukri_enabled", nullable = false)
    private Boolean naukriEnabled = true;

    @Column(name = "indeed_enabled", nullable = false)
    private Boolean indeedEnabled = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public UserPreferences() {}

    public UserPreferences(User user) {
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getMinSkillMatch() { return minSkillMatch; }
    public void setMinSkillMatch(Double minSkillMatch) { this.minSkillMatch = minSkillMatch; }

    public Integer getMaxApplicationsPerDay() { return maxApplicationsPerDay; }
    public void setMaxApplicationsPerDay(Integer maxApplicationsPerDay) { this.maxApplicationsPerDay = maxApplicationsPerDay; }

    public List<String> getPreferredLocations() { return preferredLocations; }
    public void setPreferredLocations(List<String> preferredLocations) { this.preferredLocations = preferredLocations; }

    public String getSalaryRange() { return salaryRange; }
    public void setSalaryRange(String salaryRange) { this.salaryRange = salaryRange; }

    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }

    public List<String> getJobTypes() { return jobTypes; }
    public void setJobTypes(List<String> jobTypes) { this.jobTypes = jobTypes; }

    public Boolean getAutoApplyEnabled() { return autoApplyEnabled; }
    public void setAutoApplyEnabled(Boolean autoApplyEnabled) { this.autoApplyEnabled = autoApplyEnabled; }

    public Boolean getEmailOutreachEnabled() { return emailOutreachEnabled; }
    public void setEmailOutreachEnabled(Boolean emailOutreachEnabled) { this.emailOutreachEnabled = emailOutreachEnabled; }

    public Boolean getLinkedinEnabled() { return linkedinEnabled; }
    public void setLinkedinEnabled(Boolean linkedinEnabled) { this.linkedinEnabled = linkedinEnabled; }

    public Boolean getNaukriEnabled() { return naukriEnabled; }
    public void setNaukriEnabled(Boolean naukriEnabled) { this.naukriEnabled = naukriEnabled; }

    public Boolean getIndeedEnabled() { return indeedEnabled; }
    public void setIndeedEnabled(Boolean indeedEnabled) { this.indeedEnabled = indeedEnabled; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
