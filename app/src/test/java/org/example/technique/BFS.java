package org.example.technique;

import org.example.data_structure.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BFS {
    // Method to perform a level order traversal of a binary tree.
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Create a list to hold the result.
        List<List<Integer>> result = new ArrayList<>();

        // Return an empty list if the tree is empty.
        if (root == null) {
            return result;
        }

        // Create a queue to hold nodes at each level.
        Deque<TreeNode> queue = new ArrayDeque<>();

        // Start the level order traversal from the root.
        queue.offer(root);

        // While there are nodes to process
        while (!queue.isEmpty()) {
            // Temporary list to store the values of nodes at the current level.
            List<Integer> level = new ArrayList<>();

            // Process all nodes at the current level.
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; ++i) {
                // Retrieve and remove the head of the queue.
                TreeNode currentNode = queue.poll();

                // Add the node's value to the temporary list.
                level.add(currentNode.val);

                // If the left child exists, add it to the queue for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // If the right child exists, add it to the queue for the next level.
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add the temporary list to the result list.
            result.add(level);
        }

        // Return the list of levels.
        return result;
    }

    @Test
    void q102_BinaryTreeLevelOrderTraversal() {
    }

    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int freshOranges = 0; // Counter for fresh oranges
        Deque<int[]> queue = new LinkedList<>(); // Queue for rotten oranges' positions

        // Preprocess the grid, enqueue all rotten oranges and count the fresh ones
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 2) { // If orange is rotten
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) { // If orange is fresh
                    freshOranges++;
                }
            }
        }

        int minutesElapsed = 0; // Time counter for rotting process
        int[] directions = {1, 0, -1, 0, 1}; // Directions for adjacent cells: right, down, left, up

        // Start BFS from initially rotten oranges
        while (!queue.isEmpty() && freshOranges > 0) {
            minutesElapsed++; // Increase time since each level of BFS represents 1 minute
            for (int i = queue.size(); i > 0; --i) { // Iterate over rotten oranges at current time
                int[] position = queue.poll();
                for (int j = 0; j < 4; ++j) { // Check all adjacent cells
                    int x = position[0] + directions[j];
                    int y = position[1] + directions[j + 1];
                    // If adjacent cell is within bounds and has a fresh orange
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2; // Rot the fresh orange
                        freshOranges--; // Decrement the fresh orange count
                        queue.offer(new int[] {x, y}); // Enqueue the new rotten orange's position
                    }
                }
            }
        }

        // If there are still fresh oranges left, return -1, otherwise return elapsed time
        return freshOranges > 0 ? -1 : minutesElapsed;
    }

    @Test
    void q994_RottingOranges() {
    }

    public class SolutionQ127 {
        private Set<String> wordSet;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // Initialize the word set with the given word list
            wordSet = new HashSet<>(wordList);
            // If the endWord is not in the wordList, return 0 as no ladder exists
            if (!wordSet.contains(endWord)) {
                return 0;
            }
            // Use two queues to implement bidirectional BFS
            Queue<String> queueBegin = new ArrayDeque<>();
            Queue<String> queueEnd = new ArrayDeque<>();
            // Maps to keep track of the path lengths from the begin and end words
            Map<String, Integer> visitedBegin = new HashMap<>();
            Map<String, Integer> visitedEnd = new HashMap<>();
            // Initialize the queues and maps
            queueBegin.offer(beginWord);
            queueEnd.offer(endWord);
            visitedBegin.put(beginWord, 1); // The step count starts at 1
            visitedEnd.put(endWord, 1);

            // Perform BFS until one of the queues is empty
            while (!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
                // Always extend the smaller queue in the current iteration
                int result = queueBegin.size() <= queueEnd.size() ?
                        extendQueue(visitedBegin, visitedEnd, queueBegin) :
                        extendQueue(visitedEnd, visitedBegin, queueEnd);
                // If a connection is found, return the total length of the path
                if (result != -1) {
                    return result;
                }
            }
            // If no path is found, return 0
            return 0;
        }

        private int extendQueue(Map<String, Integer> visitedOne, Map<String, Integer> visitedOther, Queue<String> queue) {
            // Process each word in the current layer
            for (int i = queue.size(); i > 0; --i) {
                String currentWord = queue.poll();
                int currentStep = visitedOne.get(currentWord);
                char[] characters = currentWord.toCharArray();
                // Try changing every character to make new words
                for (int j = 0; j < characters.length; ++j) {
                    char originalChar = characters[j];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        characters[j] = ch;
                        String newWord = new String(characters);
                        // Skip if the new word is not in the word set or already visited
                        if (!wordSet.contains(newWord) || visitedOne.containsKey(newWord)) {
                            continue;
                        }
                        // If the new word is in the other visited map, a path is found
                        if (visitedOther.containsKey(newWord)) {
                            return currentStep + visitedOther.get(newWord);
                        }
                        // Otherwise, add the new word to the queue and visited map
                        queue.offer(newWord);
                        visitedOne.put(newWord, currentStep + 1);
                    }
                    // Restore the original character
                    characters[j] = originalChar;
                }
            }
            // If no progress is made in this direction, return -1
            return -1;
        }
    }

    @Test
    void q127_WordLadder() {
    }
}
