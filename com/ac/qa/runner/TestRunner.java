package com.ac.qa.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/java/com.ac.qa.features/MyTasks.feature",
		glue = {"src/main/java/com/ac/qa/stepDefinitions/stepsMyTasks.java"},
		monochrome = true,
		plugin = {"pretty", "html:test-output"}
		
		)




public class TestRunner {

}
