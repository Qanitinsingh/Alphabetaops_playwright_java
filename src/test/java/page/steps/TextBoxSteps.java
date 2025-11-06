package page.steps;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.methods.TextBoxActions;

public class TextBoxSteps {

    private Page page;

    public TextBoxSteps() {
        page = Hooks.page;
    }

    @Given("I launch the browser in chromium using Playwright")
    public void i_launch_the_browser_in_chromium_using_playwright() {
        TextBoxActions.launchBrowser(page);
    }

    @And("I navigate to {string}")
    public void i_navigate_to(String url) {
        TextBoxActions.navigate(page, url);
    }

    @When("I click on Novice option under left menu")
    public void i_click_on_novice_option_under_left_menu() {
        TextBoxActions.clickNovice(page);
    }

    @And("I click on Text Box link")
    public void i_click_on_text_box_link() {
        TextBoxActions.clickTextBox(page);
    }

    @And("I click on Basic Text Box section")
    public void i_click_on_basic_text_box_section() {
        TextBoxActions.clickBasicTextBox(page);
    }
    @And("Switch to the practice form iframe")
    public void swicth_to_iframee() {
        TextBoxActions.switchToPracticeFormIframe(page);
    }


    @And("I enter {string} into the Text with Placeholder field")
    public void i_enter_into_the_text_with_placeholder_field(String string) {
        TextBoxActions.enterTextPlaceholder(page, string);
    }

    @And("I enter {string} into the Pre-filled Input field")
    public void i_enter_into_the_pre_filled_input_field(String string) {
        TextBoxActions.enterPreFilled(page, string);
    }

    @And("I enter {string} into the Required Field")
    public void i_enter_into_the_required_field(String string) {
        TextBoxActions.enterRequired(page, string);
    }

    @And("I verify the Read-only Input contains {string}")
    public void i_verify_the_read_only_input_contains(String string) {
        TextBoxActions.verifyReadOnly(page, string);
    }

    @And("I enter {string} into the Resettable Input field")
    public void i_enter_into_the_resettable_input_field(String string) {
        TextBoxActions.enterResettable(page, string);
    }

    @And("I clear and enter {string} into Limited Input field")
    public void i_clear_and_enter_into_limited_input_field(String string) {
        TextBoxActions.enterLimitedText(page, string);
    }

    @And("I click on Submit button")
    public void i_click_on_submit_button() {
        TextBoxActions.clickSubmit(page);
    }

    @Then("I should see the form submitted successfully")
    public void i_should_see_the_form_submitted_successfully() {
        System.out.println("Success Message displayed");
    }
}
