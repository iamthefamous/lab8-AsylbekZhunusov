# Lab 8 - Spring Dependency Injection Examples

This project demonstrates various Spring Dependency Injection (DI) concepts through a series of exercises.

## Prerequisites

- Java 17 or higher
- Gradle 9.x

## Building the Project

```bash
./gradlew build
```

## Running Tests

```bash
./gradlew test
```

## Project Structure

```
src/main/java/com/example/lab8week/
├── Lab8weekApplication.java      # Main application entry point
├── ex1/                          # Exercise 1: Manual vs Spring DI
│   ├── manual/                   # Manual dependency injection
│   └── spring/                   # Spring-based dependency injection
├── ex2/                          # Exercise 2: Field Injection
├── ex3/                          # Exercise 3: Layered Architecture
├── ex4/                          # Exercise 4: @Bean Configuration
├── ex5/                          # Exercise 5: @Qualifier Annotation
├── ex6/                          # Exercise 6: Bean Scopes (Singleton/Prototype)
└── ex7/                          # Exercise 7: Request Scope
```

## Exercises Overview

### Exercise 1: Manual vs Spring Dependency Injection (`ex1/`)

Demonstrates the difference between manual dependency injection and Spring-managed DI.

**Manual DI (`ex1/manual/`):**
- `MessageSender` interface with `EmailMessageSender` and `SmsMessageSender` implementations
- `NotificationService` receives dependencies through constructor injection
- Dependencies are created and wired manually in `ManualMain`

**Spring DI (`ex1/spring/`):**
- Same structure but with Spring annotations (`@Component`, `@Service`, `@Primary`)
- Spring container manages bean creation and injection
- `NotificationService` receives all `MessageSender` implementations as a `List`

Run the Spring example:
```bash
./gradlew bootRun -PmainClass=com.example.lab8week.ex1.spring.Ex1DemoApplication
```

### Exercise 2: Field Injection (`ex2/`)

Demonstrates field injection using `@Autowired` annotation.

- `OrderService` uses field injection to receive `PaymentService`
- `CreditCardPaymentService` is the concrete implementation

Run:
```bash
./gradlew bootRun -PmainClass=com.example.lab8week.ex2.Ex2DemoApplication
```

### Exercise 3: Layered Architecture (`ex3/`)

Demonstrates a typical layered architecture with Repository, Service, and Controller layers.

- **Repository Layer:** `InMemoryProductRepository` - stores product data in memory
- **Service Layer:** `ProductService` - business logic with discount calculation
- **Controller Layer:** `ProductController` - REST endpoints
- **Configuration:** `DiscountPolicy` interface with `PercentageDiscountPolicy` implementation

Endpoints:
- `GET /products/{id}` - Get product name
- `GET /products/{id}/discounted-price?originalPrice={price}` - Get discounted price

### Exercise 4: @Bean Configuration (`ex4/`)

Demonstrates using `@Configuration` and `@Bean` annotations for explicit bean configuration.

- `DiscountConfig` class creates beans using `@Bean` annotation
- `PercentageDiscountPolicy` is configured with 10% discount

### Exercise 5: @Qualifier Annotation (`ex5/`)

Demonstrates using `@Qualifier` to select specific bean implementations.

- Two `MessageSender` implementations: `EmailSender` and `SmsSender`
- `NotificationService` uses `@Qualifier` to inject specific implementations
- Provides separate methods: `notifyByEmail()` and `notifyBySms()`

Endpoints:
- `POST /notify/email?to={recipient}&message={msg}` - Send email notification
- `POST /notify/sms?to={recipient}&message={msg}` - Send SMS notification

### Exercise 6: Bean Scopes (`ex6/`)

Demonstrates the difference between singleton and prototype bean scopes.

- `IdBean` generates a unique UUID on creation
- Can be configured with `@Scope("singleton")` or `@Scope("prototype")`
- `ServiceA` and `ServiceB` both depend on `IdBean`
- With singleton scope, both services share the same `IdBean` instance
- With prototype scope, each service gets a new `IdBean` instance

Endpoints:
- `GET /ids` - Returns IDs from both ServiceA and ServiceB

### Exercise 7: Request Scope (`ex7/`)

Demonstrates request-scoped beans using `@Scope(WebApplicationContext.SCOPE_REQUEST)`.

- `RequestTrace` generates a unique request ID for each HTTP request
- Uses `ScopedProxyMode.TARGET_CLASS` to enable request scope injection into singleton beans
- Each request gets a fresh `RequestTrace` instance

Endpoints:
- `GET /request-id` - Returns the unique request ID for the current request

## Key Spring DI Concepts Covered

| Concept | Exercise | Description |
|---------|----------|-------------|
| Constructor Injection | ex1, ex3 | Recommended approach for mandatory dependencies |
| Field Injection | ex2 | Uses `@Autowired` on fields (not recommended) |
| `@Component` | ex1, ex5, ex6 | Marks a class as a Spring-managed component |
| `@Service` | ex1, ex2, ex3 | Specialized component for service layer |
| `@Repository` | ex3 | Specialized component for data access layer |
| `@RestController` | ex3, ex5, ex6, ex7 | Combines `@Controller` and `@ResponseBody` |
| `@Configuration` | ex4 | Marks class as source of bean definitions |
| `@Bean` | ex4 | Declares a method as a bean producer |
| `@Primary` | ex1/spring | Marks a bean as the primary candidate |
| `@Qualifier` | ex5 | Selects a specific bean by name |
| `@Scope` | ex6, ex7 | Defines bean scope (singleton, prototype, request) |

## Running Individual Exercises

Each exercise has its own `DemoApplication` that can be run independently:

```bash
# Exercise 1 - Spring DI
./gradlew bootRun -PmainClass=com.example.lab8week.ex1.spring.Ex1DemoApplication

# Exercise 2 - Field Injection
./gradlew bootRun -PmainClass=com.example.lab8week.ex2.Ex2DemoApplication

# Exercise 3 - Layered Architecture
./gradlew bootRun -PmainClass=com.example.lab8week.ex3.Ex3DemoApplication
```

## Test Coverage

The project includes comprehensive unit tests for all exercises:

- `ex1/manual/NotificationServiceTest` - Tests for manual DI
- `ex1/spring/SpringNotificationServiceTest` - Tests for Spring DI
- `ex2/OrderServiceTest` - Tests for field injection
- `ex3/ProductServiceTest` - Tests for layered architecture
- `ex4/DiscountConfigTest` - Tests for @Bean configuration
- `ex5/NotificationServiceTest` - Tests for @Qualifier usage
- `ex6/BeanScopeTest` - Tests for bean scopes
- `ex7/RequestTraceTest` - Tests for request scope

Run all tests:
```bash
./gradlew test
```

View test reports at: `build/reports/tests/test/index.html`

## License

This project is for educational purposes as part of Lab 8 coursework.
