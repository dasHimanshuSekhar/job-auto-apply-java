import React, { useState } from 'react';
import './App.css';
import ResumeUpload from './components/ResumeUpload';
import Dashboard from './components/Dashboard';
import JobSearch from './components/JobSearch';
import Settings from './components/Settings';

function App() {
  const [activeTab, setActiveTab] = useState('upload');
  const [userResume, setUserResume] = useState<any>(null);

  const tabs = [
    { id: 'upload', label: 'Upload Resume', icon: '📄' },
    { id: 'dashboard', label: 'Dashboard', icon: '📊' },
    { id: 'search', label: 'Job Search', icon: '🔍' },
    { id: 'settings', label: 'Settings', icon: '⚙️' }
  ];

  const renderContent = () => {
    switch (activeTab) {
      case 'upload':
        return <ResumeUpload onResumeUploaded={setUserResume} />;
      case 'dashboard':
        return <Dashboard userResume={userResume} />;
      case 'search':
        return <JobSearch userResume={userResume} />;
      case 'settings':
        return <Settings />;
      default:
        return <ResumeUpload onResumeUploaded={setUserResume} />;
    }
  };

  return (
    <div className="App">
      {/* Header */}
      <header className="app-header">
        <div className="container">
          <div className="header-content">
            <div className="logo">
              <span className="logo-icon">⚡</span>
              <h1>JobAutoApply</h1>
            </div>
            <button className="sign-in-btn">Sign In</button>
          </div>
        </div>
      </header>

      <div className="container">
        {/* Hero Section */}
        <div className="hero-section">
          <h2>Automate Your Job Applications</h2>
          <p>
            Upload your resume, set your preferences, and let AI find and apply to jobs that match your skills 80-100%. 
            Apply to LinkedIn, Naukri, and other platforms automatically.
          </p>
        </div>

        {/* Navigation Tabs */}
        <div className="tabs">
          {tabs.map((tab) => (
            <button
              key={tab.id}
              className={`tab ${activeTab === tab.id ? 'active' : ''}`}
              onClick={() => setActiveTab(tab.id)}
            >
              <span className="tab-icon">{tab.icon}</span>
              <span>{tab.label}</span>
            </button>
          ))}
        </div>

        {/* Content */}
        <div className="content">
          {renderContent()}
        </div>
      </div>
    </div>
  );
}

export default App;
