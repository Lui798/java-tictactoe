package lui798.tictactoe.game;

import lui798.tictactoe.util.File;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static lui798.tictactoe.util.Convert.*;

public class Scores extends File {
    private final int TIE_INDEX = 2;
    private final int LOSS_INDEX = 1;
    private final int WIN_INDEX = 0;

    public Scores(String file) {
        super(file);
        List<String> lines = readFile(getFile());
        if (lines.size() < 1) {
            initFile();
        }
        checkCorruptFile();
    }

    private void initFile() {
        writeFile(getFile(), Arrays.asList("0", "0", "0"));
    }

    private void checkCorruptFile() {
        List<String> lines = readFile(getFile());
        for (int i = 0; i < lines.size(); i++) {
            try { parseInt(lines.get(i)); }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e + "\nResetting scores file and exiting program, press OK to continue");
                initFile();
                System.exit(1);
            }
        }
    }

    public void addWin() {
        int score = getScore(WIN_INDEX);
        score++;
        addScore(score, WIN_INDEX);
    }

    public void addLoss() {
        int score = getScore(LOSS_INDEX);
        score++;
        addScore(score, LOSS_INDEX);
    }

    public void addTie() {
        int score = getScore(TIE_INDEX);
        score++;
        addScore(score, TIE_INDEX);
    }

    public String getWins() {
        return parseInt(getScore(WIN_INDEX));
    }

    public String getLosses() {
        return parseInt(getScore(LOSS_INDEX));
    }

    public String getTies() {
        return parseInt(getScore(TIE_INDEX));
    }

    private int getScore(int index) {
        List<String> lines = readFile(getFile());
        return parseInt(lines.get(index));
    }

    private void addScore(int input, int index) {
        List<String> lines = readFile(getFile());
        lines.set(index, parseInt(input));
        writeFile(getFile(), lines);
    }
}
