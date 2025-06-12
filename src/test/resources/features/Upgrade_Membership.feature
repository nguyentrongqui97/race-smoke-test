Feature: Upgrade membership

  @SMOKE @UPGRADE_CC
  Scenario Outline: Successfully upgrade a membership via Credit-Debit
    Given A user successfully logs in as a "<currentMembership>" paid via "<signUpPaymentMethod>"
    When the user upgrades the "<currentMembership>" to "<upgradedMembership>" paying via "<upgradedPaymentMethod>"
    Then the user successfully upgrades the membership to "<upgradedMembership>"

    Examples:
      | currentMembership | signUpPaymentMethod | upgradedMembership | upgradedPaymentMethod |
      | Community         |                     | Active             | Credit-Debit          |
      | Active            | Credit-Debit        | Racer              | Credit-Debit          |

  @SMOKE @UPGRADE_DD
  Scenario Outline: Successfully upgrade a membership via Direct Debit
    Given A user successfully logs in as a "<currentMembership>" paid via "<signUpPaymentMethod>"
    When the user upgrades the "<currentMembership>" to "<upgradedMembership>" paying via "<upgradedPaymentMethod>"
    Then the user successfully upgrades the membership to "<upgradedMembership>"

    Examples:
      | currentMembership | signUpPaymentMethod | upgradedMembership | upgradedPaymentMethod |
      | Community         |                     | Racer              | Direct Debit          |
      | Racer             | Direct Debit        | Ultimate Racer     | Direct Debit          |




