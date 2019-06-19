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
import static org.junit.Assert.assertNull;


public class UserStoriesDef {

    private MainActivity mainActivity;
    private Game game;
    private int fleetSize;
    private int points;

    @Given("^We open the app$")
    public void weOpenTheApp() {
        game = new Game();
    }

    @When("^We start the game$")
    public void weStartTheGame() {
        onView(isDisplayed()).perform(click());
        fleetSize = view.getInvaderSpaceFleet().getInvaderSpaceShips().size();
        points = view.getScoreValue();
    }

    @Then("^We see an activity Screen$")
    public void weSeeAnActivityScreen() {
        int state = view.getGameState();
        assertEquals(2, state);
    }

    @When("^We shoot an alien$")
    public void weShootAnAlien() {
        while (view.getScoreValue() == 0) {
            onView(isDisplayed()).perform(click());
        }
    }

    @Then("^The alien die$")
    public void theAlienDie() throws Throwable {
        assertNotEquals(fleetSize,view.getInvaderSpaceFleet().getInvaderSpaceShips().size());
    }

    @Then("^We score some points$")
    public void weScoreSomePoints() throws Throwable {
        assertNotEquals(points,view.getScoreValue());
    }

    @When("^We press back button$")
    public void wePressBackButton() {
        onView(isDisplayed()).perform(pressBack());
    }

    @Then("^We should exit the game$")
    public void weShouldExitTheGame() throws Throwable {
        assertNull(mainActivity);
    }

    @When("^Die in the game$")
    public void dieInTheGame() {
        GoodSpaceShip ship = view.getGoodSpaceShip();
        ship.setAlive(false);
    }

    @Then("^We enter the name \"([^\"]*)\"$")
    public void weEnterTheName(String arg0) throws Throwable {
        int state = view.getGameState();
        assertEquals(3, state);

        //Insert name for user
        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.custom),
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        editText.perform(replaceText(arg0), closeSoftKeyboard());

        //Click on button OK
        ViewInteraction button = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        button.perform(click());
    }

    @And("^We see our ranking in the game$")
    public void weSeeOurRankingInTheGame() throws Throwable {
        int state = view.getGameState();
        assertEquals(5, state);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
