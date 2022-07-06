Feature: Testing Headers

  Scenario: Given i don't send any headers, the API responds with a 400 Bad Request
    Given I have a valid value id to request data
    When I submit a request using the value id <1> but no headers
    Then I receive a response of 400 Bad Request