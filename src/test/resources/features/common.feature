@ignore
Feature: Common steps for reusability

  # ======== LOGIN AND NAVIGATION REUSABLE ========
  Scenario: Shared Navigation steps
    When "User" is logged in to the App
    And user navigates to the Documents page
    And user navigates to the "Created Document" path


    # ======== ACTION (CLICK, TYPE) REUSABLE ========
  Scenario: Shared action steps
    And user clicks "Add Doc" button on Documents page
    And user fills "Document Name" field on Documents page as "valid"
    And user opens "User" dropdown on Landing page
    And user selects "Dummy" option on Landing page


    # ======== ASSERTION (VISIBLE, ENABLED, TEXT) REUSABLE ========
  Scenario: Shared Assertion steps
    And the "Document Name" field on Landing page is softly Read Only
    And the "Resend Invite" item on Landing page is strictly not visible
    And the "Login" button on Login page is strictly disabled
    And the text of "User Name" item on Landing page is softly equals "Elena"
    Then the toast message starting with "Invalid Credentials Please try again" is visible
    And user saves the count of "Documents" field
    And the count of "Documents" field should change by 1


    # ======== SYSTEM REUSABLE ========
  Scenario: Shared system/api/db steps
    * system waits for 1 seconds
    * page is refreshed 1 times