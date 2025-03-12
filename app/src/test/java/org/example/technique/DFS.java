package org.example.technique;

import org.example.data_structure.Node;
import org.example.data_structure.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DFS {
    class SolutionQ133 {
        // A HashMap to keep track of all the nodes which have already been copied.
        private Map<Node, Node> visited = new HashMap<>();

        // This function returns the clone of the graph.
        public Node cloneGraph(Node node) {
            // If the input node is null, then return null.
            if (node == null) {
                return null;
            }

            // If the node has already been visited i.e., already cloned,
            // return the cloned node from the map.
            if (visited.containsKey(node)) {
                return visited.get(node);
            }

            // Create a new node with the value of the input node (clone it).
            Node cloneNode = new Node(node.val);
            // Mark this node as visited by putting into the visited map.
            visited.put(node, cloneNode);

            // Iterate over all the neighbors of the input node.
            for (Node neighbor : node.neighbors) {
                // Perform a depth-first search (DFS) for each neighbor,
                // and add the clone of the neighbor to the neighbors list
                // of the clone node.
                cloneNode.neighbors.add(cloneGraph(neighbor));
            }

            // Return the cloned graph node.
            return cloneNode;
        }
    }

    @Test
    void q133_CloneGraph() {
    }
    class SolutionQ113 {
        // A list to store all the paths that sum to the target
        private List<List<Integer>> paths = new ArrayList<>();
        // A temporary list to keep track of the current path
        private List<Integer> currentPath = new ArrayList<>();

        /**
         * Finds all paths from root to leaves that sum to targetSum.
         * @param root The root of the tree.
         * @param targetSum The sum that each path needs to add up to.
         * @return A list of all paths that sum to targetSum.
         */
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            dfs(root, targetSum);
            return paths;
        }

        /**
         * Performs a depth-first search to find all paths with the target sum.
         * @param node The current node in the recursion.
         * @param remainingSum The remaining sum after subtracting the node's value.
         */
        private void dfs(TreeNode node, int remainingSum) {
            if (node == null) {
                // If we've reached a null node, just return.
                return;
            }

            // Subtract the node's value from the remaining sum and add the node's value to the current path
            remainingSum -= node.val;
            currentPath.add(node.val);

            // Check if it's a leaf node and the remaining sum is zero, which means we've found a valid path
            if (node.left == null && node.right == null && remainingSum == 0) {
                // If so, add a copy of the current path to the list of valid paths
                paths.add(new ArrayList<>(currentPath));
            }

            // Recurse deeper into the tree
            dfs(node.left, remainingSum);  // Go left
            dfs(node.right, remainingSum); // Go right

            // After exploring both sides, remove the current node's value before going back up the tree
            currentPath.remove(currentPath.size() - 1);
        }
    }

    @Test
    void q113_PathSum_II() {
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        //creating the adjlist
        for (int i = 0; i < prerequisites.length; i++) {
            int post = prerequisites[i][0];
            int pre = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(
                    pre,
                    new ArrayList<Integer>()
            );
            lst.add(post);
            adjList.put(pre, lst);

            indegree[post] += 1;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        if (i == numCourses) {
            return topologicalOrder;
        }

        return new int[0];
    }

    void q210_CourseSchedule_II() {
    }
}
