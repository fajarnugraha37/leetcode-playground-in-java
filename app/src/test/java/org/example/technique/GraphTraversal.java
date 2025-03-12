package org.example.technique;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GraphTraversal {

    class SolutionQ79 {
        // Class level variables to hold the dimensions of the board, the word, and the board itself
        private int rows;
        private int cols;
        private String targetWord;
        private char[][] gameBoard;

        // Method to determine if the target word exists in the board
        public boolean exist(char[][] board, String word) {
            rows = board.length;       // Number of rows in the board
            cols = board[0].length;    // Number of columns in the board
            targetWord = word;         // The word we are searching for
            gameBoard = board;         // The game board

            // Iterate over every cell in the board
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    // If the first letter matches and dfs search is successful, return true
                    if (dfs(i, j, 0)) {
                        return true;
                    }
                }
            }

            // If we have not returned true at this point, the word does not exist in the board
            return false;
        }

        // Helper method to perform Depth First Search (DFS)
        private boolean dfs(int row, int col, int index) {
            // Check if we are at the last character of the word
            if (index == targetWord.length() - 1) {
                return gameBoard[row][col] == targetWord.charAt(index);
            }

            // Check if current cell does not match the character at index in word
            if (gameBoard[row][col] != targetWord.charAt(index)) {
                return false;
            }

            // Temporarily mark the current cell as visited by replacing its value
            char tempChar = gameBoard[row][col];
            gameBoard[row][col] = '0';

            // Define an array of directions (up, right, down, left)
            int[] directions = {-1, 0, 1, 0, -1};

            // Explore all possible adjacent cells (up, right, down, left)
            for (int d = 0; d < 4; ++d) {
                int newRow = row + directions[d];
                int newCol = col + directions[d + 1];

                // Check if the new position is within bounds and not visited
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && gameBoard[newRow][newCol] != '0') {
                    // If the dfs search from the adjacent cell is successful, return true
                    if (dfs(newRow, newCol, index + 1)) {
                        return true;
                    }
                }
            }

            // Reset the cell's value back from '0' to its original character
            gameBoard[row][col] = tempChar;

            // If none of the adjacent cells leads to a solution, return false
            return false;
        }
    }

    @Test
    void q79_WordSearch() {
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create a graph represented by an adjacency list where each course points to its prerequisites
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Create an array to store the in-degree (number of prerequisites) for each course
        int[] inDegrees = new int[numCourses];

        // Populate the graph and update the in-degrees based on the prerequisites
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(course);
            inDegrees[course]++; // Increment the in-degree of the course
        }

        // Queue to hold courses with in-degree of 0 (no prerequisites)
        Deque<Integer> queue = new ArrayDeque<>();

        // Enqueue all courses which have no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // Counter for number of courses that have been processed
        int processedCourses = 0;

        // Process the courses in the queue
        while (!queue.isEmpty()) {
            int course = queue.poll();
            processedCourses++; // Increment count of processed courses

            // Iterate over the neighbors of the current course
            for (int neighbor : graph.get(course)) {
                // Decrement the in-degree of each neighbor, since we have processed one of their prerequisites
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    // If in-degree becomes 0, it means all prerequisites are met, so enqueue the course
                    queue.offer(neighbor);
                }
            }
        }

        // If the number of processed courses equals the total number of courses, all can be finished
        return processedCourses == numCourses;
    }

    @Test
    void q207_CourseSchedule() {
    }

    class SolutionQ417 {
        private int[][] heightsMatrix;
        private int height;
        private int width;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            // dimensions of the input matrix
            height = heights.length;
            width = heights[0].length;
            this.heightsMatrix = heights;

            // queues for BFS from Pacific and Atlantic oceans
            Deque<int[]> pacificQueue = new LinkedList<>();
            Deque<int[]> atlanticQueue = new LinkedList<>();

            // visited sets for Pacific and Atlantic oceans
            Set<Integer> visitedPacific = new HashSet<>();
            Set<Integer> visitedAtlantic = new HashSet<>();

            // start from the edges of the matrix for both the oceans
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (i == 0 || j == 0) {  // Pacific Ocean's edge
                        visitedPacific.add(i * width + j);
                        pacificQueue.offer(new int[]{i, j});
                    }
                    if (i == height - 1 || j == width - 1) {  // Atlantic Ocean's edge
                        visitedAtlantic.add(i * width + j);
                        atlanticQueue.offer(new int[]{i, j});
                    }
                }
            }

            // perform a BFS for each ocean to find all cells reachable from each ocean
            bfs(pacificQueue, visitedPacific);
            bfs(atlanticQueue, visitedAtlantic);

            // results list for cells that can reach both oceans
            List<List<Integer>> results = new ArrayList<>();
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    int cellIndex = i * width + j;
                    // if a cell is reachable from both Pacific and Atlantic, add it to results
                    if (visitedPacific.contains(cellIndex) && visitedAtlantic.contains(cellIndex)) {
                        results.add(Arrays.asList(i, j));
                    }
                }
            }

            return results;
        }

        private void bfs(Deque<int[]> queue, Set<Integer> visited) {
            int[] directions = {-1, 0, 1, 0, -1};  // representational array for traversing neighbors

            while (!queue.isEmpty()) {
                // explore all the current level's nodes
                for (int k = queue.size(); k > 0; --k) {
                    int[] cell = queue.poll();
                    // traverse all 4 directions (up, right, down, left)
                    for (int i = 0; i < 4; ++i) {
                        int newRow = cell[0] + directions[i];
                        int newCol = cell[1] + directions[i + 1];
                        // check for valid coordinates and if the cell is not already visited
                        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width
                                && !visited.contains(newRow * width + newCol)
                                && heightsMatrix[newRow][newCol] >= heightsMatrix[cell[0]][cell[1]]) {
                            // if criteria are met, add the cell to visited and queue for further BFS
                            visited.add(newRow * width + newCol);
                            queue.offer(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }
    }

    @Test
    void q417_PacificAtlanticWaterFlow() {
    }

    /**
     * Checks if the array contains any duplicates.
     *
     * @param numbers The array of integers to check for duplicates.
     * @return true if any value appears at least twice in the array, and false if every element is distinct.
     */
    public boolean containsDuplicate(int[] numbers) {
        // Initialize a HashSet to store unique elements.
        Set<Integer> uniqueNumbers = new HashSet<>();

        // Iterate through all the elements in the array.
        for (int number : numbers) {
            // Attempt to add the current element to the set.
            // If the add method returns false, it means the element is already present in the set.
            if (!uniqueNumbers.add(number)) {
                // Duplicate found, so return true.
                return true;
            }
        }

        // No duplicates were found, return false.
        return false;
    }

    @Test
    void q217_ContainsDuplicate() {
    }

}
