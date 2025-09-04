import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export interface Resume {
  id: number;
  filename: string;
  skills: string[];
  experience: string;
  education: string;
  summary: string;
  createdAt: string;
}

export interface JobListing {
  id: number;
  title: string;
  company: string;
  location?: string;
  salary?: string;
  description: string;
  platform: string;
  skillsRequired: string[];
  skillMatch?: number;
  matchedSkills?: string[];
  isQualified?: boolean;
}

export interface JobApplication {
  id: number;
  jobTitle: string;
  company: string;
  platform: string;
  skillMatch: number;
  status: string;
  appliedAt: string;
  responseAt?: string;
  interviewDate?: string;
}

// Resume API
export const uploadResume = async (file: File, userId: number = 1): Promise<Resume> => {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('userId', userId.toString());

  const response = await api.post('/resumes/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });

  return response.data.resume;
};

export const getResume = async (id: number): Promise<Resume> => {
  const response = await api.get(`/resumes/${id}`);
  return response.data;
};

export const getUserResumes = async (userId: number = 1): Promise<Resume[]> => {
  const response = await api.get(`/resumes/user/${userId}`);
  return response.data.resumes;
};

// Job Search API
export const searchJobs = async (params: {
  searchQuery: string;
  location?: string;
  userSkills: string[];
  minSkillMatch?: number;
}): Promise<JobListing[]> => {
  const response = await api.post('/jobs/search', params);
  return response.data.jobs;
};

// Job Application API
export const applyToJob = async (params: {
  jobId: number;
  resumeId: number;
  applicationMethod?: string;
}): Promise<JobApplication> => {
  const response = await api.post('/applications', params);
  return response.data.application;
};

export const getApplications = async (userId: number = 1): Promise<JobApplication[]> => {
  const response = await api.get(`/applications?userId=${userId}`);
  return response.data.applications;
};

// Dashboard API
export const getDashboardStats = async (userId: number = 1) => {
  const response = await api.get(`/dashboard/stats?userId=${userId}`);
  return response.data;
};

export default api;
