Feature: Purchase the order from E-commerce website

  Background:
    Given I landed on E-commerce page

  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "Thank you for the order." message is displayed on ConfirmationPage

    Examples:
      | name        | password    | productName     |
      | tozi@abv.bg | ToziOnzi123 | ADIDAS ORIGINAL |
      | ala@bala.bg | aleOppa     | Invalid product |