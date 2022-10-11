Feature: User Create, Update and Delete

  Scenario Outline: Verify that the user is created successfully
    Given Add user payload with values "<Name>" "<Job>"
    When User calls "AddUserAPI" with "POST" http request
    Then The response in API call is success with status code 201 and "<Name>" and "<Job>" should be verified

    #   And verify user is created from "<Name>" "<Job>" using "GetUserAPI"
    Examples: 
      | Name   | Job                  |
      | Martin | Business Development |
      | Guptil | Quality Assurance    |

  Scenario Outline: Verify that the user is updated successfully
    Given Update user payload with values "<Name>" "<Job>"
    When User calls "UpdateUserAPI" with "PUT" http request
    Then The response in API call is success with status code 200 and "<Name>" and "<Job>" should be verified

    Examples: 
      | Name     | Job     |
      | RoseWell | Admin   |
      | Timpa    | Finance |

  Scenario: Verify to get the users 
    Given User calls "GetUserAPI" with "GET" http requests
    Then The response in API call is success with status code 200

  Scenario: Verify to delete the users
    Given Delete user and calls "DeleteUserAPI" with "DELETE" http requests
    Then The response in API call is success with status code 204
    
    
 
    
