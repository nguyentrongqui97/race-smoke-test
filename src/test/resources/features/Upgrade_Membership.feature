Feature: Upgrade membership

  @SMOKE
  Scenario Outline: Successfully upgrade a membership
    Given A user successfully logs in as a "<currentMembership>" paid via "<signUpPaymentMethod>"
    When the user upgrades the "<currentMembership>" to "<upgradedMembership>" paying via "<upgradedPaymentMethod>"
    Then the user successfully upgrades the membership to "<upgradedMembership>"

    Examples:
      | currentMembership | signUpPaymentMethod | upgradedMembership | upgradedPaymentMethod |
      | Community         |                     | Active             | Credit-Debit          |
      | Active            | Credit-Debit        | Racer              | Credit-Debit          |
      | Community         |                     | Racer              | Direct Debit          |
      | Racer             | Credit-Debit        | Ultimate Racer     | Direct Debit          |




