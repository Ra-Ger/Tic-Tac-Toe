package com.kodilla.ticTacToe.game.visualInterface.JavaFXInterfaceManager;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Tick-Tack-Toe - JavaFX");
        primaryStage.setScene(scene);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
