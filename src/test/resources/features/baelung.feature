Feature: the message can be retrieved

  @Test1
  Scenario: client makes call to POST /baeldung
    When the client calls /baeldung
    Then the baeldung client receives status code of 200
    """
    new lines added
    """
    And the client receives server version hello

  @Test2
  Scenario: client makes call to GET /hello
    Given the client calls /hello
    When the client receives status code of 200
    Then the client receives server version hello