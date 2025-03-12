package org.example.technique;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingTest {
    class SolutionQ46 {
        // List to hold all the permutations
        private List<List<Integer>> permutations = new ArrayList<>();

        // Temporary list to hold the current permutation
        private List<Integer> currentPermutation = new ArrayList<>();

        // Visited array to keep track of the elements already included in the permutation
        private boolean[] visited;

        // Array of numbers to create permutations from
        private int[] elements;

        // Method to initiate the process of finding all permutations
        public List<List<Integer>> permute(int[] nums) {
            elements = nums;
            visited = new boolean[nums.length];
            backtrack(0);
            return permutations;
        }

        // Helper method to perform backtracking
        private void backtrack(int index) {
            // Base case: if the permutation size is equal to the number of elements, add it to the answer
            if (index == elements.length) {
                permutations.add(new ArrayList<>(currentPermutation));
                return;
            }

            // Iterate through the elements array
            for (int j = 0; j < elements.length; ++j) {
                // If the element at index j has not been visited, include it in the permutation
                if (!visited[j]) {
                    // Mark the element at index j as visited
                    visited[j] = true;
                    // Add the element to the current permutation
                    currentPermutation.add(elements[j]);
                    // Continue to the next level of depth (next index)
                    backtrack(index + 1);
                    // Backtrack: remove the last element added and mark it as not visited
                    currentPermutation.remove(currentPermutation.size() - 1);
                    visited[j] = false;
                }
            }
        }
    }

    @Test
    void q46_Permutations() {
    }

    class SolutionQ78 {
        // A list to store all subsets
        private List<List<Integer>> subsetsList = new ArrayList<>();

        // A temporary list to store one subset
        private List<Integer> tempSubset = new ArrayList<>();

        // An array to store the given numbers
        private int[] numbers;

        /**
         * This is the main method that returns all possible subsets of the given array.
         * @param nums Array of integers for which subsets are to be found.
         * @return A list of all possible subsets of the given array.
         */
        public List<List<Integer>> subsets(int[] nums) {
            this.numbers = nums;
            // Start the Depth-First Search (DFS) with the first index
            depthFirstSearch(0);
            return subsetsList;
        }

        /**
         * This method uses Depth-First Search (DFS) to explore all potential subsets.
         * @param index The current index in the numbers array being explored.
         */
        private void depthFirstSearch(int index) {
            // If the current index has reached the length of the array,
            // it means we've formed a subset which can now be added to the list of subsets.
            if (index == numbers.length) {
                subsetsList.add(new ArrayList<>(tempSubset));
                return;
            }

            // Case 1: The current number is excluded from the subset,
            // so we simply call dfs on the next index.
            depthFirstSearch(index + 1);

            // Case 2: The current number is included in the subset.
            // Add the current number to the tempSubset.
            tempSubset.add(numbers[index]);

            // Move on to the next index to explore further with the current number included.
            depthFirstSearch(index + 1);

            // Backtrack: remove the last number added to the tempSubset,
            // this effectively removes the current number from the subset.
            tempSubset.remove(tempSubset.size() - 1);
        }
    }

    @Test
    void q78_Subsets() {
    }

    public class SolutionQ51 {
        // The list that will contain all possible solutions
        private List<List<String>> solutions = new ArrayList<>();

        // Arrays to mark if a column, diagonal, or anti-diagonal is occupied
        private int[] columns;
        private int[] diagonals;
        private int[] antiDiagonals;

        // Chessboard representation
        private String[][] board;

        // Size of the board
        private int size;

        // The public method to solve the N-Queens problem
        public List<List<String>> solveNQueens(int n) {
            this.size = n;
            columns = new int[n];
            diagonals = new int[2 * n];
            antiDiagonals = new int[2 * n];
            board = new String[n][n];

            // Initialize the board with empty strings
            for (int i = 0; i < n; ++i) {
                Arrays.fill(board[i], ".");
            }

            // Begin the depth-first search from the first row
            depthFirstSearch(0);
            return solutions;
        }

        // The recursive method to place a queen on the board
        private void depthFirstSearch(int row) {
            // If all queens are placed
            if (row == size) {
                List<String> oneSolution = new ArrayList<>();
                for (int i = 0; i < size; ++i) {
                    // Join the row to form the string
                    oneSolution.add(String.join("", board[i]));
                }
                // Add the current board configuration to the solutions list
                solutions.add(oneSolution);
                return;
            }
            // Iterate through each column for the current row
            for (int col = 0; col < size; ++col) {
                // Check if the current position is safe for placing a queen
                if (columns[col] + diagonals[row + col] + antiDiagonals[size - row + col] == 0) {
                    // Place the queen
                    board[row][col] = "Q";
                    // Mark current column, diagonal, and anti-diagonal as occupied
                    columns[col] = diagonals[row + col] = antiDiagonals[size - row + col] = 1;
                    // Continue to place the next queen
                    depthFirstSearch(row + 1);
                    // Backtrack and remove the queen
                    columns[col] = diagonals[row + col] = antiDiagonals[size - row + col] = 0;
                    board[row][col] = ".";
                }
            }
        }
    }

    @Test
    void q51_NQueens() {
    }

    class SolutionQ39 {
        private List<List<Integer>> combinations = new ArrayList<>(); // Store the list of all combinations
        private List<Integer> currentCombination = new ArrayList<>(); // Current combination being explored
        private int[] candidateNumbers; // Array of candidate numbers

        // Method to find all unique combinations where the candidate numbers sum up to target
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates); // Sort the array of candidates to optimize the process
            this.candidateNumbers = candidates; // Store the global reference for candidate numbers
            backtrack(0, target);
            return combinations;
        }

        // Helper method to perform the depth-first search
        private void backtrack(int startIndex, int remainingSum) {
            if (remainingSum == 0) {
                // If the remaining sum is 0, we found a valid combination
                combinations.add(new ArrayList<>(currentCombination));
                return;
            }
            if (startIndex >= candidateNumbers.length || remainingSum < candidateNumbers[startIndex]) {
                // If startIndex is out of bounds or the smallest candidate exceeds remainingSum
                return;
            }

            // Skip the current candidate and move to the next one
            backtrack(startIndex + 1, remainingSum);

            // Include the current candidate in the current combination
            currentCombination.add(candidateNumbers[startIndex]);
            // Continue exploring with the current candidate (since we can use the same number multiple times)
            backtrack(startIndex, remainingSum - candidateNumbers[startIndex]);
            // Backtrack and remove the last element before trying the next candidate
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    @Test
    void q39_CombinationSum() {
    }

    class SolutionQ37 {
        private boolean solved; // Variable to determine if the puzzle is solved
        private char[][] board; // The Sudoku board
        // List for tracking empty cells (those with '.')
        private List<Integer> emptyCells = new ArrayList<>();
        // Arrays to track the used numbers in rows, columns, and blocks
        private boolean[][] usedInRow = new boolean[9][9];
        private boolean[][] usedInColumn = new boolean[9][9];
        private boolean[][][] usedInBlock = new boolean[3][3][9];

        public void solveSudoku(char[][] board) {
            this.board = board;
            // Initialize the tracking structures and find the empty positions
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        emptyCells.add(i * 9 + j); // Record the position of an empty cell
                    } else {
                        // Convert char to int value ranging from 0-8
                        int value = board[i][j] - '1';
                        // Mark the value as used in the corresponding row, column, and block
                        usedInRow[i][value] = true;
                        usedInColumn[j][value] = true;
                        usedInBlock[i / 3][j / 3][value] = true;
                    }
                }
            }
            // Begin the recursive depth-first search to solve the puzzle
            dfs(0);
        }

        private void dfs(int index) {
            // If we have filled all empty cells, we have solved the puzzle
            if (index == emptyCells.size()) {
                solved = true;
                return;
            }
            // Calculate the row and column from the current empty cell's index
            int i = emptyCells.get(index) / 9;
            int j = emptyCells.get(index) % 9;

            // Try placing values 1-9 in the current empty cell
            for (int value = 0; value < 9; ++value) {
                if (!usedInRow[i][value] && !usedInColumn[j][value] && !usedInBlock[i / 3][j / 3][value]) {
                    // If the value isn't used in the row, column, or block, place it
                    usedInRow[i][value] = true;
                    usedInColumn[j][value] = true;
                    usedInBlock[i / 3][j / 3][value] = true;
                    board[i][j] = (char) (value + '1');
                    // Continue to the next empty cell
                    dfs(index + 1);
                    // If the puzzle is solved, exit
                    if (solved) {
                        return;
                    }
                    // If placing value did not lead to a solution, backtrack
                    usedInRow[i][value] = false;
                    usedInColumn[j][value] = false;
                    usedInBlock[i / 3][j / 3][value] = false;
                }
            }
        }
    }

    @Test
    void q37_SudokuSolver() {
    }

    // This method calculates the number of distinct ways to climb to the top.
    public int climbStairs(int n) {
        int first = 0, second = 1;

        // Loop through number of steps n
        for (int i = 0; i < n; i++) {
            // Calculate next number in the series
            int next = first + second;

            // Update the previous two numbers for next iteration
            first = second;
            second = next;
        }

        // The 'second' variable holds the total ways to reach the top
        return second;
    }

    @Test
    void q70_ClimbingStairs() {
    }


    //
    void q322_() {
    }

    void q1143_() {
    }

    void q300_() {
    }

    void q416_() {
    }

    void q312_() {
    }

}
