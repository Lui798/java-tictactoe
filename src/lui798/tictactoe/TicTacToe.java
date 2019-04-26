package lui798.tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToe {

    private Board gameboard = new Board();
    private static final String PLAYER_MARK = "X";
    private static final String CPU_MARK = "O";
    private static CPU cpu = new CPU();
    private static int winState = 0;

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
            gameboard.markBoard(row, col, PLAYER_MARK);
        }
        return true;
    }

    public void cpuTurn() {
        int col = 0, row = 0, val = 0;

        val = cpuDecide();

        if (val < 0) {
            cpu.randomNum();

            val = cpu.getNum();
            row = val / 3;
            col = val % 3;

            while (!gameboard.getMark(row, col).equals("•")) {
                cpu.randomNum();

                val = cpu.getNum();
                row = val / 3;
                col = val % 3;
            }
            cpu.setMarksOnBoard();
        }

        else {
            row = val / 3;
            col = val % 3;
        }

        gameboard.markBoard(row, col, CPU_MARK);
    }

    private int cpuDecide() {
        String[][] boardArray = gameboard.getBoard();
        int s = gameboard.getSize();
        int x = 0, y = 0, n = 0;
        Random rand = new Random();
        ArrayList<String> options = new ArrayList<String>();

        for (int i = 0; i < s; i++) {
            if (boardArray[i][0] == PLAYER_MARK && boardArray[i][1] == PLAYER_MARK) {
                options.add(Integer.toString(i) + Integer.toString(2));
            }
            if (boardArray[i][1] == PLAYER_MARK && boardArray[i][2] == PLAYER_MARK) {
                options.add(Integer.toString(i) + Integer.toString(0));
            }
            if (boardArray[i][0] == PLAYER_MARK && boardArray[i][2] == PLAYER_MARK) {
                options.add(Integer.toString(i) + Integer.toString(1));
            }
        }
        for (int i = 0; i < s; i++) {
            if (boardArray[0][i] == PLAYER_MARK && boardArray[1][i] == PLAYER_MARK) {
                options.add(Integer.toString(2) + Integer.toString(i));
            }
            if (boardArray[1][i] == PLAYER_MARK && boardArray[2][i] == PLAYER_MARK) {
                options.add(Integer.toString(0) + Integer.toString(i));
            }
            if (boardArray[0][i] == PLAYER_MARK && boardArray[2][i] == PLAYER_MARK) {
                options.add(Integer.toString(1) + Integer.toString(i));
            }
        }
        System.out.println("S:" + (options.size()));
        if (options.size() < 1) return -1;

        n = rand.nextInt(options.size());
        x = Integer.parseInt(options.get(n).substring(0, 1));
        y = Integer.parseInt(options.get(n).substring(1));

        if (!gameboard.getMark(x, y).equals("•")) return -1;
        System.out.println("X:" + x + " Y:" + y + " N:" + n);
        return coordsToInt(x, y) - 1;
    }

    private int coordsToInt(int x, int y) {
        return x * 3 + y + 1;
    }

    public boolean cpuThreeInARow() {
        return threeInARow(cpu.getNum() + 1, false);
    }

    public boolean threeInARow(int val, boolean isPlayer) {
        String[][] boardArray = gameboard.getBoard();
        int col = 0, row = 0, diag = 0, rdiag = 0;
        val--;
        int x = val / 3;
        int y = val % 3;
        int n = gameboard.getSize();

        if (isPlayer) {
            for (int i = 0; i < n; i++) {
                if (boardArray[x][i] == PLAYER_MARK) col++;
                if (boardArray[i][y] == PLAYER_MARK) row++;
                if (boardArray[i][i] == PLAYER_MARK) diag++;
                if (boardArray[i][n-i-1] == PLAYER_MARK) rdiag++;
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                if (boardArray[x][i] == CPU_MARK) col++;
                if (boardArray[i][y] == CPU_MARK) row++;
                if (boardArray[i][i] == CPU_MARK) diag++;
                if (boardArray[i][n-i-1] == CPU_MARK) rdiag++;
            }
        }


        if (row == n || col == n || diag == n || rdiag == n)
            return true;
        return false;
    }

    public boolean boardFilled() {
        String[][] boardArray = gameboard.getBoard();
        int num = 0;
        int size = gameboard.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!boardArray[i][j].equals("•")) num++;
            }
        }

        if (num == size * size) {
            return true;
        }
        return false;
    }

    public void setWinState(int winState) {
        this.winState = winState;
    }

    public int getWinState() {
        return winState;
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
