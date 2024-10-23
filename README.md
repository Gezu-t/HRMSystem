

# HRMSystem

HRMSuite is a comprehensive Human Resource Management System built with a microservices architecture. This system includes a variety of services to manage different aspects of human resources, such as employee management, payroll, attendance, and more.

## Core Services

### User Management

- **userAccount-service**: Manages userAccount accounts and their basic information.
- **userAccount-management-service**: Handles detailed userAccount profiles and settings.
- **auth-service**: Manages authentication processes (if handled separately).

### Employee Management

- **employee-service**: Manages basic employee data.
- **employee-profile-service**: Handles detailed profiles of employees, including personal information, skills, and job history.
- **employee-record-service**: Manages employment records, including job changes, promotions, and performance reviews.

### Payroll Management

- **payroll-service**: Manages payroll processing and records.
- **salary-service**: Handles salary computations and adjustments.
- **compensation-service**: Manages employee compensation packages.

### Attendance Management

- **attendance-service**: Manages attendance records and tracking.
- **time-tracking-service**: Handles time tracking and work hour logging.
- **attendance-record-service**: Stores and manages attendance records.

### Leave Management

- **leave-service**: Manages leave applications and approvals.
- **leave-management-service**: Handles leave policies and balances.
- **vacation-service**: Manages vacation requests and tracking.

### Performance Management

- **performance-service**: Manages performance reviews and appraisals.
- **performance-review-service**: Handles detailed performance review processes.
- **evaluation-service**: Manages employee evaluations and feedback.

## Auxiliary Services

### Notification Service

- **notification-service**: Manages notifications and alerts.
- **alert-service**: Handles alert generation and management.
- **messaging-service**: Manages internal messaging and communication.

### Reporting Service

- **reporting-service**: Manages report generation and data analytics.
- **analytics-service**: Handles data analysis and visualization.
- **data-report-service**: Manages detailed data reports and summaries.

### Document Management

- **document-service**: Manages employee-related documents.
- **file-storage-service**: Handles file storage and retrieval.
- **doc-management-service**: Manages document lifecycle and permissions.

## Integrative Services

### API Gateway

- **api-gateway**: Central gateway for managing API requests.
- **gateway-service**: Handles routing and load balancing of API calls.
- **api-proxy-service**: Manages API proxies and security.

### Configuration Service

- **config-service**: Manages configuration settings for all services.
- **configuration-service**: Handles dynamic configuration updates.
- **settings-service**: Manages global and service-specific settings.

### Discovery Service

- **discovery-service**: Manages service discovery and registration.
- **service-registry**: Handles service instance registration and lookup.
- **eureka-service**: Manages service discovery using Eureka (if applicable).

---

## Getting Started

To get started with HRMSuite, follow the instructions for setting up each service. Each microservice has its own README file with specific setup instructions.

## Contributing

Contributions are welcome! Please read the contributing guidelines before submitting a pull request.

## License

This project is licensed under the MIT License.

---

For more detailed documentation on each service, refer to the respective README files in each service directory.

