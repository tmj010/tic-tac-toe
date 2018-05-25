package main;

public interface Player {
    char[][] getBoard();

    char getMark();

    int[] moveTo();

    default void makeMove() {
        char[][] board = getBoard();
        int[] rowCol = moveTo();
        board[rowCol[0]][rowCol[1]] = getMark();
    }
}
