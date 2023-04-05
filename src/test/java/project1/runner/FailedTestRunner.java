package project1.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "project1/step_definitions",
        features = "@target/rerun.txt"
)
public class FailedTestRunner {

}