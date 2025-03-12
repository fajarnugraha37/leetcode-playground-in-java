package org.example.technique;

import org.junit.jupiter.api.Test;

public class MetrixTraversalTest {
    class SolutionQ733 {
        // Direction vectors representing the 4 connected pixels (up, right, down, left).
        private int[] directions = {-1, 0, 1, 0, -1};
        // The image we need to modify.
        private int[][] image;
        // The new color to apply to the flood fill.
        private int newColor;
        // The original color to be replaced.
        private int originalColor;

        // Method to begin flood fill operation
        public int[][] floodFill(int[][] image, int startRow, int startColumn, int color) {
            // Initialize the image, new color, and original color based on the input.
            this.image = image;
            this.newColor = color;
            this.originalColor = image[startRow][startColumn];

            // Call the recursive dfs method starting from the pixel at (sr, sc)
            dfs(startRow, startColumn);
            // Return the modified image after the flood fill operation.
            return image;
        }

        // Depth-first search (DFS) method to apply new color to connected components.
        private void dfs(int row, int column) {
            // Boundary check: if the pixel is out of bounds or isn't the original color or is already the new color, return.
            if (row < 0 || row >= image.length || column < 0 || column >= image[0].length ||
                    image[row][column] != originalColor || image[row][column] == newColor) {
                return;
            }

            // Change the color of the current pixel to the new color.
            image[row][column] = newColor;

            // Iterate through each of the 4 connected neighbors.
            for (int k = 0; k < 4; ++k) {
                // Recursively call dfs for the current neighbor.
                dfs(row + directions[k], column + directions[k + 1]);
            }
        }
    }

    @Test
    void q733_FloodFill() {
    }

    class SolutionQ200_NumberOfIslands {
        // Define grid and its dimensions
        private char[][] grid;
        private int numRows;
        private int numCols;

        // Method to count the number of islands in the given grid
        public int numIslands(char[][] grid) {
            numRows = grid.length;
            numCols = grid[0].length;
            this.grid = grid;

            int numIslands = 0; // Initialize island count

            // Iterate through each cell in the grid
            for (int i = 0; i < numRows; ++i) {
                for (int j = 0; j < numCols; ++j) {
                    // If cell contains '1', it is part of an island
                    if (grid[i][j] == '1') {
                        // Use DFS to mark the entire island as visited
                        depthFirstSearch(i, j);
                        // Increase the island count
                        ++numIslands;
                    }
                }
            }
            return numIslands;
        }

        // Helper method to perform DFS to mark all cells of an island as visited
        private void depthFirstSearch(int row, int col) {
            // Mark the current cell as visited by setting it to '0'
            grid[row][col] = '0';

            // Array to facilitate the exploration of adjacent directions (up, right, down, left)
            int[] directions = {-1, 0, 1, 0, -1};

            // Explore all 4 adjacent directions
            for (int k = 0; k < 4; ++k) {
                int newRow = row + directions[k];
                int newCol = col + directions[k + 1];
                // Check boundaries and if the adjacent cell is part of an island
                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && grid[newRow][newCol] == '1') {
                    // Continue DFS exploration for the adjacent cell
                    depthFirstSearch(newRow, newCol);
                }
            }
        }
    }

    @Test
    void q200_NumberOfIslands() {
    }

    class SolutionQ130_SurroundedRegions {
        private char[][] board; // Member variable to hold the input board
        private int rows; // Number of rows in the board
        private int cols; // Number of columns in the board

        // Main function that solves the board by replacing all 'O' not surrounded by 'X' with 'X'
        public void solve(char[][] board) {
            rows = board.length; // Set the number of rows
            cols = board[0].length; // Set the number of columns
            this.board = board; // Initialize the board member variable

            // Explore all 'O' on the borders, any 'O' connected to them should not be flipped
            // hence temporarily mark them with '.'
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Condition to check if it's on the border and if it's an 'O'
                    if ((i == 0 || i == rows - 1 || j == 0 || j == cols - 1) && board[i][j] == 'O') {
                        depthFirstSearch(i, j); // Call DFS to mark the connected 'O's
                    }
                }
            }

            // Flip all remaining 'O' to 'X' and back all '.' to 'O'.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // If it was marked '.', it's safe to flip it back to 'O'
                    if (board[i][j] == '.') {
                        board[i][j] = 'O';
                    }
                    // If it's still an 'O', it should be flipped to 'X' as it is not connected to a border
                    else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        // Depth-first search function to find all the 'O's connected to a border 'O'
        private void depthFirstSearch(int row, int col) {
            board[row][col] = '.'; // Mark the cell as visited by replacing 'O' with '.'
            int[] directions = {-1, 0, 1, 0, -1}; // Directions to move in the matrix
            for (int k = 0; k < 4; k++) { // Loop through possible directions (up, right, down, left)
                int nextRow = row + directions[k];
                int nextCol = col + directions[k + 1];
                // Check bounds and if the next cell is 'O', continue DFS
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && board[nextRow][nextCol] == 'O') {
                    depthFirstSearch(nextRow, nextCol); // Recursive call for connected 'O's
                }
            }
        }
    }

    void q130_SurroundedRegions() {
    }

}
