package com.kodilla.ticTacToe.game.visualInterface.JavaFXInterfaceManager;

import javafx.animation.*;
import com.kodilla.ticTacToe.game.core.Board;
import com.kodilla.ticTacToe.game.core.DifficultLevel;
import com.kodilla.ticTacToe.game.core.GameMode;
import com.kodilla.ticTacToe.game.core.GameRules;
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

    @FXML
    private GridPane boardGrid;
    @FXML
    AnchorPane mainContainer;
    @FXML
    Text infoText;
    @FXML
    Button newGameButton;
    @FXML
    Button exitButton;
    @FXML
    ImageView mainImageView;

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

    @FXML
    public void initialize() {

    }

    public void initGame(DifficultLevel difficultLevel, GameMode gameMode, boolean isAIX, boolean isAIO, String playerX, String playerO, boolean animationsEnabled)
    {
        this.difficultLevel = difficultLevel;
        this.gameMode = gameMode;
        this.isAIX = isAIX;
        this.isAIO = isAIO;
        this.playerX = playerX;
        this.playerO = playerO;
        this.animationsEnabled = animationsEnabled;

        if(animationsEnabled)
            mainImageView.setImage(mainGraphicAnimated);
        else
            mainImageView.setImage(mainGraphic);

        Board board = new Board();
        switch (gameMode)
        {
            case CLASSIC:
                board.setBoard(new char[3][3]);
                break;
            case GOMOKU10X10:
                board.setBoard(new char[10][10]);
                break;
        }
        GameRules gameRules = new GameRules(board,5);
        if(!isAIX && !isAIO)
            gameController = new GameController(gameRules,playerX,playerO);
        else
            gameController = new GameController(gameRules,isAIO,isAIO ? playerX : playerO, difficultLevel);

        setupBoard(board.getTicTacToeBoard().length);
        infoText.setVisible(false);
        if(gameController.getAiPlayer() != null && gameController.getAiPlayer().getItX())
        {
            gameController.getAiPlayer().makeMove();
            drawBoard();
        }
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
        Bounds startBounds = startBtn.localToScene(startBtn.getBoundsInLocal());
        Bounds endBounds = endBtn.localToScene(endBtn.getBoundsInLocal());

        // srodek sceny
        double startX = startBounds.getMinX() + startBounds.getWidth() / 2;
        double startY = startBounds.getMinY() + startBounds.getHeight() / 2;
        double endX = endBounds.getMinX() + endBounds.getWidth() / 2;
        double endY = endBounds.getMinY() + endBounds.getHeight() / 2;

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.RED);
        line.setStrokeWidth(8);

        ((AnchorPane) boardGrid.getScene().getRoot()).getChildren().add(line);
    }

    // wywolywane po wcisnienciu grida
    private void handleMove(int row, int col, Button btn) {

        boolean isMoveLegal = gameController.isMoveLegal(row, col);
        if(isMoveLegal)
        {
            if(gameController.getAiPlayer() == null) {
                gameController.playerMakeMove(itXturn, row, col);
                itXturn = !itXturn;
            }
            else
            {
                    gameController.playerMakeMove(!gameController.getAiPlayer().getItX(), row, col);
                    if(!checkGameState())
                        gameController.getAiPlayer().makeMove();
            }
        }
        drawBoard();
        checkGameState();
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

    private boolean checkGameState() {
        String winner = gameController.returnWinner();
        if (winner != null || gameController.isBoardFull()) {

            shinnyButton(newGameButton);
            infoText.setVisible(true);
            if(animationsEnabled) {
                infoText.layoutXProperty().set(340);
                mainImageView.setImage(endGameGraphicAnimated);
            }
            else
                mainImageView.setImage(endGameGraphic);

            if (winner != null) {
                int[] rowColX2 = gameController.getGameRules().getStartAndEndOfCombo();
                drawWinningLine(getButtonByCoords(rowColX2[0],rowColX2[1]),getButtonByCoords(rowColX2[2],rowColX2[3]));
                infoText.setText("Winner: " + winner);
            } else {
                infoText.setText("Tie!");
            }
            boardGrid.setDisable(true);
            return true;
        }
        return false;
    }

    private void updateButton(Button btn, char symbol) {
        if(symbol != '\u0000') {
            ImageView iv = new ImageView(symbol == 'X' ? xImage : oImage);
            iv.fitWidthProperty().bind(btn.widthProperty().multiply(0.7));
            iv.fitHeightProperty().bind(btn.heightProperty().multiply(0.7));
            iv.setPreserveRatio(true);

            btn.setGraphic(iv);
            btn.setDisable(true);
            btn.setOpacity(1.0);
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
    private void handleNewGameClick(ActionEvent event) throws IOException {
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
        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(15);
        glow.setSpread(0.3);
        button.setEffect(glow);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(glow.radiusProperty(), 15)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(glow.radiusProperty(), 30)),
                new KeyFrame(Duration.seconds(1.6), new KeyValue(glow.radiusProperty(), 15))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }
}
