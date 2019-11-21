package com.sybetech.presentation.bdd.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * CucumberRunner: parse features, run CucumberSteps and generate reports
 */
@RunWith(Cucumber.class)
@CucumberOptions(
	features = {"stories"}, // Features path
	glue = {"com.sybetech.presentation.bdd.cucumber"}, // Steps package
	plugin = {"pretty", "html:target/cucumber"} // Reports output path
	)
public class CucumberRunner {
}
