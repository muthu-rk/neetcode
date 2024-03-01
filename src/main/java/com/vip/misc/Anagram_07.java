package com.vip.misc;

import java.util.HashMap;

// https://leetcode.com/problems/valid-anagram/

public class Anagram_07 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hm.containsKey(c))
                hm.put(c, hm.get(c) + 1);
            else
                hm.put(c, 1);
        }

        // hm.forEach((key, value) -> System.out.println(key + " " + value));

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!hm.containsKey(c))
                return false;

            if (hm.get(c) == 0)
                return false;

            hm.put(c, hm.get(c) - 1);
        }

        // hm.forEach((key, value) -> System.out.println(key + " " + value));

        for (int value: hm.values()) {
            if (value > 0)
                return false;
        }

        return true;
    }

    public void test_01() {
        String s = "anagram";
        String t = "nagaram";
        System.out.println("Test 1: " + isAnagram(s, t));
    }

    public void test_02() {
        String s = "rat";
        String t = "car";
        System.out.println("Test 2: " + isAnagram(s, t));
    }

    public static void main(String[] args) {
        Anagram_07 obj = new Anagram_07();
        obj.test_01();
        obj.test_02();
    }
}
