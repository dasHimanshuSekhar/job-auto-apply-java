package com.himanshu.jobautoapply.repository;

import com.himanshu.jobautoapply.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUserId(Long userId);
    List<Resume> findByUserIdOrderByCreatedAtDesc(Long userId);
}
