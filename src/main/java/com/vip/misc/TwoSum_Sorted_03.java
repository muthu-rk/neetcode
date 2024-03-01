package com.vip.misc;

import java.util.Arrays;

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

public class TwoSum_Sorted_03 {
    // Two pointer approach
    public int[] twoSum(int[] numbers, int target) {
        int first = 0, last = numbers.length - 1;
        int[] retVal = new int[2];

        while (first < last) {
            int sum = numbers[first] + numbers[last];
            if (sum == target) {
                retVal[0] = first+1;
                retVal[1] = last+1;
                break;
            } else if (sum > target) {
                last--;
            } else {
                first++;
            }
        }
        return retVal;
    }

    public void test_01() {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] out = twoSum(nums, target);
        System.out.println("Test 1: " + Arrays.toString(out));
    }

    public void test_02() {
        int[] nums = { 2, 3, 4 };
        int target = 6;
        int[] out = twoSum(nums, target);
        System.out.println("Test 2: " + Arrays.toString(out));
    }

    public void test_03() {
        int[] nums = { -1, 0 };
        int target = -1;
        int[] out = twoSum(nums, target);
        System.out.println("Test 3: " + Arrays.toString(out));
    }

    public static void main(String[] args) {
        TwoSum_Sorted_03 obj = new TwoSum_Sorted_03();
        obj.test_01();
        obj.test_02();
        obj.test_03();
    }
}
