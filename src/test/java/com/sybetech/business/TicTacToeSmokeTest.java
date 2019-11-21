package com.sybetech.business;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static junit.framework.TestCase.fail;

// System does not smoke after invocation
@Ignore
public class TicTacToeSmokeTest {

    //@Test
    public void test() {
        //fail("Not yet implemented");
    }

    @Test
    public void whenDeployed_ThenWebsiteIsReachable() throws IOException {
        URL url = new URL("http://localhost:8080/tictactoe");
        URLConnection urlConnection = url.openConnection();
        assertThat(urlConnection.getContent(), notNullValue());
    }

    @Test
    public void whenDeployed_ThenDatabaseIsReachable() {
        TicTacToeGameState state = new TicTacToeGameState();
        state.findById(1);
    }
}
