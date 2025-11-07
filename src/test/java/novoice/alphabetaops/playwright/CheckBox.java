package novoice.alphabetaops.playwright;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBox extends BaseClass {

    FrameLocator iframe;

    @BeforeMethod
    public void openCheckBoxPage() {
        page.navigate("https://alphabetaops.com/");

        page.locator(".mb-2:first-child").click();
        page.locator(".mb-2:first-child ul.list-group li.list-group-item:nth-of-type(2)").click();
        page.locator(".mb-2:first-child ul.list-group li.list-group-item:nth-of-type(2) li").click();

        iframe = page.frameLocator("iframe.content-iframe");
    }

    @Test(priority = 1)
    public void testBasicCheckbox() {
        iframe.locator("input[type='checkbox']").first().click();
        System.out.println("Basic checkbox clicked");
    }

    @Test(priority = 2)
    public void testPreCheckedCheckbox() {
        boolean isChecked = iframe.locator("input[name='prechecked']").isChecked();
        Assert.assertTrue(isChecked, "Pre-checked checkbox should be selected");
        System.out.println("Pre-checked checkbox is selected: " + isChecked);
    }

    @Test(priority = 3)
    public void testDisabledCheckbox() {
        Locator checkbox = iframe.locator("input[name='disabled']");
        boolean isDisabled = checkbox.isDisabled();
        Assert.assertTrue(isDisabled, "Checkbox should be disabled");
        System.out.println("Disabled checkbox verified: " + isDisabled);
    }

    @Test(priority = 4)
    public void testSubscribeCheckbox() {
        iframe.locator("input[name='subscribe']").first().click();
        System.out.println("Subscribe checkbox clicked");
    }

    @Test(priority = 5)
    public void testCheckboxEventChange() {
        Locator checkboxevent = iframe.locator("input[name='changeListener']");
        Locator eventStatus = iframe.locator("#eventStatus");

        checkboxevent.check(); // Step 1
        page.waitForTimeout(300);

        Assert.assertEquals(eventStatus.textContent().trim(), "Checkbox is checked.");
        System.out.println("Status after check verified");

        checkboxevent.uncheck(); // Step 2
        page.waitForTimeout(300);

        Assert.assertEquals(eventStatus.textContent().trim(), "Checkbox is unchecked.");
        System.out.println("Status after uncheck verified");
    }
}
