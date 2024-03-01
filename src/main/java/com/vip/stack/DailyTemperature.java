package com.vip.stack;

import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/
public class DailyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer[]> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                Integer[] top = stack.pop();
                answer[top[1]] = i - top[1];
            }

            stack.push(new Integer[] { temperatures[i], i });
        }

        return answer;
    }

    public int[] dailyTemperatures_cluttered(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer[]> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()) {
                // System.out.println("Pushing: " + temperatures[i] + " " + i);
                stack.push(new Integer[] { temperatures[i], i });
            } else {
                Integer[] top = stack.peek();
                if (temperatures[i] <= top[0]) {
                    // System.out.println("Pushing: " + temperatures[i] + " " + i);
                    stack.push(new Integer[] { temperatures[i], i });
                } else {
                    do {
                        top = stack.peek();
                        if (temperatures[i] > top[0]) {
                            stack.pop();
                            // System.out.println("Popping: " + top[0] + " " + top[1]);
                            answer[top[1]] = i - top[1];
                        }
                    } while (temperatures[i] > top[0] && !stack.isEmpty());

                    // System.out.println("Pushing: " + temperatures[i] + " " + i);
                    stack.push(new Integer[] { temperatures[i], i });
                }
            }
        }

        // while (!stack.isEmpty()) {
        //     Integer[] top = stack.pop();
        //     answer[top[1]] = 0;
        // }

        return answer;
    }

    public int[] dailyTemperatures_naive(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            answer[i] = 0;
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }

    public void test_01() {
        int[] temp = { 73, 74, 75, 71, 69, 72, 76, 73 };
        int[] out = dailyTemperatures(temp);
        System.out.println("Test 1: ");
        for (int o : out) {
            System.out.println(o);
        }

        out = dailyTemperatures_naive(temp);
        System.out.println("Test 1: ");
        for (int o : out) {
            System.out.println(o);
        }
    }

    public void test_02() {
        int[] temp = { 30, 40, 50, 60 };
        int[] out = dailyTemperatures(temp);
        System.out.println("Test 2: ");
        for (int o : out) {
            System.out.println(o);
        }

        out = dailyTemperatures_naive(temp);
        System.out.println("Test 2: ");
        for (int o : out) {
            System.out.println(o);
        }
    }

    public void test_03() {
        int[] temp = { 30, 60, 90 };
        int[] out = dailyTemperatures(temp);
        System.out.println("Test 3: ");
        for (int o : out) {
            System.out.println(o);
        }

        out = dailyTemperatures_naive(temp);
        System.out.println("Test 3: ");
        for (int o : out) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        DailyTemperature obj = new DailyTemperature();
        obj.test_01();
        obj.test_02();
        obj.test_03();
    }
}
