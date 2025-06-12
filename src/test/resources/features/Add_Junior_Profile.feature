Feature: Add junior profiles

  @SMOKE @JUNIOR
  Scenario Outline: Successfully add a junior profile
    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user completes the junior personal details profile
    Then the new junior profile is successfully added

    Examples:
      | membership | paymentMethod |
      | Community  |               |