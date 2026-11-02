# Cucumber Selenium Gradle Framework

A lightweight, robust behavior-driven development (BDD) test automation blueprint built using **Java**, **Selenium WebDriver**, **Cucumber**, and **Gradle**.

## 🛠️ Tech Stack & Dependencies
- **Java 17**
- **Selenium WebDriver (4.x)**: Handles browser interactions (includes auto-managed browser binaries).
- **Cucumber Java & JUnit Platform Engine (7.x)**: Powers BDD parsing and modern runner discovery.
- **Gradle**: Build automation and dependency management tool.

## 📁 Project Architecture
The project follows the standard Gradle framework setup integrated with the Page Object Model (POM):
```text
cucumber-selenium-gradle-framework/
├── src/
│   └── test/
│       ├── java/
│       │   ├── hooks/            # Setup and teardown actions (Browser initialization)
│       │   ├── pages/            # Page Object Model pattern (UI elements & locators)
│       │   ├── runners/          # JUnit Suite orchestration class to run features
│       │   └── stepdefinitions/  # Gherkin step binding execution code
│       └── resources/
│           └── features/         # BDD scenario specification files (.feature)
├── build.gradle                  # Dependency declarations & build definitions
└── settings.gradle               # Root project settings
```

## 🚀 Execution Instructions

### Option 1: Running via IDE (Recommended for Development)
1. Open **IntelliJ IDEA** or **Eclipse**.
2. Select **Open/Import Project** and point to the root folder containing `build.gradle`.
3. Allow Gradle to download dependencies.
4. Navigate to `src/test/java/runners/TestRunner.java`.
5. Right-click `TestRunner` and click **Run**.

### Option 2: Running via Terminal/Command Line
Execute all tests by running the standard Gradle testing task from the project root:
```bash
./gradlew test
```
*(On Windows cmd, use `gradlew.bat test`)*

## 📊 Test Reports
After executing tests via the runner or command line, the HTML report will be generated automatically at:
`build/reports/cucumber/cucumber.html`
