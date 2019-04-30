package lui798.tictactoe;

import javax.swing.*;

public class Main {

    public static File scores = new File("scores.txt");
    public static TicTacToe game = new TicTacToe();
    public static int playerInput;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Tic Tac Toe! You can mark a spot by\n" +
                "entering a 1-9. You will be X and the CPU will be O.");

        while (!game.boardFilled()) {
            playerTurn();
            if (game.threeInARow(playerInput, true)) {
                game.setWinState(1);
                break;
            }
            if (game.boardFilled()) break;
            cpuTurn();
            if (game.cpuThreeInARow()) {
                game.setWinState(2);
                break;
            }
        }

        if (game.getWinState() == 1) {
            youWin();
        }
        else if (game.getWinState() == 2) {
            youLose();
        }
        else if (game.getWinState() == 0) {
            draw();
        }

        playAgain();
    }

    public static void playerTurn() {
        String board = game.toString();
        try {
            playerInput = Integer.parseInt(JOptionPane.showInputDialog(board + "\nEnter a place to mark 1-9:"));
        }
        catch (NumberFormatException e) {
            playerInput = -1;
        }

        if (!game.playerTurn(playerInput)) {
            JOptionPane.showMessageDialog(null, "That spot if already filled or you\n" +
                    "entered an invalid input.");
            playerTurn();
        }
    }

    public static void cpuTurn() {
        game.cpuTurn();
    }

    public static void youWin() {
        JOptionPane.showMessageDialog(null, game.toString() + "\nYou win, you got three in a row!");
    }

    public static void youLose() {
        JOptionPane.showMessageDialog(null, game.toString() + "\nYou lost, the cpu got three in a row.");
    }

    public static void draw() {
        JOptionPane.showMessageDialog(null, game.toString() + "\nIts a draw, the board is filed " +
                "\nwith no winners.");
    }

    public static void playAgain() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Play again?",
                "Game Over", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            game = new TicTacToe();
            main(new String[0]);
        }
    }
}
