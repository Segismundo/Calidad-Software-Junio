package spaceinvaders.test;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserStoriesDef {

    @Given("^We enter the game$")
    public void weEnterTheGame() {

    }

    @Given("^We make a good score$")
    public void weMakeAGoodScore() {

    }

    @When("^We enter the name \"([^\"]*)\"$")
    public void weEnterTheName(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^We see our ranking in the game$")
    public void weSeeOurRankingInTheGame() {

    }

    @Given("^We make a bad score$")
    public void weMakeABadScore() {

    }

    @Then("^We don't see our ranking in the game$")
    public void weDonTSeeOurRankingInTheGame() {

    }
}
