package com.sybetech.presentation;

import com.sybetech.business.TicTacToeGame;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Performance Test.
 * Works only after deployment
 */
@Ignore
public class TicTacToeGamePT {

    private WebDriver driver;

    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
    }

    @Test
    public void stressTest() {
        System.out.println("Testing 1000 rounds. Begin at:" + new Date());
        this.driver.get(Constants.APP_URL);
        assertThat(this.driver.getTitle(), equalTo(Constants.APP_TITLE));

        for (int i = 0; i < 1000; i++) {
            playAndCheckARound();
        }

        System.out.println("Testing 1000 rounds. End at:" + new Date());
    }

    private void playAndCheckARound() {
        assertThat(play(1, 1), equalTo("X"));
		assertThat(play(1, 2), equalTo("O"));
		assertThat(play(2, 1), equalTo("X"));
		assertThat(play(2, 3), equalTo("O"));
		assertThat(play(3, 1), equalTo("X"));

		// see how th ui framework set the names and id's
		// JSF sets f:xxx
		WebElement btn = this.driver.findElement(By.name("f:result"));
		String result = btn.getAttribute("value");
		assertThat(result, equalTo(String.format(TicTacToeGame.RESULT_WINNER, "X")));

        WebElement resetBtn = this.driver.findElement(By.name("f:reset"));
        resetBtn.submit();
	}

	// TODO test vertical and 2 diagonals
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
