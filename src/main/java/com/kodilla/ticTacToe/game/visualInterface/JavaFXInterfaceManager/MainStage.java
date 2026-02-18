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
        GameStats stats = SaveManager.loadGame();

        if (stats != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView.fxml"));
            Parent root = loader.load();
            GameViewController controller = loader.getController();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tic-Tac-Toe");

            primaryStage.show();
            primaryStage.centerOnScreen();

            controller.initGame(stats.difficultLevel, stats.gameMode, stats.isPlayerXAI,
                    stats.isPlayerOAI, stats.playerXName, stats.playerOName, stats.animations, stats);

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuView.fxml"));
            Parent root = loader.load();
            showScene(primaryStage, root);
        }
    }

    private void showScene(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tic-Tac-Toe");
        stage.show();
        stage.centerOnScreen();
    }
}
