package main;

public interface GameRule {
    char BLANK = '-';

    char[][] getBoard();

    default boolean drawGame() {
        char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    default boolean playerWon(char mark) {
        char[][] board = getBoard();
        int row;
        int col;
        int count;

        for (row = 0; row < board.length; row++) {
            count = 0;
            for (col = 0; col < board[row].length; col++) {
                if (board[row][col] == mark) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }

        for (col = 0; col < board[0].length; col++) {
            count = 0;
            for (row = 0; row < board.length; row++) {
                if (board[row][col] == mark) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }

        count = 0;
        for (row = 0; row < board.length; row++) {
            if (board[row][row] == mark) {
                count++;
            }
        }

        if (count == 3) {
            return true;
        }

        count = 0;
        for (row = 0; row < board.length; row++) {
            if (row == 0 && board[row][2] == mark) {
                count++;
            } else if (row == 1 && board[row][row] == mark) {
                count++;
            } else if (row == 2 && board[row][0] == mark) {
                count++;
            }
        }

        if (count == 3) {
            return true;
        }

        return false;
    }
}
