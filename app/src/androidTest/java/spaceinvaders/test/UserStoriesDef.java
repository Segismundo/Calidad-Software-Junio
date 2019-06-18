package spaceinvaders.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import spaceinvaders.MainActivity;

import static org.junit.Assert.assertNotNull;

public class UserStoriesDef {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Activity activity;

    @Given("^We open the app$")
    public void weOpenTheApp() {
        assertNotNull(activity);
    }

    @When("^We start the game$")
    public void weStartTheGame() {
    }

    @Then("^We see an activity Screen$")
    public void weSeeAnActivityScreen() {
    }

    @When("^We shoot an alien$")
    public void weShootAnAlien() {

    }

    @Then("^The alien die$")
    public void theAlienDie() {

    }

    @Then("^We score some points$")
    public void weScoreSomePoints() {

    }

    @When("^We press back button$")
    public void wePressBackButton() {

    }

    @Then("^We should exit the game$")
    public void weShouldExitTheGame() {

    }

    @When("^Die in the game$")
    public void dieInTheGame() {

    }

    @Then("^We enter the name \"([^\"]*)\"$")
    public void weEnterTheName(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^We see our ranking in the game$")
    public void weSeeOurRankingInTheGame() {
    }
}
