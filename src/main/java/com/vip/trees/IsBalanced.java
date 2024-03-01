package com.vip.trees;

// https://leetcode.com/problems/balanced-binary-tree/description/

// Problem: Given a binary tree, say if it is height balanced []
// 1. Run postorder DFS. Maintain 2 retvals (isBalanced, height) in recursive DFS.
// 1a. Bottom solution. Subtree heights and isBalanced are calculated. Hence tree traversed at O(n)
// 2. Define class Pair to hold (boolean isBalance, int height)
// 3. Base condition: if (node == null) return [True, 0] // 0 height and balanced.
// 4. Pair left =  dfs (node.left); Pair right =  dfs (node.right);
// 5. boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
// 6. int height = 1 + Math.max(left.height, right.height);
public class IsBalanced {
    class Pair {
        boolean isBalanced;
        int height;

        public Pair(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }

        Pair() {}

    }

    public boolean isBalanced(TreeNode root) {
        return dfs(root).isBalanced;
    }

    Pair dfs(TreeNode node) {
        if (node == null) {
            return new Pair(true, 0);
        }

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        int height = 1 + Math.max(left.height, right.height);

        return new Pair(isBalanced, height);
    }
}
