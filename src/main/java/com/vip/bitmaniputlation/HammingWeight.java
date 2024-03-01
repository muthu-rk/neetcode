package com.vip.bitmaniputlation;

// https://leetcode.com/problems/number-of-1-bits/description/

// Problem: Count no of 1s.
// 1. Java specific: Use & instead of mod. use >>> for unsigned instead of >>
// 2. Get LSB of n using & 1 and >>>1. If LSB = 1, count++
public class HammingWeight {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            // System.out.println(Integer.toBinaryString(n));

            if ((n & 1) == 1)
                count++;
            n = n >>> 1;
        }
        return count;
    }

    void printToBinary(int n) {
        System.out.println(Integer.toBinaryString(n));
    }

    void test_01() {
        int n = 128;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(hammingWeight(n));
    }

    public static void main(String[] args) {
        HammingWeight obj = new HammingWeight();
        obj.test_01();
    }
}
