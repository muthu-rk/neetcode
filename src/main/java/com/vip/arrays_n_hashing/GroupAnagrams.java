package com.vip.arrays_n_hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//  https://leetcode.com/problems/group-anagrams/description/
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<HashMap<Character, Integer>> hml = new ArrayList<>();
        List<List<String>> out = new ArrayList<>();

        for (String str : strs) {
            HashMap<Character, Integer> hmTemp = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                hmTemp.put(str.charAt(i), hmTemp.getOrDefault(str.charAt(i), 0) + 1);
            }

            boolean isPresent = false;
            int index = -1;
            for (int i = 0; i < hml.size(); i++) {
                HashMap<Character, Integer> hs = hml.get(i);
                if (hs.equals(hmTemp)) {
                    index = i;
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                hml.add(hmTemp);
                List<String> newList = new ArrayList<>();
                newList.add(str);
                out.add(newList);
            } else {
                List<String> currList = out.get(index);
                currList.add(str);
            }
        }
        return out;
    }

    public void test_01() {
        String[] input = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };

        List<List<String>> out = groupAnagrams(input);
        System.out.println("Test 1: ");
        out.forEach(l -> {
            System.out.println("**");
            l.forEach(s -> System.out.println(s));
        });
    }

    public void test_02() {
        String[] input = new String[] { "" };

        List<List<String>> out = groupAnagrams(input);
        System.out.println("Test 2: ");
        out.forEach(l -> {
            System.out.println("**");
            l.forEach(s -> System.out.println(s));
        });
    }

    public void test_03() {
        String[] input = new String[] { "a" };

        List<List<String>> out = groupAnagrams(input);
        System.out.println("Test 3: ");
        out.forEach(l -> {
            System.out.println("**");
            l.forEach(s -> System.out.println(s));
        });
    }

    public static void main(String[] args) {
        GroupAnagrams obj = new GroupAnagrams();
        obj.test_01();
        obj.test_02();
        obj.test_03();

    }
}
