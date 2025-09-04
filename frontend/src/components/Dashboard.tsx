import React, { useState, useEffect } from 'react';
import { getApplications, getDashboardStats, JobApplication } from '../services/api';

interface DashboardProps {
  userResume: any;
}

const Dashboard: React.FC<DashboardProps> = ({ userResume }) => {
  const [applications, setApplications] = useState<JobApplication[]>([]);
  const [stats, setStats] = useState({
    applicationsToday: 0,
    responseRate: 0,
    interviewsScheduled: 0
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadDashboardData();
  }, []);

  const loadDashboardData = async () => {
    try {
      setLoading(true);
      
      // Mock data for demo since backend endpoints might not be fully implemented
      const mockApplications: JobApplication[] = [
        {
          id: 1,
          jobTitle: 'Senior Full Stack Developer',
          company: 'TechCorp Solutions',
          platform: 'LinkedIn',
          skillMatch: 95,
          status: 'APPLIED',
          appliedAt: new Date().toISOString()
        },
        {
          id: 2,
          jobTitle: 'Frontend Developer',
          company: 'StartupXYZ',
          platform: 'Naukri',
          skillMatch: 88,
          status: 'VIEWED',
          appliedAt: new Date(Date.now() - 86400000).toISOString()
        },
        {
          id: 3,
          jobTitle: 'Java Developer',
          company: 'DataTech Inc',
          platform: 'Indeed',
          skillMatch: 92,
          status: 'INTERVIEW_SCHEDULED',
          appliedAt: new Date(Date.now() - 172800000).toISOString(),
          interviewDate: new Date(Date.now() + 86400000).toISOString()
        }
      ];

      setApplications(mockApplications);
      setStats({
        applicationsToday: 12,
        responseRate: 18,
        interviewsScheduled: 3
      });

    } catch (error) {
      console.error('Failed to load dashboard data:', error);
    } finally {
      setLoading(false);
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'APPLIED': return '#3b82f6';
      case 'VIEWED': return '#f59e0b';
      case 'INTERVIEW_SCHEDULED': return '#10b981';
      case 'REJECTED': return '#ef4444';
      default: return '#6b7280';
    }
  };

  const getStatusLabel = (status: string) => {
    switch (status) {
      case 'APPLIED': return 'Applied';
      case 'VIEWED': return 'Viewed';
      case 'INTERVIEW_SCHEDULED': return 'Interview';
      case 'REJECTED': return 'Rejected';
      default: return status;
    }
  };

  if (loading) {
    return (
      <div className="dashboard loading">
        <div className="loading-spinner">Loading dashboard...</div>
      </div>
    );
  }

  return (
    <div className="dashboard">
      {/* Stats Cards */}
      <div className="stats-grid">
        <div className="stat-card">
          <div className="stat-header">
            <h4>Applications Today</h4>
          </div>
          <div className="stat-content">
            <div className="stat-number">{stats.applicationsToday}</div>
            <div className="stat-change">+3 from yesterday</div>
          </div>
        </div>

        <div className="stat-card">
          <div className="stat-header">
            <h4>Response Rate</h4>
          </div>
          <div className="stat-content">
            <div className="stat-number">{stats.responseRate}%</div>
            <div className="stat-change">Above average</div>
          </div>
        </div>

        <div className="stat-card">
          <div className="stat-header">
            <h4>Interviews Scheduled</h4>
          </div>
          <div className="stat-content">
            <div className="stat-number">{stats.interviewsScheduled}</div>
            <div className="stat-change">This week</div>
          </div>
        </div>
      </div>

      {/* Recent Applications */}
      <div className="applications-section">
        <div className="section-header">
          <h3>Recent Applications</h3>
          <p>Your latest job applications and their status</p>
        </div>

        <div className="applications-list">
          {applications.map((app) => (
            <div key={app.id} className="application-item">
              <div className="application-info">
                <h4>{app.jobTitle}</h4>
                <p>{app.company} • {app.platform}</p>
                <small>Applied {new Date(app.appliedAt).toLocaleDateString()}</small>
              </div>
              
              <div className="application-details">
                <div className="skill-match">
                  <span className="match-label">Skill Match</span>
                  <span className="match-percentage">{app.skillMatch}%</span>
                </div>
                
                <div 
                  className="status-badge"
                  style={{ backgroundColor: getStatusColor(app.status) }}
                >
                  {getStatusLabel(app.status)}
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Skills Overview */}
      {userResume && (
        <div className="skills-section">
          <div className="section-header">
            <h3>Your Skills</h3>
            <p>Skills extracted from your resume</p>
          </div>
          
          <div className="skills-grid">
            {userResume.skills?.map((skill: string, index: number) => (
              <span key={index} className="skill-chip">
                {skill}
              </span>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;
