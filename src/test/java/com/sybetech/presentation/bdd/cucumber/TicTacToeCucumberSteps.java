package com.sybetech.presentation.bdd.cucumber;


import com.sybetech.business.TicTacToeGame;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Acceptance test
 * steps matching those in scenarios in feature file
 * works only after deployment
 */
public class TicTacToeCucumberSteps {

//	WebDriver driver;
    private WebDriver driver;

    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }

    @Given("^user browses to (.*)$")
    public void user_browses_to_http_localhost_tictactoe(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.driver.get(url);
    }

    @When("^first move X is \\((\\d+),(\\d+)\\)$")
    public void first_move_X_is(int x, int y) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(play(x, y), equalTo("X"));
    }

    @When("^second move O is \\((\\d+),(\\d+)\\)$")
    public void second_move_O_is(int x, int y) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(play(x, y), equalTo("O"));
    }

    @When("^third move X is \\((\\d+),(\\d+)\\)$")
    public void third_move_X_is(int x, int y) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(play(x, y), equalTo("X"));
    }

    @When("^fourth move O is \\((\\d+),(\\d+)\\)$")
    public void fourth_move_O_is(int x, int y) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(play(x, y), equalTo("O"));
    }

    @When("^fifth move X is \\((\\d+)\\.(\\d+)\\)$")
    public void fifth_move_X_is(int x, int y) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(play(x, y), equalTo("X"));
    }

    @Then("^winner is X$")
    public void winner_is_X() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement btn = this.driver.findElement(By.name("f:result"));
        String result = btn.getAttribute("value");
        assertThat(result, equalTo(String.format(TicTacToeGame.RESULT_WINNER, "X")));
    }

    private String play(int x, int y) {
        // see how th ui framework set the names and id's
        // JSF sets f:xxx
        String btnName = String.format("f:btn%s_%s", x, y);
        WebElement btn = this.driver.findElement(By.name(btnName));
        btn.submit();
        // after submit the btn is from jsf controller changed so hold it again
        btn = this.driver.findElement(By.name(btnName));
        return btn.getAttribute("value");
    }
}
