package com.vip.trees.traversals;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-inorder-traversal/
public class Inorder {
    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return result;
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            result.add(root.val);
            inorder(root.right);
        }
    }
}
