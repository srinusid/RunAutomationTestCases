
@tag
Feature: Error Validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Negative Test of submitting the order
  Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
  
    Then "Incorrect email or password." error message is displayed

   Examples: 
      | name                  | password    |
      | srinusid830@gmail.com |Devisrinu12@|
