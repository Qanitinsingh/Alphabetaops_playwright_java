package novoice.alphabetaops.playwright;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBox extends BaseClass {

    @Test
    public void handleCheckBox() {
        page.navigate("https://alphabetaops.com/");

        page.locator(".mb-2:first-child").click();
        page.locator(".mb-2:first-child ul.list-group li.list-group-item:nth-of-type(2)").click();
        page.locator(".mb-2:first-child ul.list-group li.list-group-item:nth-of-type(2) li").click();

        FrameLocator iframe = page.frameLocator("iframe.content-iframe");

        iframe.locator("input[type='checkbox']").first().click();

        System.out.println("Basic CheckBox handled successfully");
        boolean isChecked = iframe.locator("input[name='prechecked']").isChecked();
        System.out.println("Pre-checked CheckBox handled successfully" + isChecked);

        Locator checkbox = iframe.locator("input[name='disabled']");
        boolean isDisabled = checkbox.isDisabled();
        System.out.println("subscribe CheckBox handled successfully" + isDisabled);

        iframe.locator("input[name='subscribe']").first().click();
        System.out.println("subscribe CheckBox handled successfully");


        //
        Locator checkboxevent = iframe.locator("input[name='changeListener']");
        Locator eventStatus = iframe.locator("#eventStatus");

        //  Step 1: click to check (first time)
        checkboxevent.check();  // ensures checkbox becomes checked (recommended over click)

        // Verify event status text after checking
        page.waitForTimeout(500); // allow browser JS event to update
        String statusAfterCheck = eventStatus.textContent().trim();
        System.out.println("Status after check: " + statusAfterCheck);

        Assert.assertEquals(statusAfterCheck, "Checkbox is checked.");

        //  Step 2: uncheck the checkbox
        checkboxevent.uncheck(); // ensures checkbox becomes unchecked

        //  Verify event status text after unchecking
        page.waitForTimeout(500);
        String statusAfterUncheck = eventStatus.textContent().trim();
        System.out.println("Status after uncheck: " + statusAfterUncheck);

        Assert.assertEquals(statusAfterUncheck, "Checkbox is unchecked.");


    }
}
