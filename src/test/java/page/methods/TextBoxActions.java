package page.methods;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.objects.TextBoxObjects;

public  class TextBoxActions {

    private static final Logger logger = LoggerFactory.getLogger(TextBoxActions.class);

    public static void launchBrowser(Page page) {
        try {
            logger.info("Launching chromium browser from Hooks...");
        } catch (Exception e) {
            logger.error("Error launching browser: {}", e.getMessage());
            throw e;
        }
    }

    public static void navigate(Page page, String url) {
        try {
            page.navigate(url);
            logger.info("Navigating to URL: {}", url);
            page.navigate(url);
        } catch (Exception e) {
            logger.error("Failed to navigate to {} : {}", url, e.getMessage());
            throw e;
        }
    }

    public static void clickNovice(Page page) {
        try {
            page.click(TextBoxObjects.NO_VOICE);
            logger.info("Clicking 'Novice' menu option...");
            page.click("text=Novice");
        } catch (Exception e) {
            logger.error("Unable to click Novice option: {}", e.getMessage());
            throw e;
        }
    }

    public static void clickTextBox(Page page) {
        try {
            page.click(TextBoxObjects.NO_VOICE_TEXTBOX);
            logger.info("Clicking 'Text Box' link...");
            page.click("text=Text Box");
        } catch (Exception e) {
            logger.error("Unable to click Text Box link: {}", e.getMessage());
            throw e;
        }
    }

    public static void clickBasicTextBox(Page page) {
        try {
            page.click(TextBoxObjects.NO_VOICE_TEXTBOX_BASICTEXTBOX);
            logger.info("Clicking Basic Text Box section...");
            page.click("text=Basic Text Box");
        } catch (Exception e) {
            logger.error("Unable to click Basic Text Box: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterTextPlaceholder(Page page, String input) {
        try {
            page.fill(TextBoxObjects.TEXT_PLACEHOLDER, input);
            logger.info("Entering '{}' in placeholder input...", input);
            page.fill("input[placeholder='Type something...']", input);
        } catch (Exception e) {
            logger.error("Failed to enter text in placeholder field: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterPreFilled(Page page, String input) {
        try {
            page.fill(TextBoxObjects.PREFILLED, input);
            logger.info("Entering '{}' in Pre-filled field...", input);
            page.fill("input[value='Preloaded text']", input);
        } catch (Exception e) {
            logger.error("Failed to enter text in Pre-filled input: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterRequired(Page page, String input) {
        try {
            page.fill(TextBoxObjects.REQUIRED, input);
            logger.info("Entering '{}' in Required field...", input);
            page.fill("input[required]", input);
        } catch (Exception e) {
            logger.error("Failed to enter required field value: {}", e.getMessage());
            throw e;
        }
    }

    public static void verifyReadOnly(Page page, String expectedValue) {
        try {
            String actual = page.getAttribute(TextBoxObjects.READ_ONLY, "value");
            logger.info("Is Read-only field editable? {}", actual);
            logger.info("Read-only actual value: '{}'", actual);

            if (!expectedValue.equals(actual)) {
                throw new AssertionError("Expected: " + expectedValue + " but found: " + actual);
            }

        } catch (Exception e) {
            logger.error("Read-only field verification failed: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterResettable(Page page, String input) {
        try {
            page.fill(TextBoxObjects.RESET_INPUT, input);
            logger.info("Entering '{}' into Resettable input field...", input);
            page.fill("input[placeholder='Clear me']", input);
        } catch (Exception e) {
            logger.error("Failed to enter resettable input: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterLimitedText(Page page, String input) {
        try {
            page.fill(TextBoxObjects.LIMITED_TEXT, input);
            logger.info("Clearing Limited input and entering '{}'", input);
            page.fill("input[maxlength='10']", "");  // clear
            page.fill("input[maxlength='10']", input);
        } catch (Exception e) {
            logger.error("Failed to enter limited text: {}", e.getMessage());
            throw e;
        }
    }

    public static void clickSubmit(Page page) {
        try {
            page.click(TextBoxObjects.SUBMIT_BUTTON);
            logger.info("Clicking Submit button...");
            page.click("button:has-text('Submit')");
        } catch (Exception e) {
            logger.error("Failed to click Submit: {}", e.getMessage());
            throw e;
        }
    }

    public static void verifySubmission(Page page) {
        try {
            logger.info("Verifying form submission success...");
            page.waitForSelector("text=Success");
        } catch (Exception e) {
            logger.error("Form submission verification failed: {}", e.getMessage());
            throw e;
        }
    }
}
