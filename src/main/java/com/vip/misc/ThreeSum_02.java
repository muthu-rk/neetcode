package com.vip.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://leetcode.com/problems/3sum/description/
 */
public class ThreeSum_02 {
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> retVal = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int partialSum = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (partialSum + nums[k] == 0) {
                        List<Integer> entry = Arrays.asList(nums[i], nums[j], nums[k]);
                        boolean isPresent = false;
                        for (List<Integer> r : retVal) {
                            if (r.containsAll(entry)) {
                                isPresent = true;
                                break;
                            }
                        }

                        if (!isPresent)
                            retVal.add(entry);
                        // System.out.println(nums[i] + " " + nums[j] + " " + nums[k]);
                        // System.out.println(entry);
                    }
                }
            }
        }

        return retVal;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> retVal = new ArrayList<>();

        Arrays.sort(nums);

        int i;
        for (i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int target = -nums[i];
            int first = i + 1;
            int last = nums.length - 1;

            while (first < last) {
                int sum = nums[first] + nums[last];
                if (sum == target) {
                    List<Integer> entry = Arrays.asList(-target, nums[first], nums[last]);
                    retVal.add(entry);

                    // System.out.println(entry);

                   // Skip duplicate elements for j
                    while (first < last && nums[first] == nums[first + 1]) {
                        first++;
                    }

                    // Skip duplicate elements for k
                    while (first < last && nums[last] == nums[last - 1]) {
                        last--;
                    }

                    first++;
                    last--;
                } else if (sum < target) {
                    first++;
                } else {
                    last--;
                }
            }
        }

        return retVal;
    }

    public void test_01() {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        List<List<Integer>> out = threeSum(nums);
        System.out.println("Test 1: ");
        for (List<Integer> o : out) {
            System.out.println(o);
        }
    }

    public void test_02() {
        int[] nums = { 0, 1, 1 };
        List<List<Integer>> out = threeSum(nums);
        System.out.println("Test 2: ");
        for (List<Integer> o : out) {
            System.out.println(o);
        }
    }

    public void test_03() {
        int[] nums = { 0, 0, 0 };
        List<List<Integer>> out = threeSum(nums);
        System.out.println("Test 3: ");
        for (List<Integer> o : out) {
            System.out.println(o);
        }
    }

    public void test_04() {
        int[] nums = { -4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0 };
        List<List<Integer>> out = threeSum(nums);
        System.out.println("Test 4: ");
        for (List<Integer> o : out) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        ThreeSum_02 obj = new ThreeSum_02();
        obj.test_01();
        obj.test_02();
        obj.test_03();
        obj.test_04();
    }
}
