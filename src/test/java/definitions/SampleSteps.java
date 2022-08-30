package definitions;

import Pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SampleSteps {
    MainPage mainPage;

    @Given("Click of a button")
    public void clickOfAButton() {
        System.out.println("Given");
    }

    @Then("I will gain a new experience")
    public void iWillGainANewExperience() {
        System.out.println("Then");
    }

}
