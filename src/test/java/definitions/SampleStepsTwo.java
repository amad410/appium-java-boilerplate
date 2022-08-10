package definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
public class SampleStepsTwo {

    @Given("I enter url address")
    public void i_enter_url_address() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Given");
    }
    @Then("I will access the app")
    public void i_will_access_the_app() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Then");
    }

}
