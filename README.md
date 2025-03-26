# Patient Management System

A Spring Boot application for managing patient records with CRUD operations, pagination, sorting, and search functionality.

## Result (demo)
⚠️ V1 without security 

https://github.com/user-attachments/assets/5c15c922-32d6-4002-98d6-95322f497714


## Technologies Used

- **Spring Boot** (v3.4.3)
- **Spring Data JPA** - For database operations
- **Thymeleaf** - Server-side Java template engine
- **MySQL** - Database
- **Bootstrap** (v5.1.3) - Frontend styling
- **Lombok** - Reduces boilerplate code
- **MapStruct** (v1.6.2) - Object mapping
- **Maven** - Build tool

## Features

- Create, Read, Update, and Delete patient records
- Pagination with customizable page size
- Sorting by multiple fields (ID, Name, Health Status, Score)
- Search patients by name
- Form validation
- Responsive UI with Bootstrap
- Clean architecture with DTO pattern


## Entity Structure

The Patient entity contains:

- ID (auto-generated)
- Name (nom)
- Date of Birth (date de naissance)
- Health Status (malade)
- Score (score)

### Web Interface Routes

- `GET /patients` - List all patients (with pagination)
- `GET /patients/create` - Show patient creation form
- `POST /patients` - Create new patient
- `GET /patients/{id}` - View patient details
- `GET /patients/edit/{id}` - Show patient edit form
- `PUT /patients/{id}` - Update patient
- `DELETE /patients/{id}` - Delete patient

## Features in Detail

### Pagination

- Configurable page size (default: 5)
- Navigation controls
- Page information display

### Sorting

Sortable columns:

- ID
- Name (nom)
- Health Status (malade)
- Score (score)

### Search

- Real-time search by patient name
- Clear search functionality

### Validation

Patient form validation:

- Name: Required, 2-100 characters
- Date of Birth: Required, must be in the past
- Score: Required, between 0-100

## Frontend Templates

The application uses Thymeleaf templates with Bootstrap styling:

- `layouts/global.html` - Base layout template
- `patients/index.html` - Patient list view
- `patients/create-or-update.html` - Create/Edit form
- `patients/view.html` - Patient details view

## Building and Running

1. Clone the repository
2. Ensure MySQL is running
3. Configure database settings in `application.yaml`
4. Run using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080/patients`

## Development

### Adding New Features

1. Create/modify entity in `entities/`
2. Create corresponding DTOs in `dtos/`
3. Add mapping methods in `mappers/`
4. Implement repository methods in `repositories/`
5. Add business logic in `services/`
6. Create/update controller endpoints in `controllers/`
7. Create/modify Thymeleaf templates in `resources/templates/`

## Dependencies

Key dependencies include:

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Thymeleaf Layout Dialect
- MySQL Connector
- Lombok
- MapStruct
- Bootstrap (WebJar)

