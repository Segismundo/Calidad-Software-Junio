Feature: UH from SpaceInvaders application

  Background:
    Given We open the app

  Scenario: Open a game
    When We start the game
    Then We see an activity Screen


  Scenario: Kill an alien
    Given We start the game
    When We shoot an alien
    Then The alien die

  Scenario: Hit an alien and get points
    Given We start the game
    When We shoot an alien
    Then We score some points

  Scenario: We should be able to hit the shield
    Given We start the game
    When We hit the shield
    Then Our shoot should disappear

  Scenario: We complete a game
    Given We start the game
    When Die in the game
    Then We see our ranking in the game