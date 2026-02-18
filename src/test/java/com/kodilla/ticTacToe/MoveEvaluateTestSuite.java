package com.kodilla.ticTacToe;

import com.kodilla.ticTacToe.game.GameController;
import com.kodilla.ticTacToe.game.core.Board;
import com.kodilla.ticTacToe.game.core.GameRules;
import com.kodilla.ticTacToe.game.core.MovesEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveEvaluateTestSuite {

    char emp = '\u0000';
    char[] oneSegment = {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'};
    char[] oneSegment2 = {'X', emp, 'O', 'X', emp, emp, emp, emp, emp, emp};
    char[] oneSegment3 = {'X', emp, emp, emp, emp, emp, emp, emp, emp, emp};

    Board gameBoard = new Board();
    GameRules gameRules = new GameRules(gameBoard,5);
    MovesEvaluator movesEvaluator = new MovesEvaluator();
    @Test
    void evaluateSegmentToShort()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(0,movesEvaluator.evaluateSegment(oneSegment,1,false,5),0.0001f);
    }

    @Test
    void evaluateSegmentWithTwoXTwoMovesToWin()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(2,movesEvaluator.evaluateSegment(oneSegment,4,true,5),0.0001f);
    }

    @Test
    void evaluateSegmentWithTwoXTwoPlusOneMoveToWin()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(2+(5*10),movesEvaluator.evaluateSegment(oneSegment,7,true,5),0.0001f);
    }

    @Test
    void evaluateSegmentWithFiveMoveToWin()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(0.04,movesEvaluator.evaluateSegment(oneSegment2,9,true,5),0.0001f);
    }

    @Test
    void evaluateSegmentWithOneFourPlusTwoFiveMoveToWin()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(0.205,movesEvaluator.evaluateSegment(oneSegment2,7,true,5),0.0001f);
    }

    @Test
    void evaluateSegmentWithFiveFiveMovesToWin()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(0.2,movesEvaluator.evaluateSegment(oneSegment3,5,true,5),0.0001f);
    }

//    {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
//    {emp, emp, emp, 'X', 'O', emp, emp, emp, 'O','X'},
//    {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'},
//    {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
//    {emp, emp, emp, 'X', 'O', emp, emp, emp, 'X','X'},
//    {'O', emp, 'X', 'O', 'X', 'X', 'O', emp, 'O','O'},
//    {'X', emp, 'O', 'X', emp, 'X', 'X', emp, 'X','X'},
//    {emp, emp, 'X', 'X', 'O', emp, emp, emp, 'X','X'},
//    {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'O','O'},
//    {'O', emp, 'X', 'O', emp, 'X', 'O', emp, 'X','O'}

    @Test
    void evaluateMove()
    {
        double divisor = 1.1;
        gameBoard.setBoard(Resources.bigBoardNoWinner1);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals((2.0*1.0/divisor)+(1.0/3.0)+(0.0)+((1.0/3.0)/divisor),movesEvaluator.evaluateAllDirections(0,4,false,gameBoard,5),0.0001f);
    }
}
