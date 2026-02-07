package com.kodilla.ticTacToe.game.visualInterface;

import com.kodilla.ticTacToe.game.GameController;
import com.kodilla.ticTacToe.game.core.AIPlayer;
import com.kodilla.ticTacToe.game.core.Board;

import java.util.Scanner;

public class ConsoleInterfaceManager {

    public static void printBoard(char[][] board) {
        System.out.println();
        System.out.println("   1     2     3  ");
        System.out.println("      |     |     ");
        System.out.printf("1  %c  |  %c  |  %c  \n",
                getChar(board, 0, 0), getChar(board, 0, 1), getChar(board, 0, 2));
        System.out.println(" _____|_____|_____");
        System.out.println("      |     |     ");
        System.out.printf("2  %c  |  %c  |  %c  \n",
                getChar(board, 1, 0), getChar(board, 1, 1), getChar(board, 1, 2));
        System.out.println(" _____|_____|_____");
        System.out.println("      |     |     ");
        System.out.printf("3  %c  |  %c  |  %c  \n",
                getChar(board, 2, 0), getChar(board, 2, 1), getChar(board, 2, 2));
        System.out.println("      |     |     ");
        System.out.println();
    }

    private static char getChar(char[][] board, int row, int col) {
        if (board == null || row >= board.length || col >= board[0].length) {
            return ' ';
        }
        char c = board[row][col];
        return (c == '\u0000' || c == ' ') ? ' ' : c;
    }

    public static void main(String[] args) {
        gameHandler();
    }

    static void gameHandler()
    {
        AIPlayer ai = new AIPlayer(false);

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter name: ");
        String userName = myObj.nextLine();  // Read user input
        GameController gameController = new GameController(new Board(),ai,userName);

        do {
            printBoard(gameController.getBoard().getTicTacToeBoard());

            int column = 0;
            int row = 0;
            do{

                boolean thisWasNumber = false;
                do {
                    System.out.println("chose column: ");
                    try {
                        column = Integer.parseInt(myObj.nextLine());
                        column--; // zeby bylo zgodne z tablicom bo 1 tutaj to 0 w tablicy
                        thisWasNumber = true;
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a number, dummy!");
                    }
                }
                while(!thisWasNumber);
                thisWasNumber = false;

                do {
                    System.out.println("chose row: ");
                    try {
                        row = Integer.parseInt(myObj.nextLine());
                        row--;
                        thisWasNumber = true;
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a number, dummy!");
                    }
                }
                while (!thisWasNumber);

                if(!gameController.isMoveLegal(row,column))
                {
                    System.out.println("This is illegal! Try again!");
                }
            }
            while(!gameController.isMoveLegal(row,column));

            gameController.playerMakeMove(true, row, column); //-1 bo człowiek liczy od 1

            gameController.AIMakeMove();
        }
        while(gameController.checkIfAnyoneWon() == null && !gameController.isBoardFull());

        printBoard(gameController.getBoard().getTicTacToeBoard());
        String winner = gameController.returnWinner();
        if(winner != null)
        System.out.println("Game over. The winner: " + gameController.returnWinner());
        else  System.out.println("Game over. Tie!");

        System.out.println("Type exit to exit");
        myObj.nextLine();
    }
}

