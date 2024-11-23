Feature: SauceDemo Portal End-to-End Test

  Scenario: Verify complete purchase workflow on SauceDemo
    Given the application is launched
    When I login with username "<username>" and password "<password>"
    And I add multiple items to the cart
    And I proceed to checkout
    And I provide valid checkout information
    And I complete the order
    Then I should see the confirmation message "Thank you for your order!"