Feature: Add junior profiles

  Scenario Outline: Successfully add a junior profile
    Given A user successfully logs in with a "<userName>" and "<password>" and "<membership>"
    When the user completes the junior personal details profile
    Then the new junior profile is successfully added

    Examples:
      | membership     | userName                                      | password    |
      | Community      | btfautotestcommunitymembership@mailinator.com | Testing123! |
      | Active         | btfautotestactivemembership@mailinator.com    | Testing123! |
      | Racer          | btfautotestracermembership@mailinator.com     | Testing123! |
      | Ultimate Racer | btfautotestultimateracer@mailinator.com       | Testing123! |

  @SMOKE
  Scenario Outline: Successfully add a junior profile
    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user completes the junior personal details profile
    Then the new junior profile is successfully added

    Examples:
      | membership | paymentMethod |
      | Community  |               |