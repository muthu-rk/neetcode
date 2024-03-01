package com.vip.trees;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

// Do in-order BST. Maintain a count during traversal. Return element once count is reached.
public class KthSmallestElementBst {
    int kmin = Integer.MAX_VALUE;
    int count;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        dfsInorder(root);
        return kmin;
    }

    void dfsInorder(TreeNode node) {
        if (node == null)
            return;

        dfsInorder(node.left);
        // System.out.println("Count: " + count + " val: " + node.val);
        count--;
        if (count == 0) {
            kmin = node.val;
            // System.out.println("minval: " + kmin);

            return;
        }

        dfsInorder(node.right);
        return;
    }

}
