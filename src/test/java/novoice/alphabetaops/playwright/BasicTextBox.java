package novoice.alphabetaops.playwright;

import com.microsoft.playwright.*;

public class BasicTextBox {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)); // set true if you want headless mode

            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            // Open the website
            page.navigate("https://alphabetaops.com/");
            page.setViewportSize(1920, 1080);
            // Click on the novice section
            page.locator(".mb-2:first-child").click();
            // Click on the first text box menu item
            page.locator(".mb-2:first-child ul.list-group li.list-group-item:first-child").click();

            // Click on the basic text box option
            page.locator(".mb-2:first-child ul.list-group li.list-group-item:first-child ul li").click();
            // Switch to iframe
            FrameLocator iframe = page.frameLocator("iframe.content-iframe");
            // Fill the full name field inside iframe
            iframe.locator("#plain-text").fill("Nitin");
            iframe.locator("#prefilled-text").clear();
            iframe.locator("#prefilled-text").fill("Automation");
            iframe.locator("#required-field").fill("Testing");

            iframe.locator("#resettable-text").clear();
            iframe.locator("#resettable-text").fill("playwright");
            iframe.locator("#limited-text").fill("limit 10");
            iframe.locator(".submit-btn").click();
            page.locator(".mb-2:last-child").click();
            // Close the browser
            browser.close();
        }
    }
}
