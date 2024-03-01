package com.vip.trees;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

// Problem: 
class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root != null) {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }

        return 0;
    }
}