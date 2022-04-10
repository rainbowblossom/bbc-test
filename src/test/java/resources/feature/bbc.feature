Feature: Testing different request on the  BBC music tracks and metadata

  Scenario:Verify if the BBC application can be accessed by users
    When User sends a GET request to the BBC endpoints
    Then User must get back a valid status code
    And Response time should be below 1000 milliseconds

  Scenario: Verify if the BBC application can be accessed by users
    When User sends a GET request to the BBC endpoints
    Then Verify that the "id" field is never null for all the items present in the list
    And the "segment_type" field for every track is "music"

  Scenario: Verify if the BBC application can be accessed by users
    When User sends a GET request to the BBC endpoints
    Then Verify that the "primary" field in the "title_list" is never empty for any track

  Scenario: Verify if the BBC application can be accessed by users
    When User sends a GET request to the BBC endpoints
    Then Verify that only one track in the list has "now_playing" field in "offset" as true

  Scenario: Verify if the BBC application can be accessed by users
    When User sends a GET request to the BBC endpoints
    Then Verify the "Date" value in the response headers


