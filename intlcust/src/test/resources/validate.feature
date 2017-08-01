Feature: International Customer Summary

  Background: https://confluence.qantas.com.au/pages/viewpage.action?pageId=74683443
  https://confluence.qantas.com.au/display/FRTWEB/2.+Customer+%28Operational%29+Context

  Scenario Outline:
    Given An international agent with countryCode: <countryCode>, agentCode: <agentCode> and cassCode: '<cassCode>' <exists> exist
    When validating international customer request with <requestData>
    Then a http status code <status> should be returned for customer summary
    And a response matching summary with result: <result> and errorCount: <errorCount> and message: '<message>' should be returned
    Examples:
      | countryCode | agentCode | cassCode | requestData                                                   | exists  | status | result            | errorCount | message                                                                                                         |
      | 023         | 5151      | 2122     | {"countryCode":"023", "agentCode":"5151", "cassCode":"2122" } | does    | 200    | Success           | 0          |                                                                                                                 |
      | 023         | 5151      |          | {"countryCode":"023","agentCode":"5151"}                      | does    | 200    | Success           | 0          |                                                                                                                 |
      | 023         | 5151      |          | {"countryCode":"023","agentCode":"5151"}                      | doesn't | 404    | Failure           | 0          |                                                                                                                 |
      |             | 5151      |          | {"agentCode":"5151"}                                          | doesn't | 400    | ValidationFailure | 1          | countryCode cannot be empty                                                                                     |
      | 02          | 5151      |          | {"countryCode":"02","agentCode":"5151"}                       | doesn't | 400    | ValidationFailure | 1          | countryCode has to be 3 character long                                                                          |
      | 023         |           |          | {"countryCode":"023"}                                         | doesn't | 400    | ValidationFailure | 1          | agentCode cannot be empty                                                                                       |
      | 023         | 515       |          | {"countryCode":"023","agentCode":"515"}                       | doesn't | 400    | ValidationFailure | 1          | agentCode has to be 4 character long                                                                            |
      | 023         | 5151      | 212      | {"countryCode":"023","agentCode":"5151","cassCode":"212"}     | doesn't | 400    | ValidationFailure | 1          | cassCode has to be 4 character long                                                                             |
      | 02          | 515       | 212      | {"countryCode":"02","agentCode":"515","cassCode":"212"}       | doesn't | 400    | ValidationFailure | 3          | cassCode has to be 4 character long,countryCode has to be 3 character long,agentCode has to be 4 character long |