package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SampleSteps {

    @Given("Click of a button")
    public void click_of_a_button()  {
        //add POM methods here
        System.out.println("Given");
    }

    @Then("I will gain a new experience")
    public void i_will_gain_a_new_experience() {
        //add POM methods here
        System.out.println("Then");
    }

}
