Feature: Sign up for an account paying via Direct Debit

  Scenario Outline: Sign up for a new account "<membership>" via Credit-Debit
    Given a user successfully registers for an account
    When the user chooses and pays for the "<membership>" via "<paymentMethod>"
    Then the user successfully signs up for a new "<membership>" account
    Examples:
      | membership     | paymentMethod |
      | Community      |               |
      | Active         | Credit-Debit  |
      | Racer          | Credit-Debit  |
      | Ultimate Racer | Credit-Debit  |

