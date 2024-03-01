package com.vip.intervals;

import java.util.Arrays;

// https://leetcode.com/problems/non-overlapping-intervals/

// Problem: Given a set of intervals, remove min no of intervals so that the input is non-overlapping.
// Greedy approach. 
// Sort the intervals by start. Compare curr interval with prevEnd. If overlapping remove the interval with higher endvalue.
// Update prevEnd and loop over all intervals.
// Time: O(nlogn) (for sort) and O(n) for greedy traversal.
public class NonOverlappingInterval {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int prevEnd = intervals[0][1];
        int retVal = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevEnd) { // overlapping
                retVal++;
                prevEnd = Math.min(intervals[i][1], prevEnd);
            } else {
                prevEnd = intervals[i][1];
            }
        }

        return retVal;
    }

    void test_01() {
        int[][] input = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
        System.out.println(eraseOverlapIntervals(input));
    }

    void test_02() {
        int[][] input = new int[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } };
        System.out.println(eraseOverlapIntervals(input));
    }

    void test_03() {
        int[][] input = new int[][] { { 1, 2 }, { 2, 3 } };
        System.out.println(eraseOverlapIntervals(input));
    }

    public static void main(String[] args) {
        NonOverlappingInterval obj = new NonOverlappingInterval();
        obj.test_01();
        obj.test_02();
        obj.test_03();
    }
}
