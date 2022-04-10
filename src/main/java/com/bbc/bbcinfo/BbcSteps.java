package com.bbc.bbcinfo;


import com.bbc.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BbcSteps {

    @Step
    public ValidatableResponse getAllMediaTracks(){
        return SerenityRest.rest()
                .given().log().ifValidationFails()
                .when()
                .get(EndPoints.GET_MUSIC_TRACKS)
                .then();
    }
}
