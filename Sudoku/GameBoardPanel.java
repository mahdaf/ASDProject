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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel{
    private static final long serialVersionUID = 1L;  // to prevent serial warning
    private String playerName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60;   // Cell width/height in pixels
    public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;
    // Board width/height in pixels

    // Define properties
    /** The game board composes of 9x9 Cells (customized JTextFields) */
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    /** It also contains a Puzzle with array numbers and isGiven */
    private Puzzle puzzle = new Puzzle();

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel

        // Allocate the 2D array of Cell, and added into JPanel.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);   // JPanel
            }
        }

        // [TODO 3] Allocate a common listener as the ActionEvent listener for all the
        // [TODO 3]
        CellInputListener listener = new CellInputListener();

        // [TODO 4] Adds this common listener to all editable cells
        // [TODO 4]
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener);   // For all editable rows and cols
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        
    }
    public void SolveGame(){
        puzzle.solve();
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    /**
     * Generate a new puzzle; and reset the gameboard of cells based on the puzzle.
     * You can call this method to start a new game.
     */
    public void EasyGame() {
        puzzle.generateNewPuzzle(20);

        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {

                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    public void MediumGame() {
        puzzle.generateMediumPuzzle();

        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    public void HardGame() {
        puzzle.generateHardPuzzle();

        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }
    /**
     * Return true if the puzzle is solved
     * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
     */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    // [TODO 2] Define a Listener Inner Class for all the editable Cells
    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell)e.getSource();

            // Retrieve the int entered
            int numberIn = Integer.parseInt(sourceCell.getText());
            // For debugging
            System.out.println("You entered " + numberIn);

            /*
             * [TODO 5] (later - after TODO 3 and 4)
             * Check the numberIn against sourceCell.number.
             * Update the cell status sourceCell.status,
             * and re-paint the cell via sourceCell.paint().
             */
            if (numberIn == sourceCell.number) {
                sourceCell.status = CellStatus.CORRECT_GUESS;
            } else {
                // Complete the code for incorrect guess
                sourceCell.status = CellStatus.WRONG_GUESS;
            }
            sourceCell.paint();   // re-paint this cell based on its status

            /*
             * [TODO 6] (later)
             * Check if the player has solved the puzzle after this move,
             *   by calling isSolved(). Put up a congratulation JOptionPane if so.
             */
            if (isSolved()) {
                // JOptionPane.showMessageDialog(null, "Congratulations!");
                //JOptionPane.showOptionDialog(null, "Pilih ukuran board", "Ukuran Board", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                Object[] opsi = {"Yes", "No"};

                // Menampilkan dialog dengan opsi dan mendapatkan nilai kembaliannya
                int pilihan = JOptionPane.showOptionDialog(
                        null, // Komponen induk (null untuk dialog tengah layar)
                        "Do you wanna play again?", // Pesan dialog
                        "Congratulations! " + playerName, // Judul dialog
                        JOptionPane.DEFAULT_OPTION, // Tipe ikon (DEFAULT_OPTION untuk ikon default)
                        JOptionPane.QUESTION_MESSAGE, // Tipe pesan (QUESTION_MESSAGE untuk pertanyaan)
                        null, // Icon kustom (null untuk ikon default)
                        opsi, // Daftar opsi
                        opsi[0]); // Opsi default yang terpilih
        
                // Menggunakan nilai kembaliannya untuk menentukan tindakan selanjutnya
                if (pilihan == JOptionPane.CLOSED_OPTION) {
                    System.out.println("Dialog ditutup tanpa pemilihan.");
                    System.exit(0);
                } else if (opsi[pilihan]==opsi[0]){
                    EasyGame();

                } else {
                   JOptionPane.showMessageDialog(null, "Thank you for playing");
                   System.exit(0);
                }
            }
        }
    }

}
