# JobAutoApply Backend

Spring Boot REST API for the JobAutoApply system.

## Features

- **Resume Parsing**: PDF resume parsing using Apache PDFBox
- **Skill Matching**: Intelligent algorithm for job-skill compatibility (80-100%)
- **Job Management**: CRUD operations for job listings and applications
- **Multi-platform Integration**: Support for LinkedIn, Naukri, Indeed
- **Application Tracking**: Complete application lifecycle management

## Tech Stack

- **Java 17**
- **Spring Boot 3.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Apache PDFBox**
- **Maven**

## API Endpoints

### Resume Management
- `POST /api/resumes/upload` - Upload and parse PDF resume
- `GET /api/resumes/{id}` - Get resume by ID
- `GET /api/resumes/user/{userId}` - Get user's resumes

### Job Search & Matching
- `POST /api/jobs/search` - Search jobs with skill matching
- `GET /api/jobs/{id}` - Get job details
- `POST /api/jobs/match` - Get skill match for specific job

### Application Management
- `POST /api/applications` - Submit job application
- `GET /api/applications` - Get user applications
- `PUT /api/applications/{id}` - Update application status

## Setup

1. **Prerequisites**
   - Java 17+
   - PostgreSQL
   - Maven

2. **Database Setup**
   ```sql
   CREATE DATABASE jobautoapply;
   ```

3. **Configuration**
   Update `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/jobautoapply
       username: your_username
       password: your_password
   ```

4. **Run Application**
   ```bash
   ./mvnw spring-boot:run
   ```

The API will be available at `http://localhost:8080`

## Database Schema

- **users** - User profiles and authentication
- **resumes** - Parsed resume data with extracted skills
- **job_listings** - Job postings from various platforms
- **job_applications** - Application tracking and status
- **user_preferences** - User settings and automation preferences
