package com.kodilla.ticTacToe;

import com.kodilla.ticTacToe.game.GameController;
import com.kodilla.ticTacToe.game.core.Board;
import com.kodilla.ticTacToe.game.core.GameRules;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckIfWinTestSuite {

    char[][] board1WinX = {
            {'X', '\u0000', 'O'},
            {'\u0000', 'X', '\u0000'},
            {'O', '\u0000', 'X'}
    };

    char[][] board2WinX = {
            {'O', '\u0000', 'O'},
            {'X', 'X', 'X'},
            {'O', '\u0000', 'X'}
    };

    char[][] board3WinX = {
            {'O', '\u0000', 'X'},
            {'\u0000', 'X', 'X'},
            {'O', '\u0000', 'X'}
    };

    char[][] board4WinO = {
            {'X', '\u0000', 'O'},
            {'\u0000', 'O', '\u0000'},
            {'O', '\u0000', 'O'}
    };

    char[][] board1NoWinner1 = {
            {'X', '\u0000', 'X'},
            {'\u0000', '\u0000', '\u0000'},
            {'O', '\u0000', 'X'}
    };

    char[][] board1NoWinner2 = {
            {'X', 'O', 'X'},
            {'\u0000', '\u0000', '\u0000'},
            {'O', 'O', 'X'}
    };

    @BeforeAll
    static void beforeAllTests()
    {

    }

    Board gameBoard = new Board();
    GameRules gameRules = new GameRules(gameBoard,5);
    @Test
    void testWinDiagonallyX()
    {
        gameBoard.setBoard(board1WinX);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('X',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testWinDiagonallyO()
    {
        gameBoard.setBoard(board4WinO);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('O',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testWinRowX()
    {
        gameBoard.setBoard(board2WinX);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('X',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testWinColumnX()
    {
        gameBoard.setBoard(board3WinX);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('X',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testNoWinner1()
    {
        gameBoard.setBoard(board1NoWinner1);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(null,gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testNoWinner2()
    {
        gameBoard.setBoard(board1NoWinner2);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(null,gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardNoWinner()
    {
        gameBoard.setBoard(Resources.bigBoardNoWinner1);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals(null,gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXRow()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerXRow);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('X',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerOColumn()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerOColumn);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('O',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXDiagonallyRight()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerXDiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('X',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXDiagonallyRight2()
    {
        Board gameBoard2 = new Board();
        gameBoard2.setBoard(Resources.bigBoardWinnerXDiagonallyRight2);
        GameRules gameRules2 = new GameRules(gameBoard2,5);
        GameController gameController2 = new GameController(gameRules2,"One","Other");
        assertEquals('X',gameController2.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerODiagonallyRight()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('O',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerODiagonallyLeft()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyLeft);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('O',gameController.getGameRules().checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXDiagonallyLeft()
    {
        gameBoard.setBoard(Resources.bigBoardWinnerXDiagonallyLeft);
        GameController gameController = new GameController(gameRules,"One","Other");
        assertEquals('X',gameController.getGameRules().checkIfAnyoneWon());
    }
}
