package com.vip.binarysearch;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


// https://leetcode.com/problems/time-based-key-value-store/description/
// Problem: Cache storing multiple values across timestamps. Implement get() and set(). If get(ts) doesnt match, return floor of ts value or ""
// Java: Use HashMap<String, TreeMap<Integer, String>> to store sorted timestamps in the cache.
// Java: Use floorKeys() to get <= ts key.
// Alternatively, you can implement a binary search over ts array to find the nearest entry. Edgecases to be handled carefully as BS returns 1 index more or less than the target if not present. Another edge case is to handle non-presence of entry and return -1 safely esp when mid is close to 0;
// However handcrafted BS approach failed in large testcases due to time limit error. 20k sets + 20k gets.
public class TimeKvs {
    class TimeMap {

        Map<String, TreeMap<Integer, String>> cache = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            if (!cache.containsKey(key)) {
                cache.put(key, new TreeMap<>());
            }

            cache.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> tvs = cache.get(key);
            if (tvs == null)
                return "";
            else {
                Integer ts = tvs.floorKey(timestamp);

                if (ts == null)
                    return "";
                return tvs.get(ts);
            }
        }

        public String get_old(String key, int timestamp) {
            Map<Integer, String> tvs = cache.get(key);
            if (tvs == null)
                return "";
            else {
                int ts = binarySearchApprox(tvs.keySet().toArray(new Integer[tvs.keySet().size()]), timestamp);
                if (ts == -1)
                    return "";
                return tvs.get(ts);
            }
        }

        public void set_old(String key, String value, int timestamp) {
            TreeMap<Integer, String> val = cache.get(key);
            if (val == null) {
                TreeMap<Integer, String> tvs = new TreeMap<>();
                tvs.put(timestamp, value);
                val = tvs;
            } else {
                val.put(timestamp, value);
            }

            cache.put(key, val);
        }

        int binarySearchApprox(Integer[] arr, int target) {
            int left = 0, right = arr.length - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (arr[mid] == target) {
                    // System.out.println("pos: " + mid);
                    return target;
                } else if (target > arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (arr[mid] < target) {
                // System.out.println("pos: " + mid);

                return arr[mid];
            }

            int i = mid;
            while (arr[i] < target && i > 0 && i < arr.length - 1)
                i++;

            if (i == mid) {
                i--;
                while (i > 0 && i < arr.length - 1 && arr[i] > target)
                    i--;
            }

            // System.out.println("pos: " + arr[i]);
            return (i < 0) ? -1 : arr[i];
        }
    }

    static int binarySearchApprox(Integer[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                System.out.println("pos: " + mid);
                return target;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (arr[mid] < target) {
            System.out.println("pos: " + mid);

            return arr[mid];
        }

        int i = mid;
        while (arr[i] < target && i > 0 && i < arr.length - 1)
            i++;

        if (i == mid) {
            i--;
            while (i > 0 && i < arr.length - 1 && arr[i] > target)
                i--;
        }

        // System.out.println("pos: " + arr[i]);
        return (i < 0) ? -1 : arr[i];
    }

    void test_01() {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeMap.get("foo", 1)); // return "bar"
        System.out.println(timeMap.get("foo", 3)); // return "bar", since there is no value corresponding to foo at
                                                   // timestamp 3 and
        // timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeMap.get("foo", 4)); // return "bar2"
        System.out.println(timeMap.get("foo", 5)); // return "bar2"
    }

    // [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
    void test_02() {
        TimeMap timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
        System.out.println(timeMap.get("love", 5)); // return "[]"
        System.out.println(timeMap.get("love", 10)); // return "high",
        System.out.println(timeMap.get("love", 15)); // return "high",
        System.out.println(timeMap.get("love", 20)); // return "high",
        System.out.println(timeMap.get("love", 25)); // return "high",
    }

    public static void main(String[] args) {
        // Integer[] time = new Integer[] { 1, 2, 5, 6, 9, 10, 13 };
        // System.out.println("val: " + binarySearchApprox(time, 0));

        TimeKvs obj = new TimeKvs();
        obj.test_02();
    }
}
