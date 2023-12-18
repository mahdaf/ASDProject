package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 * The Board and Cell classes are separated in their own classes.
 */
public class Main extends JPanel {
   private static final long serialVersionUID = 1L; // to prevent serializable warning

    private String player1Name;
    private String player2Name;
    private String boardSize;

// Define named constants for the drawing graphics
   public static final String TITLE = "Tic Tac Toe";
   public static final Color COLOR_BG = new Color(20, 189, 172);
   public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
   public static final Color COLOR_CROSS = new Color(242, 235, 211);  // Red #EF6950
   public static final Color COLOR_NOUGHT = new Color(84, 84, 84); // Blue #409AE1
   public static final Font FONT_STATUS = new Font("Poppins", Font.PLAIN, 16);

   //Player name
   public String getPlayer1Name(){
    return player1Name;
   }

   public String getPlayer2Name(){
    return player2Name;
   }

   public String getBoardSize(){
    return boardSize;
   }

   // Define game objects
   private Board board;         // the game board
   private BoardThree boardThree; 
   private State currentState;  // the current state of the game
   private Seed currentPlayer;  // the current player
   private State currentStateThree;  // the current state of the game
   private Seed currentPlayerThree;  // the current player
   private JLabel statusBar;    // for displaying status message
   private JLabel statusBarThree;    // for displaying status message
   

   /** Constructor to setup the UI and game components */
   public Main() {
    // Meminta input nama pemain 1
    player1Name = JOptionPane.showInputDialog("Masukkan nama Player 1:");
    // Meminta input nama pemain 2
    player2Name = JOptionPane.showInputDialog("Masukkan nama Player 2:");

    Object[] opsi = {"3x3", "5x5"};
        // Display an option dialog that have the return value from Object [] opsi
        int boardSize = JOptionPane.showOptionDialog(
                null, // Parent component (null for middle of screen dialog)
                "Select Board Size", // Dialog message
                "Size : ", // Dialog title
                JOptionPane.DEFAULT_OPTION, // Icon type (DEFAULT_OPTION for default icon)
                JOptionPane.QUESTION_MESSAGE, // Message type (QUESTION_MESSAGE for question)
                null, // Custom icon (null for default icon)
                opsi, // Option list
                opsi[0]); // Default option chosen

    if(boardSize == JOptionPane.CLOSED_OPTION) {
      System.out.println("Dialog ditutup tanpa pemilihan.");
      System.exit(0);
      } else if (opsi[boardSize]==opsi[0]) {

            super.addMouseListener(new MouseAdapter() {
               @Override
               
               public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
                  int mouseX = e.getX();
                  int mouseY = e.getY();
                  // Get the row and column clicked
                  int row = mouseY / Cell.SIZE;
                  int col = mouseX / Cell.SIZE;

                  if (currentStateThree == State.PLAYING) {
                     if (row >= 0 && row < BoardThree.ROWS && col >= 0 && col < BoardThree.COLS
                           && boardThree.cells[row][col].contentThree == Seed.NO_SEED) {
                        // Update cells[][] and return the new game state after the move
                        currentStateThree = boardThree.stepGameThree(currentPlayerThree, row, col);
                        // Switch player
                        currentPlayerThree = (currentPlayerThree == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                     }
                     
                  } else {        // game over
                     newGameThree();  // restart the game
                  }
                  // Refresh the drawing canvas
                  repaint();  // Callback paintComponent().
               }
            });
             // Setup the status bar (JLabel) to display status message
      
      // Set up Game
    
      } else if (opsi[boardSize]==opsi[1]){
            super.addMouseListener(new MouseAdapter() {
               @Override

               public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
                  int mouseX = e.getX();
                  int mouseY = e.getY();
                  // Get the row and column clicked
                  int row = mouseY / Cell.SIZE;
                  int col = mouseX / Cell.SIZE;

                  if (currentState == State.PLAYING) {
                     if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                           && board.cells[row][col].content == Seed.NO_SEED) {
                        // Update cells[][] and return the new game state after the move
                        currentState = board.stepGame(currentPlayer, row, col);
                        // Switch player
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                     }
                  } else {        // game over
                     newGame();  // restart the game
                  }
                  // Refresh the drawing canvas
                  repaint();  // Callback paintComponent().
               }  
            });
             // Setup the status bar (JLabel) to display status message
      }

      // This JPanel fires MouseEvent
      
      if(opsi[boardSize]==opsi[0]){
         initGameThree();
         newGameThree();

         statusBarThree = new JLabel();
         statusBarThree.setFont(FONT_STATUS);
         statusBarThree.setBackground(COLOR_BG_STATUS);
         statusBarThree.setOpaque(true);
         statusBarThree.setPreferredSize(new Dimension(300, 30));
         statusBarThree.setHorizontalAlignment(JLabel.LEFT);
         statusBarThree.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));
         super.setLayout(new BorderLayout());
         super.add(statusBarThree, BorderLayout.PAGE_END); // same as SOUTH
         super.setPreferredSize(new Dimension(BoardThree.CANVAS_WIDTH, BoardThree.CANVAS_HEIGHT + 30));
               // account for statusBar in height
         super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
      } else if (opsi[boardSize]==opsi[1]){
         initGame();
         newGame();

         statusBar = new JLabel();
         statusBar.setFont(FONT_STATUS);
         statusBar.setBackground(COLOR_BG_STATUS);
         statusBar.setOpaque(true);
         statusBar.setPreferredSize(new Dimension(300, 30));
         statusBar.setHorizontalAlignment(JLabel.LEFT);
         statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));
         super.setLayout(new BorderLayout());
         super.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
         super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
               // account for statusBar in height
         super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
      } 
   }

   /** Initialize the game (run once) */
   public void initGame() {
      board = new Board();  // allocate the game-board
   }

   /** Reset the game-board contents and the current-state, ready for new game */
   public void newGame() {
      for (int row = 0; row < Board.ROWS; ++row) {
         for (int col = 0; col < Board.COLS; ++col) {
            board.cells[row][col].content = Seed.NO_SEED; // all cells empty
         }
      }
      currentPlayer = Seed.CROSS;    // cross plays first
      currentState = State.PLAYING;  // ready to play
   }

   public void initGameThree() {
      boardThree = new BoardThree();  // allocate the game-board
   }

   /** Reset the game-board contents and the current-state, ready for new game */
   public void newGameThree() {
      for (int row = 0; row < BoardThree.ROWS; ++row) {
         for (int col = 0; col < BoardThree.COLS; ++col) {
            boardThree.cells[row][col].contentThree = Seed.NO_SEED; // all cells empty
         }
      }
      currentPlayerThree = Seed.CROSS;    // cross plays first
      currentStateThree = State.PLAYING;  // ready to play
   }

   /** Custom painting codes on this JPanel */
   @Override
   public void paintComponent(Graphics g) {  // Callback via repaint()

      super.paintComponent(g);
      setBackground(COLOR_BG); // set its background color



      // Print status-bar message
      if (currentState == State.PLAYING) {
         board.paint(g);
        statusBar.setForeground(Color.BLACK);
        statusBar.setText((currentPlayer == Seed.CROSS) ?
                player1Name + "'s Turn"  :  player2Name + "'s Turn");
      } else if (currentState == State.DRAW) {
         board.paint(g);
         statusBar.setForeground(Color.RED);
         statusBar.setText("It's a Draw! Click to play again.");
      } else if (currentState == State.CROSS_WON) {
         board.paint(g);
         statusBar.setForeground(new Color(8, 137,91));
         statusBar.setText( player1Name +" Won! Click to play again.");
      } else if (currentState == State.NOUGHT_WON) {
         board.paint(g);
         statusBar.setForeground(new Color(8, 137,91));
         statusBar.setText( player2Name +" Won! Click to play again.");
      }

      if (currentStateThree == State.PLAYING) {
         boardThree.paintThree(g);  // ask the game board to paint itself
        statusBarThree.setForeground(Color.BLACK);
        statusBarThree.setText((currentPlayerThree == Seed.CROSS) ?
                player1Name + "'s Turn"  :  player2Name + "'s Turn");
      } else if (currentStateThree == State.DRAW) {
         boardThree.paintThree(g);  // ask the game board to paint itself
         statusBarThree.setForeground(Color.RED);
         statusBarThree.setText("It's a Draw! Click to play again.");
      } else if (currentStateThree == State.CROSS_WON) {
         boardThree.paintThree(g);  // ask the game board to paint itself
         statusBarThree.setForeground(new Color(8, 137,91));
         statusBarThree.setText( player1Name +" Won! Click to play again.");
      } else if (currentStateThree == State.NOUGHT_WON) {
         boardThree.paintThree(g);  // ask the game board to paint itself
         statusBarThree.setForeground(new Color(8, 137,91));
         statusBarThree.setText( player2Name +" Won! Click to play again.");
      }
   }

   /** The entry "main" method */
   public static void main(String[] args) {

     
      // Run GUI construction codes in Event-Dispatching thread for thread safety
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            JFrame frame = new JFrame(TITLE);
            // Add a window listener to capture frame close events
            frame.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null, "Thank you for playing");
                        System.exit(0);
               }
            });
            // Set the content-pane of the JFrame to an instance of main JPanel
            frame.setContentPane(new Main());
            
            }

      });
   }
}