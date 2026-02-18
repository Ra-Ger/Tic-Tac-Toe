package com.kodilla.ticTacToe.game.visualInterface.JavaFXInterfaceManager;

import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameMode;
import com.kodilla.ticTacToe.game.core.saveSystem.GameStats;
import com.kodilla.ticTacToe.game.core.saveSystem.SaveManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainStage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Sprawdzamy, czy istnieje plik zapisu
        GameStats stats = SaveManager.loadGame();

        // 2. Ładujemy widok gry
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView.fxml"));
        Parent root = loader.load();
        GameViewController controller = loader.getController();

        if (stats != null) {
            // JEŚLI JEST ZAPIS: Inicjujemy grę danymi z pliku
            // Uwaga: Upewnij się, że Twoje GameStats przechowuje też DifficultLevel i GameMode
            controller.initGame(
                    stats.difficultLevel, // musisz dodać to pole do GameStats i SaveManager
                    stats.gameMode,      // musisz dodać to pole do GameStats i SaveManager
                    stats.isPlayerXAI,
                    stats.isPlayerOAI,
                    stats.playerXName,
                    stats.playerOName,
                    stats.animations
            );
        } else {
            // JEŚLI NIE MA ZAPISU: Możesz albo załadować Menu, albo nową czystą grę
            controller.initGame(DifficultLevel.EASY, GameMode.CLASSIC, false, true, "Player", "AI", true);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuView.fxml"));
//        Parent root = loader.load();
//
//        Scene scene = new Scene(root);
//
//        primaryStage.setTitle("Tick-Tack-Toe - JavaFX");
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//        primaryStage.centerOnScreen();
    }
}
