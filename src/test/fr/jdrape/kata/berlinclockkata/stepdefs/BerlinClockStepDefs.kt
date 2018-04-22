package fr.jdrape.kata.berlinclockkata.stepdefs;

import org.assertj.core.api.Assertions

import org.springframework.beans.factory.annotation.Autowired

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import fr.jdrape.kata.berlinclockkata.service.BerlinClockService
import fr.jdrape.kata.berlinclockkata.service.BerlinClockServiceImpl

class BerlinClockStepDefs @Autowired constructor(){

    private var berlinClockTime:String?=null;
    private var berlinClockService:BerlinClockService?=null;


    @Given("^I have started the converter$")
    fun I_have_started_the_converter() {
        berlinClockService = BerlinClockServiceImpl();
    }
    
    @When("^I enter ([^\"]*)$")
    fun I_enter_time(time:String) {
        berlinClockTime = berlinClockService?.convertDigitalToBerlin(time);
    }
    
    @Then("^([^\"]*) is returned for the single minutes row$")
    fun row_is_returned_for_the_single_minutes_row( row:String) {
        Assertions.assertThat(berlinClockTime?.substring(20, 24)).isEqualTo(row);
    }
    
    @Then("^([^\"]*) is returned for the five minutes row$")
    fun row_is_returned_for_the_five_minutes_row( row:String) {
        Assertions.assertThat(berlinClockTime?.substring(9, 20)).isEqualTo(row);
    }
    
    @Then("^([^\"]*) is returned for the single hours row$")
    fun _row_is_returned_for_the_single_hours_row( row:String) {
        Assertions.assertThat(berlinClockTime?.substring(5, 9)).isEqualTo(row);
    }
    
    @Then("^([^\"]*) is returned for the five hours row$")
    fun row_is_returned_for_the_five_hours_row( row:String) {
        Assertions.assertThat(berlinClockTime?.substring(1, 5)).isEqualTo(row);
    }
    
    @Then("^([^\"]*) is returned for the seconds lamp$")
    fun lamp_is_returned_for_the_seconds_lamp( lamp:String) {
        Assertions.assertThat(berlinClockTime)
        Assertions.assertThat(berlinClockTime?.substring(0, 1)).isEqualTo(lamp);
    }
    
    @Then("^([^\"]*) is returned$")
    fun clock_is_returned( clock:String) {
        Assertions.assertThat(berlinClockTime).isEqualTo(clock);
    }

}