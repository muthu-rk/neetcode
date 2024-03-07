package com.vip.linkedlist;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-the-duplicate-number/

// Problem: Given an array of size n+1 have no. between [1, n] find the duplicate no. It is not necessary to have the duplicate only once. 
// Naive solution: Use hashmap and return count > 1.
public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i : nums) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            // System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
            if (entry.getValue() > 1)
                return entry.getKey();
        }

        return -1;
    }

    // Incorrect solution: Assumes all no. between 1,n occurs once and the repeated
    // no. occurs only once. Which is not the case.
    // public int findDuplicate(int[] nums) {
    // int sum = 0;
    // int max = 0;

    // for (int i : nums) {
    // sum += i;
    // max = Math.max(max, i);
    // }

    // int ap = max * (max + 1) / 2;

    // return sum - ap;
    // }

    void test_01() {
        int[] n = new int[] { 1, 3, 4, 2, 1 };

        System.out.println(findDuplicate(n));
    }

    public static void main(String[] args) {
        FindDuplicate obj = new FindDuplicate();

        obj.test_01();
    }
}
