package com.vip.slidingwindow;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-repeating-character-replacement/description/

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int result = 0;
        HashMap<Character, Integer> hm = new HashMap<>();

        while (right < s.length()) {
            hm.put(s.charAt(right), hm.getOrDefault(s.charAt(right), 0) + 1);

            if ((right - left + 1) - getMaxCountFromHashmap(hm) > k) {
                hm.put(s.charAt(left), hm.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            }

            result = Math.max(right - left + 1, result);
            right++;

        }
        return result;
    }

    private int getMaxCountFromHashmap(HashMap<Character, Integer> hm) {
        int retVal = 0;
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            retVal = Math.max(retVal, entry.getValue());
        }

        // System.out.println(retVal);

        return retVal;
    }

    public void test_01() {
        String s = "ABAB";
        int k = 2;
        System.out.println("Test 1: " + characterReplacement(s, k));
    }

    public void test_02() {
        String s = "AABABBA";
        int k = 1;
        System.out.println("Test 2: " + characterReplacement(s, k));
    }

    public void test_03() {
        String s = "ABBABA";
        int k = 2;
        System.out.println("Test 3: " + characterReplacement(s, k));
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement obj = new LongestRepeatingCharacterReplacement();
        // obj.test_01();
        // obj.test_02();
        obj.test_03();
    }
}