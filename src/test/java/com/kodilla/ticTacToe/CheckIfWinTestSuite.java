package com.kodilla.ticTacToe;

import com.kodilla.ticTacToe.game.GameController;
import com.kodilla.ticTacToe.game.core.Board;
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

    @Test
    void testWinDiagonallyX()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board1WinX);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('X',gameController.checkIfAnyoneWon());
    }

    @Test
    void testWinDiagonallyO()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board4WinO);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('O',gameController.checkIfAnyoneWon());
    }

    @Test
    void testWinRowX()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board2WinX);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('X',gameController.checkIfAnyoneWon());
    }

    @Test
    void testWinColumnX()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board3WinX);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('X',gameController.checkIfAnyoneWon());
    }

    @Test
    void testNoWinner1()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board1NoWinner1);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals(null,gameController.checkIfAnyoneWon());
    }

    @Test
    void testNoWinner2()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board1NoWinner2);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals(null,gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardNoWinner()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardNoWinner1);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals(null,gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXRow()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardWinnerXRow);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('X',gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerOColumn()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardWinnerOColumn);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('O',gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXDiagonallyRight()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardWinnerXDiagonallyRight);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('X',gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXDiagonallyRight2()
    {
        Board gameBoard2 = new Board();
        gameBoard2.setBoard(Resources.bigBoardWinnerXDiagonallyRight2);
        GameController gameController2 = new GameController(gameBoard2,"One","Other");
        assertEquals('X',gameController2.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerODiagonallyRight()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyRight);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('O',gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerODiagonallyLeft()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardWinnerODiagonallyLeft);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('O',gameController.checkIfAnyoneWon());
    }

    @Test
    void testBigBoardWinnerXDiagonallyLeft()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(Resources.bigBoardWinnerXDiagonallyLeft);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals('X',gameController.checkIfAnyoneWon());
    }
}
