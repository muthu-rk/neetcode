package com.vip.intervals;

import java.util.Arrays;

// https://leetcode.com/problems/meeting-rooms/

// Problem: Check if intervals overlap. Note: prevEnd == start is valid.
// 1. Sort the array
// 2. if (start < prevEnd) -> Overlaps. Return false.
public class MeetingRoom {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length <= 1)
            return true;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int prevEnd = intervals[0][1];

        int i = 1;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start < prevEnd) // overlaps
                return false;
            prevEnd = end;
            i++;
        }
        return true;
    }

    void test_02() {
        int[][] input = new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } };
        System.out.println(canAttendMeetings(input));
    }

    void test_01() {
        int[][] input = new int[][] { { 7, 10 }, { 2, 4 } };
        System.out.println(canAttendMeetings(input));
    }

    public static void main(String[] args) {
        MeetingRoom obj = new MeetingRoom();

        obj.test_01();
        obj.test_02();
    }
}
