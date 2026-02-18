package com.kodilla.ticTacToe.game.visualInterface.JavaFXInterfaceManager;

import com.kodilla.ticTacToe.Resources;
import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameMode;
import com.kodilla.ticTacToe.game.core.OptionsSave;
import com.kodilla.ticTacToe.game.core.saveSystem.SaveManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.File;

import java.util.*;

public class MenuViewController {
    @FXML private ChoiceBox<String> gameModeChoiceBox;
    @FXML private ChoiceBox<String> difficultChoiceBox;
    @FXML private TextField playerXTextField;
    @FXML private TextField playerOTextField;
    @FXML private RadioButton aiXRadioButton;
    @FXML private RadioButton aiORadioButton;
    @FXML private Button startButton;
    @FXML private RadioButton animationButton;

    private Map<String,DifficultLevel> difficultChoiceBoxDependicies;
    private Map<String,GameMode> gameModeChoiceBoxDependicies;

    @FXML
    public void initialize() {
        difficultChoiceBoxDependicies = setChoiceBoxDifficult();
        gameModeChoiceBoxDependicies = setChoiceBoxGameMode();

        difficultChoiceBox.setValue(Resources.getKeyByValue(difficultChoiceBoxDependicies,DifficultLevel.HARD));
        gameModeChoiceBox.setValue(Resources.getKeyByValue(gameModeChoiceBoxDependicies,GameMode.CLASSIC));

        if(OptionsSave.difficultLevel != null)
            difficultChoiceBox.setValue(Resources.getKeyByValue(difficultChoiceBoxDependicies,OptionsSave.difficultLevel));
        if(OptionsSave.gameMode != null)
            gameModeChoiceBox.setValue(Resources.getKeyByValue(gameModeChoiceBoxDependicies,OptionsSave.gameMode));
        playerXTextField.setText(OptionsSave.playerX);
        playerOTextField.setText(OptionsSave.playerO);
        aiXRadioButton.setSelected(OptionsSave.isAIX);
        aiORadioButton.setSelected(OptionsSave.isAIO);
        animationButton.setSelected(OptionsSave.animationsEnabled);
    }

    @FXML
    private void handleStartClick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView.fxml"));
        Parent root = loader.load();

        GameViewController gameCtrl = loader.getController();

        OptionsSave.setOptionsSave(difficultChoiceBoxDependicies.get(difficultChoiceBox.getValue()), gameModeChoiceBoxDependicies.get(gameModeChoiceBox.getValue()),aiXRadioButton.isSelected(), aiORadioButton.isSelected(), playerXTextField.getText(), playerOTextField.getText(), animationButton.isSelected());
        gameCtrl.initGame(difficultChoiceBoxDependicies.get(difficultChoiceBox.getValue()), gameModeChoiceBoxDependicies.get(gameModeChoiceBox.getValue()),aiXRadioButton.isSelected(), aiORadioButton.isSelected(), playerXTextField.getText(), playerOTextField.getText(), animationButton.isSelected(),null);

        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    @FXML
    private void handleAIXRadioButton(ActionEvent event)
    {
        if(aiORadioButton.isSelected())
            aiORadioButton.setSelected(false);
    }

    @FXML
    private void handleAIORadioButton(ActionEvent event)
    {
        if(aiXRadioButton.isSelected())
            aiXRadioButton.setSelected(false);
    }

    @FXML
    private void handleClearSave(ActionEvent event)
    {
        SaveManager.clearSave();
    }


    private Map<String, DifficultLevel> setChoiceBoxDifficult() {
        Map<String, DifficultLevel> map = new HashMap<>();
        difficultChoiceBox.getItems().clear();
        for (DifficultLevel dl : DifficultLevel.values()) {
            difficultChoiceBox.getItems().add(dl.toString().replaceAll("_", " ").toLowerCase());
            map.put(dl.toString().replaceAll("_", " ").toLowerCase(), dl);
        }
        return map;
    }

    private Map<String, GameMode> setChoiceBoxGameMode() {
        Map<String, GameMode> map = new HashMap<>();
        gameModeChoiceBox.getItems().clear();
        for (GameMode gm : GameMode.values()) {
            gameModeChoiceBox.getItems().add(gm.toString().replaceAll("_", " ").toLowerCase());
            map.put(gm.toString().replaceAll("_", " ").toLowerCase(), gm);
        }
        return map;
    }
}
