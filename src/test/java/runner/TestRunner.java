package runner;


import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features"}, glue = {"stepDefinition"},
        plugin={"html:target/result/cucumber-html-report.html","json:target/result/cucumber.json", "pretty:target/result/cucumber-pretty.txt",
        "usage:target/result/cucumber-usage.json","junit:target/result/cucumber-results.xml"}
    )


public class TestRunner {
}
