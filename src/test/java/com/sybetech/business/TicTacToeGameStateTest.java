package com.sybetech.business;

import com.mongodb.MongoException;
import org.jongo.FindOne;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Demo for mocking framework Mockito
 * - spy (partial mocking): real object using real methods, unless specified otherwise
 */
@RunWith(MockitoJUnitRunner.class) // creates required mocks and inject them in the test class
//@Ignore
public class TicTacToeGameStateTest {
    /*****************************************************************************************
     * R5: use MongoDB as persistent storage for the game state.
     ****************************************************************************************/

    /**
     * R5.1: implement bean for storage representation TicTacToeGameState
     */

    private TicTacToeGameState state;
    private MongoCollection mongoCollection;
    private TicTacToeGameMove move;

    @Before
    public void setUp() throws UnknownHostException {
        // init move
        this.move = new TicTacToeGameMove(1, 'X', 1, 2);

        // use method spy() to partial mock class to test
        this.state = spy(TicTacToeGameState.class);

        // use method mock(Class) to mock db dependency mongoCollection (all methods are shallow (Empty)).
        this.mongoCollection = mock(MongoCollection.class);

    }

    /**
     * R5.2: specify DB-Name
     */
    @Test
    public void whenInstantiated_ThenMongoHasDbName() throws Exception {
        assertThat(this.state.getMongoCollection().getDBCollection().getDB().getName(),
                equalTo(TicTacToeGameState.DB_NAME));
    }

    /**
     * R5.2: specify the mongo collection-name
     */
    @Test
    public void whenInstantiated_ThenMongoCollectionHasName() throws Exception { ;
        assertThat(this.state.getMongoCollection().getName(),
                equalTo(TicTacToeGameState.COLLECTION_NAME));
    }

    /**
     * R5.3: save data to mongo-db and return true
     * use doReturn(mockObj).when(classToMock).methodToMock(), verify()
     */
    @Test
    public void whenSave_ThenInvokeMongoCollectionSaveAndReturnTrue() throws Exception {
        // set mock mongoCollection when call state.getMongoCollection()
        doReturn(this.mongoCollection).when(this.state).getMongoCollection();
        boolean result = this.state.save(this.move);
        assertThat(result, equalTo(true));
    }

    /**
     * R5.4: return false if MongoException occurs while saving
     * use doReturn, doThrow
     */
    @Test
    public void givenMongoException_WhenSave_ThenReturnFalse() {
        // set mock mongoCollection when call state.getMongoCollection()
        doReturn(this.mongoCollection).when(this.state).getMongoCollection();
        doThrow(new MongoException("Saving fail")).when(this.mongoCollection).save(this.move);
        boolean result = this.state.save(this.move);
        assertThat(result, equalTo(false));
    }

    /**
     * R5.5: specify droping data
     */
    @Test
    public void whenClear_ThenInvokeMongoCollectionDrop() {
        // set mock mongoCollection when call state.getMongoCollection()
        doReturn(this.mongoCollection).when(this.state).getMongoCollection();
        boolean result = this.state.clear();
        assertThat(result, equalTo(true));
        verify(this.mongoCollection, times(1)).drop();
    }

    /**
     * R5.6: return false if MongoException occurs while dropping
     * use doReturn, doThrow
     * check when exception occurs during clear, then clear returns false
     */
    @Test
    public void givenMongoException_WhenClear_ThenReturnFalse() {
        doReturn(this.mongoCollection).when(this.state).getMongoCollection();
        doThrow(new MongoException("Clear fail")).when(this.mongoCollection).drop();
        boolean result = this.state.clear();
        assertThat(result, equalTo(false));
    }

    @Test
    public void save() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void whenFindById_ThenInvokeMongoDBCollectionFindOneAndReturnMove() {
        // mock (check behavior)
/*        doReturn(this.mongoCollection).when(this.state).getMongoCollection();

        // stubbing (change state / result values)
        FindOne findOne = mock(FindOne.class);
        doReturn(findOne).when(this.mongoCollection).findOne("{_id:#}", 1);*/
        doReturn(this.move).when(this.state).findById(1);

        TicTacToeGameMove result = this.state.findById(1);
        assertThat(result.getId(), equalTo(1));
    }

    @Test
    public void getMongoCollection() {
    }
}
