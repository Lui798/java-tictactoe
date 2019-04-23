package lui798.tictactoe;

import javax.swing.*;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {

    public static TicTacToe game = new TicTacToe();

    public static void main(String[] args) {
        int val = Integer.parseInt(showInputDialog("Enter Value"));
        game.playerTurn(val);
        game.cpuTurn();
        val = Integer.parseInt(showInputDialog("Enter Value"));
        game.playerTurn(val);
        game.cpuTurn();
        val = Integer.parseInt(showInputDialog("Enter Value"));
        game.playerTurn(val);
        game.cpuTurn();

        showMessageDialog(null, game);
    }
}
