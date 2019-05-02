package lui798.tictactoe.game;

import lui798.tictactoe.util.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static lui798.tictactoe.util.Convert.*;

public class Scores extends File {
    private Path file;
    private final int TIE_INDEX = 2;
    private final int LOSS_INDEX = 1;
    private final int WIN_INDEX = 0;

    public Scores(String file) {
        this.file = Paths.get(file);
        List<String> lines = readFile(this.file);
        if (lines.size() < 1) {
            initFile();
        }
    }

    private void initFile() {
        writeFile(this.file, Arrays.asList("0", "0", "0"));
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
        List<String> lines = readFile(file);
        return lines.get(WIN_INDEX);
    }

    public String getLosses() {
        List<String> lines = readFile(file);
        return lines.get(LOSS_INDEX);
    }

    public String getTies() {
        List<String> lines = readFile(file);
        return lines.get(TIE_INDEX);
    }

    private int getScore(int index) {
        List<String> lines = readFile(file);
        return parseInt(lines.get(index));
    }

    private void addScore(int input, int index) {
        List<String> lines = readFile(file);
        lines.set(index, parseInt(input));
        writeFile(file, lines);
    }
}
