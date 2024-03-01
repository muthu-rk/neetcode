package com.vip.trees;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

// Problem: Find lowest common ancestor, given 2 values; p, q.
// Run preorder DFS and solve distint cases.
// Case 1: If p, q < node.val, search left subtree.
// Case 2: If p, q > node.val, search right subtree.
// Case 3: If p or q < node.val, there is a split. One of them is in right subtree. So return node as lca.
// Case 4: If p or q = node.val, return node as lca.
public class FindLCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;

        if (p.val == root.val || q.val == root.val) // Case 4
            return root;

        if (Math.max(p.val, q.val) > root.val && (Math.min(p.val, q.val) < root.val)) // Case 3
            return root;

        if (p.val < root.val && q.val < root.val) // case 1
            return lowestCommonAncestor(root.left, p, q);

        if (p.val > root.val && q.val > root.val) // case 2
            return lowestCommonAncestor(root.right, p, q);

        return root;
    }

}
