package com.kodilla.ticTacToe.game.core;

public class Board {

    // dwuwymiarowa tablica mająca 3 kolumny i 3 wiersze
    char[][] ticTacToeBoard = new char[3][3];
    public Board()
    {

    }

    public char[][] getTicTacToeBoard()
    {
        return ticTacToeBoard.clone();
    }

    public void putSignOnBoard(boolean itX, int column, int row)
    {
        if(itX)
        {
            ticTacToeBoard[row][column] = 'X';
        }
        else
        {
            ticTacToeBoard[row][column] = 'O';
        }
    }

    public boolean isFull()
    {
        for(char[] charArray : ticTacToeBoard)
        {
            for(char c : charArray)
            {
                if(c == '\u0000')
                    return false;
            }
        }
        return true;
    }

    public char getCharAt(int row, int column)
    {
        return ticTacToeBoard[row][column];
    }

    public void setBoard(char[][] ticTacToeBoard)
    {
        for(char[] row : ticTacToeBoard)
        {
            if(row.length != ticTacToeBoard.length)
            {
                throw new RuntimeException("Board should be square!");
            }
        }
        this.ticTacToeBoard = ticTacToeBoard;
    }
}
