package com.bbc.cucumber.stepsdefinitions;

import com.bbc.bbcinfo.BbcSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;


public class StepDefinitions {

    @Steps
    BbcSteps steps;
    static ValidatableResponse response;


    @When("^User sends a GET request to the BBC endpoints$")
    public void userSendsAGETRequestToTheBBCEndpoints() {
        response = steps.getAllMediaTracks();
    }

    @Then("^User must get back a valid status code$")
    public void user_must_get_back_a_valid_status_code() {
        response.statusCode(200);
    }

    @And("^Response time should be below (\\d+) milliseconds$")
    public void responseTimeShouldBeBelowMilliseconds(int statusCode) {
        response.time(lessThan(1000L), TimeUnit.MILLISECONDS);

    }

    @Then("^Verify that the \"([^\"]*)\" field is never null for all the items present in the list$")
    public void verifyThatTheFieldIsNeverNullForAllTheItemsPresentInTheList(String arg0) {
        List<String> ids = response.extract().path("data.id");
        for (String id : ids) {
            Assert.assertFalse(id.isEmpty());
        }


    }

    @And("^the \"([^\"]*)\" field for every track is \"([^\"]*)\"$")
    public void theFieldForEveryTrackIs(String arg0, String arg1) {
        List<String> segmentTypesList = response.extract().path("data.segment_type");
        for (String segmentType : segmentTypesList) {
            Assert.assertEquals("music", segmentType);
        }
    }

    @Then("^Verify that the \"([^\"]*)\" field in the \"([^\"]*)\" is never empty for any track$")
    public void verifyThatTheFieldInTheIsNeverEmptyForAnyTrack(String arg0, String arg1) {
        List<String> primaryFields = response.extract().path("data.title_list.primary");
        for (String primary : primaryFields) {
            Assert.assertFalse(primary.isEmpty());
        }
    }

    @Then("^Verify that only one track in the list has \"([^\"]*)\" field in \"([^\"]*)\" as true$")
    public void verifyThatOnlyOneTrackInTheListHasFieldInAsTrue(String arg0, String arg1) {
        response.body("data.offset.now_playing", hasItem(true));
    }

    @Then("^Verify the \"([^\"]*)\" value in the response headers$")
    public void verifyTheValueInTheResponseHeaders(String arg0) {
        response.header("date", "Date");
    }
}
