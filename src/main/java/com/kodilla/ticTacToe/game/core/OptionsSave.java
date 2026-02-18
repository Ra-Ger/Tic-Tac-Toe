package com.kodilla.ticTacToe.game.core;

public class OptionsSave {

    public static DifficultLevel difficultLevel;
    public static GameMode gameMode;
    public static boolean isAIX;
    public static boolean isAIO;
    public static String playerX = "player 1";
    public static String playerO = "player 2";
    public static boolean animationsEnabled;

    public static void setOptionsSave(DifficultLevel difficultLevel, GameMode gameMode, boolean isAIX, boolean isAIO, String playerX, String playerO, boolean animationsEnabled) {
        OptionsSave.difficultLevel = difficultLevel;
        OptionsSave.gameMode = gameMode;
        OptionsSave.isAIX = isAIX;
        OptionsSave.isAIO = isAIO;
        OptionsSave.playerX = playerX;
        OptionsSave.playerO = playerO;
        OptionsSave.animationsEnabled = animationsEnabled;
    }
}