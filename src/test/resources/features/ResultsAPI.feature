Feature: Test Seasons API End Point

  Background:

    Given I have the API
    When I hit the "results.json" endPoint
    Then I expect the responseCode to be 200


  Scenario: Record season years returned from the endPoint

    And I expect the race names returned to be correct


  Scenario: Verify that circuitId of British Grand Prix raceName is silverstone

    Then I expect the circuitId of "silverstone" returned to be correct