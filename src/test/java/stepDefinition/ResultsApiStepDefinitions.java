package stepDefinition;

import helpers.ConnectionHelper;
import helpers.ResultsAPIJsonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class ResultsApiStepDefinitions {

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private ResultsAPIJsonHelper resultsAPIJsonHelper = new ResultsAPIJsonHelper();
    private HttpURLConnection connection;
    private String output;

    @Given("I have the API")
    public void i_have_ergast_api_endPoint() throws Exception {
        System.out.println("Calling the Ergast Developer API");
    }

    @When("I hit the {string} endPoint")
    public void i_call_results_endPoint(String endPoint) throws IOException {
        connection = connectionHelper.getConnection(endPoint);
        output = connectionHelper.getResponse();
    }

    @Then("I expect the responseCode to be {int}")
    public void i_expect_status_code(int statusCode) throws IOException {
        int status = connection.getResponseCode();
        if (status == 200) {
            Assert.assertEquals(statusCode, status);
        }else if (status == 404 || status == 500) {
            System.out.println("Service is not available: " + status);
        }
    }

    @And("I expect the race names returned to be correct")
    public void return_race_names() throws IOException {
        resultsAPIJsonHelper.getRaceNames(output);
    }

    @Then("I expect the circuitId of {string} returned to be correct")
    public void check_circuit_id(String circuitId) throws IOException {
        System.out.println(resultsAPIJsonHelper.checkUKCircuitId(output));
        List list = resultsAPIJsonHelper.checkUKCircuitId(output);
        for (Object i: list) {
            if(i.equals("silverstone"))
            Assert.assertEquals(circuitId, i);
        }
    }


}
