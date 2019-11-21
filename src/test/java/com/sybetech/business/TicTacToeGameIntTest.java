package com.sybetech.business;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
/**
 * Demo for integration test
 * - tests should run fast. Therefore test only what is not yet unit tested
 * - Requires MongoDB running at default port 27017.
 */
@Ignore
public class TicTacToeGameIntTest {
    /****************************************************************************************
     * BP2: integrate real MongoDb.
     ****************************************************************************************/

    /**
     * 1: interface to MongoDb (drop and save) should work
     */
    @Test
    public void givenMongoDbIsUp_WhenPlay_ThenDropAndSaveWork() {
        TicTacToeGame game = new TicTacToeGame();
        TicTacToeGameMove move1 = new TicTacToeGameMove(1,'X',1,1);
        TicTacToeGameMove move2 = new TicTacToeGameMove(2,'X',1,2);

        game.play(move1);
        game.play(move2);

        TicTacToeGameState state = new TicTacToeGameState();
        TicTacToeGameMove persistedMove1 = state.findById(move1.getId());
        TicTacToeGameMove persistedMove2 = state.findById(move2.getId());

        assertThat(persistedMove1.getId(), equalTo(move1.getId()));
        assertThat(persistedMove2.getId(), equalTo(move2.getId()));
    }
}
