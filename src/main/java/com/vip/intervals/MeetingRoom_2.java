package com.vip.intervals;

import java.util.Arrays;

// https://leetcode.com/problems/meeting-rooms-ii/description/

// 1. Store sorted start and end separately. And sort them independently.
// 2. Have 2 pointers for start and end arrays. 
// 3. If start entry is less than end entry imples overlapping. Move start and increment result when start pointer moves, 
//    decrement when end pointer moves
// 4. Repeat until all intervals are checked.
// 5. Track max count using Math.max(result, count)
public class MeetingRoom_2 {
    public int minMeetingRooms(int[][] intervals) {
        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        int i = 0;
        while (i < intervals.length) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
            i++;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int s = 0, e = 0; // start and end pointers
        int count = 0, result = 0;

        while (s < start.length && e < end.length) {
            if (start[s] < end[e]) { // overlaps
                s++;
                count++;
            } else { // no overlap. if (start == end) consider it as non-overlapping
                e++;
                count--;
            }

            result = Math.max(result, count);
        }

        return result;
    }

    void test_01() {
        int[][] input = new int[][] { { 5, 10 }, { 0, 30 }, { 15, 20 } };
        System.out.println(minMeetingRooms(input));
    }

    void test_02() {
        int[][] input = new int[][] { { 7, 10 }, { 2, 4 } };
        System.out.println(minMeetingRooms(input));
    }

    void test_03() {
        int[][] input = new int[][] { { 13, 15 }, { 1, 13 }, { 6, 9 } };
        System.out.println(minMeetingRooms(input));
    }

    public static void main(String[] args) {
        MeetingRoom_2 obj = new MeetingRoom_2();

        obj.test_01();
        obj.test_02();
        obj.test_03();
    }
}
