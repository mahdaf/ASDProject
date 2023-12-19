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
    private static final long serialVersionUID = 1L;
    private String playerName;
    private GameBoardPanel board = new GameBoardPanel();
    private JPanel buttonPanel = new JPanel();
    private JMenuBar difficulty;
    private JMenu diffmenu;
    private JMenuItem easy, medium, hard;

    public String getPlayerName() {
        return playerName;
    }

    private void updateButtonActions(int pilihan) {
        
        buttonPanel.removeAll();

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());
        cp.add(board, BorderLayout.CENTER);
        JButton btnNewGame = new JButton("New Game");
        JButton btnSolveGame = new JButton("Solve");

        btnNewGame.addActionListener(e -> board.NewGame(pilihan));
        btnSolveGame.addActionListener(e -> board.SolveGame());


        difficulty = new JMenuBar();
        diffmenu = new JMenu("  Select Difficulty Level  ");

        easy = new JMenuItem("Easy");
        medium = new JMenuItem("Medium");
        hard = new JMenuItem("Hard");

        diffmenu.add(easy);
        diffmenu.add(medium);
        diffmenu.add(hard);

        difficulty.add(diffmenu);
        buttonPanel.add(difficulty);
        buttonPanel.add(btnNewGame);
        buttonPanel.add(btnSolveGame);

        cp.add(buttonPanel, BorderLayout.SOUTH);

        // Set action listeners for the menu items
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice  = 0;
                board.NewGame(choice);
                updateButtonActions(choice);
            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice  = 1;
                board.NewGame(choice);
                updateButtonActions(choice);
            }
        });
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice  = 2;
                board.NewGame(choice);
                updateButtonActions(choice);
            }
        });
    }

    public Main() throws Exception {
        playerName = JOptionPane.showInputDialog("Masukkan nama Player:");
        System.out.println("Player Name: " + playerName);

        board.setPlayerName(playerName);

        Object[] opsi = {"Easy", "Medium", "Hard"};

        // Display the option dialog to let the user choose the difficulty
        int pilihan = JOptionPane.showOptionDialog(
                null,
                "Select Difficulties",
                "Difficulties ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opsi,
                opsi[0]);

        System.out.println("Selected Difficulty: " + opsi[pilihan]);

        // If the user cancels the option dialog, exit the program
        if (pilihan == JOptionPane.CLOSED_OPTION) {
            System.out.println("Dialog ditutup tanpa pemilihan.");
            System.exit(0);
        }

        updateButtonActions(pilihan);

        
        if (opsi[pilihan] == opsi[0]) {
            board.NewGame(pilihan);
        } else if (opsi[pilihan] == opsi[1]) {
            board.NewGame(pilihan);
        } else if (opsi[pilihan] == opsi[2]) {
            board.NewGame(pilihan);
        }

    
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}