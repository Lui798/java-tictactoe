package lui798.tictactoe;

public class TicTacToe {

    private Board gameboard = new Board();
    private static final String PLAYER_MARK = "X";
    private static final String CPU_MARK = "X";
    private static CPU cpu = new CPU();

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
        cpu.randomNum();

        int val = cpu.getNum();
        int row = val / 3;
        int col = val % 3;

        while (!gameboard.getMark(row, col).equals("•")) {
            cpu.randomNum();

            val = cpu.getNum();
            row = val / 3;
            col = val % 3;
        }

        gameboard.markBoard(row, col, CPU_MARK);
    }

    public boolean cpuThreeInARow() {
        return threeInARow(cpu.getNum(), false);
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
