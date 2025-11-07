package novoice.alphabetaops.playwright;
import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseClass {


        protected static Playwright playwright;
        protected Browser browser;
        protected BrowserContext context;
        protected Page page;

        @BeforeSuite(alwaysRun = true)
        public void beforeSuite() {
            playwright = Playwright.create();
        }

        @Parameters({"browserName"})
        @BeforeMethod(alwaysRun = true)
        public void setUp(@Optional("chromium") String browserName) {
            BrowserType browserType = switch (browserName.toLowerCase()) {
                case "firefox" -> playwright.firefox();
                case "webkit" -> playwright.webkit();
                default -> playwright.chromium();
            };

            browser = browserType.launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setSlowMo(50));

            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080));

            page = context.newPage();
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown() {
            if (context != null) context.close();
            if (browser != null) browser.close();
        }

        @AfterSuite(alwaysRun = true)
        public void afterSuite() {
            if (playwright != null) playwright.close();
        }
    }
