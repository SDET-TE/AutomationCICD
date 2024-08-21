package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber -> depends on either TestNG, Junit runner for execution
@CucumberOptions(features="src/test/java/cucumber",glue="AutomationFramework.stepDefinitions",
monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	

}
