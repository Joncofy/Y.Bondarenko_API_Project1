package project1.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import project1.utilities.ConfigurationReader;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;


public class C5_BadRequest_StepDef {

    static Response response;

    @When("I send a DELETE request to the API")
    public void i_send_a_delete_request_to_the_api() {
       response = given()
               .when()
               .delete(ConfigurationReader.getProperty("baseURL")).prettyPeek()
               .then().statusCode(403).extract().response();
    }

    @Then("the response status code should be {int} Forbidden")
    public void the_response_status_code_should_be_forbidden(Integer statusCode) {

       assertEquals(statusCode,response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
    }
}
