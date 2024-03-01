package com.vip.stack;

import java.util.HashMap;
import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParanthesis_04 {
    public boolean isValid(String s) {
        boolean dontMatch = false;
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> hm = new HashMap<> ();
        hm.put('{','}');
        hm.put('(',')');
        hm.put('[',']');

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (hm.keySet().contains(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    dontMatch = true;
                    break;
                }
                char out = stack.pop();
                if (hm.get(out) != c) {
                    dontMatch = true;
                    break;
                }
            }
        }

        if (!dontMatch && stack.isEmpty())
            return true;
        else
            return false;
    }

    public void test_01() {
        String s = "()";
        System.out.println("Test 1: " + isValid(s));
    }

    public void test_02() {
        String s = "()[]{}";
        System.out.println("Test 2: " + isValid(s));
    }

    public void test_03() {
        String s = "(]";
        System.out.println("Test 3: " + isValid(s));
    }

    public void test_04() {
        String s = "[({})]";
        System.out.println("Test 4: " + isValid(s));
    }

    public static void main(String[] args) {
        ValidParanthesis_04 obj = new ValidParanthesis_04();
        obj.test_01();
        obj.test_02();
        obj.test_03();
        obj.test_04();
    }
}
