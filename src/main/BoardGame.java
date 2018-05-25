package main;

public abstract class BoardGame implements GameRule {
    private char[][] board;

    public BoardGame(char[][] board) {
        this.board = board;
    }

    public char[][] getBoard() {
        return board;
    }

    public abstract void resetBoard();

    public abstract Player getNextPlayer();

    public abstract void playGame();
}
