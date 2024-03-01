package com.vip.slidingwindow;

import java.util.HashSet;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class longest_subsequence_no_repeat {
    // 1. Combination of hashset and 2 pointer for sliding window.
    // 2. Until a duplicate is met, slide the window to right and add window entries to hashset.
    // 3. If duplicate is encountered, shrink the window from left until the duplicates are removed from hashset.
    // 4. Max(currMax sliding window size, left, right pointer distance) tracks the result.
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;

        HashSet<Character> hs = new HashSet<>();
        int left = 0;
        int result = 0;

        for (int right = 0; right < s.length(); right++) {
            while (hs.contains(s.charAt(right))) {
                hs.remove(s.charAt(left));
                left++;
            }
            hs.add(s.charAt(right));
            result = Math.max(result, right - left + 1);

            // System.out.println("left: " + left + " right: " + right + " result: " + result);
            // hs.forEach((val) -> System.out.println(val));
        }

        return result;
    }

    public int lengthOfLongestSubstring_noworking(String s) {
        if (s.length() == 0)
            return 0;

        HashSet<Character> hs = new HashSet<>();
        int left = 0, right = 1;
        int max = 0;
        hs.add(s.charAt(left));
        while (right < s.length()) {
            if (hs.contains(s.charAt(right))) {
                hs.remove(s.charAt(left));
                left++;
            }
            hs.add(s.charAt(right));
            max = right - left + 1;
            right++;

            System.out.println("left: " + left + " right: " + right + " max: " + max);
            hs.forEach((val) -> System.out.println(val));
        }
        return max;
    }

    public void test_01() {
        String s = "abcabcbb";
        System.out.println("Test 1: " + lengthOfLongestSubstring(s));
    }

    public void test_02() {
        String s = "pwwkew";
        System.out.println("Test 2: " + lengthOfLongestSubstring(s));
    }

    public static void main(String[] args) {
        longest_subsequence_no_repeat obj = new longest_subsequence_no_repeat();
        obj.test_01();
        obj.test_02();
        // obj.test_03();
    }
}
