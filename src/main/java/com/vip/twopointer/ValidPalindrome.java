package com.vip.twopointer;

// https://leetcode.com/problems/valid-palindrome/description/
public class ValidPalindrome {
    // 1. Cleanup non-alphanumeric chars using regex
    // 2. Keep 2 pointers at both ends and compare.
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z0-9]", "");
        System.out.println(s);

        int first = 0;
        int last = s.length() - 1;

        boolean invalid = false;
        while (first < last) {
            if (s.charAt(first) != s.charAt(last)) {
                invalid = true;
                break;
            }

            first++;
            last--;
        }

        return !invalid;
    }

    public void test_01() {
        String s = "A man, a plan, a canal: Panama";
        System.out.println("Test 1: " + isPalindrome(s));
    }

    public void test_02() {
        String s = "race a car";
        System.out.println("Test 2: " + isPalindrome(s));
    }

    public void test_03() {
        String s = " ";
        System.out.println("Test 3: " + isPalindrome(s));
    }

    public void test_04() {
        String s = "0P";
        System.out.println("Test 4: " + isPalindrome(s));
    }

    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        // obj.test_01();
        // obj.test_02();
        // obj.test_03();
        obj.test_04();
    }
}
