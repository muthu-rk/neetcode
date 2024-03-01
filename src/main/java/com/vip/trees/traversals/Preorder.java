package com.vip.trees.traversals;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-preorder-traversal/
public class Preorder {
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return result;
    }

    private void preorder(TreeNode root) {
        if (root != null) {
            result.add(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }
}
