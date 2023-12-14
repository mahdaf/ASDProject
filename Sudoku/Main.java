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
        // Meminta input nama pemain
        playerName = JOptionPane.showInputDialog("Masukkan nama Player:");
        System.out.println("Player Name: " + playerName);

        board.setPlayerName(playerName);

        Object[] opsi = {"Easy", "Medium", "Hard"};

        // Menampilkan dialog dengan opsi dan mendapatkan nilai kembaliannya
        int pilihan = JOptionPane.showOptionDialog(
                null, // Komponen induk (null untuk dialog tengah layar)
                "Select Difficulties", // Pesan dialog
                "Difficulties ", // Judul dialog
                JOptionPane.DEFAULT_OPTION, // Tipe ikon (DEFAULT_OPTION untuk ikon default)
                JOptionPane.QUESTION_MESSAGE, // Tipe pesan (QUESTION_MESSAGE untuk pertanyaan)
                null, // Icon kustom (null untuk ikon default)
                opsi, // Daftar opsi
                opsi[0]); // Opsi default yang terpilih

        System.out.println("Selected Difficulty: " + opsi[pilihan]);
        // Menggunakan nilai kembaliannya untuk menentukan tindakan selanjutnya
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
            cp.add(btnSolve, BorderLayout.SOUTH);

            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(btnNewGameEasy);
            buttonPanel.add(btnSolve);
            cp.add(buttonPanel, BorderLayout.SOUTH);

            board.EasyGame();

            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Sudoku");
            setVisible(true);
        } else if (opsi[pilihan]==opsi[1]){
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            cp.add(board, BorderLayout.CENTER);

            // Add a button to the south to re-start the game via board.newGame()
            btnNewGame.addActionListener(e -> board.MediumGame()); // Add ActionListener to the button
            cp.add(btnNewGame, BorderLayout.SOUTH); // Add button to the south

            btnSolve.addActionListener(e -> board.SolveGame());
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(btnNewGame);
            buttonPanel.add(btnSolve);
            cp.add(buttonPanel, BorderLayout.SOUTH);
            // Initialize the game board to start the game
            board.MediumGame();

            pack();     // Pack the UI components, instead of using setSize()
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
            setTitle("Sudoku");
            setVisible(true);
        } else if (opsi[pilihan]==opsi[2]){
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            cp.add(board, BorderLayout.CENTER);

            // Add a button to the south to re-start the game via board.newGame()
            btnNewGame.addActionListener(e -> board.HardGame()); // Add ActionListener to the button
            cp.add(btnNewGame, BorderLayout.SOUTH); // Add button to the south

            btnSolve.addActionListener(e -> board.SolveGame());

            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(btnNewGame);
            buttonPanel.add(btnSolve);
            cp.add(buttonPanel, BorderLayout.SOUTH);
            // Initialize the game board to start the game
            board.HardGame();

            pack();     // Pack the UI components, instead of using setSize()
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
            setTitle("Sudoku");
            setVisible(true);
        }
        
            
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // [TODO 1] Check "Swing program template" on how to run
        // the constructor of "SudokuMain"
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
        Puzzle app = new Puzzle();
        app.solve();
        for(int r = 0 ; r < 9 ; r++){
            for (int c = 0 ; c < 9 ; c++){
                System.out.print(app.numbers[r][c] + " ");
            }
        System.out.println();
    }
    }
}
