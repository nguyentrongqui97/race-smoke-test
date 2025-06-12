Feature: Refund membership

  @SMOKE @REFUND_CC
  Scenario Outline: Successfully refund a membership via Credit-Debit
    Given A user successfully logs in as a "<membership>" paid via "<signUpPaymentMethod>"
    And the user upgrades the "<membership>" to "<upgradedMembership>" paying via "<upgradedPaymentMethod>"
    And the user successfully upgrades the membership to "<upgradedMembership>"
    When the user refunds the membership within 14 days from "<upgradedMembership>" to "<membership>"
    Then the user successfully reverts to the "<membership>"

    Examples:
      | membership | signUpPaymentMethod | upgradedMembership | upgradedPaymentMethod |
      | Community  |                     | Racer              | Credit-Debit          |
      | Active     | Credit-Debit        | Ultimate Racer     | Credit-Debit          |

  @SMOKE @REFUND_dd
  Scenario Outline: Successfully refund a membership via Direct Debit
    Given A user successfully logs in as a "<membership>" paid via "<signUpPaymentMethod>"
    And the user upgrades the "<membership>" to "<upgradedMembership>" paying via "<upgradedPaymentMethod>"
    And the user successfully upgrades the membership to "<upgradedMembership>"
    When the user refunds the membership within 14 days from "<upgradedMembership>" to "<membership>"
    Then the user successfully reverts to the "<membership>"

    Examples:
      | membership | signUpPaymentMethod | upgradedMembership | upgradedPaymentMethod |
      | Community  |                     | Active             | Direct Debit          |
      | Racer      | Direct Debit        | Ultimate Racer     | Direct Debit          |
