package com.sybetech.presentation;

import com.sybetech.business.TicTacToeGame;
import com.sybetech.business.TicTacToeGameMove;
import com.sybetech.business.TicTacToeGameState;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * E2E Test.
 * Works only after deployment
 */
@Ignore
public class TicTacToeGameE2ETest {

    private WebDriver driver;

    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
    }

    @Test
    public void whenPlayAndWholeHorizontalLineFilled_ThenWinner() throws Exception {
        this.driver.get(Constants.APP_URL);
        assertThat(this.driver.getTitle(), equalTo(Constants.APP_TITLE));

        assertThat(play(1, 1), equalTo("X"));
        TicTacToeGameState db = new TicTacToeGameState();
        TicTacToeGameMove persistedMove1 = db.findById(1);
        TicTacToeGameMove expectedMove1 = new TicTacToeGameMove(1,'X',1,1);
        assertThat(persistedMove1, equalTo(expectedMove1));

        assertThat(play(1, 2), equalTo("O"));
        TicTacToeGameMove persistedMove2 = db.findById(2);
        TicTacToeGameMove expectedMove2 = new TicTacToeGameMove(2,'O',1,2);
        assertThat(persistedMove2, equalTo(expectedMove2));

		assertThat(play(2, 1), equalTo("X"));
        TicTacToeGameMove persistedMove3 = db.findById(3);
        TicTacToeGameMove expectedMove3 = new TicTacToeGameMove(3,'X',2,1);
        assertThat(persistedMove3, equalTo(expectedMove3));

        assertThat(play(2, 3), equalTo("O"));
        TicTacToeGameMove persistedMove4 = db.findById(4);
        TicTacToeGameMove expectedMove4 = new TicTacToeGameMove(4,'O',2,3);
        assertThat(persistedMove4, equalTo(expectedMove4));

        assertThat(play(3, 1), equalTo("X"));
        TicTacToeGameMove persistedMove5 = db.findById(5);
        TicTacToeGameMove expectedMove5 = new TicTacToeGameMove(5,'X',3,1);
        assertThat(persistedMove5, equalTo(expectedMove5));


        // see how th ui framework set the names and id's
		// JSF sets f:xxx
		WebElement btn = this.driver.findElement(By.name("f:result"));
		String result = btn.getAttribute("value");
		assertThat(result, equalTo(String.format(TicTacToeGame.RESULT_WINNER, "X")));
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
