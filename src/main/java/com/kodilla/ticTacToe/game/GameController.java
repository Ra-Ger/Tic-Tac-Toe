package com.kodilla.ticTacToe.game;

import com.kodilla.ticTacToe.game.core.AIPlayer;
import com.kodilla.ticTacToe.game.core.Board;

public class GameController {

    Board board;
    String playerX;
    String playerY;
    int gameCounter;
    int playerXScore;
    int playerYScore;
    AIPlayer aiPlayer;

    public GameController(Board board, String playerX, String playerY) {
        this.board = board;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public GameController(Board board, AIPlayer aiPlayer, String player)
    {
        this.board = board;
        this.aiPlayer = aiPlayer;
        if(aiPlayer.getItX()) {
            playerX = "AI";
            playerY = player;
        }
        else {
            playerX = player;
            playerY = "AI";
        }
    }

    public void AIMakeMove()
    {
        aiPlayer.makeMove(board);
    }

    public void playerMakeMove(boolean itX, int row, int column)
    {
        board.putSignOnBoard(itX,column,row);
    }

    public boolean isMoveLegal(int row, int column)
    {
        if(row < board.getTicTacToeBoard().length && column < board.getTicTacToeBoard()[row].length)
             return board.getCharAt(row,column)  == '\u0000';
        else return false;
    }

    public Character checkIfAnyoneWon()
    {
        char[][] charBoard = board.getTicTacToeBoard();

        // row
        for (int i = 0; i < charBoard.length; i++) {
            for (int j = 0; j < charBoard[i].length; j++) {
                if (charBoard[i][j] != '\u0000') {
                    if(j != 0)
                    {
                        if(charBoard[i][j] == charBoard[i][j-1]) // jeżeli obecna ruwna poprzedniej
                        {
                            if(j == charBoard[i].length-1)
                            {
                                return charBoard[i][j];
                            }
                        }
                        else break;
                    }
                }
                else break;
            }
        }

        // column
        for (int i = 0; i < charBoard.length && i < charBoard[i].length; i++) {
            for (int j = 1; j < charBoard.length; j++) {
                if(charBoard[j-1][i] != '\u0000' && charBoard[j][i] != '\u0000')
                {
                    if(charBoard[j-1][i] == charBoard[j][i])
                    {
                        if(j >= charBoard.length-1)
                        {
                            return charBoard[j][i];
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }

        //diagonally from top
        for(int i = 1; i < charBoard.length; i++)
        {
            if(charBoard[i - 1][i - 1] != '\u0000' && charBoard[i][i] != '\u0000') {
                if (charBoard[i - 1][i - 1] == charBoard[i][i]) {
                    if(i >= charBoard.length-1)
                    {
                        return charBoard[i][i];
                    }
                }
                else break;
            }
            else break;
        }

        // diagonally from down
        for(int i = 1; i < charBoard.length; i++)
        {
            if(charBoard[charBoard.length-1-i][i] != '\u0000' && charBoard[charBoard.length-i][i-1] != '\u0000') {
                if (charBoard[charBoard.length-1-i][i] == charBoard[charBoard.length-i][i-1]) {
                    if(i >= charBoard.length-1)
                    {
                        return charBoard[charBoard.length-1-i][i];
                    }
                }
                else break;
            }
            else break;
        }
        return null;
    }

    public String returnWinner()
    {
        char winner = checkIfAnyoneWon();
        if(winner == '\u0000')
        {
            return null;
        }
        else
        {
            if(winner == 'X')
                return playerX;
            else return playerY;
        }

    }

    public boolean isBoardFull()
    {
        return board.isFull();
    }

    public void incrementGameCounter()
    {
        gameCounter++;
    }

    public void incrementPlayerXScore()
    {
        playerXScore++;
    }

    public void incrementPlayerYScore()
    {
        playerYScore++;
    }

    public Board getBoard()
    {
        return board;
    }

    public String getPlayerX() {
        return playerX;
    }

    public AIPlayer getAiPlayer() {
        return aiPlayer;
    }

    public int getPlayerYScore() {
        return playerYScore;
    }

    public int getPlayerXScore() {
        return playerXScore;
    }

    public int getGameCounter() {
        return gameCounter;
    }

    public String getPlayerY() {
        return playerY;
    }
}
