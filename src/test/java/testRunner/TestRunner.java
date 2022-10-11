package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/Feature/user.feature"},

glue= {"stepDefs"}
, monochrome = true )


public class TestRunner {

}
