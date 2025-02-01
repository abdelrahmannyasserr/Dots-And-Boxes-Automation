Feature: Game Initialization

  @happyScenario
  Scenario: Initialize game with valid data
    Given I enter board size "5x4"
    And I enter number of players "2"
    And I enter player names:
      | Alice |
      | Bob   |
    Then the game should initialize successfully

  @happyScenario
  Scenario: Initialize game with valid data and Alice Win
    Given I enter board size "3x2"
    And I enter number of players "2"
    And I enter player names:
      | Alice |
      | Bob   |
    And the game should initialize successfully
    Then Play game and finish it win

  @happyScenario
  Scenario: Initialize game with valid data and draw
    Given I enter board size "3x2"
    And I enter number of players "2"
    And I enter player names:
      | Alice |
      | Bob   |
    And the game should initialize successfully
    Then Play game and finish it draw

  @negativeScenarios
  Scenario: Initialize game with invalid board size
    Given I enter board size "15x4"
    Then verify that user got error message of invalid board size

  @negativeScenarios
  Scenario: Initialize game with invalid number of players
    Given I enter board size "8x6"
    And I enter number of players "5"
    Then verify that user got error message of invalid player numbers


  @negativeScenarios
  Scenario: Initialize game with invalid player names
    Given I enter board size "8x6"
    And I enter number of players "2"
    And I enter player names:
      | Alice |
      |       |
    Then verify that user got error message of invalid player name
