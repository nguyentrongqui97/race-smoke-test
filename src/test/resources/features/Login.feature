#Feature: Login into BTF platform
#
#  Scenario: Successfully log in
#    Given a user is on the login page
#    When the user inputs valid username and password
#      | membershipType | username                              | password    |
#      | Community      | communitytestuser13btf@mailinator.com | Testing123! |
#      | Active         | activetestuser9btf@mailinator.com     | Testing123! |
##    And click the login button
##    Then the user shall be redirected to the Dashboard

Feature: Log-in

  Scenario Outline: Successfully log into with "<membershipType>" user
    Given a user is on the login page
    When the user inputs "<username>" and "<password>"
    Then the user should be logged into successfully as a "<membershipType>" member

    Examples:
      | membershipType | username                                      | password    |
      | Community      | btfautotestcommunitymembership@mailinator.com | Testing123! |
      | Active         | btfautotestactivemembership@mailinator.com    | Testing123! |
      | Racer          | btfautotestracermembership@mailinator.com     | Testing123! |
      | Ultimate Racer | btfautotestultimateracer@mailinator.com       | Testing123! |

