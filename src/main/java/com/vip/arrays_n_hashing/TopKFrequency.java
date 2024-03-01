package com.vip.arrays_n_hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/top-k-frequent-elements/description/
public class TopKFrequency {
    public int[] topKFrequent(int[] nums, int k) {
        // if (k == 1)
        // return new int[] { nums[0] };

        if (nums.length == 1)
            return nums;

        // Item:Frequency
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i : nums) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }

        // Initialize bucket list.
        // bucket is indexed with frequency count instead of entries.
        // Hence max freq can be the size of the array. Each item appearing only once.
        // Frequency: [Items]
        List<List<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            bucketList.add(new ArrayList<>());
        }

        hm.forEach((key, value) -> System.out.println("hm: key " + key + " value: "
                + value));

        // Read HM value and use bucket to store the frequency list
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            bucketList.get(entry.getValue()).add(entry.getKey());
        }

        for (int i = 0; i < bucketList.size(); i++) {
            System.out.println("Bucket " + i);
            List<Integer> be = bucketList.get(i);
            be.forEach(e -> System.out.println(e));
        }

        int topFreq = k;
        List<Integer> retVal = new ArrayList<>();
        for (int i = bucketList.size() - 1; i >= 0; i--) {
            List<Integer> entry = bucketList.get(i);
            if (entry.size() > 0) {
                if (entry.size() <= topFreq) {
                    retVal.addAll(entry);
                    topFreq -= entry.size();
                } else {
                    int j = 0;
                    while (topFreq > 0) {
                        retVal.add(entry.get(j++));
                        topFreq--;
                    }
                }
            }

            if (topFreq == 0)
                break;
        }

        int[] retArray = new int[retVal.size()];
        for (int i = 0; i < retVal.size(); i++) {
            retArray[i] = retVal.get(i);
        }
        return retArray;
    }

    public void test_01() {
        int[] nums = { 1, 1, 1, 2, 2, 2, 3 };
        int[] out = topKFrequent(nums, 2);
        System.out.println("Test 1:");
        for (int i : out)
            System.out.println(i);
    }

    public void test_02() {
        int[] nums = { -1, -1 };
        int[] out = topKFrequent(nums, 1);
        System.out.println("Test 2:");
        for (int i : out)
            System.out.println(i);
    }

    public void test_03() {
        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 1 };
        int[] out = topKFrequent(nums, 2);
        System.out.println("Test 3:");
        for (int i : out)
            System.out.println(i);
    }

    public static void main(String[] args) {
        TopKFrequency obj = new TopKFrequency();
        // obj.test_01();
        obj.test_02();

    }
}
