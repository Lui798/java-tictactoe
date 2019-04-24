package lui798.tictactoe;

import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class Main {

    public static TicTacToe game = new TicTacToe();

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Tic Tac Toe! You can mark a spot by\n" +
                "entering a 1-9. You will be X and the CPU will be O.");

        while (!game.boardFilled()) {
            playerTurn();

        }
    }

    public static void playerTurn() {
        String board = game.toString();
        int input;
        try {
            input = Integer.parseInt(JOptionPane.showInputDialog(board + "\nEnter a place to mark 1-9:"));
        }
        catch (NumberFormatException e) {
            input = -1;
        }

        if (!game.playerTurn(input)) {
            JOptionPane.showMessageDialog(null, "That spot if already filled or you\n" +
                    "entered an invalid input.");
            playerTurn();
        }

        game.threeInARow(input, true);
    }

    public static void youWin() {

    }

    public static void youLose() {

    }
}
