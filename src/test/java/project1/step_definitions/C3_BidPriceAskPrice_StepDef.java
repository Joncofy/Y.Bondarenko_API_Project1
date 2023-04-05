package project1.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import project1.pojo.Properties_POJO;
import project1.utilities.ConfigurationReader;
import java.util.*;

import static io.restassured.RestAssured.given;

public class C3_BidPriceAskPrice_StepDef {

    static Map<String,Double> diff = new HashMap<>();
    static Map<String, Double> sortedMap = new LinkedHashMap<>();

    @When("I calculate the difference between bidPrice and askPrice for each asset symbol")
    public void iCalculateTheDifferenceBetweenBidPriceAndAskPriceForEachAssetSymbol() {
        Response response = given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("baseURL"))
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Properties_POJO> responseList = jsonPath.getList("",Properties_POJO.class);

        for (Properties_POJO each:responseList){
            diff.put(each.getSymbol(),Double.parseDouble(each.getAskPrice())-Double.parseDouble(each.getBidPrice()));
        }
    }

    @And("I sort the assets symbols based on the differences in ascending order")
    public void iSortTheAssetsSymbolsBasedOnTheDifferencesInAscendingOrder() {

        List<Map.Entry<String, Double>> list = new ArrayList<>(diff.entrySet());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getValue() > list.get(j + 1).getValue()) {
                    Map.Entry<String, Double> temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
    }

    @And("I select the top {int} assets symbols with the least differences")
    public void iSelectTheTopAssetsSymbolsWithTheLeastDifferences(int top5) {
        int count = 0;
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count++;
            if (count == top5) {
                break;
            }
        }
    }
}
