package com.vip.trees;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/same-tree/description/

// Problem: Given 2 binary trees, check if they are same.
// 1. Run DFS either preorder or postorder.
// 2. Maintain 2 lists, one for each tree. Walk the tree and add entries to the list.
// 3. Compare 2 lists.
// Note: It is necessary to add null entries to the list to determine correct structure of the tree.
public class IsSameTree {
    List<Integer> pList = new ArrayList<>();
    List<Integer> qList = new ArrayList<>();

    public boolean isSameTree(TreeNode p, TreeNode q) {

        traverseTree(p, pList);
        traverseTree(q, qList);

        if (pList.size() != qList.size())
            return false;

        return pList.equals(qList);
    }

    void traverseTree(TreeNode node, List<Integer> list) {
        if (node != null) {
            traverseTree(node.left, list);
            traverseTree(node.right, list);
            list.add(node.val);

        } else
            list.add(null);
    }
}
