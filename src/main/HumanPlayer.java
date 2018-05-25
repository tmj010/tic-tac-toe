package main;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private char[][] board;
    private char mark;
    private Scanner input;

    private final static char BLANK = '-';

    public HumanPlayer(Scanner input, char[][] board, char mark) {
        this.input = input;
        this.board = board;
        this.mark = mark;
    }

    @Override
    public char[][] getBoard() {
        return board;
    }

    @Override
    public char getMark() {
        return mark;
    }

    @Override
    public int[] moveTo() {
        boolean empty = false;
        int row = -1;
        int col = -1;
        do {
            System.out.print("Move: ");
            row = input.nextInt();
            col = input.nextInt();
            char[][] board = getBoard();
            if (row < board.length && col < board[0].length && board[row][col] == BLANK) {
                empty = true;
            }
        } while (!empty);

        return new int[]{row, col};
    }
}
