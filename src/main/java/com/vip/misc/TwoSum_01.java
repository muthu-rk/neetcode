package com.vip.misc;

import java.util.Arrays;
import java.util.HashMap;

// https://leetcode.com/problems/two-sum/description/

public class TwoSum_01 {
    public int[] twoSum_naive(int[] nums, int target) {
        int[] retVal = new int[2];
        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    retVal[0] = i;
                    retVal[1] = j;
                    break;
                }
            }
        }
        
        return retVal;
    }

    // Hashmap approach
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        int[] retVal = new int[2];
        for (int i=0; i<nums.length; i++) {
            int diff = target - nums[i];
            if (hm.containsKey(diff)) {
                retVal[0] = hm.get(diff);
                retVal[1] = i;
                break;
            } else {
                hm.put(nums[i], i);
            }
        }

        return retVal;
    }

    public void test_01() {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] out = twoSum(nums, target);
        System.out.println("Test 1: " + Arrays.toString(out));
    }

    public void test_02() {
        int[] nums = {3,2,4};
        int target = 6;
        int[] out = twoSum(nums, target);
        System.out.println("Test 2: " + Arrays.toString(out));
    }

    public void test_03() {
        int[] nums = {3,3};
        int target = 6;
        int[] out = twoSum(nums, target);
        System.out.println("Test 3: " + Arrays.toString(out));
    }

    public static void main(String[] args) {
        TwoSum_01 obj = new TwoSum_01();
        obj.test_01();
        obj.test_02();
        obj.test_03();
    }
}
