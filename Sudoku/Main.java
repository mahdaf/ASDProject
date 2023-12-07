package Sudoku;
/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #1
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning
    private String playerName;
    // private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");
    
    public String getPlayerName() {
            return playerName;
        }
    // Constructor
    public Main() {
        
        // Meminta input nama pemain
        playerName = JOptionPane.showInputDialog("Masukkan nama Player:");
        board.setPlayerName(playerName);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        // Add a button to the south to re-start the game via board.newGame()
        btnNewGame.addActionListener(e -> board.newGame()); // Add ActionListener to the button
        cp.add(btnNewGame, BorderLayout.SOUTH); // Add button to the south

        // Initialize the game board to start the game
        board.newGame();

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // [TODO 1] Check "Swing program template" on how to run
        // the constructor of "SudokuMain"
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
