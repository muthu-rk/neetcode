package com.vip.trees;
// https://leetcode.com/problems/diameter-of-binary-tree/description/

// Problem: Find the longest path (no. of edges thru a node) in the tree. May or may not be via root.
// Run postorder DFS. Find height of each subtree (1+max(left st, right st)).
// Find diameter of a node as dia = LeftHeight + RightHeight + 2 (2 = no. of edges from that node)
// Maintain a max = 0 to track max diameter. max = max (max, nodediameter)
// IMPORTANT: in base case, return -1 instead of 0 for the diameter math to workout.
public class Diameter {
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    int dfs(TreeNode node) { // returns height
        if (node == null)
            return -1;

        int lh = dfs(node.left);
        int rh = dfs(node.right);

        int height = 1 + Math.max(lh, rh);
        int dia = lh + rh + 2;
        maxDiameter = Math.max(maxDiameter, dia);
        System.out.println("Node: " + node.val + " H: " + height + " dia: " + dia + " max dia: " + maxDiameter);
        return height;
    }
}
