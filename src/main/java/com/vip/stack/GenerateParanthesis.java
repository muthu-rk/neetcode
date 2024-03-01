package com.vip.stack;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/

// Key idea: Use backtracking via recursion (implicit stack).
public class GenerateParanthesis {
    String stack = "";
    List<String> retVal;
    int total;

    public List<String> generateParenthesis(int n) {
        total = n;
        retVal = new ArrayList<>();

        backtrack(0, 0);
        return retVal;
    }

    // Exit condition: openVal == closedVal == total
    // Insert Open brace when openVal < total
    // Insert Closed brace when closedVal < openVal
    void backtrack(int openVal, int closedVal) {
        if (openVal == total && closedVal == total) {
            retVal.add(stack);
            return;
        }

        if (openVal < total) {
            stack += '(';
            //System.out.println(stack);
            backtrack(openVal + 1, closedVal);
            stack = stack.substring(0, stack.length() - 1);
            //System.out.println("**. After open cleanup" + stack);
        }

        if (closedVal < openVal) {
            stack += ')';
            //System.out.println(stack);
            backtrack(openVal, closedVal + 1);
            stack = stack.substring(0, stack.length() - 1);
            //System.out.println("##. After closed cleanup" + stack);
        }
    }

    public void test_01() {
        System.out.println("Test 1: " + generateParenthesis(3));
    }

    public void test_02() {
        System.out.println("Test 2: " + generateParenthesis(2));
    }

    public static void main(String[] args) {
        GenerateParanthesis obj = new GenerateParanthesis();
        obj.test_01();
        obj.test_02();
    }
}
