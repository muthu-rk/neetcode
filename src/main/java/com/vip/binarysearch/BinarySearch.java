package com.vip.binarysearch;

// https://leetcode.com/problems/binary-search/description/

/*
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
 

Constraints:

1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.
 */
public class BinarySearch {
    public int search_iteration(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (target > nums[mid])
                left = mid + 1;
            else if (target < nums[mid])
                right = mid - 1;
            else
                return mid;
        }

        return -1;
    }

    public int search(int[] nums, int target) {
        return search_recurse(0, nums.length - 1, nums, target);
    }

    int search_recurse(int left, int right, int[] nums, int target) {
        System.out.println(left + " " + right);
        if (left > right)
            return -1;

        int mid = (left + right) / 2;
        if (target > nums[mid]) {
            int r = search_recurse(mid + 1, right, nums, target);
            System.out.println("retval 1: " + r);
            return r;
        } else if (target < nums[mid]) {
            int r = search_recurse(left, mid - 1, nums, target);
            System.out.println("retval 2: " + r);
            return r;
        } else
            return mid;
    }

    public void test_01() {
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int out = search(nums, -1);
        System.out.println("Test 1: " + out);
    }

    public static void main(String[] args) {
        BinarySearch obj = new BinarySearch();
        obj.test_01();
        // obj.test_02();
    }
}
