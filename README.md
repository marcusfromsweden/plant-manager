# README for Plant Manager

# Plant Manager

Plant Manager is a Spring Boot application designed to manage plant records. It provides a RESTful API for performing CRUD operations on plant entities.

## Features

- Create, Read, Update, and Delete (CRUD) operations for plants
- Simple and intuitive REST API
- Built with Spring Boot and Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/plant-manager.git
   ```

2. Navigate to the project directory:
   ```
   cd plant-manager
   ```

3. Build the project using Maven:
   ```
   ./mvnw clean install
   ```

### Running the Application

To run the application, use the following command:
```
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

### API Endpoints

- `POST /plants` - Create a new plant
- `GET /plants` - Retrieve all plants
- `GET /plants/{id}` - Retrieve a plant by ID
- `PUT /plants/{id}` - Update a plant by ID
- `DELETE /plants/{id}` - Delete a plant by ID

## Testing

To run the tests, use the following command:
```
./mvnw test
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.