package com.kodilla.ticTacToe;

import com.kodilla.ticTacToe.game.core.Board;

import java.util.*;
import java.util.Random;

public class Resources {
    static char emp = '\u0000';
    public static Random getRandom() {
        return new Random();
    }

    public static List<int[]> freeFields(Board board)
    {
        List<int[]> positions = new ArrayList<>();
        char[][] charBoard = board.getTicTacToeBoard();

        for (int i = 0; i < charBoard.length; i++) {
            for (int j = 0; j < charBoard[i].length; j++) {
                if (charBoard[i][j] == '\u0000') {
                    positions.add(new int[]{i, j});  // [row, column]
                }
            }
        }
        return positions;
    }

    public static char[] convertColumnToSingleArray(int column, Board board)
    {
        char[] resultCharArray = new char[board.getTicTacToeBoard().length];
        for(int i = 0; i < resultCharArray.length; i++)
        {
            resultCharArray[i] = board.getTicTacToeBoard()[i][column];
        }
        return resultCharArray;
    }

    public static char[] convertFromLeftToRightDiagonallyToSingleArray(int row, int column, Board board)
    {
        int min = Math.min(row, column);
        row -= min;
        column -= min;
        char[] resultCharArray = new char[board.getTicTacToeBoard().length -(row+column)];

        for(int i = 0; i < resultCharArray.length; i++)
        {
            resultCharArray[i] = board.getTicTacToeBoard()[row+i][column+i];
        }
        return resultCharArray;
    }

    public static char[] convertFromRightToLeftDiagonallyToSingleArray(int row, int column, Board board)
    {
        // musisz pokombinowac tutaj
        // albo row 0 albo column length-1
        int arrayLength;
        if(column + row <= board.getTicTacToeBoard().length-1)
        {
            column = column + row;
            row = 0;
            arrayLength = column + 1;
        }
        else // dodac to co brakuje column do 9 i zabrac z row to co brakuje column do 9
        {
            int missingDistance = (board.getTicTacToeBoard().length - 1) - column;
            column = column + missingDistance;
            row = row - missingDistance;
            arrayLength = (column - row) + 1;
        }
        // jestesmy na gorze lub po prwej i schodzimy w lewo w dol
        char[] resultCharArray = new char[arrayLength];

        for(int i = 0; i < resultCharArray.length; i++)
        {
            resultCharArray[i] = board.getTicTacToeBoard()[row+i][column-i];
        }
        return resultCharArray;
    }

    public static List<int[]> occupiedFields(Board board)
    {
        List<int[]> positions = new ArrayList<>();

        for (int i = 0; i < board.getTicTacToeBoard().length; i++) {
            for (int j = 0; j < board.getTicTacToeBoard()[i].length; j++) {
                if (board.getTicTacToeBoard()[i][j] == 'X' || board.getTicTacToeBoard()[i][j] == 'O') {
                    positions.add(new int[]{i, j});  // [row, column]
                }
            }
        }
        return positions;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    public static char[][] bigBoardNoWinner1 = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, 'X', 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'O','O'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'}
    };

    public static char[][] bigBoardWinnerXRow = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, 'X', 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'O','O'},
            {'O', emp, 'X', 'O', emp, 'X', 'X', 'X', 'X','X'}
    };

    public static char[][] bigBoardWinnerOColumn = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'O', emp, 'X','X'},
            {emp, emp, 'X', 'X', 'O', emp, 'O', emp, 'X','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'O','O'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', 'O', 'X','X'}
    };

    public static char[][] bigBoardWinnerXDiagonallyRight = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, 'X', 'X', 'O', emp, 'X', 'X', 'X','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', 'O', 'X','X'}
    };

    public static char[][] bigBoardWinnerXDiagonallyRight2 = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, 'X', emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', 'X', 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
            {'X', emp, 'O', 'X', emp, 'O', 'X', emp, 'X','X'},
            {emp, emp, 'X', 'X', 'O', emp, 'X', 'O', 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'O','O'},
            {'O', emp, 'O', 'O', emp, 'X', 'O', 'O', 'X','X'}
    };

    public static char[][] bigBoardWinnerODiagonallyRight = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
            {'X', 'O', 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, 'O', 'X', 'O', emp, 'X', 'O', 'X','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'O', emp, 'X', 'O', 'O', 'X', 'O', 'O', 'X','X'}
    };

    public static char[][] bigBoardWinnerODiagonallyLeft = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'O', 'X', 'O', emp, 'O','O'},
            {'X', emp, 'O', 'O', emp, 'O', 'X', emp, 'X','X'},
            {emp, emp, 'O', 'X', 'X', emp, 'X', 'X', 'O','X'},
            {'O', 'O', 'X', 'O', emp, 'X', 'O', emp, 'O','O'},
            {'O', emp, 'O', 'O', emp, 'X', 'O', 'O', 'X','X'}
    };

    public static char[][] bigBoardWinnerXDiagonallyLeft = {
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
            {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
            {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
            {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','X'},
            {'X', emp, 'O', 'X', emp, 'O', 'X', emp, 'X','X'},
            {emp, emp, 'X', 'X', 'X', emp, 'X', 'X', 'O','X'},
            {'O', emp, 'X', 'O', emp, 'X', 'X', emp, 'O','O'},
            {'O', emp, 'O', 'O', emp, 'X', 'O', 'O', 'X','X'}
    };


}
