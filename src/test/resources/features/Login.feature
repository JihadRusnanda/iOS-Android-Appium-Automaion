Feature: Login

  @Android @iOS @LoginFeature
  Scenario: Success login
    Given User is on stockbit landingpage
    When User click login
    And User input username as "jihad1"

