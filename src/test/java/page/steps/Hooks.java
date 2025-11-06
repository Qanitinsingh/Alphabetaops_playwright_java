package page.steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {
        try {
            logger.info("Launching Chromium browser...");

            playwright = Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setArgs(Arrays.asList(new String[]{"--start-maximized"}))
                    // ✅ Force maximize
            );
            // ✅ Maximize window
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(null) // full screen automatically
            );

            page = context.newPage();
            context.clearCookies();

            logger.info("Chromium launched & maximized.");
        } catch (Exception e) {
            logger.error("Exception during setup: {}", e.getMessage(), e);
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
            logger.error("Error during teardown: {}", e.getMessage());
        } finally {
            cleanUp();
        }
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

            String screenshotPath = System.getProperty("user.dir") + "/ScreenShots/" + screenshotName;

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));

            scenario.attach(page.screenshot(), "image/png", screenshotName);

            logger.error("Scenario failed. Screenshot saved at: {}", screenshotPath);

        } catch (Exception e) {
            logger.error("Error capturing screenshot: {}", e.getMessage());
        }
    }

    private void cleanUp() {
        try {
            if (context != null) context.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();

            logger.info("Browser closed successfully.");
        } catch (Exception e) {
            logger.error("Error during cleanup: {}", e.getMessage());
        }
    }
}
