package com.kodilla.ticTacToe.game.core;

import com.kodilla.ticTacToe.Exceptions.AIIllegalMoveException;
import com.kodilla.ticTacToe.Resources;

import java.util.List;

public class AIPlayer {

    boolean itX;
    public AIPlayer(boolean itX)
    {
        this.itX = itX;
    }
    public void makeMove(Board board)
    {
        List<int[]> accessible = Resources.freeFields(board);
        int[] field = accessible.get(Resources.getRandom().nextInt(accessible.size()));
        if(board.getCharAt(field[0],field[1])  == '\u0000')
            board.putSignOnBoard(itX,field[1],field[0]);
        else throw new AIIllegalMoveException();
    }

    public boolean getItX()
    {
        return itX;
    }
}
