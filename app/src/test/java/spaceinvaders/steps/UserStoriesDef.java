package spaceinvaders.steps;

import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import spaceinvaders.GameView;
import spaceinvaders.GoodSpaceShip;
import spaceinvaders.MainActivity;
import spaceinvaders.utils.Game;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class UserStoriesDef {

    private Game game;

    @Given("^We open the app$")
    public void weOpenTheApp() {
        this.game = new Game();
        assertNotNull(game);
    }

    @When("^We start the game$")
    public void weStartTheGame() {
        game.start();
        assertEquals(true, game.isStarted());
    }

    @Then("^We see an activity Screen$")
    public void weSeeAnActivityScreen() {
        assertEquals(true, game.isLoaded());
    }

    @When("^We shoot an alien$")
    public void weShootAnAlien() {
        boolean alienLiveOld = game.getInvader().isAlive();
        game.shootInvader();
        boolean alienLiveNew = game.getInvader().isAlive();
        assertNotEquals(alienLiveNew, alienLiveOld);
    }

    @Then("^The alien die$")
    public void theAlienDie(){
        game.killInvader();
        assertEquals(false, game.getInvader().isAlive());
    }

    @Then("^We score some points$")
    public void weScoreSomePoints() throws Throwable {

    }

    @When("^We hit the defense$")
    public void weHitDefense() {
        game.defense(shoot);
        assertEquals(1, game.getLastObjectHit());
    }

    @Then("^Defense should reduce its life$")
    public void defenseReduceLife() throws Throwable {
        int defenseLiveOld = game.defense.getLive();
        game.defense.receiveShot(shoot);
        int defenseLiveNew = game.defense.getLive();
        assertNotSame(defenseLiveNew, defenseLiveOld);
    }

    @When("^Die in the game$")
    public void dieInTheGame() {
        assertEquals(0, game.getLifes());
    }

    @Then("^We enter the name \"([^\"]*)\"$")
    public void weEnterTheName(String arg0) throws Throwable {
        assertTrue(!game.getUserName().isEmpty());
    }

    @And("^We see our ranking in the game$")
    public void weSeeOurRankingInTheGame() throws Throwable {
        game.showRanking();
        assertEquals(true, game.rankingShowed());
    }

}
