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

public class Cell extends JTextField {
    private static final long serialVersionUID = 1L;  // To prevent serial warning

    // Define named constants for JTextField's colors and fonts
    //  To be chosen based on CellStatus
    public static final Color BG_GIVEN = new Color(20, 189, 172); // RGB new Color(68, 68, 68)
    public static final Color FG_GIVEN = Color.white;
    public static final Color FG_NOT_GIVEN = Color.WHITE;
    public static final Color BG_TO_GUESS  = new Color(13, 152,138);
    public static final Color BG_CORRECT_GUESS =  new Color(20, 189, 172);
    public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
    public static final Font FONT_NUMBERS = new Font("Poppins", Font.PLAIN,30);

    // Define properties (package-visible)
    /** The row and column number [0-8] of this cell */
    int row, col, value;
    /** The puzzle number [1-9] for this cell */
    int number;
    /** The status of this cell defined in enum CellStatus */
    CellStatus status;

    /** Constructor */
    public Cell(int row, int col) {

        super();   // JTextField
        this.row = row;
        this.col = col;

        // Inherited from JTextField: Beautify all the cells once for all
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
    }

    public Cell(int row, int col, int value) {
        super();   // JTextField

        this.row = row;
        this.col = col;
        this.value = value;

        // Inherited from JTextField: Beautify all the cells once for all
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
    }

    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getValue(){
        return value;
    }

    /** Reset this cell for a new game, given the puzzle number and isGiven */
    public void newGame(int num, boolean isGiven) {
        setText(num == 0 ? "" : String.valueOf(num));
        setEditable(num == 0);
         // Set the isGiven attribute
        if(isGiven){
            status = CellStatus.GIVEN;
        } else{
            status = CellStatus.TO_GUESS;
        }
        this.number= num;
        paint(); // Repaint the cell
    }

    /** This Cell (JTextField) paints itself based on its status */
    public void paint() {
        if (status == CellStatus.GIVEN) {
            // Inherited from JTextField: Set display properties
            super.setText(number + "");
            super.setEditable(false);
            super.setBackground(BG_GIVEN);
            super.setForeground(FG_GIVEN);
        } else if (status == CellStatus.TO_GUESS) {
            // Inherited from JTextField: Set display properties
            super.setText("");
            super.setEditable(true);
            super.setBackground(BG_TO_GUESS);
            super.setForeground(FG_NOT_GIVEN);
        } else if (status == CellStatus.CORRECT_GUESS) {  // From TO_GUESS
            super.setBackground(BG_CORRECT_GUESS);
        } else if (status == CellStatus.WRONG_GUESS) {    // From TO_GUESS
            super.setBackground(BG_WRONG_GUESS);
        }
    }
}
