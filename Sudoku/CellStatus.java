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

public enum CellStatus {
    GIVEN,         // Clue, no need to guess
    TO_GUESS,      // Need to guess - not attempted yet
    CORRECT_GUESS, // Need to guess - correct guess
    WRONG_GUESS    // Need to guess - wrong guess
    // The puzzle is solved if none of the cells have
    //  Status of TO_GUESS or WRONG_GUESS
}
