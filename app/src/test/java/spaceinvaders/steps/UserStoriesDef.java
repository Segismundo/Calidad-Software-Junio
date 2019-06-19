package spaceinvaders.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import spaceinvaders.utils.Game;
import spaceinvaders.utils.SmokeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserStoriesDef {

    private Game game;

    @SmokeTest
    @Given("^We open the app$")
    public void weOpenTheApp() {
        this.game = new Game();
        //assertNotNull(game);
    }

    @SmokeTest
    @When("^We start the game$")
    public void weStartTheGame() {
        game.start();
    }

    @SmokeTest
    @Then("^We see an activity Screen$")
    public void weSeeAnActivityScreen() {
        assertTrue(game.isStarted());
    }

    @SmokeTest
    @When("^We shoot an alien$")
    public void weShootAnAlien() {
        game.shootInvader();
        //assertNotNull(game.getGoodSpaceShipShoot());
    }

    @SmokeTest
    @Then("^The alien die$")
    public void theAlienDie(){
        assertFalse(game.getInvader().isAlive());
    }

    @Then("^We score some points$")
    public void weScoreSomePoints(){
        assertTrue(0<game.getScoreValue());
    }

    @When("^We hit the shield$")
    public void weHitShield() {
        game.shootShield();
    }

    @Then("^Our shoot should disappear$")
    public void ourShootShouldDisappear(){
        assertFalse(game.getGoodSpaceShipShoot().isAlive());
    }

    @SmokeTest
    @When("^Die in the game$")
    public void dieInTheGame() {
        game.alienShoot();
    }

    @SmokeTest
    @Then("^We see our ranking in the game$")
    public void weSeeOurRankingInTheGame(){
        assertEquals(5, game.getGameState());
    }

}
