package fr.jdrape.kata.berlinclockkata


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber::class)
@CucumberOptions(features = arrayOf("src/test/resources/features"), plugin = arrayOf( "pretty", "html:target/cucumber-reports/cucumber-pretty",
        "json:target/cucumber-reports/CucumberTestReport.json", "rerun:target/cucumber-reports/rerun.txt" ))
class RunCucumberTest {
}