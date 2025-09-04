import React, { useState } from 'react';
import { uploadResume, Resume } from '../services/api';

interface ResumeUploadProps {
  onResumeUploaded: (resume: Resume) => void;
}

const ResumeUpload: React.FC<ResumeUploadProps> = ({ onResumeUploaded }) => {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [isUploading, setIsUploading] = useState(false);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [extractedSkills, setExtractedSkills] = useState<string[]>([]);
  const [error, setError] = useState<string>('');

  const handleFileSelect = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      if (file.type !== 'application/pdf') {
        setError('Please select a PDF file');
        return;
      }
      if (file.size > 10 * 1024 * 1024) { // 10MB limit
        setError('File size must be less than 10MB');
        return;
      }
      setSelectedFile(file);
      setError('');
    }
  };

  const handleUpload = async () => {
    if (!selectedFile) return;

    setIsUploading(true);
    setUploadProgress(0);
    setError('');

    try {
      // Simulate progress
      const progressInterval = setInterval(() => {
        setUploadProgress(prev => {
          if (prev >= 90) {
            clearInterval(progressInterval);
            return 90;
          }
          return prev + 10;
        });
      }, 200);

      const resume = await uploadResume(selectedFile);
      
      clearInterval(progressInterval);
      setUploadProgress(100);
      setExtractedSkills(resume.skills);
      onResumeUploaded(resume);

      setTimeout(() => {
        setIsUploading(false);
      }, 500);

    } catch (err: any) {
      setError(err.response?.data?.error || 'Failed to upload resume');
      setIsUploading(false);
      setUploadProgress(0);
    }
  };

  return (
    <div className="resume-upload">
      <div className="upload-card">
        <h3>Upload Your Resume</h3>
        <p>Upload your resume in PDF format. We'll extract your skills and experience automatically.</p>

        <div className="upload-area">
          <div className="file-input-wrapper">
            <input
              type="file"
              id="resume-file"
              accept=".pdf"
              onChange={handleFileSelect}
              className="file-input"
            />
            <label htmlFor="resume-file" className="file-input-label">
              <span className="upload-icon">📄</span>
              <span>Choose your resume file</span>
              <small>PDF files only, max 10MB</small>
            </label>
          </div>
        </div>

        {error && (
          <div className="error-message">
            {error}
          </div>
        )}

        {selectedFile && (
          <div className="selected-file">
            <div className="file-info">
              <div>
                <strong>{selectedFile.name}</strong>
                <small>{(selectedFile.size / 1024 / 1024).toFixed(2)} MB</small>
              </div>
              <span className="file-status">Selected</span>
            </div>

            {!isUploading && extractedSkills.length === 0 && (
              <button onClick={handleUpload} className="upload-btn">
                Upload & Process Resume
              </button>
            )}
          </div>
        )}

        {isUploading && (
          <div className="upload-progress">
            <div className="progress-info">
              <span>Processing resume...</span>
              <span>{uploadProgress}%</span>
            </div>
            <div className="progress-bar">
              <div 
                className="progress-fill" 
                style={{ width: `${uploadProgress}%` }}
              ></div>
            </div>
          </div>
        )}

        {extractedSkills.length > 0 && !isUploading && (
          <div className="extracted-skills">
            <h4>Extracted Skills:</h4>
            <div className="skills-list">
              {extractedSkills.map((skill, index) => (
                <span key={index} className="skill-tag">
                  {skill}
                </span>
              ))}
            </div>
            <button className="continue-btn">
              Continue to Dashboard
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default ResumeUpload;
