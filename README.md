# Spring Boot Event Sourcing

A demonstration project comparing **Event Sourcing** pattern with **Traditional CRUD** approach in Spring Boot
applications for stock management.

## Overview

This project contains two separate Spring Boot applications that implement the same stock management functionality using
different architectural patterns:

1. **stock-management-eventstore** - Event Sourcing pattern
2. **stock-management-traditional** - Traditional CRUD pattern

## What is Event Sourcing?

Event Sourcing is a pattern where state changes are stored as a sequence of events. Instead of storing just the current
state, every change to the application state is captured in an event object, and these events are stored in an event
store. The current state can be reconstructed by replaying all events.

### Key Benefits

- Complete audit trail of all changes
- Ability to reconstruct state at any point in time
- Event replay and debugging capabilities
- Temporal queries (what was the state on a specific date?)

## Project Structure

```
spring-boot-event-sourcing/
├── stock-management-eventstore/    # Event Sourcing implementation
└── stock-management-traditional/   # Traditional CRUD implementation
```

## Technologies Used

- **Spring Boot** 3.5.6
- **Java** 21
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Lombok**
- **Maven**
- **Gson** (for JSON processing in eventstore module)

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Building the Applications

### Build Both Modules

```bash
# Build Event Sourcing module
cd stock-management-eventstore
mvn clean install

# Build Traditional CRUD module
cd ../stock-management-traditional
mvn clean install
```

## Running the Applications

### Event Sourcing Application

```bash
cd stock-management-eventstore
mvn spring-boot:run
```

Default port: `8080`

### Traditional CRUD Application

```bash
cd stock-management-traditional
mvn spring-boot:run
```

Default port: `8080` (change if running both simultaneously)

## API Endpoints

### Event Sourcing Module (`stock-management-eventstore`)

#### Add Stock

```bash
POST http://localhost:8080/stocks
Content-Type: application/json

{
  "name": "Laptop",
  "quantity": 10,
  "user": "John"
}
```

#### Remove Stock

```bash
DELETE http://localhost:8080/stocks
Content-Type: application/json

{
  "name": "Laptop",
  "quantity": 5,
  "user": "John"
}
```

#### Get Current Stock

```bash
GET http://localhost:8080/stocks?name=Laptop
```

#### Get All Events for Stock

```bash
GET http://localhost:8080/stocks/events?name=Laptop
```

#### Get Stock History (at specific date)

```bash
GET http://localhost:8080/stocks/history?name=Laptop&date=2024-10-15
```

Date format: `YYYY-MM-DD`

### Traditional CRUD Module (`stock-management-traditional`)

#### Add Stock

```bash
POST http://localhost:8080/stocks
Content-Type: application/json

{
  "name": "Laptop",
  "quantity": 10,
  "addedBy": "John"
}
```

#### Remove Stock

```bash
DELETE http://localhost:8080/stocks
Content-Type: application/json

{
  "name": "Laptop",
  "quantity": 5,
  "addedBy": "John"
}
```

#### Get Stock

```bash
GET http://localhost:8080/stocks?name=Laptop
```

Or get all stocks:

```bash
GET http://localhost:8080/stocks
```

## Database Access

Both applications use H2 in-memory database with console enabled.

### H2 Console Access

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:stockdb`
- Username: `sa`
- Password: (leave empty)

## Key Differences

| Feature              | Event Sourcing        | Traditional CRUD          |
|----------------------|-----------------------|---------------------------|
| Data Storage         | Stores all events     | Stores current state only |
| Audit Trail          | Complete history      | No built-in history       |
| Temporal Queries     | Supported             | Not supported             |
| State Reconstruction | Replay events         | Query current state       |
| Complexity           | Higher                | Lower                     |
| Storage              | More storage needed   | Less storage needed       |
| Time Travel          | Can query past states | Cannot query past states  |

## Example Scenarios

### Event Sourcing Advantages

1. **Audit Trail**: Every stock addition/removal is recorded as an event
2. **Time Travel**: Query stock quantity at any past date
3. **Event Replay**: Reconstruct current state from all events
4. **Debugging**: See exact sequence of operations

### Traditional CRUD Advantages

1. **Simplicity**: Straightforward implementation
2. **Performance**: Direct state queries without event replay
3. **Storage**: Less storage space required
4. **Learning Curve**: Easier to understand and maintain

## Configuration

### Application Properties

Both applications use similar configuration (`application.yml`):

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:stockdb
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    generate-ddl: true
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
```

## License

This project is created for educational purposes to demonstrate Event Sourcing pattern in Spring Boot.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34
- Link: s.id/hendisantika

## Acknowledgments

This project demonstrates the implementation of Event Sourcing pattern as an alternative to traditional CRUD operations
in Spring Boot applications.
