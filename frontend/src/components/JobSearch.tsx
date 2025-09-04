import React, { useState } from 'react';
import { searchJobs, applyToJob, JobListing } from '../services/api';

interface JobSearchProps {
  userResume: any;
}

const JobSearch: React.FC<JobSearchProps> = ({ userResume }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [location, setLocation] = useState('');
  const [jobs, setJobs] = useState<JobListing[]>([]);
  const [loading, setLoading] = useState(false);
  const [applying, setApplying] = useState<number | null>(null);

  const handleSearch = async () => {
    if (!searchQuery.trim()) return;

    setLoading(true);
    try {
      // Mock data for demo
      const mockJobs: JobListing[] = [
        {
          id: 1,
          title: 'Senior Full Stack Developer',
          company: 'TechCorp Solutions',
          location: 'Bangalore, India',
          salary: '₹15-25 LPA',
          description: 'We are looking for a Senior Full Stack Developer with expertise in Java Spring Boot, React, and cloud technologies.',
          platform: 'LinkedIn',
          skillsRequired: ['Java', 'Spring Boot', 'React', 'PostgreSQL', 'AWS'],
          skillMatch: 95,
          matchedSkills: ['Java', 'Spring Boot', 'React', 'PostgreSQL'],
          isQualified: true
        },
        {
          id: 2,
          title: 'Java Backend Developer',
          company: 'StartupXYZ',
          location: 'Mumbai, India',
          salary: '₹12-18 LPA',
          description: 'Join our team as a Java Backend Developer. Work with Spring Boot, microservices, and modern technologies.',
          platform: 'Naukri',
          skillsRequired: ['Java', 'Spring Boot', 'Microservices', 'MySQL'],
          skillMatch: 88,
          matchedSkills: ['Java', 'Spring Boot'],
          isQualified: true
        },
        {
          id: 3,
          title: 'Full Stack Developer',
          company: 'DataTech Inc',
          location: 'Hyderabad, India',
          salary: '₹10-16 LPA',
          description: 'Looking for a Full Stack Developer with React and Java experience.',
          platform: 'Indeed',
          skillsRequired: ['Java', 'React', 'Node.js', 'MongoDB'],
          skillMatch: 92,
          matchedSkills: ['Java', 'React'],
          isQualified: true
        }
      ];

      setJobs(mockJobs);
    } catch (error) {
      console.error('Search failed:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleApply = async (job: JobListing) => {
    if (!userResume) {
      alert('Please upload your resume first');
      return;
    }

    setApplying(job.id);
    try {
      await applyToJob({
        jobId: job.id,
        resumeId: userResume.id,
        applicationMethod: 'auto_submit'
      });
      
      alert(`Successfully applied to ${job.title} at ${job.company}!`);
    } catch (error) {
      console.error('Application failed:', error);
      alert('Failed to apply. Please try again.');
    } finally {
      setApplying(null);
    }
  };

  return (
    <div className="job-search">
      <div className="search-section">
        <h3>Find Matching Jobs</h3>
        <p>Search for jobs that match your skills across multiple platforms</p>

        <div className="search-form">
          <div className="search-inputs">
            <div className="input-group">
              <label>Job Title / Keywords</label>
              <input
                type="text"
                placeholder="e.g. Full Stack Developer"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
              />
            </div>
            
            <div className="input-group">
              <label>Location</label>
              <input
                type="text"
                placeholder="e.g. Bangalore, India"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
              />
            </div>
          </div>

          <button 
            onClick={handleSearch} 
            disabled={loading || !searchQuery.trim()}
            className="search-btn"
          >
            {loading ? 'Searching...' : '🔍 Search Jobs'}
          </button>
        </div>
      </div>

      {jobs.length > 0 && (
        <div className="results-section">
          <h4>Found Jobs ({jobs.length})</h4>
          
          <div className="jobs-list">
            {jobs.map((job) => (
              <div key={job.id} className="job-item">
                <div className="job-header">
                  <div className="job-title-company">
                    <h5>{job.title}</h5>
                    <p>{job.company} • {job.platform}</p>
                    {job.location && <small>📍 {job.location}</small>}
                    {job.salary && <small>💰 {job.salary}</small>}
                  </div>
                  
                  <div className="job-actions">
                    <div className="skill-match-badge">
                      {job.skillMatch}% match
                    </div>
                    <button
                      onClick={() => handleApply(job)}
                      disabled={applying === job.id}
                      className="apply-btn"
                    >
                      {applying === job.id ? 'Applying...' : 'Apply'}
                    </button>
                  </div>
                </div>

                <div className="job-description">
                  <p>{job.description}</p>
                </div>

                <div className="job-skills">
                  <div className="skills-section">
                    <span className="skills-label">Required Skills:</span>
                    <div className="skills-tags">
                      {job.skillsRequired?.map((skill, index) => (
                        <span 
                          key={index} 
                          className={`skill-tag ${job.matchedSkills?.includes(skill) ? 'matched' : 'missing'}`}
                        >
                          {skill}
                        </span>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      )}

      {!userResume && (
        <div className="no-resume-warning">
          <p>⚠️ Please upload your resume first to search and apply for jobs.</p>
        </div>
      )}
    </div>
  );
};

export default JobSearch;
