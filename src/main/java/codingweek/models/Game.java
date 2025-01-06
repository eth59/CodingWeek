package codingweek.models;

import java.io.Serializable;

public class Game implements Serializable {
    private int boardSize;
    private int timeLimit; // in seconds
    private Board board;
    private Game instance;

    private Game() {
        this.board = new Board();
        this.boardSize = 5;
        this.timeLimit = 60;
    }

    public Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Board getBoard() {
        return board;
    }
}
