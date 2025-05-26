Feature: Sign-up

  Scenario Outline: Sign up for a new account
    Given a user successfully registers for an account
    When the user chooses and pays for the "<membership>"
    Examples:
      | membership     |
      | Community      |
      | Active         |
      | Racer          |
      | Ultimate Racer |
