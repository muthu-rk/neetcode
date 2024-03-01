package com.vip.trees;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subtree-of-another-tree/description/

// This is not a BST. So there can be many entries in parent with node.val == subtree.val.
// Maintain a List< TreeNode> to capture call nodes by running DFS. Once element is found in parent, add to list and repeat for all nodes.
// maintain 2 lists, run dfs for both trees and check if the trees are same. 
public class IsSubtree {
    List<Integer> plist = new ArrayList<>();
    List<Integer> slist = new ArrayList<>();
    List<TreeNode> parentRoots = new ArrayList<>();

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        dfsFindElement(root, subRoot.val);
        if (parentRoots == null)
            return false;

        // System.out.println("Parent root list count: " + parentRoots.size());

        dfsFillList(subRoot, slist);
        // System.out.println("SList: ");
        // slist.forEach(e -> System.out.println(e));

        for (TreeNode p : parentRoots) {
            // System.out.println("PVal: " + p.val);

            plist.clear();
            dfsFillList(p, plist);
            // System.out.println("PList: ");
            // plist.forEach(e -> System.out.println(e));

            if (plist.equals(slist))
                return true;
        }

        return false;
    }

    void dfsFillList(TreeNode node, List<Integer> list) {
        if (node == null) {
            list.add(null);
            return;
        }

        list.add(node.val);
        dfsFillList(node.left, list);
        dfsFillList(node.right, list);
    }

    void dfsFindElement(TreeNode node, int k) {
        if (node == null)
            return;

        if (node.val == k) {
            parentRoots.add(node);
            // return;
        }

        dfsFindElement(node.left, k);
        dfsFindElement(node.right, k);

        return;
    }
}
