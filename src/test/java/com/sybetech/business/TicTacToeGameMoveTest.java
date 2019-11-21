package com.sybetech.business;

import org.junit.*;
import org.junit.rules.TestName;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * JUnit Demo
 * use assertEquals, assertTrue (with and without message
 */
public class TicTacToeGameMoveTest {

    private TicTacToeGameMove move;
    private final int id = 3;
    private final int x = 1;
    private final int y = 1;
    private final char player = 'X';

    // https://github.com/junit-team/junit4/wiki/rules (JUnit interceptors)
    @Rule
    public final TestName name = new TestName();

    @BeforeClass
    public static void beforeClass() {
        // called once on class initialization time
    }

    @AfterClass
    public static void afterClass() {
        // called once after all methods are executed
    }

    @Before
    // the name is as you want you can call it setUp or somthing like this
    public void before() {
        // called before each method execution
        // init a move
        this.move = new TicTacToeGameMove(id,player,x,y);
    }

    @After
    public void cleanUpMethod() {
        // called after each method execution
        this.move = null;
    }

    // check id is set after instantiation
    @Test
    public void whenInstantiated_ThenIdIsSet() {
        assertEquals("Assert that id is set in constructor",3, this.move.getId());
        // you can print the method name
        System.out.println(name.getMethodName());
    }

    // check x is set after instantiation
    @Test
    public void whenInstantiated_ThenXIsSet() {
        assertEquals("Assert that x is set in constructor",1, this.move.getX());
    }

    // check y is set after instantiation
    @Test
    public void whenInstantiated_ThenYIsSet() {
        assertEquals("Assert that y is set in constructor",1, this.move.getY());
    }

    // check player is set after instantiation
    @Test
    public void whenInstantiated_ThenPlayerIsSet() {
        assertEquals("Assert that player is set in constructor",'X', this.move.getPlayer());
    }

    // check allowedChars are O and X after instantiation
    @Test
    public void whenInstantiated_ThenAllowedCharsAreOandX() {
        List<Character> expectedCharacter = Arrays.asList('O', 'X');
        assertTrue("Assert that allowedChars are 'O' or 'X",
                expectedCharacter.containsAll(this.move.getAllowedChars()));
        assertEquals(2, this.move.getAllowedChars().size());
    }

    // check Rule TestName.getMethodName
    //@Test
    public void whenGetMethodNameOfTestNameRuleInvoked_ThenReturnNameOfThisTest() {

    }

}
