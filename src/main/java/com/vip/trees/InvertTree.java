package com.vip.trees;

// https://leetcode.com/problems/invert-binary-tree/description/
// Problem: Tree mirroring. Swap left and right subtrees.
// 1. Run DFS either postorder or preorder.
// 2. Swap left and right pointers of the node.
// 3. Recurse on left and right subtrees.
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        invertInternal(root);
        return root;
    }

    void invertInternal(TreeNode node) {
        if (node != null) {
            TreeNode node2 = node.left;
            node.left = node.right;
            node.right = node2;
            invertInternal(node.left);
            invertInternal(node.right);
        }
    }
}
