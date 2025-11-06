package page.steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;
    public static String browserName;

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {
        try {
            String browserFromProperty = System.getProperty("browser");

            String browserFromTag = scenario.getSourceTagNames().stream()
                    .filter(tag -> tag.matches("@(chrome|firefox|edge|webkit|chromium)"))
                    .map(tag -> tag.replace("@", ""))
                    .findFirst()
                    .orElse(null);

            if (browserFromProperty != null && !browserFromProperty.isEmpty()) {
                browserName = browserFromProperty;
            } else if (browserFromTag != null) {
                browserName = browserFromTag;
            } else {
                browserName = "chromium";
            }

            logger.info("Launching Playwright browser: {}", browserName);

            playwright = Playwright.create();

            BrowserType browserType =
                    switch (browserName.toLowerCase()) {
                        case "firefox" -> playwright.firefox();
                        case "edge" -> playwright.chromium(); // Edge = Chromium
                        case "webkit" -> playwright.webkit();
                        default -> playwright.chromium();
                    };

            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // ✅ Maximize window (Playwright way)
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(null)  // This forces full screen (maximized)
            );

            page = context.newPage();

            // ✅ Clear cookies to ensure fresh session
            context.clearCookies();

            logger.info("{} browser launched, maximized, and cookies cleared.", browserName);

        } catch (Exception e) {
            logger.error("Exception during Playwright setup: {}", e.getMessage(), e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                captureScreenshot(scenario);
            } else {
                logger.info("Scenario '{}' passed.", scenario.getName());
            }
        } catch (Exception e) {
            logger.error("Exception during teardown: {}", e.getMessage(), e);
        } finally {
            cleanUp();
        }
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

            String screenshotPath = System.getProperty("user.dir") +
                    "/ScreenShots/" + screenshotName;

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));

            byte[] screenshotBytes = page.screenshot();
            scenario.attach(screenshotBytes, "image/png", screenshotName);

            logger.error("Scenario '{}' failed. Screenshot saved at: {}", scenario.getName(), screenshotPath);

        } catch (Exception e) {
            logger.error("Error capturing screenshot: {}", e.getMessage());
        }
    }

    private void cleanUp() {
        try {
            if (context != null) {
                context.close();
                logger.info("Browser context closed.");
            }

            if (browser != null) {
                browser.close();
                logger.info("Browser instance closed.");
            }

            if (playwright != null) {
                playwright.close();
                logger.info("Playwright closed.");
            }

        } catch (Exception e) {
            logger.error("Error during cleanup: {}", e.getMessage());
        }
    }
}
