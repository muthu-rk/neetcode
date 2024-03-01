package com.vip.intervals;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/insert-interval/
// Problem: Given an array of [start, end] in ascending sorted form and a new interval [start, end], make the intervals non-overlapping entries.
// Since input is already sorted, no external sorting needed.
// 1. Loop on all inputs.
// 2. Track newIntervals with newStart, newEnd.
// 2. Two non-overlapping cases:
//      a. New interval is before current entry. If so, add new entry and rest of the input (already sorted) to answer and return.
//      b. New interval is after current entry. Add currEntry to answer.
// 3. Overlaps. Find new values for newStart and newEnd using min() and max()
// 4. Add newEntries to answer list.

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        List<Integer[][]> ansList = new ArrayList<>();

        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (newEnd < start) { // non-overlapping.
                // Add newInterval to answer.
                Integer[][] temp = new Integer[][] { { newStart, newEnd } };
                ansList.add(temp);

                // Rest of the input is already sorted. So add them to answer and return.
                for (int j = i; j < intervals.length; j++) {
                    temp = new Integer[][] { { intervals[j][0], intervals[j][1] } };
                    ansList.add(temp);
                }

                // return ansList.toArray(new int[ansList.size()][]);
                return convert2Arr(ansList);
            } else if (newStart > end) { // non-overlapping
                // add only the curr interval to list.
                Integer[][] temp = new Integer[][] { { start, end } };
                ansList.add(temp);
            } else { // overlapping
                // adjust the new interval range.
                newStart = Math.min(start, newStart);
                newEnd = Math.max(end, newEnd);
            }

            i++;
        }

        // Add newEntries to answer list.
        Integer[][] temp = new Integer[][] { { newStart, newEnd } };
        ansList.add(temp);
        // return ansList.toArray(new int[ansList.size()][]);
        return convert2Arr(ansList);
    }

    int[][] convert2Arr(List<Integer[][]> list) {

        int[][] out = new int[list.size()][2];
        int i = 0;
        for (Integer[][] l : list) {
            // System.out.println(l[0][0] + " " + l[0][1]);
            out[i][0] = l[0][0];
            out[i][1] = l[0][1];
            i++;
        }

        return out;
    }

    void test_01() {
        int[][] input = new int[][] { { 1, 3 }, { 6, 9 } };

        int[] newInterval = new int[] { 2, 5 };
        int[][] out = insert(input, newInterval);
        for (int[] out2 : out) {
            System.out.println("new entry");
            for (int out22 : out2) {
                System.out.println(out22);
            }
        }
    }

    void test_02() {
        int[][] input = new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval = new int[] { 4, 8 };

        int[][] out = insert(input, newInterval);
        for (int[] out2 : out) {
            System.out.println("new entry");
            for (int out22 : out2) {
                System.out.println(out22);
            }
        }
    }

    public static void main(String[] args) {
        InsertInterval obj = new InsertInterval();

        // obj.test_01();
        obj.test_02();
    }
}
