package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        Scanner input = new Scanner(System.in);

        Player humanPlayer = new HumanPlayer(input, board, 'X');
        Player aiPlayer = new AIPlayer(board, 'O', 'X', 30);

        BoardGame boardGame = new TicTacToe(aiPlayer, humanPlayer, board);
        boardGame.playGame();
    }
}

