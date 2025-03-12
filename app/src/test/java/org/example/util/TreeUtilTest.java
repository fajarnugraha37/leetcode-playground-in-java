package org.example.util;

import org.example.data_structure.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.example.util.TreeUtil.buildTree;
import static org.junit.jupiter.api.Assertions.*;

class TreeUtilTest {
    @Test
    void main() {
        int[] preorder = { 3, 9, 2, 1, 7 };
        int[] inorder = { 9, 3, 1, 2, 7 };
        System.out.println("preorder = " + Arrays.toString(preorder));
        System.out.println("inorder = " + Arrays.toString(inorder));

        TreeNode root = buildTree(preorder, inorder);
        System.out.println("tree = ");
        PrintUtil.printTree(root);
    }
}