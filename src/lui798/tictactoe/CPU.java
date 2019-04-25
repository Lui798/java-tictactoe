package lui798.tictactoe;

import java.util.Random;

public class CPU {
    private Random rand = new Random();
    private int randNum = 0;
    private int marksOnBoard = 0;
    private final int MAX_NUM = 9;

    public CPU() {
        this.randomNum();
    }

    public void randomNum() {
        rand = new Random();
        this.randNum = rand.nextInt(MAX_NUM);
    }

    public int getNum() {
        return this.randNum;
    }

    public int getMarksOnBoard() {
        return marksOnBoard;
    }

    public void setMarksOnBoard() {
        marksOnBoard++;
    }
}
