package lui798.tictactoe.game;

public class Board {

    private static final int SIZE = 3;
    private String[][] board = new String[SIZE][SIZE];

    public Board() {
        this.initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                markBoard(i, j, "•");
            }
        }
    }

    public void markBoard(int row, int col, String mark) {
        board[row][col] = mark;
    }

    public boolean checkForMatch(int x1, int y1, int x2, int y2, String mark) {
        if (board[x1][y1] == mark && board[x2][y2] == mark) return true;
        else return false;
    }

    public String getMark(int row, int col) {
        return board[row][col];
    }

    public String[][] getBoard() {
        return board;
    }

    public int getSize() {
        return SIZE;
    }
}
