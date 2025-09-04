package com.himanshu.jobautoapply.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_listing_id", nullable = false)
    private JobListing jobListing;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @DecimalMin(value = "0.0", message = "Skill match must be at least 0")
    @DecimalMax(value = "100.0", message = "Skill match must be at most 100")
    @Column(name = "skill_match", nullable = false)
    private Double skillMatch;

    @Column(name = "cover_letter", columnDefinition = "TEXT")
    private String coverLetter;

    @NotNull(message = "Application method is required")
    @Column(name = "application_method")
    private String applicationMethod;

    @Column(name = "applied_at")
    private LocalDateTime appliedAt;

    @Column(name = "response_at")
    private LocalDateTime responseAt;

    @Column(name = "interview_date")
    private LocalDateTime interviewDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public JobApplication() {}

    public JobApplication(User user, Resume resume, JobListing jobListing, Double skillMatch, String applicationMethod) {
        this.user = user;
        this.resume = resume;
        this.jobListing = jobListing;
        this.skillMatch = skillMatch;
        this.applicationMethod = applicationMethod;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Resume getResume() { return resume; }
    public void setResume(Resume resume) { this.resume = resume; }

    public JobListing getJobListing() { return jobListing; }
    public void setJobListing(JobListing jobListing) { this.jobListing = jobListing; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public Double getSkillMatch() { return skillMatch; }
    public void setSkillMatch(Double skillMatch) { this.skillMatch = skillMatch; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public String getApplicationMethod() { return applicationMethod; }
    public void setApplicationMethod(String applicationMethod) { this.applicationMethod = applicationMethod; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    public LocalDateTime getResponseAt() { return responseAt; }
    public void setResponseAt(LocalDateTime responseAt) { this.responseAt = responseAt; }

    public LocalDateTime getInterviewDate() { return interviewDate; }
    public void setInterviewDate(LocalDateTime interviewDate) { this.interviewDate = interviewDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

enum ApplicationStatus {
    PENDING,
    APPLIED,
    VIEWED,
    REJECTED,
    INTERVIEW_SCHEDULED,
    INTERVIEW_COMPLETED,
    OFFER_RECEIVED,
    OFFER_ACCEPTED,
    OFFER_REJECTED
}
