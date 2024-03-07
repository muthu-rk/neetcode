package com.vip.stack;

import java.util.EmptyStackException;
import java.util.Stack;

// https://leetcode.com/problems/min-stack/description/

// Problem: Implement stack operations. Also implement getMin() returning min value. All operations must be in O(1)
// Maintain 2 stacks. One for operations another for min value until that point. 
public class MinStackConstantTime {
    class MinStack {
        Stack<Integer> stack, minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            try {
                int min = minStack.peek();
                minStack.push(Math.min(val, min));
            } catch (EmptyStackException e) {
                minStack.push(val);
            }

            // System.out.println(String.format("PUSH: Counts: Min: %d, Num: %d. Top: Min: %d, Num: %d", minStack.size(),
            //         stack.size(), minStack.peek(), stack.peek()));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

        void printStack() {
            System.out.println("Size: Minstack: " + minStack.size());
            minStack.forEach(e -> System.out.println(e));

            System.out.println("Size: Stack: " + stack.size());
            stack.forEach(e -> System.out.println(e));
            System.out.println("===========");
        }
    }

    // ["MinStack","push","push","push","getMin","pop","top","getMin"]
    // [[],[-2],[0],[-3],[],[],[],[]]
    public static void main(String[] args) {
        MinStackConstantTime obj = new MinStackConstantTime();
        MinStack ms = obj.new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);

        ms.printStack();
        System.out.println(ms.getMin());

        ms.pop();
        ms.printStack();

        System.out.println(ms.top());

        System.out.println(ms.getMin());
        ms.printStack();
    }
}
