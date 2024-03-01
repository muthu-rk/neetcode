package com.vip.bitmaniputlation;

// https://leetcode.com/problems/counting-bits/description/

// Problem: Given a no. return an ans array where ans[i] contains the no. of #1 in i.
// 1. Count 1s using hammingweight and add the result to ans array.
public class CountBits {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = hammingWeight(i);
        }
        return ans;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1)
                count++;
            n = n >>> 1;
        }
        return count;
    }

    void test_01() {
        int n = 2;
        System.out.println(Integer.toBinaryString(n));
        System.out.println("Test 01: ");
        int[] out = countBits(n);
        for (int o : out) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        CountBits obj = new CountBits();
        obj.test_01();
    }
}
