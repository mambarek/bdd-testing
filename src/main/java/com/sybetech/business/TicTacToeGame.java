package com.sybetech.business;

import java.util.Objects;

public class TicTacToeGame {
    private static final char UNOCCUPIED = '\0';

    public static final String RESULT_IN_PROGRESS = "In progress";
    static final String RESULT_DRAW = "Draw";
    public static final String RESULT_WINNER = "Winner is %s";

    public static final String COORDINATE_ERR_MSG = "Coordinate must be between 1 and 3";
    public static final String FIELD_OCCUPIED_ERR_MSG = "Field is already occupied!";
    public static final String SAVE_STATE_ERR_MSG = "Could not save state to DB!";
    public static final String DROP_DB_ERR_MSG = "Could not drop DB collection!";

    private Character[][] board = {
            {UNOCCUPIED, UNOCCUPIED, UNOCCUPIED}
            , {UNOCCUPIED, UNOCCUPIED, UNOCCUPIED}
            , {UNOCCUPIED, UNOCCUPIED, UNOCCUPIED}
    };

    public char getLastPlayer() {
        return lastPlayer;
    }

    private char lastPlayer = UNOCCUPIED;

    private int counter = 0;
    private static final int SIZE = 3;

    TicTacToeGameState state;

    public TicTacToeGame() {
        this.state = new TicTacToeGameState();
    }

    public TicTacToeGame(TicTacToeGameState state) {
        this.state = state;
        this.state.clear();
    }
    //

    /**
     * 1     2     3
     * |-----|-----|-----|-->x
     * 1|(1,1)|(2,1)|(3,1)|
     * 2|(1,2)|(2,2)|(3,2)|
     * 3|(1,3)|(2,3)|(3,3)|
     * |-----|-----|-----|
     * |
     * y
     * <p>
     * Hint: you may use the players ascii values: 'X'=88*3 = 264 => X wins, 'O'=79*3 = 237 => O wins
     **/
    private boolean isWin(int x, int y) {
        boolean vWinner = true;
        boolean hWinner = true;
        boolean d1Winner = true;
        boolean d2Winner = true;

        for (int i = 0; i < 3; i++) {
            if (this.board[i][y - 1] != this.lastPlayer) {
                vWinner = false;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (this.board[x - 1][i] != this.lastPlayer) {
                hWinner = false;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (this.board[i][i] != this.lastPlayer) {
                d1Winner = false;
                break;
            }
        }

        for (int i = 2; i >= 0; i--) {
            if (this.board[i][2 - i] != this.lastPlayer) {
                d2Winner = false;
                break;
            }
        }

        return vWinner | hWinner | d1Winner | d2Winner;
    }

    // Hint: if one cell is UNOCCUPIED false, else true
    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == null) {
                    //...
                }
            }
        }
        // TODO set return value
        return false;
    }

    public String play(int x, int y) {
        checkCoordinates(x);
        checkCoordinates(y);
        this.lastPlayer = this.getNextPlayer();

        if (this.board[x - 1][y - 1] != UNOCCUPIED) {
            throw new RuntimeException(FIELD_OCCUPIED_ERR_MSG);
        }

        this.board[x - 1][y - 1] = this.lastPlayer;

        TicTacToeGameMove move = new TicTacToeGameMove(++this.counter, this.lastPlayer, x, y);
        if (!this.getState().save(move)) {
            throw new RuntimeException("Saving failed!");
        }

        if (this.isWin(x, y)) {
            return String.format(RESULT_WINNER, this.lastPlayer);
        }

        return RESULT_IN_PROGRESS;
    }

    public String play(TicTacToeGameMove move) {
        Objects.requireNonNull(move);
        //if (move == null) return null;
        return this.play(move.getX(), move.getY());
    }

    private void checkCoordinates(int x) {
        if (x < 1 || x > 3) {
            throw new RuntimeException(TicTacToeGame.COORDINATE_ERR_MSG);
        }
    }

    public char getNextPlayer() {
        return this.lastPlayer == 'X' ? 'O' : 'X';
    }

    public TicTacToeGameState getState() {
        return this.state;
    }

}
