package com.kodilla.ticTacToe.game.visualInterface;

import com.kodilla.ticTacToe.Resources;
import com.kodilla.ticTacToe.game.GameController;
import com.kodilla.ticTacToe.game.core.AIPlayer;
import com.kodilla.ticTacToe.game.core.Board;

import java.util.Scanner;

public class ConsoleInterfaceManager {

public static void printBoard(char[][] board) {
    if (board == null) return;
    int rows = board.length;
    int cols = board[0].length;

    System.out.println();

    System.out.print("    ");
    for (int j = 0; j < cols; j++) {
        System.out.printf("%-4d  ", j + 1); //<<<
    }
    System.out.println();

    for (int i = 0; i < rows; i++) {

        System.out.print("       ");
        for (int j = 0; j < cols - 1; j++) {
            System.out.print("|     ");
        }
        System.out.println();

        System.out.printf("%-2d", i + 1); //<<<

        for (int j = 0; j < cols; j++) {
            System.out.printf("  %c  ", getChar(board, i, j));

            if (j < cols - 1) {
                System.out.print("|");
            }
        }
        System.out.println();

        if (i < rows - 1) {
            System.out.print("  _____");
            for (int j = 0; j < cols - 1; j++) {
                System.out.print("|_____");
            }
        } else {
            System.out.print("      ");
            for (int j = 0; j < cols - 1; j++) {
                System.out.print(" |    ");
            }
        }
        System.out.println();
    }
}

    private static char getChar(char[][] board, int row, int col) {
        if (board == null || row >= board.length || col >= board[0].length) {
            return ' ';
        }
        char c = board[row][col];
        return (c == '\u0000' || c == ' ') ? ' ' : c;
    }

    public static void main(String[] args) {
        //printBoard(Resources.bigBoardWinnerXDiagonallyLeft);
        gameHandler();
    }

    static void playerMakeMove(Scanner myObj, GameController gameController,boolean itX)
    {
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

        gameController.playerMakeMove(itX, row, column);
    }

    static void gameHandler()
    {
        Board board;

        Scanner myObj = new Scanner(System.in);

        System.out.println("type \"1\" to play on classic or \"2\" to play to five.");
        int gameBoard = 0;
        do {
            try {
                gameBoard = Integer.parseInt(myObj.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please! Type \"1\" or \"2\" ticTacToe for more players is not suppoerted right now!");
            }
        }
        while (gameBoard != 1 && gameBoard != 2);

        board = new Board();
        if(gameBoard == 2)
            board.setBoard(new char[10][10]);

        System.out.println("type \"1\" to play with AI or \"2\" to play with other human.");
        int gameMode = 0;
        do {
            try {
                gameMode = Integer.parseInt(myObj.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please! Type \"1\" or \"2\" ticTacToe for more players is not suppoerted right now!");
            }
        }
        while (gameMode != 1 && gameMode != 2);

        GameController gameController;
        if(gameMode == 1) {
            String AIXorO = "";
            do{
                System.out.println("Type \"X\" to be first or \"O\" to be second.");
                AIXorO = myObj.nextLine().toUpperCase();  // Read user input
            }
            while (!AIXorO.equals("X") && !AIXorO.equals("O"));

            AIPlayer ai = new AIPlayer(AIXorO.equals("O"));
            System.out.println("Enter name: ");
            String userName = myObj.nextLine();  // Read user input
            gameController = new GameController(board, ai, userName);
        }
        else {
            System.out.println("Enter first player (X) name: ");
            String firstPlayer = myObj.nextLine();  // Read user input
            System.out.println("Enter secound player (O) name: ");
            String secondPlayer = myObj.nextLine();  // Read user input
            gameController = new GameController(board, firstPlayer, secondPlayer);
        }

        printBoard(gameController.getBoard().getTicTacToeBoard());
        do {
            if(gameMode == 1 && gameController.getAiPlayer().getItX())
            {
                gameController.AIMakeMove(); // jezeli ai x to zaczyna
                printBoard(gameController.getBoard().getTicTacToeBoard());
                if(gameController.isBoardFull() || gameController.checkIfAnyoneWon() != null)
                    break;
                playerMakeMove(myObj,gameController,false);
            }
            else if(gameMode == 1 && !gameController.getAiPlayer().getItX())
            {
                playerMakeMove(myObj,gameController,true);
                if(gameController.isBoardFull() || gameController.checkIfAnyoneWon() != null) {
                    printBoard(gameController.getBoard().getTicTacToeBoard());
                    break;
                }
                gameController.AIMakeMove();
                printBoard(gameController.getBoard().getTicTacToeBoard());
            }
            else
            {
                printBoard(gameController.getBoard().getTicTacToeBoard());
                System.out.println("Player " + gameController.getPlayerX() + " have move.");
                playerMakeMove(myObj,gameController,true);
                if(gameController.isBoardFull() || gameController.checkIfAnyoneWon() != null)
                    break;
                printBoard(gameController.getBoard().getTicTacToeBoard());
                System.out.println("Player " + gameController.getPlayerO() + " have move.");
                playerMakeMove(myObj,gameController,false);
            }

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

