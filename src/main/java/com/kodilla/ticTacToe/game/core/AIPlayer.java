package com.kodilla.ticTacToe.game.core;

import com.kodilla.ticTacToe.Resources;
import com.kodilla.ticTacToe.game.Move;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer {

    GameRules gameRules;
    MovesEvaluator movesEvaluator;
    DifficultLevel difficultLevel;
    boolean itX;
    public AIPlayer(boolean itX, DifficultLevel difficultLevel, GameRules gameRules)
    {
        this.itX = itX;
        this.difficultLevel = difficultLevel;
        this.gameRules = gameRules;

    }
    public void makeMove()
    {
        List<Move> movesList = new ArrayList<>(MovesEvaluator.evaluatedMoves(itX,gameRules.getBoard(), gameRules.getMaxWinCombo()));
        Move move;

        if(!movesList.isEmpty()) {
            switch (difficultLevel) {
                case DifficultLevel.EASY:
                    move = movesList.get(Resources.getRandom().nextInt(movesList.size() / 2 > 0 ? movesList.size() / 2 : movesList.size()));
                    break;
                case DifficultLevel.NORMAL:
                    move = Resources.getRandom().nextInt(100) > 20 ? movesList.get(0) : movesList.get(Resources.getRandom().nextInt(movesList.size() / 10 > 0 ? movesList.size() / 2 : movesList.size()));
                    break;
                case DifficultLevel.HARD:
                    move = movesList.get(0);
                    break;
                default:
                    move = movesList.get(0);

            }

            gameRules.getBoard().putSignOnBoard(itX, move.col(), move.row());
        }
    }

    public boolean getItX()
    {
        return itX;
    }
}
