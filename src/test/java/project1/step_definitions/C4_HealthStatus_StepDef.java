package project1.step_definitions;

import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import project1.utilities.ConfigurationReader;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class C4_HealthStatus_StepDef {

    @When("the response status code should be {int} OK")
    public void the_response_status_code_should_be_ok(Integer statusCode) {
                given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("baseURL"))
                .then().statusCode(is(statusCode));
    }

    @When("the response time should be less than {int} milliseconds")
    public void the_response_time_should_be_less_than_milliseconds(Integer time) {
        given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("baseURL"))
                .then().time(lessThan(Long.valueOf(time)));
    }

    @When("the response should be in {string} valid JSON format")
    public void the_response_should_be_in_valid_json_format(String jSOn) {
        given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("baseURL"))
                .then().header("Content-Type", matchesRegex(jSOn));
    }
}
