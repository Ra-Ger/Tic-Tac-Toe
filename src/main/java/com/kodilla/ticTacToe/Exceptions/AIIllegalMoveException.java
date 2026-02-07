package com.kodilla.ticTacToe.Exceptions;

public class AIIllegalMoveException extends RuntimeException{
    public AIIllegalMoveException()
    {
        super("Ai tries to kill us all, we must destroy all technology and back to live in forests and caves!!!");
    }
}
