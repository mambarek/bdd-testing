package com.sybetech.presentation.bdd.jbehave;

import com.codeborne.selenide.SelenideElement;
import com.sybetech.business.TicTacToeGame;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Acceptance test
 * steps matching those in scenarios in story file
 * works only after deployment
 */
public class JbehaveSteps {

    @BeforeStory
    public void setUp() {
        // call a headless Browser instead of the standard Browser
        System.setProperty("selenide.browser", "htmlunit");
    }

    @Given("user browses to $url")
    public void givenUserBrowsesToHttplocalhost8080tictactoe(String url) {
        open(url);
    }

    @When("first move X is ($x,$y)")
    public void whenFirstMoveXIs11(int x, int y) {
        assertThat(play(x, y), equalTo("X"));
    }

    @When("second move O is ($x,$y)")
    public void whenSecondMoveOIs12(int x, int y) {
        assertThat(play(x, y), equalTo("O"));
    }

    @When("third move X is ($x,$y)")
    public void whenThirdMoveXIs21(int x, int y) {
        assertThat(play(x, y), equalTo("X"));
    }

    @When("fourth move O is ($x,$y)")
    public void whenFourthMoveOIs23(int x, int y) {
        assertThat(play(x, y), equalTo("O"));
    }

    @When("fifth move X is ($x,$y)")
    public void whenFifthMoveXIs31(int x, int y) {
        assertThat(play(x, y), equalTo("X"));
    }

    @Then(" winner is X")
    public void thenWinnerIsX() {
        SelenideElement btn = $(By.name("f:result"));
        String result = btn.getAttribute("value");
        assertThat(result, equalTo(String.format(TicTacToeGame.RESULT_WINNER, "X")));
    }

    private String play(int x, int y) {
        // see how th ui framework set the names and id's
        // JSF sets f:xxx
        String btnName = String.format("f:btn%s_%s", x, y);
        SelenideElement btn = $(By.name(btnName));
        btn.submit();
        // after submit the btn is from jsf controller changed so hold it again
        btn = $(By.name(btnName));
        return btn.getAttribute("value");
    }
}
