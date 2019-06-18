package spaceinvaders.test;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import spaceinvaders.GameView;
import spaceinvaders.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserStoriesDef {

    private MainActivity activity;
    private GameView view;



    @Given("^We open the app$")
    public void weOpenTheApp() {
        activity = new MainActivity();
        view = activity.getGameView();
    }

    @When("^We start the game$")
    public void weStartTheGame() {
        onView(isDisplayed()).perform(click());
    }

    @Then("^We see an activity Screen$")
    public void weSeeAnActivityScreen() {
        int state = view.getGameState();
        assertEquals(2,state);
    }

    @When("^We shoot an alien$")
    public void weShootAnAlien() {
        onView(isDisplayed()).perform(click());
    }

    @Then("^The alien die$")
    public void theAlienDie() {

    }

    @Then("^We score some points$")
    public void weScoreSomePoints() {

    }

    @When("^We press back button$")
    public void wePressBackButton() {
        onView(isDisplayed()).perform(pressBack());
    }

    @Then("^We should exit the game$")
    public void weShouldExitTheGame() {
        assertNull(activity);
    }

    @When("^Die in the game$")
    public void dieInTheGame() {

    }

    @Then("^We enter the name \"([^\"]*)\"$")
    public void weEnterTheName(String arg0) throws Throwable {
        int state = view.getGameState();
        assertEquals(3,state);


        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^We see our ranking in the game$")
    public void weSeeOurRankingInTheGame() {
        int state = view.getGameState();
        assertEquals(5,state);
    }
}
