package com.kodilla.ticTacToe.game.core.saveSystem;

import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameMode;
import com.kodilla.ticTacToe.game.visualInterface.JavaFXInterfaceManager.GameViewController;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class SaveManager {
    private static final String SAVE_FILE = "tictactoe_save.properties";

    public static void saveGame(String nameX, String nameO, boolean xAI, boolean oAI, int sX, int sO, char[][] board, int gameCounter, boolean xHaveMove, DifficultLevel diffLevel, GameMode gameMode, boolean animations) {
        Properties props = new Properties();

        props.setProperty("playerX", nameX);
        props.setProperty("playerO", nameO);
        props.setProperty("isXAI", String.valueOf(xAI));
        props.setProperty("isOAI", String.valueOf(oAI));
        props.setProperty("scoreX", String.valueOf(sX));
        props.setProperty("scoreO", String.valueOf(sO));
        props.setProperty("gameCounter", String.valueOf(gameCounter));
        props.setProperty("xHaveMove", String.valueOf(xHaveMove));
        props.setProperty("diffLevel", diffLevel.name());
        props.setProperty("gameMode", gameMode.name());
        props.setProperty("animations", String.valueOf(animations));

        int size = board.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = board[i][j];
                sb.append(c == '\0' ? '.' : c);
            }
        }
        props.setProperty("board", sb.toString());

        try (FileOutputStream out = new FileOutputStream(SAVE_FILE)) {
            props.store(out, "TicTacToe Save File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameStats loadGame() {
        Properties props = new Properties();
        File file = new File(SAVE_FILE);

        if (!file.exists()) return null;

        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
            GameStats stats = new GameStats();
            stats.playerXName = props.getProperty("playerX", "Player 1");
            stats.playerOName = props.getProperty("playerO", "Player 2");
            stats.isPlayerXAI = Boolean.parseBoolean(props.getProperty("isXAI", "false"));
            stats.isPlayerOAI = Boolean.parseBoolean(props.getProperty("isOAI", "false"));
            stats.scoreX = Integer.parseInt(props.getProperty("scoreX", "0"));
            stats.scoreO = Integer.parseInt(props.getProperty("scoreO", "0"));
            stats.gameCounter = Integer.parseInt(props.getProperty("gameCounter", "0"));
            stats.xHaveMove = Boolean.parseBoolean(props.getProperty("xHaveMove","false"));
            stats.difficultLevel = DifficultLevel.valueOf(props.getProperty("diffLevel", "EASY"));

            stats.gameMode = GameMode.valueOf(props.getProperty("gameMode", "CLASSIC"));
            stats.animations = Boolean.parseBoolean(props.getProperty("animations","false"));

            int size = (stats.gameMode == GameMode.GOMOKU10X10) ? 10 : 3;
            String boardStr = props.getProperty("board", ".".repeat(size * size));

            char[][] loadedBoard = new char[size][size];
            for (int i = 0; i < size * size; i++) {
                if (i < boardStr.length()) {
                    char c = boardStr.charAt(i);
                    loadedBoard[i / size][i % size] = (c == '.' ? '\0' : c);
                }
            }
            stats.board = loadedBoard;

            return stats;
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void clearSave()
    {
        File saveFile = new File("tictactoe_save.properties");

        if (saveFile.exists()) {
            boolean deleted = saveFile.delete();
            if (deleted) {
                System.out.println("Zapis gry został usunięty.");

            } else {
                System.out.println("Nie udało się usunąć zapisu.");
            }
        } else {
            System.out.println("Brak zapisu do usunięcia.");
        }
    }
}
