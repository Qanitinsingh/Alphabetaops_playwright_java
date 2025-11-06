Feature: Fill and submit AlphaBetaOps form

  Background:
    Given I launch the browser in chromium using Playwright
    And I navigate to "https://alphabetaops.com/"

  @SmokeTest
  Scenario: Fill the form and submit
    When I click on Novice option under left menu
    And I click on Text Box link
    And I click on Basic Text Box section
    And Switch to the practice form iframe
    And I enter "Nitin" into the Text with Placeholder field
    And I enter "Sample" into the Pre-filled Input field
    And I enter "Required value" into the Required Field
    And I verify the Read-only Input contains "Read-only content"
    And I enter "Clear me updated" into the Resettable Input field
    And I clear and enter "Limit10" into Limited Input field
    And I click on Submit button
    Then I should see the form submitted successfully
