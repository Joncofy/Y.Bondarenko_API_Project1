package project1.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import project1.pojo.Properties_POJO;

import static io.restassured.RestAssured.*;
import java.util.*;

public class C1_OpenLower_StepDef {

    static String baseUrl;
    static List<String> filteredSymbols = new ArrayList<>();

    @Given("The endpoint is {string}")
    public void the_endpoint_is(String url) {
    baseUrl = url;

    }

    @When("I filter symbols whose open price is less than low price")
    public void i_filter_symbols_whose_open_price_is_less_than_low_price() {
        Response response = given().accept(ContentType.JSON)
                .when().get(baseUrl)
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Properties_POJO> responseList = jsonPath.getList("",Properties_POJO.class);

        for (Properties_POJO each:responseList){
            if(Double.parseDouble(each.getOpenPrice()) < Double.parseDouble(each.getLowPrice())){
                filteredSymbols.add(each.getSymbol());
            }
        }
    }

    @When("I verify that the response contains the filtered symbols")
    public void i_verify_that_the_response_contains_the_filtered_symbols() {

        for (int i = 0; i < filteredSymbols.size(); i++) {

            System.out.println((i+1)+". filteredSymbols.get(i) = " + filteredSymbols.get(i));
        }
    }
}
