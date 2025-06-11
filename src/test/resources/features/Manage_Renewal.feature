Feature: Manage renewal
#
#  Scenario Outline: Community membership cannot manage renewal
#
#    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
#    When the user attempts to manage the renewal for the "<membership>"
#    Then the user should not be able to complete the renewal process.
#    Examples:
#      | membership | paymentMethod |
#      | Community  | Direct Debit  |
#
#
#  Scenario Outline: Membership paid via Credit-Debit cannot manage renewal
#
#    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
#    When the user attempts to manage the renewal for the "<membership>"
#    Then the user should not be able to complete the renewal process.
#    Examples:
#      | membership | paymentMethod |
#      | Active     | Credit-Debit  |
#
  @SMOKE
  Scenario Outline: Successfully manage renewal membership paid via Direct Debit
    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user manages renewal for the "<membership>" to "<membershipManageRenewal>"
    Then the user successfully manages renewal to "<membershipManageRenewal>"
    Examples:
      | membership | paymentMethod | membershipManageRenewal |
      | Active     | Direct Debit  | Community               |

#  @SMOKE
#  Scenario Outline: Community membership cannot manage renewal
#
#    Given a user is on the login page
#    When the user inputs "<username>" and "<password>"
#    Then the user should be logged into successfully as a "<membership>" member
#    When the user attempts to manage the renewal for the "<membership>"
#    Then the user should not be able to complete the renewal process.
#    Examples:
#      | membership | paymentMethod | username           | password    |
#      | Community  | Direct Debit  | fzb46614@toaik.com | Testing123! |
#
#  @SMOKE
#  Scenario Outline: Membership paid via Credit-Debit cannot manage renewal
#
#    Given a user is on the login page
#    When the user inputs "<username>" and "<password>"
#    Then the user should be logged into successfully as a "<membership>" member
#    When the user attempts to manage the renewal for the "<membership>"
#    Then the user should not be able to complete the renewal process.
#    Examples:
#      | membership | paymentMethod | username           | password    |
#      | Active     | Credit-Debit  | ohx30111@toaik.com | Testing123! |