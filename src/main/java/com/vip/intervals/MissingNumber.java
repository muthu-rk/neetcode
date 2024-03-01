package com.vip.intervals;

//https://leetcode.com/problems/missing-number/description/

// Problem: Find missing no. between [0,n].
// Two solutions: 
// 1. Sum between 0 to n+1 using loop or arithmetic progression n*n+1/2 and subtract the sum by the sum of no. in array.
// 2. Find XOR of no. betweeen 0-n. Find XOR of no. in input array. XOR those 2 values gives missing no.
// Time: O(n), Space: O(1)
// Other solutions: Sort and iterate: O(nlogn), Hashset: Space - O(n), likely sorth binary search: O(nlogn)
public class MissingNumber {

    public int missingNumber_sum(int[] nums) {
        int n = nums.length;
        int sum = (n + 1) * n / 2;

        for (int i = 0; i < n; i++) {
            sum -= nums[i];
        }

        return sum;
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xor1 = n, xor2 = 0;
        for (int i = 0; i < n; i++) {
            xor1 ^= i;
            xor2 ^= nums[i];
        }

        return xor1 ^ xor2;
    }

    void test_01() {
        int[] input = new int[] { 9, 6, 8, 4, 3, 5,  0, 2, 1 };
        System.out.println(missingNumber(input));
    }

    void test_02() {
        int[] input = new int[] { 0 };
        System.out.println(missingNumber(input));
    }

    public static void main(String[] args) {
        MissingNumber obj = new MissingNumber();
        obj.test_01();
        obj.test_02();
    }
}
