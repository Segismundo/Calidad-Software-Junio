Feature: UH from SpaceInvaders application

  Background:
    Given We enter the game

  Scenario: Complete a good game
    Given We make a good score
    When We enter the name "ExampleName"
    Then We see our ranking in the game

  Scenario: Complete a bad game
    Given We make a bad score
    Then We don't see our ranking in the game