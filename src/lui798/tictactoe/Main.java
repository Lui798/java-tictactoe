package lui798.tictactoe;

import javax.swing.*;

public class Main {

    public static TicTacToe game = new TicTacToe();
    public static int playerInput;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Tic Tac Toe! You can mark a spot by\n" +
                "entering a 1-9. You will be X and the CPU will be O.");

        while (!game.boardFilled() && game.getWinState() == 0) {
            playerTurn();
            if (game.threeInARow(playerInput, true)) {
                youWin();
                break;
            }
            if (game.cpuThreeInARow()) {
                youLose();
                break;
            }
            cpuTurn();
        }
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
        game.setWinState(1);
    }

    public static void youLose() {
        JOptionPane.showMessageDialog(null, game.toString() + "\nYou lost, the cpu got three in a row.");
        game.setWinState(2);
    }
}
