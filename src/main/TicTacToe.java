package main;

public class TicTacToe extends BoardGame {
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToe(Player player1, Player player2, char[][] board) {
        super(board);
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = null;
        resetBoard();
        getBoard();
    }

    @Override
    public void playGame() {
        printBoard();
        System.out.println();
        currentPlayer = getNextPlayer();
        boolean continuePlaying = true;
        boolean drawGame = false;
        while (continuePlaying) {
            currentPlayer.makeMove();
            if (playerWon(currentPlayer.getMark())) {
                continuePlaying = false;
            } else if (drawGame()) {
                continuePlaying = false;
                drawGame = true;
            } else {
                currentPlayer = getNextPlayer();
            }
            printBoard();
            System.out.println();
        }

        System.out.println("GAME OVER!!");
        if (drawGame) {
            System.out.println("DRAW GAME!!");
        } else {
            System.out.println(currentPlayer.getMark() + " Won!!");
        }

    }

    @Override
    public void resetBoard() {
        char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = BLANK;
            }
        }
    }

    @Override
    public Player getNextPlayer() {
        if (null == currentPlayer) {
            return player1;
        }
        return (currentPlayer.getMark() == player1.getMark()) ? player2 : player1;
    }

    private void printBoard() {
        char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
