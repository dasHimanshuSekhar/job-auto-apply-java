# 🚀 JobAutoApply Setup Guide

## Project Structure

```
job-auto-apply-java/
├── backend/          # Java Spring Boot API
├── frontend/         # React TypeScript UI
├── README.md         # Project overview
└── SETUP.md          # This setup guide
```

## Prerequisites
- Java 17 or higher
- Node.js 16+ and npm
- PostgreSQL database
- Maven

## Backend Setup (`/backend`)

### 1. Database Configuration
```sql
-- Create database
CREATE DATABASE jobautoapply;
CREATE USER jobuser WITH PASSWORD 'jobpass';
GRANT ALL PRIVILEGES ON DATABASE jobautoapply TO jobuser;
```

### 2. Application Configuration
Update `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/jobautoapply
    username: jobuser
    password: jobpass
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

### 3. Run Backend
```bash
cd backend

# Install dependencies and run
./mvnw spring-boot:run
```

Backend will be available at: `http://localhost:8080`

## Frontend Setup (`/frontend`)

### 1. Install Dependencies
```bash
cd frontend
npm install
```

### 2. Environment Configuration (Optional)
Create `frontend/.env.local`:
```env
REACT_APP_API_URL=http://localhost:8080/api
```

### 3. Run Frontend
```bash
npm start
```

Frontend will be available at: `http://localhost:3000`

## API Endpoints

### Resume Management
- `POST /api/resumes/upload` - Upload PDF resume
- `GET /api/resumes/{id}` - Get resume details
- `GET /api/resumes/user/{userId}` - Get user resumes

### Job Management
- `POST /api/jobs/search` - Search jobs with skill matching
- `GET /api/jobs/{id}` - Get job details

### Application Management
- `POST /api/applications` - Submit application
- `GET /api/applications` - Get user applications

## Live Demo
- **Frontend**: https://job-auto-apply.lindy.site
- **Backend API**: https://job-auto-apply-api.lindy.site

## Features
- PDF resume parsing and skill extraction
- Intelligent job matching (80-100%)
- Multi-platform integration (LinkedIn, Naukri, Indeed)
- Interactive dashboard with analytics
- Auto-apply functionality
- Modern responsive UI

## Troubleshooting

### Common Issues
1. **Database Connection**: Ensure PostgreSQL is running and credentials are correct
2. **Port Conflicts**: Backend uses 8080, Frontend uses 3000
3. **CORS Issues**: Backend is configured to allow frontend origin

### Development Tips
- Use `./mvnw spring-boot:run` for backend hot reload
- Use `npm start` for frontend hot reload
- Check browser console for frontend errors
- Check backend logs for API errors
