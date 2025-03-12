package org.example.util;

import org.example.data_structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public final class TreeUtil {
    private TreeUtil() {
    }

    private static TreeNode dfs(int[] preorder, Map<Integer, Integer> inorderMap, int i, int l, int r) {
        if (r - l < 0)
            return null;
        TreeNode root = new TreeNode(preorder[i]);
        int m = inorderMap.get(preorder[i]);
        root.left = dfs(preorder, inorderMap, i + 1, l, m - 1);
        root.right = dfs(preorder, inorderMap, i + 1 + m - l, m + 1, r);
        return root;
    }

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = dfs(preorder, inorderMap, 0, 0, inorder.length - 1);
        return root;
    }

}
