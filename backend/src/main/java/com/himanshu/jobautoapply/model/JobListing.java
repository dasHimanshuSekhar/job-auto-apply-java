package com.himanshu.jobautoapply.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_listings", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"platform", "external_id"}))
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Company is required")
    private String company;

    private String location;
    private String salary;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "job_requirements", joinColumns = @JoinColumn(name = "job_listing_id"))
    @Column(name = "requirement")
    private List<String> requirements;

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotBlank(message = "External ID is required")
    @Column(name = "external_id")
    private String externalId;

    @NotBlank(message = "URL is required")
    private String url;

    @ElementCollection
    @CollectionTable(name = "job_skills_required", joinColumns = @JoinColumn(name = "job_listing_id"))
    @Column(name = "skill")
    private List<String> skillsRequired;

    @Column(name = "experience_level")
    private String experienceLevel;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "jobListing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobApplication> jobApplications;

    // Constructors
    public JobListing() {}

    public JobListing(String title, String company, String platform, String externalId, String url) {
        this.title = title;
        this.company = company;
        this.platform = platform;
        this.externalId = externalId;
        this.url = url;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getRequirements() { return requirements; }
    public void setRequirements(List<String> requirements) { this.requirements = requirements; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<String> getSkillsRequired() { return skillsRequired; }
    public void setSkillsRequired(List<String> skillsRequired) { this.skillsRequired = skillsRequired; }

    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }

    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }

    public LocalDateTime getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDateTime postedDate) { this.postedDate = postedDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<JobApplication> getJobApplications() { return jobApplications; }
    public void setJobApplications(List<JobApplication> jobApplications) { this.jobApplications = jobApplications; }
}
