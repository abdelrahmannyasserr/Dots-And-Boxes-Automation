# Dots-and-Boxes-Game-Automation
This project is designed to automate the testing of a game initialization system using TestNG and Cucumber. The game setup involves configuring parameters like board size, number of players, and player names. Automated tests are implemented to validate both the happy path (valid inputs) and various negative scenarios (invalid inputs).

# Features:
Automated Game Setup: Tests verify the ability to initialize the game with specific board sizes and player counts.
Player Input Validation: Tests ensure player names are correctly handled, with validation for missing or invalid names.
Comprehensive Input Validation: Automates the validation of inputs like board size, number of players, and player names. Invalid data triggers appropriate error messages.
Testing Approach:
TestNG: The test execution framework used to run the scenarios, ensuring proper test management and reporting.
Step Definitions: Step classes define the automation logic for each scenario, providing reusable and maintainable test steps.
Test Runner: A TestNG-based test runner is used to execute the Cucumber scenarios and generate detailed test reports.
Scenario Context Class: Handles error messages from input validation. The ScenarioContext class stores and manages error messages that are displayed during negative scenarios, ensuring proper validation and feedback to the user.
Scenarios Covered:
Happy Path: Validates successful game initialization with correct data (board size, players, names).
Negative Scenarios: Automates tests for invalid inputs like incorrect board size, invalid player counts, and empty player names. These scenarios check if proper error messages are triggered, which are managed via the ScenarioContext class.
Technologies Used:
TestNG: Test execution and reporting framework
Cucumber: BDD (Behavior-Driven Development) for scenario definition
Step Classes: Define automation steps for each test scenario
Gherkin: Human-readable format for defining test scenarios
Maven: Dependency management and build tool
