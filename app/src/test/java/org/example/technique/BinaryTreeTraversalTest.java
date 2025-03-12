package org.example.technique;

import org.example.data_structure.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//PreOrder: root -> left -> right
//InOrder: left -> root -> right
//PostOrder: left -> right -> root
public class BinaryTreeTraversalTest {

    class SolutionQ257 {
        // A list to store all path strings
        private List<String> allPaths = new ArrayList<>();
        // A temporary list to keep the current path nodes
        private List<String> currentPath = new ArrayList<>();

        /**
         * Finds all paths from root to leaf in a binary tree.
         *
         * @param root The root of the binary tree.
         * @return A list of all root-to-leaf paths in string format.
         */
        public List<String> binaryTreePaths(TreeNode root) {
            // Perform a depth-first search starting from the root
            depthFirstSearch(root);
            return allPaths;
        }

        /**
         * Performs a recursive depth-first search to find all paths.
         *
         * @param node The current node in the binary tree.
         */
        private void depthFirstSearch(TreeNode node) {
            if (node == null) {
                // Base case: if node is null, do nothing
                return;
            }

            // Append current node's value to the path
            currentPath.add(String.valueOf(node.val));

            // If node is a leaf, add the path to the list of all paths
            if (node.left == null && node.right == null) {
                allPaths.add(String.join("->", currentPath));
            } else {
                // Recur for left and right children
                depthFirstSearch(node.left);
                depthFirstSearch(node.right);
            }

            // Backtrack: remove the last node from the path before returning
            currentPath.remove(currentPath.size() - 1);
        }
    }

    void q257_BinaryTreePaths() {
        var solution = new SolutionQ257();
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    @Test
    void q230_KTHSmallestElementInaBst() {
    }

    public int maxPathSum(TreeNode root) {
        int[] res = {Integer.MIN_VALUE};
        maxPathSum(root, res);
        return res[0];
    }

    public int maxPathSum(TreeNode root, int[] res) {
        if (root == null)
            return 0;

        int left = Math.max(0, maxPathSum(root.left, res));
        int right = Math.max(0, maxPathSum(root.right, res));
        res[0] = Math.max(res[0], root.val + left + right);

        return root.val + Math.max(left, right);
    }

    @Test
    void q124_BinaryTreeMaximumPathSum() {
    }
}
