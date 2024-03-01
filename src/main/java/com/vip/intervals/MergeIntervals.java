package com.vip.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/

// Problem: Merge intervals into non-overlapping entries. Entries need not be in sorting.
// 0. Visualize with axis diagram.
// 1. Sort the array first. Java: Arrays.sort and custom comparator.
// 2. Add first entry to answer list.
// 3. Compare with previous answer entry only.
// 4. If doesnt overlap, add curr entry to answer
// 5. If overlaps, change the end of previous entry in answer (extending the range use Math.max().)
// Note: Since input is sorted, above step #5 will work.
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<Integer[][]> ansList = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ansList.add(new Integer[][] { { intervals[0][0], intervals[0][1] } });

        int i = 1;
        while (i < intervals.length) {
            int lastStart = ansList.get(ansList.size() - 1)[0][0];
            int lastEnd = ansList.get(ansList.size() - 1)[0][1];

            int start = intervals[i][0];
            int end = intervals[i][1];
            if (start <= lastEnd) {// overlaps
                end = Math.max(end, lastEnd);
                ansList.set(ansList.size() - 1, new Integer[][] { { lastStart, end } });
            } else {
                ansList.add(new Integer[][] { { start, end } });
            }
            i++;
        }

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

    void test_02() {
        int[][] input = new int[][] { { 15, 18 }, { 1, 3 }, { 2, 6 }, { 8, 10 }, };

        Arrays.sort(input, (a, b) -> Integer.compare(a[0], b[0]));

        int[][] out = merge(input);
        for (int[] out2 : out) {
            System.out.println("new entry");
            for (int out22 : out2) {
                System.out.println(out22);
            }
        }
    }

    public static void main(String[] args) {
        MergeIntervals obj = new MergeIntervals();

        // obj.test_01();
        obj.test_02();
    }
}
