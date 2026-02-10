package com.kodilla.ticTacToe.game;

import com.kodilla.ticTacToe.game.core.AIPlayer;
import com.kodilla.ticTacToe.game.core.Board;

public class GameController {

    Board board;
    String playerX;
    String playerO;
    int gameCounter;
    int playerXScore;
    int playerOScore;
    AIPlayer aiPlayer;
    int maxWinCombo = 5;

    public GameController(Board board, String playerX, String playerO) {
        this.board = board;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    public GameController(Board board, AIPlayer aiPlayer, String player)
    {
        this.board = board;
        this.aiPlayer = aiPlayer;
        if(aiPlayer.getItX()) {
            playerX = "AI";
            playerO = player;
        }
        else {
            playerX = player;
            playerO = "AI";
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
/*
        if(charBoard.length < 4) {
            // row
            for (int i = 0; i < charBoard.length; i++) {
                for (int j = 0; j < charBoard[i].length; j++) {
                    if (charBoard[i][j] != '\u0000') {
                        if (j != 0) {
                            if (charBoard[i][j] == charBoard[i][j - 1]) // jeżeli obecna ruwna poprzedniej
                            {
                                if (j == charBoard[i].length - 1) {
                                    return charBoard[i][j];
                                }
                            } else break;
                        }
                    } else break;
                }
            }

            // column
            for (int i = 0; i < charBoard.length && i < charBoard[i].length; i++) {
                for (int j = 1; j < charBoard.length; j++) {
                    if (charBoard[j - 1][i] != '\u0000' && charBoard[j][i] != '\u0000') {
                        if (charBoard[j - 1][i] == charBoard[j][i]) {
                            if (j >= charBoard.length - 1) {
                                return charBoard[j][i];
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }

            //diagonally from top
            for (int i = 1; i < charBoard.length; i++) {
                if (charBoard[i - 1][i - 1] != '\u0000' && charBoard[i][i] != '\u0000') {
                    if (charBoard[i - 1][i - 1] == charBoard[i][i]) {
                        if (i >= charBoard.length - 1) {
                            return charBoard[i][i];
                        }
                    } else break;
                } else break;
            }

            // diagonally from down
            for (int i = 1; i < charBoard.length; i++) {
                if (charBoard[charBoard.length - 1 - i][i] != '\u0000' && charBoard[charBoard.length - i][i - 1] != '\u0000') {
                    if (charBoard[charBoard.length - 1 - i][i] == charBoard[charBoard.length - i][i - 1]) {
                        if (i >= charBoard.length - 1) {
                            return charBoard[charBoard.length - 1 - i][i];
                        }
                    } else break;
                } else break;
            }
            return null;
        }
        else // dla 4 i wiencej
        {*/
            int combo = maxWinCombo;
            if(charBoard.length < 5)
                combo = charBoard.length; // jezeli mniejsze niz 5 to dostosowoje sie do wielkosci dablicy

            // w prawo
            for (int i = 0; i < charBoard.length; i++) {
                for (int j = 0; j < charBoard[i].length; j++) {
                    if(charBoard[i].length - j < combo)
                        break;  // jezeli tablica krotsza niz combo to na pewno nie ma tu wygranej
                    if (charBoard[i][j] != '\u0000')
                    {
                        for(int c = 1; c < combo; c++)
                        {
                            // tutaj sprawdzasz czy kolejne 5 znakow takie same
                            if(charBoard[i][j] != charBoard[i][j+c])
                            {
                                j = j+c-1; // jezeli nie to przeskakujesz do ostatnio sprawdzanego pola gdzie byla inna wartosc
                                break;
                            }
                            if(c == combo-1)
                                return charBoard[i][j];
                        }
                    }
                }
            }
            // w dol
            for (int i = 0; i < charBoard.length; i++) { // przeskakujemy po wierszach
                for (int j = 0; j < charBoard.length; j++) { // przeskakujemy po kolumnach
//                    if(charBoard.length - i < combo)
//                        break;  // jezeli tablica krotsza niz combo to na pewno nie ma tu wygranej
                    if(charBoard.length - j < combo)
                        break;  // jezeli tablica krotsza niz combo to na pewno nie ma tu wygranej
                    if (charBoard[j][i] != '\u0000')
                    {
                        for(int c = 1; c < combo; c++)
                        {
                            // tutaj sprawdzasz czy kolejne 5 znakow takie same
                            if(charBoard[j][i] != charBoard[j+c][i])
                            {
                                j = j+c-1; // jezeli nie to przeskakujesz do ostatnio sprawdzanego pola gdzie byla inna wartosc
                                break;
                            }
                            if(c == combo-1)
                                return charBoard[j][i];
                        }
                    }
                }
            }
            // prawo dol skos
            int startRow = charBoard.length - combo;
            int startColumn = 0;
            int row = startRow;
            int column = startColumn;
            while(!(startRow < 1 && startColumn > charBoard.length - combo))
            {
                if(row > charBoard[column].length - combo && startRow > 0)
                {
                    startRow--;
                    column = startColumn;
                    row = startRow;
                    //System.out.println("Wspinam sie po row do gury na " + startRow);
                }
                else if(column > charBoard.length - combo && startRow <= 0) // <<<
                {
                    startColumn++;
                    row = startRow;
                    column = startColumn;
                    //System.out.println("ide po kolumnach w prawo na " + startColumn);
                }

                //System.out.println("Jestem na: " + charBoard[row][column] + " na pozycji: row: " + row + " column: " + column);
                if (charBoard[row][column] != '\u0000')
                {
                    for(int c = 1; c < combo; c++)
                    {
                            //System.out.println("Znalazłem: " + charBoard[row][column] + " na pozycji: row: " + row + " column: " + column);

                        // tutaj sprawdzasz czy kolejne 5 znakow takie same
                        if(charBoard[row][column] != charBoard[row+c][column+c])
                        {
                            //System.out.println("Poczontek: row: " + row + " column " +column+ " Koniec serii: row: " + (row +c) + " column: " + (column+c));
                            row = row+c-1; // jezeli nie to przeskakujesz do ostatnio sprawdzanego pola gdzie byla inna wartosc
                            column = column+c-1;
                            break;
                        }
                        if(c == combo-1)
                            return charBoard[row][column];
                    }
                }

                column++;
                row++;
            }

            // lewo dol skos
             startRow = 0;
             startColumn = combo-1;
             row = startRow;
             column = startColumn;
            while(!(startColumn >= charBoard.length-1 && startRow > charBoard[column].length - combo))
            {
                if(column < (combo-1) && startColumn < charBoard[startColumn].length-1)
                {
                    startColumn++;
                    column = startColumn;
                    row = startRow;
                }
                else if(row > charBoard.length - combo && startColumn >= charBoard[startColumn].length-1)
                {
                    startRow++;
                    row = startRow;
                    column = startColumn;
                }

              //  System.out.println("Jestem na: " + charBoard[row][column] + " na pozycji: row: " + row + " column: " + column);
                if (charBoard[row][column] != '\u0000')
                {
                    for(int c = 1; c < combo; c++)
                    {
                        // tutaj sprawdzasz czy kolejne 5 znakow takie same
                        if(charBoard[row][column] != charBoard[row+c][column-c])
                        {
                            row = row+c+1; // jezeli nie to przeskakujesz do ostatnio sprawdzanego pola gdzie byla inna wartosc
                            column = column-c+1;
                            break;
                        }
                        if(c == combo-1)
                            return charBoard[row][column];
                    }
                }
                column--;
                row++;
            }

            return null;
        //}
    }

    public String returnWinner()
    {
        Character winner = checkIfAnyoneWon();
        if(winner == null || winner == '\u0000')
        {
            return null;
        }
        else
        {
            if(winner == 'X')
                return playerX;
            else return playerO;
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
        playerOScore++;
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

    public int getPlayerOScore() {
        return playerOScore;
    }

    public int getPlayerXScore() {
        return playerXScore;
    }

    public int getGameCounter() {
        return gameCounter;
    }

    public String getPlayerO() {
        return playerO;
    }
}
