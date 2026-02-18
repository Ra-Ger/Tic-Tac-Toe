package com.kodilla.ticTacToe.game.core;

import com.kodilla.ticTacToe.Resources;
import com.kodilla.ticTacToe.game.Move;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovesEvaluator {


    public static List<Move> evaluatedMoves(boolean itX, Board board, int combo)
    {
        char[][] entireBoard = board.getTicTacToeBoard();
        List<Move> movesList = new ArrayList<>();

        for(int i = 0; i < entireBoard.length; i++)
        {
            for(int j = 0; j < entireBoard[i].length; j++)
            {
                if(entireBoard[i][j] == '\u0000')
                {
                    int[] move = new int[3]; // 0 row 1 column 2 evaluation
                    movesList.add(new Move(i,j,evaluateAllDirections(i,j,itX,board,combo)));
                }
            }
        }

        movesList.sort(Comparator.comparingDouble(Move::evaluation).reversed());
        // od najwiekszych do najmniejszych
        return movesList;
    }

    static public double evaluateAllDirections(int row, int column, boolean itX, Board board, int combo)
    {
        double evaluation = 0;
        double enemyMoveDivisor = 1.1;
        char[][] charBoard = board.getTicTacToeBoard();

        if(charBoard.length < combo)
            combo = charBoard.length; // jezeli mniejsze niz 5 to dostosowoje sie do wielkosci dablicy

        // row
        evaluation += evaluateSegment(charBoard[row],column,itX,combo) + (evaluateSegment(charBoard[row],column,!itX,combo))/enemyMoveDivisor;
        // column
        char[] segment = Resources.convertColumnToSingleArray(column,board);
        evaluation += evaluateSegment(segment,row,itX,combo) + (evaluateSegment(segment,row,!itX,combo))/enemyMoveDivisor;

        //diagonally right down
        segment = Resources.convertFromLeftToRightDiagonallyToSingleArray(row,column,board);
        int index = Math.min(row, column);
        evaluation += evaluateSegment(segment,index,itX,combo) + (evaluateSegment(segment,index,!itX,combo))/enemyMoveDivisor;

        //diagonally left down
        index = (row + column) < (charBoard.length) ? row : (charBoard.length - 1) - column;
        segment = Resources.convertFromRightToLeftDiagonallyToSingleArray(row,column,board);
        evaluation += evaluateSegment(segment,index,itX,combo) + (evaluateSegment(segment,index,!itX,combo))/enemyMoveDivisor;

        return evaluation;
    }

    static public double evaluateSegment(char[] oneSegment, int index, boolean itX, int combo)
    {
        double evaluation = 0;
        char ourSign = itX ? 'X' : 'O';
        char enemySign = itX ? 'O' : 'X';

        int leftBorder = index;
        int leftSteps = 0;

        while (leftBorder > 0 && leftSteps < combo - 1) {
            leftBorder--;
            leftSteps++;

            if(oneSegment[leftBorder] == enemySign) {
                leftBorder++;
                break;
            }
        }
        leftBorder = Math.max(leftBorder, 0);

        int rightBorder = index;
        int rightSteps = 0;

        while (rightBorder < oneSegment.length - 1 && rightSteps < combo - 1) {
            rightBorder++;
            rightSteps++;

            if(oneSegment[rightBorder] == enemySign) {
                rightBorder--;
                break;
            }
        }
        rightBorder = Math.min(rightBorder, oneSegment.length - 1);

        // skoro mamy granice to sprawdzamy co mozemy zrobic w ramach tych granic
        if(rightBorder - leftBorder + 1 < combo) // jezeli sie nie miesci combo to jest to bezwartosciowy segment
            return 0;


        //sprawdzanie poszczegolnych ruchuw w ramach zakresu
        int atIndex = leftBorder;
        int stepsToWin = combo;
        while(atIndex <= rightBorder - combo + 1)
        {
            for(int i = 0; i < combo; i++) {
                if (oneSegment[i+ atIndex] == ourSign) {
                    stepsToWin--;
                }
            }
            evaluation += moveEvaluate(combo,stepsToWin);
            stepsToWin = combo;

            atIndex++;
        }

        return evaluation;
    }

    static double moveEvaluate(int combo, int movesToWin)
    {
        if(movesToWin < 2)
            return ((combo - movesToWin) +1)/Math.pow(movesToWin,2) * 10;
        return ((combo - movesToWin) +1)/Math.pow(movesToWin,2);
    }
}
