package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Puzzle {
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    // Constructor
    public Puzzle() {
        super();
    }

    // Generate a new puzzle with unique numbers in each row and column
    public void generateNewPuzzle(int difficulty) {
        List<Integer> availableNumbers = new ArrayList<>();
        for (int i = 1; i <= SudokuConstants.GRID_SIZE; ++i) {
            availableNumbers.add(i);
        }
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            Collections.shuffle(availableNumbers);
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = availableNumbers.get(col);
            } 
        }
        
        // Randomly set some cells as given based on difficulty
        Random random = new Random();
        int cellsToKeep = SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE - difficulty;

        for (int i = 0; i < cellsToKeep; ++i) {
            int row = random.nextInt(SudokuConstants.GRID_SIZE);
            int col = random.nextInt(SudokuConstants.GRID_SIZE);
            isGiven[row][col] = true;
        }
        for (int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(isGiven[i][j]+" ");
            }
            System.out.println();
        }
    
    }

    public boolean solve(){
        Stack<Cell> stack = new Stack<>();
        boolean[][] isLocked = setLocked(numbers);
        int curRow = 0;
        int curCol = 0;
        int curValue =1;
        int time = 0 ;
        while(stack.size() < 81){
            time++;
            if(isLocked[curRow][curCol]){
                Cell lockedCell = new Cell(curRow, curCol, numbers[curRow][curCol]);
                stack.push(lockedCell);
                curRow = curRow + (curCol+1)/9;
                curCol = (curCol+1)%9;
                continue;
            }
            for (;curValue <= 9 ; curValue++){
                if (isValid(numbers, curRow, curCol, curValue)){
                    break;
                }
            }
            if(curValue <= 9){
                Cell cell = new Cell(curRow, curCol, curValue);
                numbers[curRow][curCol] = curValue;
                stack.push(cell);
                curRow = curRow + (curCol+1)/9;
                curCol = (curCol+1)%9;
                curValue = 1;
            }else{
                if (stack.size() > 0) {
                    // Assign to a Cell variable the top of the stack (stack.pop())
                    Cell cell = stack.pop();
                    // while the Cell is locked
                    while (isLocked[cell.row][cell.col]) {
                        // if stack size is greater than 0
                        if (stack.size() > 0) {
                            // assign to the Cell variable the top of the stack (i.e. pop)
                            cell = stack.pop();
                        } else {
                            // print out the number of steps (time)
                            // return false (no solution found)
                            System.out.println("Number of steps: " + time);
                            return false;
                        }
                    }
                    // assign to curRow the row value of the Cell
                    curRow = cell.row;
                    // assign to curCol the col value of the Cell
                    curCol = cell.col;
                    // assign to curValue the value of the Cell + 1
                    curValue = cell.value + 1;
                    // set the value of the numbers Cell at curRow, curCol to 0
                    numbers[curRow][curCol] =  0;
                } else {
                    // print out the number of steps (time)
                    // return false (no solution found)
                    System.out.println("Number of steps: " + time);
                    return false;
                }
            }
        }
        return true;
    }
    class Cell{
        int row;
        int col;
        int value;

        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    public boolean[][] setLocked(int[][] numbers){
        boolean[][] isLocked = new boolean[9][9];
        for(int r = 0 ; r < 9 ; r++){
            for(int c = 0 ; c < 9 ; c++){
                if(numbers[r][c]!=0){
                    isLocked[r][c] = true;
                }
            }
        }

        return isLocked;
    }
    public boolean isValid(int[][] numbers, int row, int col, int currValue){
        // check row
        for(int r = 0 ; r < 9 ; r++){
            if(r != row && numbers[r][col] == currValue){
                return false;
            }
        }
        //check column
        for(int c = 0 ; c < 9 ; c++){
            if(c != col && numbers[row][c] == currValue){
                return false;
            }
        }
        int rowStartSquare = row - (row%3);
        int colStartSquare = col - (col%3);
        for(int r = 0 ; r < 3 ; r++){
            for(int c = 0 ; c < 3 ; c++){
                if(r != row && c != col && numbers[rowStartSquare+r][colStartSquare+c] == currValue){
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method to set some cells as given randomly
    private void setGivenCellsRandomly(int difficulty) {
        Random random = new Random();
        int cellsToKeep = SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE - difficulty;

        for (int i = 0; i < cellsToKeep; ++i) {
            int row = random.nextInt(SudokuConstants.GRID_SIZE);
            int col = random.nextInt(SudokuConstants.GRID_SIZE);

            isGiven[row][col] = false;
        }
    }

    public void generateEasyPuzzle() {
        generateNewPuzzle(SudokuConstants.EASY_DIFFICULTY);
        //setGivenCellsRandomly(SudokuConstants.EASY_DIFFICULTY);
        // removeCellsForDifficulty(SudokuConstants.EASY_DIFFICULTY);
    }

    // Generate a new medium puzzle
    public void generateMediumPuzzle() {
        generateNewPuzzle(SudokuConstants.MEDIUM_DIFFICULTY);
        setGivenCellsRandomly(SudokuConstants.MEDIUM_DIFFICULTY);
        removeCellsForDifficulty(SudokuConstants.MEDIUM_DIFFICULTY);
    }

    // Generate a new hard puzzle
    public void generateHardPuzzle() {
        generateNewPuzzle(SudokuConstants.HARD_DIFFICULTY);
        setGivenCellsRandomly(SudokuConstants.HARD_DIFFICULTY);
        removeCellsForDifficulty(SudokuConstants.HARD_DIFFICULTY);
    }

    private void removeCellsForDifficulty(int difficulty) {
        int cellsToRemove = calculateCellsToRemove(difficulty);
        int maxAttempts = 100; // Set a maximum number of removal attempts
    
        Random random = new Random();
        int removedCells = 0;
    
        for (int attempt = 0; attempt < maxAttempts && removedCells < cellsToRemove; ++attempt) {
            int row = random.nextInt(SudokuConstants.GRID_SIZE);
            int col = random.nextInt(SudokuConstants.GRID_SIZE);
            // Skip already removed cells or given cells
            if (numbers[row][col] != 0 && !isGiven[row][col]) {
                System.out.println("TAI");

            // Remove the cell
                removedCells++;
            }
        }
    }    

    
    private int calculateCellsToRemove(int difficulty) {
        int totalCells = SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE;
        int cellsToRemove = 0;

        switch (difficulty) {
            case SudokuConstants.EASY_DIFFICULTY:
                cellsToRemove = totalCells / 2;
                System.out.print("tes");
                break;
            case SudokuConstants.MEDIUM_DIFFICULTY:
                cellsToRemove = totalCells * 2 / 3;
                break;
            case SudokuConstants.HARD_DIFFICULTY:
                cellsToRemove = totalCells * 4 / 5;
                break;
        }

        return cellsToRemove;
    }
}

