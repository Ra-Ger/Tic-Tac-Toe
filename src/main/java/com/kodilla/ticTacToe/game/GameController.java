package com.kodilla.ticTacToe.game;

import com.kodilla.ticTacToe.game.core.AIPlayer;
import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameRules;

public class GameController {

   // Board board;
    GameRules gameRules;
    String playerX;
    String playerO;
    int gameCounter;
    int playerXScore;
    int playerOScore;
    AIPlayer aiPlayer;
    int maxWinCombo = 5;
    DifficultLevel difficultLevel;



    public GameController(GameRules gameRules, String playerX, String playerO) {
        this.gameRules = gameRules;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    public GameController(GameRules gameRules, boolean isPlayerX, String player, DifficultLevel difficultLevel)
    {
        this.difficultLevel = difficultLevel;
        this.gameRules = gameRules;
        this.aiPlayer = new AIPlayer(!isPlayerX,difficultLevel,gameRules);
        if(aiPlayer.getItX()) {
            playerX = "AI";
            playerO = player;
        }
        else {
            playerX = player;
            playerO = "AI";
        }
    }

    public void AIMakeMove()
    {
        aiPlayer.makeMove();
    }

    public void playerMakeMove(boolean itX, int row, int column)
    {
        gameRules.getBoard().putSignOnBoard(itX,column,row);
    }

    public boolean isMoveLegal(int row, int column)
    {
        if(row < gameRules.getBoard().getTicTacToeBoard().length && column < gameRules.getBoard().getTicTacToeBoard()[row].length)
             return gameRules.getBoard().getCharAt(row,column)  == '\u0000';
        else return false;
    }

    public String returnWinner()
    {
        Character winner = gameRules.checkIfAnyoneWon();
        if(winner == null || winner == '\u0000')
        {
            return null;
        }
        else
        {
            if(winner == 'X')
                return playerX;
            else return playerO;
        }

    }

    public boolean isBoardFull()
    {
        return gameRules.getBoard().isFull();
    }

    public GameRules getGameRules()
    {
        return gameRules;
    }

    public String getPlayerX() {
        return playerX;
    }

    public AIPlayer getAiPlayer() {
        return aiPlayer;
    }

    public String getPlayerO() {
        return playerO;
    }

}
