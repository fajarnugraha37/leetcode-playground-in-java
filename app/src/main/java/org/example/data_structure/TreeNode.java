package org.example.data_structure;

import java.util.*;

public class TreeNode {
    public int val;
    public int height;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // 序列化编码规则请参考：
    // https://www.hello-algo.com/chapter_tree/array_representation_of_tree/
    // 二叉树的数组表示：
    // [1, 2, 3, 4, None, 6, 7, 8, 9, None, None, 12, None, None, 15]
    // 二叉树的链表表示：
    // /——— 15
    // /——— 7
    // /——— 3
    // | \——— 6
    // | \——— 12
    // ——— 1
    // \——— 2
    // | /——— 9
    // \——— 4
    // \——— 8
    private static TreeNode listToTreeDFS(List<Integer> arr, int i) {
        if (i < 0 || i >= arr.size() || arr.get(i) == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr.get(i));
        root.left = listToTreeDFS(arr, 2 * i + 1);
        root.right = listToTreeDFS(arr, 2 * i + 2);
        return root;
    }

    public static TreeNode listToTree(List<Integer> arr) {
        return listToTreeDFS(arr, 0);
    }

    private static void treeToListDFS(TreeNode root, int i, List<Integer> res) {
        if (root == null)
            return;
        while (i >= res.size()) {
            res.add(null);
        }
        res.set(i, root.val);
        treeToListDFS(root.left, 2 * i + 1, res);
        treeToListDFS(root.right, 2 * i + 2, res);
    }

    public static List<Integer> treeToList(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        treeToListDFS(root, 0, res);
        return res;
    }
}