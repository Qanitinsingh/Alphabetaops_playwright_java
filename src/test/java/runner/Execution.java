package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features/TextBox.feature", glue = {"page.steps"}, plugin = {"com.aventstack.chaintest.plugins.ChainTestCucumberListener:"}, monochrome = true,
        tags = "@SmokeTest"
)
public class Execution extends AbstractTestNGCucumberTests {


}
