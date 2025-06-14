Feature: Manage renewal

  @SMOKE @RENEWAL_COMMUNITY
  Scenario Outline: Community membership cannot manage renewal

    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user attempts to manage the renewal for the "<membership>"
    Then the user should not be able to complete the renewal process.
    Examples:
      | membership | paymentMethod |
      | Community  |               |

  @SMOKE @RENEWAL_CC
  Scenario Outline: Memberships paid via Credit-Debit cannot manage renewal

    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user attempts to manage the renewal for the "<membership>"
    Then the user should not be able to complete the renewal process.
    Examples:
      | membership | paymentMethod |
      | Active     | Credit-Debit  |

  @SMOKE @RENEWAL_DD
  Scenario Outline: Successfully manage renewal memberships
    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user manages renewal for the "<membership>" to "<membershipManageRenewal>"
    Then the user successfully manages renewal to "<membershipManageRenewal>"
    Examples:
      | membership | paymentMethod | membershipManageRenewal |
      | Active     | Direct Debit  | Community               |
