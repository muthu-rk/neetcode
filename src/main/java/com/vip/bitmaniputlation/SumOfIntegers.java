package com.vip.bitmaniputlation;

// https://leetcode.com/problems/sum-of-two-integers/

// Problem: Add 2 numbers without using + and -
// 1. Use AND op and << 1 to preserve carry.
// 2. Apply above with XOR input.
// 3. Repeat above until carry/remainder is 0.
public class SumOfIntegers {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    void test_01() {
        System.out.println(getSum(5, 0));
    }

    public static void main(String[] args) {
        SumOfIntegers obj = new SumOfIntegers();
        obj.test_01();
    }
}
