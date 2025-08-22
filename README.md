# servimatch-backend
ServiMatch - Intelligent Local Services Marketplace
ServiMatch is a marketplace platform that connects local service providers with customers, featuring intelligent AI-powered recommendations.
🏗️ Architecture
The project follows Hexagonal Architecture with Domain-Driven Design (DDD) principles, organized using Spring Modulith for modular monolith structure.
Modules

Common: Shared domain building blocks and infrastructure
Auth: User authentication and authorization
Catalog: Service categories and listings (TODO)
Scheduling: Appointment booking system (TODO)
Rating: Service ratings and reviews (TODO)
Payment: Payment processing (TODO)
Notification: Email/SMS notifications (TODO)
AI: External AI service integration (TODO)

🚀 Technology Stack

Java 21
Spring Boot 3.2.0
Spring Modulith - Modular architecture
PostgreSQL - Primary database
Flyway - Database migrations
JWT - Authentication tokens
Swagger/OpenAPI 3 - API documentation
Lombok - Boilerplate reduction
MapStruct - Object mapping

📋 Prerequisites

Java 21 or higher
Maven 3.8+
PostgreSQL 14+
Docker (optional)

🛠️ Setup Instructions
1. Clone the repository
   bashgit clone https://github.com/your-org/servimatch.git
   cd servimatch
2. Database Setup
   Using Docker:
   bashdocker run --name servimatch-db \
   -e POSTGRES_DB=servimatch \
   -e POSTGRES_USER=servimatch \
   -e POSTGRES_PASSWORD=servimatch123 \
   -p 5432:5432 \
   -d postgres:14
   Manual Setup:
   sqlCREATE DATABASE servimatch;
   CREATE USER servimatch WITH PASSWORD 'servimatch123';
   GRANT ALL PRIVILEGES ON DATABASE servimatch TO servimatch;
3. Configure Application
   Update src/main/resources/application.yml with your database credentials if different:
   yamlspring:
   datasource:
   url: jdbc:postgresql://localhost:5432/servimatch
   username: servimatch
   password: servimatch123
4. Build and Run
   bash# Build the project
   mvn clean install

# Run the application
mvn spring-boot:run

# Or run the JAR directly
java -jar target/servimatch-0.0.1-SNAPSHOT.jar
The application will start on http://localhost:8080
📚 API Documentation
Once the application is running, access the Swagger UI at:

http://localhost:8080/swagger-ui.html

API documentation is also available at:

http://localhost:8080/v3/api-docs

🔐 Authentication
The application uses JWT tokens for authentication. To access protected endpoints:

Register a new user via POST /auth/register
Login via POST /auth/login to receive a JWT token
Include the token in subsequent requests: Authorization: Bearer <token>

Available Roles

CLIENT: Regular customers
PROVIDER: Service providers
ADMIN: System administrators

🧪 Testing
bash# Run all tests
mvn test

# Run specific module tests
mvn test -pl :auth

# Run with coverage
mvn test jacoco:report
📦 Project Structure
servimatch/
├── common/              # Shared domain and infrastructure
│   ├── domain/         # Base entities, value objects
│   ├── application/    # Common interfaces
│   └── infrastructure/ # Shared configurations
├── auth/               # Authentication module
│   ├── domain/        # User aggregate, events
│   ├── application/   # Use cases, ports
│   ├── infrastructure/# JPA, security, mappers
│   └── adapter/       # REST controllers
└── [other modules]    # Following similar structure
🎯 Development Roadmap
Sprint 1 (Current)

✅ Project setup and architecture
✅ Common module with DDD building blocks
✅ Auth module with JWT authentication
⏳ Catalog module
⏳ Basic service search

Sprint 2

Scheduling module
Rating system
AI integration for recommendations

Sprint 3

Payment processing
Notification system
Provider dashboard

Sprint 4

Admin panel
Performance optimization
Production deployment

🤝 Contributing

Fork the repository
Create your feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request

📄 License
This project is licensed under the Apache License 2.0 - see the LICENSE file for details.
📞 Support
For questions and support:

Email: support@servimatch.com
Documentation: https://docs.servimatch.com
