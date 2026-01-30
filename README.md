# Selenium TestNG Automation Framework

A professional UI test automation framework built with **Java, Selenium, TestNG**, and **Page Object Model**, designed with a **Service Layer architecture** for scalable and maintainable test automation.

# Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Service Layer Pattern
- Explicit Wait Strategy
- Config-driven Test Data

- # Project Structure
- src/test/java
├── base # Base test setup
├── pages # Page Objects
├── services # Business logic layer
├── tests # Test scenarios
└── utilities # Driver, Wait, Browser utils

# Sample Test Scenario
- Login with valid user
- Search product
- Add product to cart
- Verify cart product
- Place order (E2E flow)

 # Highlights

- Clean architecture with BaseTest + Services + Utils
- Explicit wait strategy (stable & non-flaky tests)
- Scroll & browser utility abstraction
- Reusable login & cart flows
- Soft assertion strategy

- # Configuration
Create a `configuration.properties` file based on the example:

```properties
browser=chrome
toUrl=https://www.testotomasyonu.com
validEmail=your_email
validPassword=your_password
searchTerm=iphone
```

Author
Cem Izci
QA Automation Engineer (in progress)
