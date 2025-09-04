import React, { useState } from 'react';

const Settings: React.FC = () => {
  const [settings, setSettings] = useState({
    minSkillMatch: 80,
    maxApplicationsPerDay: 10,
    autoApplyEnabled: false,
    emailOutreachEnabled: true,
    linkedinEnabled: true,
    naukriEnabled: true,
    indeedEnabled: false,
    preferredLocations: ['Bangalore', 'Mumbai', 'Hyderabad'],
    salaryRange: '10-20 LPA',
    experienceLevel: 'Mid-level'
  });

  const handleInputChange = (field: string, value: any) => {
    setSettings(prev => ({
      ...prev,
      [field]: value
    }));
  };

  const handleSaveSettings = () => {
    // In a real app, this would save to the backend
    console.log('Saving settings:', settings);
    alert('Settings saved successfully!');
  };

  return (
    <div className="settings">
      <div className="settings-card">
        <div className="settings-header">
          <h3>Application Settings</h3>
          <p>Configure your job application preferences and automation settings</p>
        </div>

        <div className="settings-content">
          <div className="settings-grid">
            {/* Matching Criteria */}
            <div className="settings-section">
              <h4>Matching Criteria</h4>
              
              <div className="setting-item">
                <label>Minimum Skill Match (%)</label>
                <input
                  type="number"
                  min="0"
                  max="100"
                  value={settings.minSkillMatch}
                  onChange={(e) => handleInputChange('minSkillMatch', parseInt(e.target.value))}
                />
              </div>

              <div className="setting-item">
                <label>Max Applications per Day</label>
                <input
                  type="number"
                  min="1"
                  max="50"
                  value={settings.maxApplicationsPerDay}
                  onChange={(e) => handleInputChange('maxApplicationsPerDay', parseInt(e.target.value))}
                />
              </div>

              <div className="setting-item">
                <label>Salary Range</label>
                <input
                  type="text"
                  placeholder="e.g. 10-20 LPA"
                  value={settings.salaryRange}
                  onChange={(e) => handleInputChange('salaryRange', e.target.value)}
                />
              </div>

              <div className="setting-item">
                <label>Experience Level</label>
                <select
                  value={settings.experienceLevel}
                  onChange={(e) => handleInputChange('experienceLevel', e.target.value)}
                >
                  <option value="Entry-level">Entry-level</option>
                  <option value="Mid-level">Mid-level</option>
                  <option value="Senior">Senior</option>
                  <option value="Lead">Lead</option>
                </select>
              </div>
            </div>

            {/* Platform Settings */}
            <div className="settings-section">
              <h4>Platform Settings</h4>
              
              <div className="platform-toggles">
                <div className="platform-item">
                  <div className="platform-info">
                    <span>LinkedIn Auto-Apply</span>
                    <small>Automatically apply to LinkedIn jobs</small>
                  </div>
                  <div className="toggle-switch">
                    <input
                      type="checkbox"
                      id="linkedin"
                      checked={settings.linkedinEnabled}
                      onChange={(e) => handleInputChange('linkedinEnabled', e.target.checked)}
                    />
                    <label htmlFor="linkedin" className="switch"></label>
                  </div>
                </div>

                <div className="platform-item">
                  <div className="platform-info">
                    <span>Naukri Email Outreach</span>
                    <small>Send personalized emails for Naukri jobs</small>
                  </div>
                  <div className="toggle-switch">
                    <input
                      type="checkbox"
                      id="naukri"
                      checked={settings.naukriEnabled}
                      onChange={(e) => handleInputChange('naukriEnabled', e.target.checked)}
                    />
                    <label htmlFor="naukri" className="switch"></label>
                  </div>
                </div>

                <div className="platform-item">
                  <div className="platform-info">
                    <span>Indeed Direct Apply</span>
                    <small>Apply directly through Indeed</small>
                  </div>
                  <div className="toggle-switch">
                    <input
                      type="checkbox"
                      id="indeed"
                      checked={settings.indeedEnabled}
                      onChange={(e) => handleInputChange('indeedEnabled', e.target.checked)}
                    />
                    <label htmlFor="indeed" className="switch"></label>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Automation Settings */}
          <div className="automation-section">
            <h4>Automation Settings</h4>
            
            <div className="automation-toggles">
              <div className="automation-item">
                <div className="automation-info">
                  <span>Auto-Apply Mode</span>
                  <small>Automatically submit applications without review</small>
                </div>
                <div className="toggle-switch">
                  <input
                    type="checkbox"
                    id="autoApply"
                    checked={settings.autoApplyEnabled}
                    onChange={(e) => handleInputChange('autoApplyEnabled', e.target.checked)}
                  />
                  <label htmlFor="autoApply" className="switch"></label>
                </div>
              </div>

              <div className="automation-item">
                <div className="automation-info">
                  <span>Email Outreach</span>
                  <small>Send personalized emails to hiring managers</small>
                </div>
                <div className="toggle-switch">
                  <input
                    type="checkbox"
                    id="emailOutreach"
                    checked={settings.emailOutreachEnabled}
                    onChange={(e) => handleInputChange('emailOutreachEnabled', e.target.checked)}
                  />
                  <label htmlFor="emailOutreach" className="switch"></label>
                </div>
              </div>
            </div>
          </div>

          {/* Preferred Locations */}
          <div className="locations-section">
            <h4>Preferred Locations</h4>
            <div className="locations-tags">
              {settings.preferredLocations.map((location, index) => (
                <span key={index} className="location-tag">
                  {location}
                  <button 
                    onClick={() => {
                      const newLocations = settings.preferredLocations.filter((_, i) => i !== index);
                      handleInputChange('preferredLocations', newLocations);
                    }}
                    className="remove-tag"
                  >
                    ×
                  </button>
                </span>
              ))}
            </div>
            <input
              type="text"
              placeholder="Add new location..."
              onKeyPress={(e) => {
                if (e.key === 'Enter') {
                  const value = (e.target as HTMLInputElement).value.trim();
                  if (value && !settings.preferredLocations.includes(value)) {
                    handleInputChange('preferredLocations', [...settings.preferredLocations, value]);
                    (e.target as HTMLInputElement).value = '';
                  }
                }
              }}
            />
          </div>

          <button onClick={handleSaveSettings} className="save-btn">
            Save Settings
          </button>
        </div>
      </div>
    </div>
  );
};

export default Settings;
