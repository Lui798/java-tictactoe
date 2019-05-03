package lui798.tictactoe.game;

import java.util.ArrayList;
import java.util.Random;

import static lui798.tictactoe.util.Convert.*;

public class TicTacToe {

    private static final String PLAYER_MARK = "X";
    private static final String CPU_MARK = "O";
    private static final String EMPTY_MARK = "•";
    private Board board;
    private static CPU cpu;
    private static int winState;

    public TicTacToe() {
        board = new Board();
        cpu = new CPU();
        winState = 0;
    }

    public boolean playerTurn(int val) {
        //return if val is invalid
        if (val > 9 || val < 1) return false;
        //Reduce value by 1 for use with array index of 0
        val--;
        //convert number into rows and columns for 2d array
        int row = val / 3;
        int col = val % 3;

        //return false if spot has already been marked
        if (!board.getMark(row, col).equals(EMPTY_MARK)) {
            return false;
        }
        else {
            //mark spot if it hasn't been filled
            board.markBoard(row, col, PLAYER_MARK);
        }
        return true;
    }

    public void cpuTurn() {
        int col = 0, row = 0, val = 0;

        //cpu makes smart decision and returns -1 if it cant
        val = cpuDecide();

        //if cpuDecide returns -1 generate random position
        if (val < 0) {
            cpu.randomNum();

            val = cpu.getNum();
            row = val / 3;
            col = val % 3;

            while (!board.getMark(row, col).equals(EMPTY_MARK)) {
                cpu.randomNum();

                val = cpu.getNum();
                row = val / 3;
                col = val % 3;
            }
        }
        else {
            row = val / 3;
            col = val % 3;
        }

        board.markBoard(row, col, CPU_MARK);
    }

    //cpu decides where to place a mark based on if the cpu or player can get three in a row
    private int cpuDecide() {
        String[][] boardArray = board.getBoard();
        int s = board.getSize();
        int x = 0, y = 0, n = 0;
        Random rand = new Random();
        //List of places the cpu can place a mark
        ArrayList<String> options = new ArrayList<String>();

        //Check if there are two player or cpu marks in a row then add that to list
        for (int i = 0; i < s; i++) {
            if (board.checkForMatch(i, 0, i, 1, PLAYER_MARK)
                    || board.checkForMatch(i, 0, i, 1, CPU_MARK))
                options.add(parseInt(i) + parseInt(2));
            if (board.checkForMatch(i, 1, i, 2, PLAYER_MARK)
                    || board.checkForMatch(i, 1, i, 2, CPU_MARK))
                options.add(parseInt(i) + parseInt(0));
            if (board.checkForMatch(i, 0, i, 2, PLAYER_MARK)
                    || board.checkForMatch(i, 0, i, 2, CPU_MARK))
                options.add(parseInt(i) + parseInt(1));
        }
        //Check if there are two player or cpu marks in a column then add that to list
        for (int i = 0; i < s; i++) {
            if (board.checkForMatch(0, i, 1, i, PLAYER_MARK)
                    || board.checkForMatch(0, i, 1, i, CPU_MARK))
                options.add(parseInt(2) + parseInt(i));
            if (board.checkForMatch(1, i, 2, i, PLAYER_MARK)
                    || board.checkForMatch(1, i, 2, i, CPU_MARK))
                options.add(parseInt(0) + parseInt(i));
            if (board.checkForMatch(0, i, 2, i, PLAYER_MARK)
                    || board.checkForMatch(0, i, 2, i, CPU_MARK))
                options.add(parseInt(1) + parseInt(i));
        }
        //Check if there are two player or cpu marks in a diagonal then add that to list
        if (board.checkForMatch(0, 0, 1, 1, PLAYER_MARK)
                || board.checkForMatch(0, 0, 1, 1, CPU_MARK))
            options.add(parseInt(2) + parseInt(2));
        if (board.checkForMatch(1, 1, 2, 2, PLAYER_MARK)
                || board.checkForMatch(1, 1, 2, 2, CPU_MARK))
            options.add(parseInt(0) + parseInt(0));
        if (board.checkForMatch(0, 0, 2, 2, PLAYER_MARK)
                || board.checkForMatch(0, 0, 2, 2, CPU_MARK))
            options.add(parseInt(1) + parseInt(1));

        if (options.size() < 1) return -1;

        n = rand.nextInt(options.size());
        x = Integer.parseInt(options.get(n).substring(0, 1));
        y = Integer.parseInt(options.get(n).substring(1));

        if (!board.getMark(x, y).equals(EMPTY_MARK)) return -1;
        return coordsToInt(x, y) - 1;
    }

    //Convert coords for board array into user-friendy number 1-9
    private int coordsToInt(int x, int y) {
        return x * 3 + y + 1;
    }

    public boolean cpuThreeInARow() {
        return threeInARow(cpu.getNum() + 1, false);
    }

    //Check for a win
    public boolean threeInARow(int val, boolean isPlayer) {
        String[][] boardArray = board.getBoard();
        int col = 0, row = 0, diag = 0, rdiag = 0; //values here are added to based on how many marks are in the row, col, etc. that the given mark is in
        val--;
        //Gets position in array of the last marked spot based on input
        int x = val / 3;
        int y = val % 3;
        int n = board.getSize();

        if (isPlayer) {
            for (int i = 0; i < n; i++) {
                if (boardArray[x][i] == PLAYER_MARK) col++; //adds to col, row, etc. for every mark in a row
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

        //if marks in row col, etc. equals there return true for three in a row
        if (row == n || col == n || diag == n || rdiag == n)
            return true;
        return false;
    }

    public boolean boardFilled() {
        String[][] boardArray = board.getBoard();
        int num = 0;
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!boardArray[i][j].equals(EMPTY_MARK)) num++;
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
        String[][] boardArray = board.getBoard();
        String stringBoard = "";

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                stringBoard += boardArray[i][j] + "   ";
            }
            stringBoard += "\n";
        }

        return stringBoard;
    }
}
