package com.vip.trees;

// https://leetcode.com/problems/validate-binary-search-tree/description/

// Problem: BST: all entries in left subtree is < node.val < all entries in right subtree.
// Problem: Given root, find if it is a BST.
// 1. Run preorder DFS. Have 2 pointers (left = -infinity, right = +infinity).
// 2. Base condition: if (!node) return true
// 3. if !( (left < node.val) and (node.val<right)) return false.
// 4. return dfs(node.left, left, node.val) && dfs(node.left, node.val, right);
// 5. Invoke dfs (root, -inf, +inf)
// 6. NOTE: Change min, max type from int to Long to pass the edgecases.
public class IsValidBst {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean validate(TreeNode node, long left, long right) {
        if (node == null)
            return true;

        if (!((left < node.val) && (node.val < right)))
            return false;

        return (validate(node.left, left, node.val) && validate(node.right, node.val, right));
    }
}
