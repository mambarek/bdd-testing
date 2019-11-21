package com.sybetech.business;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.FindOne;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class TicTacToeGameState {

    static final String DB_NAME = "tic-tac-toe-db";
    static final String COLLECTION_NAME = "tic-tac-toe-collection";
    private MongoCollection mongoCollection; // CRUD

    public TicTacToeGameState() {
        // use new Jongo (MongoClient().getDB).getCollection to initialize mongoCollection
        this.mongoCollection = new Jongo(new MongoClient().getDB(DB_NAME)).getCollection(COLLECTION_NAME);
    }

    public boolean save(TicTacToeGameMove move) {
        try {
            this.getMongoCollection().save(move);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            this.getMongoCollection().drop();
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public TicTacToeGameMove findById(int id) {
        FindOne one = this.getMongoCollection().findOne("{_id: #}", id);
        if(one == null) return null;
        TicTacToeGameMove as = one.as(TicTacToeGameMove.class);// see jongo on jongo.org
        return as;
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

}
