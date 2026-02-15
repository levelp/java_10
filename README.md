# Java 10 Quality Demo

Demonstration project showcasing comprehensive code quality practices:
- **100% Test Coverage** with JaCoCo
- **DRY** (Don't Repeat Yourself)
- **KISS** (Keep It Simple, Stupid)
- **SOLID** Principles
- **BDD** (Behavior-Driven Development) with Cucumber
- **DSL** (Domain-Specific Language) patterns

## Project Status

This project follows the **Codev SPIDER-SOLO** methodology for context-driven development.

**Current Phase**: Phase 1 - Foundation ✅

## Prerequisites

- **Java 10+** (JDK 10 or higher)
- **Maven 3.8+**

## Quick Start

### Clone and Build

```bash
# Clone the repository
git clone <repository-url>
cd java_10

# Build the project
mvn clean install

# Run tests
mvn test

# Compile only
mvn compile
```

## Project Structure

```
java_10/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       └── HelloWorld.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   ├── com/example/demo/
│       │   │   └── HelloWorldTest.java
│       │   └── steps/              # BDD step definitions (Phase 5)
│       └── resources/
│           └── features/           # BDD feature files (Phase 5)
├── codev/
│   ├── specs/                      # Specifications
│   ├── plans/                      # Implementation plans
│   └── reviews/                    # Reviews and lessons learned
├── pom.xml
├── README.md
└── .gitignore
```

## Running Tests

```bash
# Run all tests
mvn test

# Run tests with detailed output
mvn test -DtrimStackTrace=false

# Run specific test class
mvn test -Dtest=HelloWorldTest

# Run specific test method
mvn test -Dtest=HelloWorldTest#shouldGreetWithProvidedName
```

## Code Quality Features (Current Implementation)

### Phase 1: Foundation ✅
- [x] Maven project structure
- [x] Java 10 configuration
- [x] JUnit 5 for testing
- [x] AssertJ for fluent assertions
- [x] Example code with comprehensive tests

### Future Phases
- [ ] Phase 2: JaCoCo coverage (100% enforcement)
- [ ] Phase 3: Static analysis (Checkstyle, PMD, SpotBugs)
- [ ] Phase 4: SOLID validation (ArchUnit)
- [ ] Phase 5: BDD framework (Cucumber)
- [ ] Phase 6: DSL development
- [ ] Phase 7: CI/CD integration
- [ ] Phase 8: Documentation and training

## Example Usage

```java
HelloWorld greeter = new HelloWorld();
String greeting = greeter.greet("World");
// Returns: "Hello, World!"
```

## Test Examples

The project includes comprehensive tests demonstrating:

- **Positive test cases**: Valid inputs
- **Negative test cases**: Invalid inputs (null, empty, blank)
- **Edge cases**: Single character, long names, special characters
- **Parameterized tests**: Multiple test cases with different data
- **BDD-style naming**: Clear, descriptive test names
- **Fluent assertions**: AssertJ DSL for readable assertions

Example test:

```java
@Test
@DisplayName("Should greet with provided name")
void shouldGreetWithProvidedName() {
    // Given
    String name = "World";

    // When
    String result = helloWorld.greet(name);

    // Then
    assertThat(result)
        .as("Greeting should contain the name")
        .isEqualTo("Hello, World!");
}
```

## Maven Commands Reference

```bash
# Clean build artifacts
mvn clean

# Compile source code
mvn compile

# Run tests
mvn test

# Package JAR
mvn package

# Install to local repository
mvn install

# Full clean build and install
mvn clean install

# Skip tests (not recommended!)
mvn install -DskipTests
```

## Development Workflow

Following **Codev SPIDER-SOLO** protocol:

1. **Specification**: Define requirements in `codev/specs/`
2. **Planning**: Create implementation plan in `codev/plans/`
3. **IDE Loop**: Implement, Defend (test), Evaluate
4. **Review**: Document lessons learned in `codev/reviews/`

## Code Quality Standards

### DRY (Don't Repeat Yourself)
- Code duplication target: < 3%
- Reusable utilities and helpers
- Shared test fixtures and builders

### KISS (Keep It Simple, Stupid)
- Cyclomatic complexity: < 10 per method
- Clear, descriptive naming
- Single responsibility per method/class

### SOLID Principles
- **S**ingle Responsibility: Each class has one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes substitutable for base types
- **I**nterface Segregation: No unused interface dependencies
- **D**ependency Inversion: Depend on abstractions

## Contributing

1. Write specification in `codev/specs/`
2. Create implementation plan in `codev/plans/`
3. Implement with tests (100% coverage required)
4. Run all quality checks
5. Create pull request
6. Document review in `codev/reviews/`

## Resources

- [Codev Methodology](./codev/protocols/spider-solo/protocol.md)
- [Specifications](./codev/specs/)
- [Implementation Plans](./codev/plans/)
- [Quality Standards](./codev/specs/0001-code-quality-standards.md)
- [BDD Testing](./codev/specs/0002-bdd-testing-framework.md)

## License

[Specify your license here]

## Support

For questions or issues, please refer to the project documentation in the `codev/` directory.
