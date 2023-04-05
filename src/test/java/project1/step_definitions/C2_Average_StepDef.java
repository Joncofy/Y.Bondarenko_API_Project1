package project1.step_definitions;

import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import project1.pojo.Properties_POJO;
import project1.utilities.ConfigurationReader;
import java.util.List;
import static io.restassured.RestAssured.given;

public class C2_Average_StepDef {

    static double average;

    @When("I calculate the average of all volumes in the list")
    public void i_calculate_the_average_of_all_volumes_in_the_list() {
        Response response = given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("baseURL"))
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Properties_POJO> responseList = jsonPath.getList("",Properties_POJO.class);

        for (Properties_POJO each:responseList){
           average+=Double.parseDouble(each.getVolume());
        }
         average= average / responseList.size();

    }
    @When("I filter symbols and base assets whose volume is more than the calculated average")
    public void i_filter_symbols_and_base_assets_whose_volume_is_more_than_the_calculated_average() {
        Response response = given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("baseURL"))
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Properties_POJO> responseList = jsonPath.getList("",Properties_POJO.class);

        int count =1;
        for (Properties_POJO each:responseList){
            if(Double.parseDouble(each.getVolume()) > average){
                System.out.println(count+".symbol = " + each.getSymbol()+" | baseAssert = "+each.getBaseAsset());
                count++;
            }
        }
    }
}
