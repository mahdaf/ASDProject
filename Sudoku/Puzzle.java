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

import java.util.Collections;
import java.util.Stack;

public class Puzzle {
    //Declare the variable to make the puzzle generator
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    Cell [][] cell = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    public Puzzle() {
        super();
    }
    
    //Create a method to generate the puzzle based on each difficulties
    public void generateEasyPuzzle() {
        generateNewPuzzle(SudokuConstants.EASY_DIFFICULTY);
    }

    public void generateMediumPuzzle() {
        generateNewPuzzle(SudokuConstants.MEDIUM_DIFFICULTY);
    }

    public void generateHardPuzzle() {
        generateNewPuzzle(SudokuConstants.HARD_DIFFICULTY);
    }

    //Create the puzzle generator to generate the number of each row and col
    private void generateNewPuzzle(int cellsToGuess) {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = 0;
                isGiven[row][col] = false;
            }
        }
        solve();
        generateGuess(cellsToGuess);
        SudokuRandom();
    }

    //Method to input random number into a stack
    private boolean SudokuRandom() {
        Stack<Integer> sudokuStack = new Stack<>();
        for (int i = 1; i <= SudokuConstants.GRID_SIZE; i++) {
            sudokuStack.push(i);
        }

        Collections.shuffle(sudokuStack);
        return sudokuBoard(sudokuStack);
    }

    //Method to fill the board with the numbers that have been insert to a stack
    private boolean sudokuBoard(Stack<Integer> sudokuStack) {
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                numbers[i][j] = 0;
            }
        }
        // Fill the sudoku board randomly
        return sudokuRecursive(0, 0, sudokuStack);
    }

    //Method to fill the sudoku board recursively by using the stack
    private boolean sudokuRecursive(int row, int col, Stack<Integer> sudokuStack) {
        if (row == SudokuConstants.GRID_SIZE - 1 && col == SudokuConstants.GRID_SIZE) {
            return true;
        }
        if (col == SudokuConstants.GRID_SIZE) {
            row++;
            col = 0;
        }
        Collections.shuffle(sudokuStack);

        for (int num : sudokuStack) {
            if (isValid(row, col, num)) {
                numbers[row][col] = num;
                if (sudokuRecursive(row, col + 1, sudokuStack)) {
                    return true;
                }
                numbers[row][col] = 0;
            }
        }
        return false;
    }

    //This is the method to validate the number that inserted, such as valid row, valid col, and valid grid
    private boolean isValid(int row, int col, int num) {
        return isValidRow(row, num) && isValidCol(col, num) && isValidGrid(row - row % SudokuConstants.SUBGRID_SIZE, col - col % SudokuConstants.SUBGRID_SIZE, num);
    }
    private boolean isValidRow(int row, int num) {
        for (int col = 0; col < SudokuConstants.GRID_SIZE; col++) {
            if (numbers[row][col] == num) {
                return false;
            }
        }
        return true;
    }
    private boolean isValidCol(int col, int num) {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
            if (numbers[row][col] == num) {
                return false;
            }
        }
        return true;
    }
    private boolean isValidGrid(int firstRow, int firstCol, int num) {
        for (int row = 0; row < SudokuConstants.SUBGRID_SIZE; row++) {
            for (int col = 0; col < SudokuConstants.SUBGRID_SIZE; col++) {
                if (numbers[row + firstRow][col + firstCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    //The method to solve the sudoku board by using stack and DFS
    private boolean solve() {
        Stack<Cell> cellStack = new Stack<>();
        int curRow = 0;
        int curCol = 0;
        int curValue = 1;
        int time = 0;
        boolean isValidValue = false;

        while (cellStack.size() < SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE) {
            time++;

            if (isGiven[curRow][curCol]) {
                cellStack.push(new Cell(curRow, curCol, numbers[curRow][curCol]));
                int[] next = getNextPosition(curRow, curCol);
                curRow = next[0];
                curCol = next[1];
                continue;
            }

            
            for (; curValue <= SudokuConstants.GRID_SIZE; curValue++) {
                if (isValidBox(curRow, curCol, curValue)) {
                    isValidValue = true;
                    break;
                }
            }

            if (isValidValue && curValue <= SudokuConstants.GRID_SIZE) {
                numbers[curRow][curCol] = curValue;
                cellStack.push(new Cell(curRow, curCol, curValue));
                int[] next = getNextPosition(curRow, curCol);
                curRow = next[0];
                curCol = next[1];
                curValue = 1;
            } else {
                if (!cellStack.isEmpty()) {
                    Cell cell = cellStack.pop();
                    while (isGiven[cell.getRow()][cell.getCol()]) {
                        if (!cellStack.isEmpty()) {
                            cell = cellStack.pop();
                        } else {
                            System.out.println("Number of steps: " + time);
                            return false;
                        }
                    }
                    curRow = cell.getRow();
                    curCol = cell.getCol();
                    curValue = cell.getValue() + 1;
                    numbers[curRow][curCol] = 0;
                } else {
                    System.out.println("Number of steps: " + time);
                    return false;
                }
            }
        }
        return true;
    }

    //This is method to check the validity of each sudoku box 
    private boolean isValidBox(int row, int col, int num) {
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            if (numbers[row][i] == num || numbers[i][col] == num) {
                return false;
            }
        }

        int firstRowGrid = row - row % SudokuConstants.SUBGRID_SIZE;
        int firstColGrid = col - col % SudokuConstants.SUBGRID_SIZE;
        for (int i = firstRowGrid; i < firstRowGrid + SudokuConstants.SUBGRID_SIZE; i++) {
            for (int j = firstColGrid; j < firstColGrid + SudokuConstants.SUBGRID_SIZE; j++) {
                if (numbers[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    //Method to get the next position / box to be inserted and check the validity
    private int[] getNextPosition(int currentRow, int currentCol) {
        int[] nextPosition = new int[2];
        currentCol++;
        if (currentCol == SudokuConstants.GRID_SIZE) {
            currentCol = 0;
            currentRow++;
        }
        nextPosition[0] = currentRow;
        nextPosition[1] = currentCol;
        return nextPosition;
    }

    //Method to generate the cell to guess by using stack and check whether the cell is already filled or not
    private void generateGuess(int cellsToGuess) {
        int targetFilledCells = cellsToGuess + (SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE) / 2; 
        Stack<Integer> indices = new Stack<>();
        for (int i = 0; i < SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE; i++) {
            indices.push(i);
        }
        Collections.shuffle(indices);
    
        int filledCells = 0;
        while (!indices.isEmpty() && filledCells < targetFilledCells) {
            int index = indices.pop();
            int row = index / SudokuConstants.GRID_SIZE;
            int col = index % SudokuConstants.GRID_SIZE;
            if (numbers[row][col] != 0) {
                isGiven[row][col] = true;
                filledCells++;
            }
        }
    }
}