Feature: Log-in

  @SMOKE @LOGIN
  Scenario Outline: Successfully log into with "<membershipType>" user
    Given a user is on the login page
    When the user inputs "<username>" and "<password>"
    Then the user should be logged into successfully as a "<membershipType>" member

    Examples:
      | membershipType | username                                   | password    |
      | Community      | zjt29619@jioso.com                         | Testing123! |
      | Active         | btfautotestactivemembership@mailinator.com | Testing123! |
      | Racer          | btfautotestracermembership@mailinator.com  | Testing123! |
      | Ultimate Racer | btfautotestultimateracer@mailinator.com    | Testing123! |
