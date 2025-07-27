# Budget Sharing REST API

This is a simple REST API for managing shared expenses between friends. It allows creating expense groups, adding expenses, and calculating balances to see who owes whom.

## Features

*   **User Management:** Create, read, update, and delete users.
*   **Expense Groups (Events):** Create expense groups and add participants.
*   **Expenses:** Add expenses to a group, specifying the payer.
*   **Balance Calculation:** View the balance for an event, showing who owes money to whom.
*   **Payments:** Record payments made between users to settle debts.

## Technologies Used

*   **Java 21**
*   **Spring Boot 3.5.3:** For building the REST API.
*   **Spring Data JPA:** For database interaction.
*   **H2 Database:** In-memory database for development and testing.
*   **MySQL:** For production database.
*   **Lombok:** To reduce boilerplate code.
*   **MapStruct:** For mapping between DTOs and entities.
*   **Maven:** For dependency management and building the project.

## Project Structure

The project follows a standard layered architecture:

*   `controller`: Contains the REST controllers that handle HTTP requests.
*   `dto`: Data Transfer Objects used for API requests and responses.
*   `entity`: JPA entities representing the database tables.
*   `repository`: Spring Data JPA repositories for database access.
*   `service`: Contains the business logic of the application.
*   `security`: Configuration for Spring Security.

## Getting Started

### Prerequisites

*   Java 21
*   Maven
*   MySQL (optional, for production)

### Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/timlapov/apispringexam.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd apispringexam
    ```
3.  Build the project:
    ```bash
    ./mvnw clean install
    ```
4.  Run the application:
    ```bash
    java -jar target/apispringexam-0.0.1-SNAPSHOT.jar
    ```

The application will be available at `http://localhost:8080`.

## API Endpoints

### Users

*   **Create a new user**

    ```bash
    curl -X POST http://localhost:8081/api/users/add \
         -H "Content-Type: application/json" \
         -d '{'''
           "name": "John Doe",
           "email": "john.doe@example.com",
           "password": "password123"
         }'''
    ```

*   **Get user by email**

    ```bash
    curl -X GET http://localhost:8081/api/users/john.doe@example.com \
         -u john.doe@example.com:password123
    ```

*   **Update a user**

    ```bash
    curl -X POST http://localhost:8081/api/users/update/{userId} \
         -H "Content-Type: application/json" \
         -u john.doe@example.com:password123 \
         -d '{'''
           "name": "Johnathan Doe",
           "email": "john.doe@example.com",
           "password": "newpassword123"
         }'''
    ```

*   **Delete a user**

    ```bash
    curl -X DELETE http://localhost:8081/api/users/delete/{userId} \
         -u john.doe@example.com:password123
    ```

### Events

*   **Create a new event**

    ```bash
    curl -X POST http://localhost:8081/api/events/add \
         -H "Content-Type: application/json" \
         -u john.doe@example.com:password123 \
         -d '{'''
           "name": "Trip to Paris",
           "description": "A weekend trip to Paris with friends.",
           "participants": [
             "john.doe@example.com",
             "jane.doe@example.com"
           ]
         }'''
    ```

*   **Get event balance**

    ```bash
    curl -X GET http://localhost:8081/api/events/{eventId}/balance \
         -u john.doe@example.com:password123
    ```

### Expenses

*   **Add an expense to an event**

    ```bash
    curl -X POST http://localhost:8081/api/events/{eventId}/expenses \
         -H "Content-Type: application/json" \
         -u john.doe@example.com:password123 \
         -d '{'''
           "amount": 50.0,
           "description": "Train tickets",
           "payerId": "john.doe@example.com"
         }'''
    ```

*   **Get expenses for an event**

    ```bash
    curl -X GET "http://localhost:8081/api/events/{eventId}/expenses?payerId=john.doe@example.com&amount_gt=20" \
         -u john.doe@example.com:password123
    ```

### Payments

*   **Create a payment**

    ```bash
    curl -X POST http://localhost:8081/api/payments \
         -H "Content-Type: application/json" \
         -u jane.doe@example.com:password123 \
         -d '{'''
           "eventId": "{eventId}",
           "fromUserId": "jane.doe@example.com",
           "toUserId": "john.doe@example.com",
           "amount": 25.0
         }'''
    ```