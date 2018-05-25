package main;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer implements Player, GameRule {
    private char mark;
    private char oppMark;
    private char[][] board;
    private int level;

    public AIPlayer(char[][] board, char mark, char oppMark, int level) {
        this.oppMark = oppMark;
        this.board = board;
        this.mark = mark;
        this.level = level;
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
        int[] miniMax = miniMax(level, getMark(), Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{miniMax[1], miniMax[2]};
    }

    private int[] miniMax(int depth, char seed, int alpha, int beta) {
        List<int[]> nextMove = getAvaliablesMoves();

        int score;
        int row = -1;
        int col = -1;

        if (nextMove.isEmpty() || depth == 0) {
            score = evaluate();
            return new int[]{score, row, col};
        } else {
            char[][] board = getBoard();
            for (int cell[] : nextMove) {
                board[cell[0]][cell[1]] = seed;
                if (seed == getMark()) {
                    score = miniMax(depth - 1, oppMark, alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        row = cell[0];
                        col = cell[1];
                    }
                } else {
                    score = miniMax(depth - 1, mark, alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        row = cell[0];
                        col = cell[1];
                    }
                }
                board[cell[0]][cell[1]] = BLANK;
                // Cut-Off
                if (alpha >= beta) {
                    break;
                }
            }
        }

        return new int[]{(seed == getMark()) ? alpha : beta, row, col};
    }

    private List<int[]> getAvaliablesMoves() {
        List<int[]> avaliblesMoves = new ArrayList<>();

        if (playerWon(mark) || playerWon(oppMark)) {
            return avaliblesMoves;
        }

        char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == BLANK) {
                    avaliblesMoves.add(new int[]{row, col});
                }
            }
        }

        return avaliblesMoves;
    }

    private int evaluate() {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
        score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
        score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
        score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
        score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
        score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
        score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
        score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
        return score;
    }

    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;
        char[][] cells = getBoard();
        // First cell
        if (cells[row1][col1] == mark) {
            score = 1;
        } else if (cells[row1][col1] == oppMark) {
            score = -1;
        }

        // Second cell
        if (cells[row2][col2] == mark) {
            if (score == 1) {   // cell1 is mySeed
                score = 10;
            } else if (score == -1) {  // cell1 is oppSeed
                return 0;
            } else {  // cell1 is empty
                score = 1;
            }
        } else if (cells[row2][col2] == oppMark) {
            if (score == -1) { // cell1 is oppSeed
                score = -10;
            } else if (score == 1) { // cell1 is mySeed
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        // Third cell
        if (cells[row3][col3] == mark) {
            if (score > 0) {  // cell1 and/or cell2 is mySeed
                score *= 10;
            } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (cells[row3][col3] == oppMark) {
            if (score < 0) {  // cell1 and/or cell2 is oppSeed
                score *= 10;
            } else if (score > 1) {  // cell1 and/or cell2 is mySeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = -1;
            }
        }
        return score;
    }
}
