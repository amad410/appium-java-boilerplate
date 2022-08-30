package definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
public class SampleStepsTwo {

    @Given("I enter url address")
    public void i_enter_url_address() {
        System.out.println("Given");
    }
    @Then("I will access the app")
    public void i_will_access_the_app() {
        System.out.println("Then");
    }

    @Given("I click on a tab")
    public void iClickOnATab() {
        System.out.println("Given");
    }
    @Then("the tab will open")
    public void theTabWillOpen() {
        System.out.println("Then");
    }

}
