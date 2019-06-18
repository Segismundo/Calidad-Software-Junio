Feature: UH from SpaceInvaders application

  Background:
    Given We open the app

  @UserStories-feature
  Scenario: Open a game
    When We start the game
    Then We see an activity Screen

  @UserStories-feature
  Scenario: Kill an alien
    Given We start the game
    When We shoot an alien
    Then The alien die

  @UserStories-feature
  Scenario: Hit an alien and get points
    Given We start the game
    When We shoot an alien
    Then We score some points

  @UserStories-feature
  Scenario: We should be able to exit the game
    Given We start the game
    When We press back button
    Then We should exit the game

  @UserStories-feature
  Scenario: We complete a game
    Given We start the game
    When Die in the game
    Then We enter the name "test"
    And We see our ranking in the game