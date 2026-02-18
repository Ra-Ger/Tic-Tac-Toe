package com.kodilla.ticTacToe.game.core;

public class GameRules {

    Board board;
    int maxWinCombo;
    int[] startAndEndOfCombo = new int[4];

    public GameRules(Board board, int maxWinCombo)
    {
        this.board = board;
        this.maxWinCombo = maxWinCombo;
    }

    public Character checkIfAnyoneWon() {
        char[][] charBoard = board.getTicTacToeBoard();

        int combo = maxWinCombo;
        if (charBoard.length < combo) {
            combo = charBoard.length;
        }

        // w prawo
        for (int i = 0; i < charBoard.length; i++) {
            for (int j = 0; j < charBoard[i].length; j++) {
                if(charBoard[i].length - j < combo)
                    break; // jezeli tablica krotsza niz combo to na pewno nie ma tu wygranej
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
                        if(c == combo-1) {
                            startAndEndOfCombo[0] = i;
                            startAndEndOfCombo[1] = j;
                            startAndEndOfCombo[2] = i;
                            startAndEndOfCombo[3] = j+c;
                            return charBoard[i][j];
                        }
                    }
                }
            }
        }

        // w dol
        for (int i = 0; i < charBoard.length; i++) { // przeskakujemy po wierszach
            for (int j = 0; j < charBoard.length; j++) { // przeskakujemy po kolumnach
                if(charBoard.length - j < combo)
                    break; // jezeli tablica krotsza niz combo to na pewno nie ma tu wygranej
                if (charBoard[j][i] != '\u0000')
                {
                    for(int c = 1; c < combo; c++)
                    {
                        if(charBoard[j][i] != charBoard[j+c][i])
                        {
                            j = j+c-1; // jezeli nie to przeskakujesz do ostatnio sprawdzanego pola gdzie byla inna wartosc
                            break;
                        }
                        if(c == combo-1) {
                            startAndEndOfCombo[0] = j;
                            startAndEndOfCombo[1] = i;
                            startAndEndOfCombo[2] = j+c;
                            startAndEndOfCombo[3] = i;
                            return charBoard[j][i];
                        }
                    }
                }
            }
        }

        // prawo dol skos
        int rowBorder = charBoard.length - combo; // ostatni indeks mozliwy do sprawdzenia
        int columnBorder = charBoard.length - combo; // ostani indeks dla kolumny mozliwy do sprawdzenia
        for(int row = 0; row <= rowBorder;row++)
        {
            for(int column = 0; column <= columnBorder; column++)
            {
                if(charBoard[row][column] != '\u0000')
                {
                    for(int i = 1; i < combo;i++)
                    {
                        if(charBoard[row][column] != charBoard[row+i][column+i])
                            break;

                        if(i >= combo-1) {
                            startAndEndOfCombo[0] = row;
                            startAndEndOfCombo[1] = column;
                            startAndEndOfCombo[2] = row+i;
                            startAndEndOfCombo[3] = column+i;
                            return charBoard[row][column];
                        }
                    }
                }
            }
        }
        // w lewo dol skos
        for(int row = 0; row <= rowBorder;row++)
        {
            for(int column = combo-1; column < charBoard[row].length; column++)
            {
                if(charBoard[row][column] != '\u0000')
                {
                    for(int i = 1; i < combo;i++) // jestesmy na 1 polu i sprawdzamy kolejne 4 dla combo 5
                    {
                        if(charBoard[row][column] != charBoard[row+i][column-i])
                            break;

                        if(i >= combo-1) {
                            startAndEndOfCombo[0] = row;
                            startAndEndOfCombo[1] = column;
                            startAndEndOfCombo[2] = row+i;
                            startAndEndOfCombo[3] = column-i;
                            return charBoard[row][column];
                        }
                    }
                }
            }

        }
        return null;
    }

    public Board getBoard()
    {
        return board;
    }

    public int getMaxWinCombo()
    {
        return maxWinCombo;
    }

    public int[] getStartAndEndOfCombo()
    {
        return startAndEndOfCombo;
    }

}
