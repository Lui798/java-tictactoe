package lui798.tictactoe;

import java.util.Random;

public class TicTacToe {

    private Board gameboard = new Board();

    public boolean playerTurn(int val) {
        //return if val is invalid
        if (val > 9 || val < 0) return false;
        //Reduce value by 1 for use with array index of 0
        val--;
        //convert number into rows and columns for 2d array
        int row = val / 3;
        int col = val % 3;

        //return false if spot has already been marked
        if (!gameboard.getMark(row, col).equals("•")) {
            return false;
        }
        else {
            //mark spot if it hasn't been filled
            gameboard.markBoard(row, col, "X");
        }
        return true;
    }

    public void cpuTurn() {
        Random rand = new Random();
        int val = rand.nextInt(8);

        int row = val / 3;
        int col = val % 3;

        while (!gameboard.getMark(row, col).equals("•")) {
            val = rand.nextInt(8);
            row = val / 3;
            col = val % 3;
        }

        gameboard.markBoard(row, col, "O");
    }

    public boolean threeInARow(int val) {
        String[][] boardArray = gameboard.getBoard();
        int col, row, diag, rdiag = 0;
        boolean win = false;
        int x = val / 3;
        int y = val % 3;

        for (int i = 0; i < gameboard.getSize(); i++) {
            if (boardArray[])
        }
    }

    public String toString() {
        String[][] boardArray = gameboard.getBoard();
        String stringBoard = "";

        for (int i = 0; i < gameboard.getSize(); i++) {
            for (int j = 0; j < gameboard.getSize(); j++) {
                stringBoard += boardArray[i][j] + "  ";
            }
            stringBoard += "\n";
        }

        return stringBoard;
    }
}
