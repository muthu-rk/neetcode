package com.vip.trees.traversals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-level-order-traversal/
// Problem: Given a binary tree, return list of entries in each level.
// 1. Run BFS on the tree. Use Queue<TreeNode>.
// 2. Add root to Q before looping. Loop till Q not empty.
// 3. Dequeue all elements and store it array<TreeNode>. For each element in this array, add values to results.
// 4. If left and right pointers are not null, add to Q.
public class Levelorder {
    List<List<Integer>> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return result;

        List<TreeNode> levelList = new ArrayList<>();

        queue.add(root);
        if (!queue.isEmpty()) {
            levelList.clear();
            while (!queue.isEmpty()) {
                levelList.add(queue.remove());
            }

            List<Integer> valList = new ArrayList<>();
            for (TreeNode tn : levelList) {
                valList.add(tn.val);
                if (tn.left != null) {
                    queue.add(tn.left);
                    // System.out.println("Adding to Q L: " + tn.left.val);
                }
                if (tn.right != null) {
                    queue.add(tn.right);
                    // System.out.println("Adding to Q after: " + tn.left.val);
                }
            }
            result.add(valList);
        }
        return result;
    }
}
