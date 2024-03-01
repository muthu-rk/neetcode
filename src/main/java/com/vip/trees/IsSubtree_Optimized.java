package com.vip.trees;

// https://leetcode.com/problems/subtree-of-another-tree/description/

public class IsSubtree_Optimized {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null)
            return true;

        if (root == null) // subRoot != null is imperertive because of above check.
            return false;

        if (isSameTree(root, subRoot))
            return true;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        if (p.val != q.val)
            return false;

        boolean leftVal = isSameTree(p.left, q.left);
        boolean rightVal = isSameTree(p.right, q.right);
        return leftVal && rightVal;

        // return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }
}
