package TicTacToe;

import java.awt.*;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #1
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */

/**
 * The cells class models the ROWS-by-COLS game cells.
 */
public class Board {
   // Define named constants
   public static final int ROWS = 5;  // ROWS x COLS cells
   public static final int COLS = 5;
   // Define named constants for drawing
   public static final int CANVAS_WIDTH = Cell.SIZE * COLS;  // the drawing canvas
   public static final int CANVAS_HEIGHT = Cell.SIZE * ROWS;
   public static final int GRID_WIDTH = 8;  // Grid-line's width
   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
   public static final Color COLOR_GRID = Color.LIGHT_GRAY;  // grid lines
   public static final int Y_OFFSET = 1;  // Fine tune for better display

   // Define properties (package-visible)
   /** Composes of 2D array of ROWS-by-COLS Cell instances */
   Cell[][] cells;

   /** Constructor to initialize the game cells */
   public Board() {
      initGame();
   }

   /** Initialize the game objects (run once) */
   public void initGame() {
      cells = new Cell[ROWS][COLS]; // allocate the array
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            // Allocate element of the array
            cells[row][col] = new Cell(row, col);
               // Cells are initialized in the constructor
         }
      }
   }

   /** Reset the game cells, ready for new game */
   public void newGame() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col].newGame(); // clear the cell content
         }
      }
   }

   /**
    *  The given player makes a move on (selectedRow, selectedCol).
    *  Update cells[selectedRow][selectedCol]. Compute and return the
    *  new game state (PLAYING, DRAW, CROSS_WON, NOUGHT_WON).
    */
   public State stepGame(Seed player, int selectedRow, int selectedCol) {
      // Update game cells
      cells[selectedRow][selectedCol].content = player;

      // Compute and return the new game state
      if (cells[selectedRow][0].content == player  // 3-in-the-row
                && cells[selectedRow][1].content == player
                && cells[selectedRow][2].content == player
                && cells[selectedRow][3].content == player
                && cells[selectedRow][4].content == player
                || cells[0][selectedCol].content == player // 3-in-the-column
                && cells[1][selectedCol].content == player
                && cells[2][selectedCol].content == player
                && cells[3][selectedCol].content == player
                && cells[4][selectedCol].content == player

                || selectedRow == selectedCol  // 3-in-the-diagonal
                && cells[0][0].content == player
                && cells[1][1].content == player
                && cells[2][2].content == player
                && cells[3][3].content == player
                && cells[4][4].content == player

                || selectedRow + selectedCol == 4 // 3-in-the-opposite-diagonal
                && cells[0][4].content == player
                && cells[1][3].content == player
                && cells[2][2].content == player
                && cells[3][1].content == player
                && cells[4][0].content == player) {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        } else {
            // Nobody win. Check for DRAW (all cells occupied) or PLAYING.
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    if (cells[row][col].content == Seed.NO_SEED) {
                        return State.PLAYING; // still have empty cells
                    }
                }
            }
            return State.DRAW; // no empty cell, it's a draw
        }
   }
   /** Paint itself on the graphics canvas, given the Graphics context */
   public void paint(Graphics g) {
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
            cells[row][col].paint(g);  // ask the cell to paint itself
         }
      }
   }
}