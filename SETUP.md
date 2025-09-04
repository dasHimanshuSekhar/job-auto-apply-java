# 🚀 JobAutoApply Setup Guide

## Prerequisites
- Java 17 or higher
- Node.js 16+ and npm
- PostgreSQL database
- Maven

## Backend Setup
```bash
# Configure database in application.yml
# Update src/main/resources/application.yml with your DB credentials

# Run the Spring Boot application
./mvnw spring-boot:run
```

## Frontend Setup
```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start the React development server
npm start
```

## Environment Configuration
1. Set up PostgreSQL database
2. Configure database connection in `application.yml`
3. Update API endpoints in frontend if needed

## Running the Application
- Backend: http://localhost:8080
- Frontend: http://localhost:3000

## Live Demo
- Frontend: https://job-auto-apply.lindy.site
- Backend API: https://job-auto-apply-api.lindy.site

## Features
- PDF resume parsing and skill extraction
- Intelligent job matching (80-100%)
- Multi-platform integration (LinkedIn, Naukri, Indeed)
- Interactive dashboard with analytics
- Auto-apply functionality
- Modern responsive UI
