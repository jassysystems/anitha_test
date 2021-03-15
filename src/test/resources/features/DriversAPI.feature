Feature: Test Drivers API End Point

  Background:

    Given I have the Ergast Developer API
    When I call the "drivers.json" endPoint


  Scenario: Record Driver Id's returned from the endPoint

    Then I expect the correct responseCode to be 200
    And I expect the driver Ids returned to be correct

  Scenario: Verify that driverId of adams nationality is Belgian

    Then I expect the driverId of "adams" returned

  Scenario: Verify that driverId of all British nationals are returned

    Then I expect the driverId of all "British" nationals returned