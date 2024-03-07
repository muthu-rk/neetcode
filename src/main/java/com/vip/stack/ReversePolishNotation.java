package com.vip.stack;

import java.util.Stack;

// https://leetcode.com/problems/evaluate-reverse-polish-notation/

// Problem: Given a valid expression in RPN string array, using only operators +,-,*,/ evaluate the expression.
// Use stack DS. Parse each token and use Integer.parseInt within try-catch. Catch for NumberFormatException
// If string parsed to int, push to stack. If not, pop 2 operands from stack, evaluate for operator and push result.
// Final answer is in stack. Return it.
public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String t : tokens) {
            try {
                int i = Integer.parseInt(t);
                stack.push(i);
            } catch (NumberFormatException e) {
                int opr2 = stack.pop();
                int opr1 = stack.pop();
                int result = 0;
                switch (t) {
                    case "+":
                        result = opr1 + opr2;
                        break;

                    case "-":
                        result = opr1 - opr2;
                        break;

                    case "*":
                        result = opr1 * opr2;
                        break;

                    case "/":
                        result = opr1 / opr2;
                        break;
                }

                stack.push(result);
            }
        }
        return stack.pop();
    }

    void test_01() {
        String[] token = { "2", "1", "+", "3", "*" };

        System.out.println(evalRPN(token));
    }

    void test_02() {
        String[] token = { "4", "13", "5", "/", "+" };

        System.out.println(evalRPN(token));
    }

    void test_03() {
        String[] token = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };

        System.out.println(evalRPN(token));
    }

    public static void main(String[] args) {
        ReversePolishNotation obj = new ReversePolishNotation();

        obj.test_03();
    }
}
