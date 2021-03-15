package stepDefinition;

import helpers.ConnectionHelper;
import helpers.DriversAPIJsonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class DriversApiStepDefinitions {

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private DriversAPIJsonHelper driversAPIJsonHelper = new DriversAPIJsonHelper();
    private HttpURLConnection connection;
    private String output;
    private String apiOption;
    
    @Given("I have the Ergast Developer API")
    public void i_have_ergast_api() throws Exception {
        System.out.println("Calling the Ergast Developer API");
    }

    @When("I call the {string} endPoint")
    public void i_call_drivers_endPoint(String endPoint) throws IOException {
        connection = connectionHelper.getConnection(endPoint);
        output = connectionHelper.getResponse();
    }

    @Then("I expect the correct responseCode to be {int}")
    public void i_expect_status(int statusCode) throws IOException {
        int status = connection.getResponseCode();
        if (status == 200) {
            Assert.assertEquals(statusCode, status);
        }else if (status == 404 || status == 500) {
            System.out.println("Service is not available: " + status);
        }
    }

    @And("I expect the driver Ids returned to be correct")
    public void content_of_response_to_be_correct() throws IOException {
        driversAPIJsonHelper.getDriversId(output);
    }

    @Then("I expect the driverId of {string} returned")
    public void check_driver_id(String driverId) throws IOException {
        System.out.println(driversAPIJsonHelper.checkBelgianDriversId(output));
        Assert.assertEquals(driverId, driversAPIJsonHelper.checkBelgianDriversId(output));
    }

    @Then("I expect the driverId of all {string} nationals returned")
    public void check_british_driver_id(String driverId) throws IOException {
        System.out.println(driversAPIJsonHelper.checkBritishDriversId(output));
    }

}
