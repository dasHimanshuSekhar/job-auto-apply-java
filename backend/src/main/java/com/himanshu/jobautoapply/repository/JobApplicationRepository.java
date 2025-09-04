package com.himanshu.jobautoapply.repository;

import com.himanshu.jobautoapply.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<JobApplication> findByUserIdAndAppliedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.user.id = :userId AND DATE(ja.appliedAt) = CURRENT_DATE")
    long countTodayApplicationsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ja FROM JobApplication ja WHERE ja.user.id = :userId AND ja.status = 'INTERVIEW_SCHEDULED'")
    List<JobApplication> findUpcomingInterviewsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT AVG(CASE WHEN ja.responseAt IS NOT NULL THEN 1.0 ELSE 0.0 END) * 100 FROM JobApplication ja WHERE ja.user.id = :userId")
    Double getResponseRateByUserId(@Param("userId") Long userId);
}
