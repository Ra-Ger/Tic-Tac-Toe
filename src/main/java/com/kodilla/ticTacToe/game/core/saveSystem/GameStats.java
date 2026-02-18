package com.kodilla.ticTacToe.game.core.saveSystem;

import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameMode;

public class GameStats {
    public String playerXName;
    public String playerOName;
    public boolean isPlayerXAI;
    public boolean isPlayerOAI;
    public int scoreX;
    public int scoreO;
    public char[][] board;
    public int gameCounter;
    public boolean xHaveMove;
    public DifficultLevel difficultLevel;
    public GameMode gameMode;
    public boolean animations;
}
