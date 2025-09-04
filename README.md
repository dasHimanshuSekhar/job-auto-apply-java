# JobAutoApply - Automated Job Application System

🚀 **A comprehensive full-stack web application that automates job applications across multiple platforms (LinkedIn, Naukri, Indeed) with intelligent skill matching and resume parsing capabilities.**

## 🌐 Live Demo
- **Frontend**: [https://job-auto-apply.lindy.site](https://job-auto-apply.lindy.site)
- **Backend API**: [https://job-auto-apply-api.lindy.site](https://job-auto-apply-api.lindy.site)

## 📁 Project Structure

```
job-auto-apply-java/
├── backend/                      # Java Spring Boot API
│   ├── src/main/java/           # Java source code
│   ├── src/main/resources/      # Configuration files
│   ├── pom.xml                  # Maven dependencies
│   └── README.md                # Backend documentation
├── frontend/                    # React TypeScript UI
│   ├── src/components/          # React components
│   ├── src/services/            # API integration
│   ├── package.json             # Node dependencies
│   └── public/                  # Static assets
├── README.md                    # This file
└── SETUP.md                     # Setup instructions
```

## 🎯 Key Features

### ✅ Resume Processing
- PDF upload and parsing using Apache PDFBox
- Automatic skill extraction
- Experience and education parsing
- Resume storage and management

### ✅ Intelligent Job Matching
- Smart skill matching algorithm (80-100% compatibility)
- Multi-platform job aggregation (LinkedIn, Naukri, Indeed)
- Real-time search and filtering
- Skill gap analysis

### ✅ Application Automation
- One-click auto-apply functionality
- Platform-specific integration
- Application status tracking
- Email outreach capabilities

### ✅ Interactive Dashboard
- Application statistics and analytics
- Response rate tracking (18% average)
- Interview scheduling management
- Recent activity overview

### ✅ Advanced Settings
- Minimum skill match threshold configuration
- Daily application limits (customizable)
- Platform enable/disable toggles
- Location preferences management
- Salary range settings

## 🏗️ Technology Stack

### Backend (`/backend`)
- **Java 17** with Spring Boot 3.x
- **PostgreSQL** database with JPA/Hibernate
- **Apache PDFBox** for PDF resume parsing
- **Maven** for dependency management
- RESTful API architecture

### Frontend (`/frontend`)
- **React 18** with TypeScript
- **Custom CSS** with modern responsive design
- **Axios** for API communication
- Component-based architecture

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Node.js 16+ and npm
- PostgreSQL database
- Maven

### Backend Setup
```bash
cd backend

# Configure database in application.yml
# Update src/main/resources/application.yml with your DB credentials

# Run the Spring Boot application
./mvnw spring-boot:run
```

### Frontend Setup
```bash
cd frontend

# Install dependencies
npm install

# Start the React development server
npm start
```

The application will be available at:
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080

## 🔄 Application Workflow

1. **Upload Resume** → PDF parsing → Skill extraction
2. **Configure Settings** → Set matching criteria and preferences
3. **Search Jobs** → Find opportunities with 80-100% skill match
4. **Auto Apply** → Submit applications automatically
5. **Track Progress** → Monitor responses and schedule interviews

## 🎨 UI/UX Features

- **Modern Design**: Beautiful gradient design with professional styling
- **Responsive Layout**: Works seamlessly across all devices
- **Interactive Components**: Smooth transitions and real-time feedback
- **Progress Tracking**: Visual indicators for uploads and applications
- **Dashboard Analytics**: Comprehensive statistics and insights

## 📊 Database Schema

### Core Entities
- **User**: User profiles and authentication
- **Resume**: Parsed resume data with extracted skills
- **JobListing**: Job postings from various platforms
- **JobApplication**: Application tracking and status management
- **UserPreferences**: User settings and automation preferences

## 📈 Future Enhancements

- [ ] AI-powered cover letter generation
- [ ] Advanced analytics and reporting dashboard
- [ ] Mobile application development
- [ ] Integration with additional job platforms
- [ ] Machine learning for improved matching algorithms
- [ ] Automated interview scheduling system

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Himanshu Sekhar Das**
- GitHub: [@dasHimanshuSekhar](https://github.com/dasHimanshuSekhar)
- Email: dashimanshusekhar58@gmail.com

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- React team for the amazing frontend framework
- Apache PDFBox for PDF processing capabilities
- All contributors and supporters of this project

---

⭐ **Star this repository if you find it helpful!**
