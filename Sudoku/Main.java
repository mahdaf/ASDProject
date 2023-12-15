package Sudoku;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #3
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning
    private String playerName;
    // private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");
    JPanel buttonPanel = new JPanel();
    JButton btnSolve = new JButton("Solve");
    
    public String getPlayerName() {
            return playerName;
        }
    // Constructor
    public Main() {
        // Input player name
        playerName = JOptionPane.showInputDialog("Masukkan nama Player:");
        System.out.println("Player Name: " + playerName);

        board.setPlayerName(playerName);

        Object[] opsi = {"Easy", "Medium", "Hard"};

        // Display an option dialog that have the return value from Object [] opsi
        int pilihan = JOptionPane.showOptionDialog(
                null, // Parent component (null for middle of screen dialog)
                "Select Difficulties", // Dialog message
                "Difficulties ", // Dialog title
                JOptionPane.DEFAULT_OPTION, // Icon type (DEFAULT_OPTION for default icon)
                JOptionPane.QUESTION_MESSAGE, // Message type (QUESTION_MESSAGE for question)
                null, // Custom icon (null for default icon)
                opsi, // Option list
                opsi[0]); // Default option chosen

        System.out.println("Selected Difficulty: " + opsi[pilihan]);
        // Uses the return value to determine the next action
        if(pilihan == JOptionPane.CLOSED_OPTION) {
            System.out.println("Dialog ditutup tanpa pemilihan.");
            System.exit(0);
        } 
        if (opsi[pilihan] == opsi[0]) {
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            cp.add(board, BorderLayout.CENTER);

            JButton btnNewGameEasy = new JButton("New Game");
            btnNewGameEasy.addActionListener(e -> board.EasyGame());
            cp.add(btnNewGameEasy, BorderLayout.SOUTH);
            
            JButton btnSolveGame = new JButton("Solve");
            btnSolveGame.addActionListener(e -> board.SolveGame());
            cp.add(btnSolve, BorderLayout.SOUTH);

            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(btnNewGameEasy);
            buttonPanel.add(btnSolveGame);
            cp.add(buttonPanel, BorderLayout.SOUTH);

            board.EasyGame();

            pack();
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // to handle window-closing
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent a) {
                         JOptionPane.showMessageDialog(null, "Thank you for playing");
                         System.exit(0);
                }
            });
            setTitle("Sudoku");
            setVisible(true);
        } else if (opsi[pilihan]==opsi[1]){
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            cp.add(board, BorderLayout.CENTER);

            // Add a button to the south to re-start the game via board.newGame()
            JButton btnNewGameMedium = new JButton("New Game");
            btnNewGameMedium.addActionListener(e -> board.MediumGame()); // Add ActionListener to the button
            cp.add(btnNewGameMedium, BorderLayout.SOUTH); // Add button to the south

            JButton btnSolveGame = new JButton("Solve");
            btnSolveGame.addActionListener(e -> board.SolveGame());
            cp.add(btnSolveGame, BorderLayout.SOUTH);

            // btnSolve.addActionListener(e -> board.SolveGame());
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(btnNewGameMedium);
            buttonPanel.add(btnSolveGame);
            cp.add(buttonPanel, BorderLayout.SOUTH);
            // Initialize the game board to start the game
            board.MediumGame();

            pack();     // Pack the UI components, instead of using setSize()
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // to handle window-closing
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent a) {
                         JOptionPane.showMessageDialog(null, "Thank you for playing");
                         System.exit(0);
                }
            });
            setTitle("Sudoku");
            setVisible(true);
        } else if (opsi[pilihan]==opsi[2]){
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            cp.add(board, BorderLayout.CENTER);

            // Add a button to the south to re-start the game via board.newGame()
            JButton btnNewGameHard = new JButton("New Game");
            btnNewGameHard.addActionListener(e -> board.HardGame()); // Add ActionListener to the button
            cp.add(btnNewGameHard, BorderLayout.SOUTH); // Add button to the south

            JButton btnSolveGame = new JButton("Solve");
            btnSolveGame.addActionListener(e -> board.SolveGame());
            cp.add(btnSolveGame, BorderLayout.SOUTH);

            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(btnNewGameHard);
            buttonPanel.add(btnSolveGame);
            cp.add(buttonPanel, BorderLayout.SOUTH);
            // Initialize the game board to start the game
            board.HardGame();

            pack();
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // to handle window-closing
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent a) {
                         JOptionPane.showMessageDialog(null, "Thank you for playing");
                         System.exit(0);
                }
             });
           
            setTitle("Sudoku");
            setVisible(true);
        }
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // [TODO 1] Check "Swing program template" on how to run
        // the constructor of "Main"
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
