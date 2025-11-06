package page.methods;



import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.objects.TextBoxObjects;



public  class TextBoxActions {

    private static FrameLocator iframe;
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

        } catch (Exception e) {
            logger.error("Failed to navigate to {} : {}", url, e.getMessage());
            throw e;
        }
    }

    public static void clickNovice(Page page) {
        try {
           page.locator(TextBoxObjects.NO_VOICE).click();
            logger.info("Clicking 'Novice' menu option...");

        } catch (Exception e) {
            logger.error("Unable to click Novice option: {}", e.getMessage());
            throw e;
        }
    }

    public static void clickTextBox(Page page) {
        try {
           page.locator(TextBoxObjects.NO_VOICE_TEXTBOX).click();
            logger.info("Clicking 'Text Box' link...");

        } catch (Exception e) {
            logger.error("Unable to click Text Box link: {}", e.getMessage());
            throw e;
        }
    }

    public static void clickBasicTextBox(Page page) {
        try {
           page.locator(TextBoxObjects.NO_VOICE_TEXTBOX_BASICTEXTBOX).click();
            logger.info("Clicking Basic Text Box section...");

        } catch (Exception e) {
            logger.error("Unable to click Basic Text Box: {}", e.getMessage());
            throw e;
        }
    }
    public  static void switchToPracticeFormIframe(Page page) {
        try {
             iframe = page.frameLocator("iframe.content-iframe");
            logger.info("Switched to practice form Iframe.");
        } catch (Exception e) {
            logger.error("Unable to switch to Iframe: {}", e.getMessage());
            throw e;
        }

    }

    public static void enterTextPlaceholder(Page page, String input) {
        try {
            iframe.locator(TextBoxObjects.TEXT_PLACEHOLDER).fill(input);
            logger.info("Entering '{}' in placeholder input...", input);
           page.fill("input[placeholder='Type something...']", input);
        } catch (Exception e) {
            logger.error("Failed to enter text in placeholder field: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterPreFilled(Page page, String input) {
        try {
            iframe.locator(TextBoxObjects.PREFILLED).fill(input);
            logger.info("Entering '{}' in Pre-filled field...", input);

        } catch (Exception e) {
            logger.error("Failed to enter text in Pre-filled input: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterRequired(Page page, String input) {
        try {
            iframe.locator(TextBoxObjects.REQUIRED).fill(input);
            logger.info("Entering '{}' in Required field...", input);

        } catch (Exception e) {
            logger.error("Failed to enter required field value: {}", e.getMessage());
            throw e;
        }
    }

    public static void verifyReadOnly(Page page, String expectedValue) {
        try {
            String actual =page.getAttribute(TextBoxObjects.READ_ONLY, "value");
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
            iframe.locator(TextBoxObjects.RESET_INPUT).fill(input);
            logger.info("Entering '{}' into Resettable input field...", input);

        } catch (Exception e) {
            logger.error("Failed to enter resettable input: {}", e.getMessage());
            throw e;
        }
    }

    public static void enterLimitedText(Page page, String input) {
        try {
            iframe.locator(TextBoxObjects.LIMITED_TEXT).clear();
            iframe.locator(TextBoxObjects.LIMITED_TEXT).fill(input);
            logger.info("Clearing Limited input and entering '{}'", input);

        } catch (Exception e) {
            logger.error("Failed to enter limited text: {}", e.getMessage());
            throw e;
        }
    }

    public static void clickSubmit(Page page) {
        try {
            iframe.locator(TextBoxObjects.SUBMIT_BUTTON).click();
            logger.info("Clicking Submit button...");

        } catch (Exception e) {
            logger.error("Failed to click Submit: {}", e.getMessage());
            throw e;
        }
    }

    public static void verifySubmission(Page page) {
        try {
            logger.info("Verifying form submission success...");

        } catch (Exception e) {
            logger.error("Form submission verification failed: {}", e.getMessage());
            throw e;
        }
    }
}
