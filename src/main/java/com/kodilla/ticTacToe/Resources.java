package com.kodilla.ticTacToe;

import com.kodilla.ticTacToe.game.core.Board;

import java.util.*;
import java.util.Random;

public class Resources {
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
}
