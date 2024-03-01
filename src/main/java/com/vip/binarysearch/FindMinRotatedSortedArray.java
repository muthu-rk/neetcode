package com.vip.binarysearch;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/

// Problem: Given a sorted and rotated array, find the min value.
// Use binary search for O(logn). Property: Contents in left sorted array are alway greater than right sorted array
// Track min value seen till now in res. Initialize to arr[0]
// The following condition (arr[left] <= arr[mid]) wont work we have already reached the sorted array.
// So if arr[left] <= arr[right] return min (arr[left], res)
// res = min(arr[mid], res)
// Find if mid is in left or right sorted array. If left subarray, search right. If right, we dont know if left pointer is at the min value. so go left without terminating search. 

public class FindMinRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        int currMin = nums[0];

        while (left <= right) {

            // we are already in the right sorted part of the array.
            if (nums[left] <= nums[right]) {
                // Tracking the min coz the we could reach the min on the first run and without
                // tracking min, this would fail.
                currMin = Math.min(nums[left], currMin);
                break;
            }

            mid = (left + right) / 2;
            currMin = Math.min(nums[mid], currMin);

            if (nums[left] <= nums[mid]) { // mid is in left sorted array
                left = mid + 1;
            } else { // mid is in right sorted array
                right = mid - 1;
            }
        }

        return currMin;
    }

    public int findMin_scrap(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        int currMin = nums[0];

        while (left <= right) {

            // we are already in the sorted part of the arry.
            if (nums[left] <= nums[right]) {
                currMin = Math.min(nums[left], currMin);
                break;
            }

            mid = (left + right) / 2;

            currMin = Math.min(nums[mid], currMin);

            if (nums[left] <= nums[mid]) { // mid is in left sorted array
                left = mid + 1;
                // if (nums[left] > nums[right]) // min is in right side
                // left = mid + 1;
                // else
                // right = mid - 1;
            } else { // mid is in right sorted array
                right = mid - 1;
                // if (nums[left] < nums[right])
                // right = mid - 1;
                // else
                // left = mid + 1;
            }

            // if (nums[mid] > nums[right])
            // left = mid + 1;
            // else if (nums[mid] < nums[right])
            // right = mid - 1;
            // else
            // break;
        }

        return currMin;
    }

    public void test_01() {
        int[] nums = { 3, 4, 5, 1, 2 };
        int out = findMin(nums);
        System.out.println("Test 1: " + out);
    }

    public void test_02() {
        int[] nums = { 20, 17, 14, 13, 0, 3, 5, 9, 12 };
        int out = findMin(nums);
        System.out.println("Test 2: " + out);
    }

    public void test_03() {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int out = findMin(nums);
        System.out.println("Test 3: " + out);
    }

    public void test_04() {
        int[] nums = { 11, 13, 15, 17 };
        int out = findMin(nums);
        System.out.println("Test 4: " + out);
    }

    public static void main(String[] args) {
        FindMinRotatedSortedArray obj = new FindMinRotatedSortedArray();
        obj.test_01();
        obj.test_02();
        obj.test_03();
        obj.test_04();

    }
}
