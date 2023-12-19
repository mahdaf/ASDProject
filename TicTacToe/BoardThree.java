package TicTacToe;

import java.awt.*;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #3
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */

/**
 * The cells class models the ROWS-by-COLS game cells.
 */
public class BoardThree {
   // Define named constants
   public static final int ROWS = 3;  // ROWS x COLS cells
   public static final int COLS = 3;
   // Define named constants for drawing
   public static final int CANVAS_WIDTH = Cell.SIZE * COLS;  // The canvas width
   public static final int CANVAS_HEIGHT = Cell.SIZE * ROWS; // The canvas height
   public static final int GRID_WIDTH = 10;  // Grid-line's width
   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
   public static final Color COLOR_GRID = new Color(13, 161,146);  // Grid lines
   public static final int Y_OFFSET = 1;  // Fine tune for better display

   // Define properties (package-visible)
   /** Composes of 2D array of ROWS-by-COLS Cell instances */
   CellThree[][] cells;

   /** Constructor to initialize the game cells */
   public BoardThree() {
      initGameThree();
   }

   /** Initialize the game objects (run once) */
   public void initGameThree() {
      cells = new CellThree[ROWS][COLS]; // Allocate the array
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            // Allocate element of the array
            cells[row][col] = new CellThree(row, col);
               // Cells are initialized in the constructor
         }
      }
   }

   /** Reset the game cells, ready for new game */
   public void newGameThree() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col].newGameThree(); // Clear the cell contentThree
         }
      }
   }

   /**
    *  The given player makes a move on (selectedRow, selectedCol).
    *  Update cells[selectedRow][selectedCol]. Compute and return the
    *  New game state (PLAYING, DRAW, CROSS_WON, NOUGHT_WON).
    */
   public State stepGameThree(Seed player, int selectedRow, int selectedCol) {
      // Update game cells
      cells[selectedRow][selectedCol].contentThree = player;

      // Compute and return the new game state
      if (cells[selectedRow][0].contentThree == player  // 3-in-the-row
                && cells[selectedRow][1].contentThree == player
                && cells[selectedRow][2].contentThree == player

                || cells[0][selectedCol].contentThree == player // 3-in-the-column
                && cells[1][selectedCol].contentThree == player
                && cells[2][selectedCol].contentThree == player

                || selectedRow == selectedCol  // 3-in-the-diagonal
                && cells[0][0].contentThree == player
                && cells[1][1].contentThree == player
                && cells[2][2].contentThree == player

                || selectedRow + selectedCol == 2 // 3-in-the-opposite-diagonal
                && cells[0][2].contentThree == player
                && cells[1][1].contentThree == player
                && cells[2][0].contentThree == player) {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        } else {
            // Nobody win. Check for DRAW (all cells occupied) or PLAYING.
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    if (cells[row][col].contentThree == Seed.NO_SEED) {
                        return State.PLAYING; // Still have empty cells
                    }
                }
            }
            return State.DRAW; // No empty cell, it's a draw
        }
   }
   /** Paint itself on the graphics canvas, given the Graphics context */

    public void paintThree(Graphics g) {
      // Draw the grid-lines
      g.setColor(COLOR_GRID);
      for (int row = 1; row < ROWS; ++row) {
         g.fillRoundRect(0, Cell.SIZE * row - GRID_WIDHT_HALF,
               CANVAS_WIDTH - 1, GRID_WIDTH,
               GRID_WIDTH, GRID_WIDTH);
      }
      for (int col = 1; col < COLS; ++col) {
         g.fillRoundRect(Cell.SIZE * col - GRID_WIDHT_HALF, 0 + Y_OFFSET,
               GRID_WIDTH, CANVAS_HEIGHT - 1,
               GRID_WIDTH, GRID_WIDTH);
      }

      // Draw all the cells
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col].paintThree(g);  // Ask the cell to paint itself
         }
      }
   }
}