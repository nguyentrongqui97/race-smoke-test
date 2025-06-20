Feature: Add Emergency Contacts

  @SMOKE @EMERGENCY
  Scenario Outline: Successfully add an emergency contact
    Given A user successfully logs in as a "<membership>" paid via "<paymentMethod>"
    When the user adds information for "<numberOfEmergencyContact>" emergency contact
    Then "<numberOfEmergencyContact>" emergency contact shall be successfully added

    Examples:
      | membership | paymentMethod | numberOfEmergencyContact |
      | Community  |               | 1                        |
      | Community  |               | 2                        |
