package com.kodilla.ticTacToe;

import com.kodilla.ticTacToe.game.GameController;
import com.kodilla.ticTacToe.game.core.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicTestSuite {

    char[][] board1WinX = {
            {'X', '\u0000', 'O'},
            {'\u0000', 'X', '\u0000'},
            {'O', '\u0000', 'X'}
    };

    @Test
    void moveLegalTest()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board1WinX);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals(true,gameController.isMoveLegal(2,1));
    }

    @Test
    void moveIllegalTest()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board1WinX);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals(false,gameController.isMoveLegal(0,0));
    }

    @Test
    void moveIllegalTestOutOfBoundaries()
    {
        Board gameBoard = new Board();
        gameBoard.setBoard(board1WinX);
        GameController gameController = new GameController(gameBoard,"One","Other");
        assertEquals(false,gameController.isMoveLegal(100,5));
    }
}
