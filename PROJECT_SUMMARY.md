# JobAutoApply - Automated Job Application System

## 🚀 Project Overview
A comprehensive full-stack web application that automates job applications across multiple platforms (LinkedIn, Naukri, Indeed) with intelligent skill matching and resume parsing capabilities.

## 🏗️ Architecture

### Backend (Java Spring Boot)
- **Framework**: Spring Boot 3.x with Java 17
- **Database**: PostgreSQL with JPA/Hibernate
- **Key Features**:
  - PDF Resume parsing using Apache PDFBox
  - Intelligent skill matching algorithm
  - RESTful API endpoints
  - Job application tracking
  - Multi-platform integration support

### Frontend (React TypeScript)
- **Framework**: React 18 with TypeScript
- **Styling**: Custom CSS with modern design
- **Key Features**:
  - Responsive design
  - File upload with progress tracking
  - Interactive dashboard with statistics
  - Job search with real-time filtering
  - Settings management with toggles

## 📊 Database Schema

### Core Entities:
1. **User** - User profiles and authentication
2. **Resume** - Parsed resume data with extracted skills
3. **JobListing** - Job postings from various platforms
4. **JobApplication** - Application tracking and status
5. **UserPreferences** - User settings and preferences

## 🔧 Key Components

### Backend Services:
- **ResumeParsingService**: Extracts skills, experience, education from PDFs
- **JobMatchingService**: Calculates skill compatibility (80-100% matching)
- **JobScrapingService**: Integrates with job platforms
- **ApplicationService**: Manages application workflow

### Frontend Components:
- **ResumeUpload**: Drag-and-drop PDF upload with parsing
- **Dashboard**: Statistics and recent applications overview
- **JobSearch**: Search and filter jobs with skill matching
- **Settings**: Configure automation preferences

## 🌐 Live Application URLs

### Frontend (React): https://job-auto-apply.lindy.site
- Modern, responsive UI
- Real-time job search
- Interactive dashboard
- Settings management

### Backend API: https://job-auto-apply-api.lindy.site
- RESTful endpoints
- Resume parsing API
- Job matching algorithms
- Application tracking

## 🎯 Key Features Implemented

### ✅ Resume Processing
- PDF upload and parsing
- Automatic skill extraction
- Experience and education parsing
- Resume storage and management

### ✅ Job Matching
- Intelligent skill matching algorithm
- 80-100% compatibility scoring
- Multi-platform job aggregation
- Real-time search and filtering

### ✅ Application Automation
- Auto-apply functionality
- Platform-specific integration
- Application status tracking
- Email outreach capabilities

### ✅ User Dashboard
- Application statistics
- Response rate tracking
- Interview scheduling
- Recent activity overview

### ✅ Settings & Preferences
- Minimum skill match threshold
- Daily application limits
- Platform enable/disable toggles
- Location preferences
- Salary range settings

## 🔄 Workflow

1. **Upload Resume** → PDF parsing → Skill extraction
2. **Set Preferences** → Configure matching criteria
3. **Job Search** → Find matching opportunities
4. **Auto Apply** → Submit applications automatically
5. **Track Progress** → Monitor responses and interviews

## 🛠️ Technology Stack

**Backend:**
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Apache PDFBox
- Maven

**Frontend:**
- React 18
- TypeScript
- Axios for API calls
- Custom CSS styling
- Responsive design

**Infrastructure:**
- RESTful API architecture
- Database-driven design
- Scalable microservices approach

## 📈 Future Enhancements

- AI-powered cover letter generation
- Advanced analytics and reporting
- Mobile application
- Integration with more job platforms
- Machine learning for better matching
- Automated interview scheduling

## 🎉 Status: FULLY FUNCTIONAL
The application is live and ready for use with all core features implemented!
