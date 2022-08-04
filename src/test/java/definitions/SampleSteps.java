package definitions;

import Pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SampleSteps {
    MainPage mainPage;
  /*  @Given("Click of a button")
    public void click_of_a_button()  {
        //add POM methods here
        System.out.println("Given");
    }*/

    @Given("Click of a button")
    public void clickOfAButton() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Given");
    }

    @Then("I will gain a new experience")
    public void iWillGainANewExperience() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Then");
    }

   /* @Then("I will gain a new experience")
    public void i_will_gain_a_new_experience() {
        //add POM methods here
        System.out.println("Then");
    }*/

}
