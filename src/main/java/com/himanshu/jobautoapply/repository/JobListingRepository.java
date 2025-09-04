package com.himanshu.jobautoapply.repository;

import com.himanshu.jobautoapply.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    Optional<JobListing> findByPlatformAndExternalId(String platform, String externalId);
    
    List<JobListing> findByPlatformIn(List<String> platforms);
    
    @Query("SELECT j FROM JobListing j WHERE " +
           "LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<JobListing> findByKeyword(@Param("keyword") String keyword);
    
    @Query("SELECT j FROM JobListing j WHERE " +
           "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<JobListing> findByLocationAndKeyword(@Param("location") String location, 
                                            @Param("keyword") String keyword);
}
