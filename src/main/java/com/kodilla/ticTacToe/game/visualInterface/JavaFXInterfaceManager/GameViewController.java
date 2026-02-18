package com.kodilla.ticTacToe.game.visualInterface.JavaFXInterfaceManager;

import com.kodilla.ticTacToe.game.core.saveSystem.GameStats;
import com.kodilla.ticTacToe.game.core.saveSystem.SaveManager;
import javafx.animation.*;
import com.kodilla.ticTacToe.game.core.Board;
import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameMode;
import com.kodilla.ticTacToe.game.core.GameRules;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import com.kodilla.ticTacToe.game.GameController;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;

public class GameViewController {

    @FXML private GridPane boardGrid;
    @FXML AnchorPane mainContainer;
    @FXML Text infoText;
    @FXML Button newGameButton;
    @FXML Button exitButton;
    @FXML ImageView mainImageView;
    @FXML Button nextRoundButton;
    @FXML Text xScoreText;
    @FXML Text oScoreText;
    @FXML Text roundText;

    private GameController gameController;
    DifficultLevel difficultLevel;
    GameMode gameMode;
    boolean isAIX;
    boolean isAIO;
    String playerX;
    String playerO;
    boolean animationsEnabled;
    private final Image xImage = new Image(getClass().getResourceAsStream("/SimpleX.png"));
    private final Image oImage = new Image(getClass().getResourceAsStream("/SimpleO.png"));
    private final Image mainGraphic = new Image(getClass().getResourceAsStream("/MainGraphic1.png"));
    private final Image endGameGraphic = new Image(getClass().getResourceAsStream("/MainGraphicWin1.png"));
    private final Image mainGraphicAnimated = new Image(getClass().getResourceAsStream("/GameAnimation.gif"));
    private final Image endGameGraphicAnimated = new Image(getClass().getResourceAsStream("/GameOverAnimation.gif"));
    boolean itXturn = true;
    private Timeline shinyTimeline;

    @FXML
    public void initialize() {

    }

    public void initGame(DifficultLevel difficultLevel, GameMode gameMode, boolean isAIX, boolean isAIO,
                         String playerX, String playerO, boolean animationsEnabled, GameStats loadedStats) {

        if (loadedStats != null) {
            this.gameMode = loadedStats.gameMode;
            this.difficultLevel = loadedStats.difficultLevel;
            this.isAIX = loadedStats.isPlayerXAI;
            this.isAIO = loadedStats.isPlayerOAI;
            this.playerX = loadedStats.playerXName;
            this.playerO = loadedStats.playerOName;
            this.animationsEnabled = loadedStats.animations;
        } else {
            this.gameMode = gameMode;
            this.difficultLevel = difficultLevel;
            this.isAIX = isAIX;
            this.isAIO = isAIO;
            this.playerX = playerX;
            this.playerO = playerO;
            this.animationsEnabled = animationsEnabled;
        }

        int size = (this.gameMode == GameMode.GOMOKU10X10) ? 10 : 3;
        int winCondition = (this.gameMode == GameMode.GOMOKU10X10) ? 5 : 3;

        Board board = new Board();
        board.setBoard(new char[size][size]);
        GameRules gameRules = new GameRules(board, winCondition);

        if (!this.isAIX && !this.isAIO) {
            gameController = new GameController(gameRules, this.playerX, this.playerO);
        } else {
            String name = this.isAIO ? this.playerX : this.playerO;
            gameController = new GameController(gameRules, this.isAIO, name, this.difficultLevel);
        }

        setupBoard(size);
        infoText.setVisible(false);
        nextRoundButton.setDisable(true);

        if (this.animationsEnabled) mainImageView.setImage(mainGraphicAnimated);
        else mainImageView.setImage(mainGraphic);

        if (loadedStats != null) {
            applyLoadedStats(loadedStats);

            Platform.runLater(() -> {
                boolean isGameOver = checkGameState(false);
                if (!isGameOver) {
                    boardGrid.setDisable(false);
                }
            });
        } else {
            updateScoreLabels();
            roundText.setText("1");
            if (gameController.getAiPlayer() != null && gameController.getAiPlayer().getItX()) {
                gameController.getAiPlayer().makeMove();
                drawBoard();
            }
        }
    }

    private void applyLoadedStats(GameStats stats) {
        gameController.setPlayerXScore(stats.scoreX);
        gameController.setPlayerOScore(stats.scoreO);
        gameController.setGameCounter(stats.gameCounter);

        gameController.getGameRules().getBoard().setBoard(stats.board);

        itXturn = stats.xHaveMove;
        updateScoreLabels();
        roundText.setText(String.valueOf(stats.gameCounter + 1));
        drawBoard();
    }

    private void updateScoreLabels() {
        xScoreText.setText(gameController.getPlayerX() + ": " + gameController.getPlayerXScore());
        oScoreText.setText(gameController.getPlayerO() + ": " + gameController.getPlayerOScore());
    }

    private void setupBoard(int size) {
        boardGrid.getChildren().clear();
        boardGrid.getColumnConstraints().clear();
        boardGrid.getRowConstraints().clear();

        double percent = 100.0 / size;
        for (int i = 0; i < size; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(percent);
            boardGrid.getColumnConstraints().add(col);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(percent);
            boardGrid.getRowConstraints().add(row);
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button btn = new Button();
                btn.setMinSize(0, 0);
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                btn.setStyle("-fx-background-color: rgba(255,255,255,0.1); " +
                        "-fx-border-color: rgba(255,255,255,0.5);; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-style: solid;");

                int finalRow = row;
                int finalCol = col;
                btn.setOnAction(e -> handleMove(finalRow, finalCol, btn));

                boardGrid.add(btn, col, row);
            }
        }
    }

    private void drawWinningLine(Button startBtn, Button endBtn) {
        if (startBtn == null || endBtn == null) return;

        Bounds startBounds = startBtn.localToScene(startBtn.getBoundsInLocal());
        Bounds endBounds = endBtn.localToScene(endBtn.getBoundsInLocal());

        if (startBounds == null || endBounds == null) return;

        double startX = startBounds.getMinX() + startBounds.getWidth() / 2;
        double startY = startBounds.getMinY() + startBounds.getHeight() / 2;
        double endX = endBounds.getMinX() + endBounds.getWidth() / 2;
        double endY = endBounds.getMinY() + endBounds.getHeight() / 2;

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.RED);
        line.setStrokeWidth(8);

        Platform.runLater(() -> {
            if (boardGrid.getScene() != null) {
                ((AnchorPane) boardGrid.getScene().getRoot()).getChildren().add(line);
            }
        });
    }

    private void handleMove(int row, int col, Button btn) {
        boolean isMoveLegal = gameController.isMoveLegal(row, col);

        if (isMoveLegal) {
            if (gameController.getAiPlayer() == null) {
                gameController.playerMakeMove(itXturn, row, col);
                itXturn = !itXturn;

                checkGameState(true);
            } else {
                gameController.playerMakeMove(!gameController.getAiPlayer().getItX(), row, col);

                if (!checkGameState(true)) {
                    gameController.getAiPlayer().makeMove();
                    checkGameState(true);
                }
            }
        }

        drawBoard();
        saveCurrentState();
    }

    private void drawBoard()
    {
        char[][] gameBoard = gameController.getGameRules().getBoard().getTicTacToeBoard();
        for(int row = 0; row < gameBoard.length; row++)
        {
            for(int column = 0; column < gameBoard[row].length; column++)
            {
               updateButton(getButtonByCoords(row,column),gameBoard[row][column]);
            }
        }
    }

    private boolean checkGameState(boolean addPoint) {
        String winner = gameController.returnWinner();
        if (winner != null || gameController.isBoardFull()) {

            shinnyButton(nextRoundButton);
            infoText.setVisible(true);

            if(animationsEnabled) {
                infoText.layoutXProperty().set(340);
                mainImageView.setImage(endGameGraphicAnimated);
            } else {
                mainImageView.setImage(endGameGraphic);
            }

            if (winner != null) {
                if (addPoint) {
                    if(winner.equals(gameController.getPlayerX())) {
                        gameController.setPlayerXScore(gameController.getPlayerXScore() + 1);
                    } else {
                        gameController.setPlayerOScore(gameController.getPlayerOScore() + 1);
                    }
                    gameController.setGameCounter(gameController.getGameCounter() + 1);
                    saveCurrentState();
                }

                int[] rowColX2 = gameController.getGameRules().getStartAndEndOfCombo();
                drawWinningLine(getButtonByCoords(rowColX2[0], rowColX2[1]), getButtonByCoords(rowColX2[2], rowColX2[3]));
                infoText.setText("Winner: " + winner);

                updateScoreLabels();
            } else {
                infoText.setText("Tie!");
                if (addPoint) {
                    gameController.setGameCounter(gameController.getGameCounter() + 1);
                    saveCurrentState();
                }
            }

            boardGrid.setDisable(true);
            nextRoundButton.setDisable(false);
            return true;
        }
        return false;
    }
    private void updateButton(Button btn, char symbol) {
        if (btn == null) return;

        if (symbol != '\u0000') {
            ImageView iv = new ImageView(symbol == 'X' ? xImage : oImage);
            iv.fitWidthProperty().bind(btn.widthProperty().multiply(0.7));
            iv.fitHeightProperty().bind(btn.heightProperty().multiply(0.7));
            iv.setPreserveRatio(true);

            btn.setGraphic(iv);
            btn.setDisable(true);
            btn.setOpacity(1.0);
        } else {
            btn.setGraphic(null);
            btn.setDisable(false);
        }
    }

    private Button getButtonByCoords(int row, int col) {
        for (Node node : boardGrid.getChildren()) {
            if (node instanceof Button &&
                    GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }

    @FXML
    private void handleNextRoundButtonClick(ActionEvent event) throws IOException
    {
        stopShinyEffect(nextRoundButton);
        char[][] board = new char[gameController.getGameRules().getBoard().getTicTacToeBoard().length][gameController.getGameRules().getBoard().getTicTacToeBoard().length];
        gameController.getGameRules().getBoard().setBoard(board);
        itXturn = true;

        infoText.setVisible(false);
        boardGrid.setDisable(false);

        mainContainer.getChildren().removeIf(node -> node instanceof Line);

        if (animationsEnabled)
            mainImageView.setImage(mainGraphicAnimated);
        else
            mainImageView.setImage(mainGraphic);

        setupBoard(board.length);

        roundText.setText(String.valueOf(gameController.getGameCounter() + 1));
        updateScoreLabels();

        if (gameController.getAiPlayer() != null && gameController.getAiPlayer().getItX()) {
            gameController.getAiPlayer().makeMove();
            drawBoard();
        }

        nextRoundButton.setDisable(true);
        saveCurrentState();
    }

    @FXML
    private void handleNewGameClick(ActionEvent event) throws IOException {
        SaveManager.clearSave();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) newGameButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    @FXML
    private void handleExitClick(ActionEvent event)
    {
        System.exit(0);
    }

    void shinnyButton(Button button)
    {
        stopShinyEffect(button);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(15);
        glow.setSpread(0.3);
        button.setEffect(glow);

        shinyTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(glow.radiusProperty(), 15)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(glow.radiusProperty(), 30)),
                new KeyFrame(Duration.seconds(1.6), new KeyValue(glow.radiusProperty(), 15))
        );
        shinyTimeline.setCycleCount(Animation.INDEFINITE);
        shinyTimeline.setAutoReverse(true);
        shinyTimeline.play();
    }

    private void stopShinyEffect(Button button) {
        if (shinyTimeline != null) {
            shinyTimeline.stop();
            shinyTimeline = null;
        }
        button.setEffect(null); // Usuwa DropShadow
    }

    private void saveCurrentState() {
        if (gameController != null) {
            char[][] currentBoard = gameController.getGameRules().getBoard().getTicTacToeBoard();
            SaveManager.saveGame(
                    playerX, playerO, isAIX, isAIO,
                    gameController.getPlayerXScore(),
                    gameController.getPlayerOScore(),
                    currentBoard,
                    gameController.getGameCounter(),
                    itXturn,
                    difficultLevel,
                    gameMode,
                    animationsEnabled
            );
        }
    }

    private boolean loadSavedState() {
        GameStats stats = SaveManager.loadGame();
        if (stats != null) {
            isAIX = stats.isPlayerXAI;
            isAIO = stats.isPlayerOAI;
            animationsEnabled = stats.animations;
            gameController.setPlayerXScore(stats.scoreX);
            gameController.setPlayerOScore(stats.scoreO);
            gameController.setGameCounter(stats.gameCounter);
            gameController.setPlayerX(stats.playerXName);
            gameController.setPlayerO(stats.playerOName);

            gameController.getGameRules().getBoard().setBoard(stats.board);

            updateScoreLabels();
            roundText.setText(String.valueOf(stats.gameCounter + 1));

            drawBoard();
            itXturn = stats.xHaveMove;
            return true;
        }
        return false;
    }
}
