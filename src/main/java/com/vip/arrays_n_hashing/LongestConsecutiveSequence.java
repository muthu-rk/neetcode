package com.vip.arrays_n_hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/longest-consecutive-sequence/description/

public class LongestConsecutiveSequence {
    // Key idea: Imagine numbers in an axis. There will be clusters of consecutive
    // numbers.
    // Start-1 of the seq wont be present in the input. Example 1,2,3,4. Start-1 is 0. Wont be there in input.
    // Create a hashset and search for previous entry.
    // Then loopover hashset and look for next entry (curr+1). and keep track of the
    // length to report max length.
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            hs.add(nums[i]);
        }

        int longest = 0;
        for (int i = 0; i < nums.length; i++) {

            if (!hs.contains(nums[i] - 1)) { // if prev no is not in set, then it is start of a seq
                int length = 0;
                while (hs.contains(nums[i] + length)) {
                    length++;
                }

                longest = Math.max(longest, length);
            }

        }

        return longest;
    }

    public int longestConsecutive_verbose(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            hs.add(nums[i]);
        }

        // hs.forEach(e -> System.out.println(e));

        List<List<Integer>> seqList = new ArrayList<>();
        List<Integer> seq = null;
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {

            if (!hs.contains(nums[i] - 1)) { // if prev no is not in set, then it is start of a seq
                System.out.println("New sequence started for " + (nums[i] - 1));
                int length = 0;
                seq = new ArrayList<>();
                while (hs.contains(nums[i] + length)) {
                    seq.add(nums[i] + length);
                    System.out.println("Added: " + (nums[i] + length) + " Curr seq len: " +
                            seq.size());
                    length++;
                }

                seqList.add(seq);

                longest = Math.max(longest, length);
            }

        }

        System.out.println(seqList.size());
        for (List<Integer> l : seqList) {
            System.out.println("Neq seq: ");
            l.forEach(e -> System.out.println(e));
        }
        return longest;
    }

    public void test_01() {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        int out = longestConsecutive(nums);
        System.out.println("Test 1: " + out);
    }

    public void test_02() {
        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        int out = longestConsecutive(nums);
        System.out.println("Test 2: " + out);
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
        // obj.test_01();
        obj.test_02();
    }
}
