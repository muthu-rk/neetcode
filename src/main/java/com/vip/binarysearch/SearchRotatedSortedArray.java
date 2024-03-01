package com.vip.binarysearch;

// https://leetcode.com/problems/search-in-rotated-sorted-array/description/

// Problem: Array is sorted but rotated. Find a given target location else return -1. Do it in O(logn) not O(n)
// Visualize with a graph. both sorted arrays are separated. Left line (left sorted array) start at higher y than right line (representing right sorted array)
// Solution is to code many discrete cases. Using binary search. The input has 2 sorted array left and right. Build cases around this.
// Like BS, left, mid and right pointers are used. End condition is while (left<=right)
// mid = (l+r)/2. If arr[mid] == target, return mid;
// Idea is to check if the mid is in left or right sorted array.
//      If arr[left] <= arr[mid] // mid is in left sorted array. 
//          Should you go left or right. Compare target with arr[mid]. 
//          If target > arr[mid], go right. so l = mid+1;
//          Else // we've 2 choices target is left of mid or right. compare target with arr[left]
//              if (target < arr[left]) go right. left = mid +1 else go left right = mid - 1;
//      Else // mid is in right sorted array. Do the exact reverse checking and direction movement as above.
 public class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[left] <= nums[mid]) { // mid is in left sorted array
                if (target > nums[mid]) {
                    left = mid + 1;
                } else {
                    if (target < nums[left]) { // target is in right sorted array
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            } else { // mid is in right sorted array
                if (target < nums[mid]) { // left of right sorted array
                    right = mid - 1;
                } else {
                    if (target > nums[right]) { // target is in left sorted array
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }

        }

        return -1;
    }

    public void test_01() {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int out = search(nums, 7);
        System.out.println("Test 1: " + out);
    }

    public static void main(String[] args) {
        SearchRotatedSortedArray obj = new SearchRotatedSortedArray();
        obj.test_01();
        // obj.test_02();
    }
}
