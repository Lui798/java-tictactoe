package lui798.tictactoe;

public class Board {

    private static final int SIZE = 3;
    private String[][] board = new String[SIZE][SIZE];

    public Board() {
        this.initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                markBoard(i, j, "•");
            }
        }
    }

    public void markBoard(int row, int col, String mark) {
        board[row][col] = mark;
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
