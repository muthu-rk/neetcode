package com.vip.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// https://leetcode.com/problems/design-twitter/

// Problem: Design twitter LLD.
// Key ideas: Maintain a global timestamp across tweets from different users. And increment in post call.
// User PriorityQueue to tweets based on timestamps across users and his followers.
// While constructing newsfeed, use MaxHeap.
// class TweetTimePair {tweetId, timestamp}, 
// class User {Set<FollowerId>, List<TweetTimePair>, PriorityQueue<TweetTimePair> pq; }
// class Twitter { int globaltime = 0; Map<Integer, User> uMap;}
// Java: Use a comparator to compare timestamp for PQ. This comparator makes it a maxheap instead of default minheap.
public class TwitterDesign {
    class TweetTimePair {
        int timeStamp;
        int tweetId;

        TweetTimePair(int ts, int twtId) {
            timeStamp = ts;
            tweetId = twtId;
        }
    }

    class TimestampComparator implements Comparator<TweetTimePair> {
        public int compare(TweetTimePair t1, TweetTimePair t2) {
            if (t1.timeStamp < t2.timeStamp)
                return 1;
            else if (t1.timeStamp > t2.timeStamp)
                return -1;
            return 0;
        }
    }

    class User {
        int userId;
        List<TweetTimePair> tweetList; // Timestamp, TweetId
        Set<Integer> followSet;
        PriorityQueue<TweetTimePair> pq;

        User() {
            tweetList = new ArrayList<>();
            followSet = new HashSet<>();
        }

        List<Integer> getNewsFeed(List<TweetTimePair> tweetList) {
            pq = new PriorityQueue<>(new TimestampComparator());

            tweetList.forEach(t -> pq.add(t));
            List<Integer> retVal = new ArrayList<>();
            int count = 0;
            while (count <= 10 && !pq.isEmpty()) {
                retVal.add(pq.poll().tweetId);
                count++;
            }
            return retVal;
        }

        List<TweetTimePair> getSubList() {
            int size = tweetList.size();
            if (size < 10)
                return tweetList;
            else
                return tweetList.subList(size - 10, size);
        }
    }

    class Twitter {
        int globaltime = 0;
        Map<Integer, User> uMap;

        public Twitter() {
            uMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            globaltime++;
            User user = uMap.get(userId);
            if (user == null) {
                user = createAndAddUser(userId);
            }

            user.tweetList.add(new TweetTimePair(globaltime, tweetId));
        }

        User createAndAddUser(int userId) {
            User user = new User();
            user.userId = userId;
            uMap.put(userId, user);
            return user;
        }

        public List<Integer> getNewsFeed(int userId) {
            List<TweetTimePair> ttp = new ArrayList<>();

            User user = uMap.get(userId);
            if (user == null)
                return new ArrayList<>();

            List<TweetTimePair> subList = user.getSubList();
            if (subList != null && !subList.isEmpty())
                ttp.addAll(subList);
            for (Integer fId : user.followSet) {
                User follower = uMap.get(fId);
                subList = follower.getSubList();
                if (subList != null && !subList.isEmpty())
                    ttp.addAll(subList);
            }

            return user.getNewsFeed(ttp);
        }

        public void follow(int followerId, int followeeId) {
            addRemoveUser(followerId, followeeId, true);
        }

        public void unfollow(int followerId, int followeeId) {
            addRemoveUser(followerId, followeeId, false);
        }

        void addRemoveUser(int userId, int followerId, boolean isAdd) {
            User user = uMap.get(userId);
            if (user == null) {
                user = createAndAddUser(userId);
            }
            if (isAdd)
                user.followSet.add(followerId);
            else
                user.followSet.remove(followerId);

            uMap.put(userId, user);

            User follower = uMap.get(followerId);
            if (follower == null) {
                follower = createAndAddUser(followerId);
            }
            uMap.put(followerId, follower);
        }
    }

    void test_01() {
        // [[],[1,5],[1,3],[1,101],[1,13],[1,10],[1,2],[1,94],[1,505],[1,333],[1,22],[1]]
        Twitter obj = new Twitter();

        obj.postTweet(1, 5);
        obj.postTweet(1, 3);
        obj.postTweet(1, 101);
        obj.postTweet(1, 13);
        obj.postTweet(1, 10);
        obj.postTweet(1, 2);
        obj.postTweet(1, 94);
        obj.postTweet(1, 505);
        obj.postTweet(1, 333);
        obj.postTweet(1, 22);

        System.out.println(obj.getNewsFeed(1));
    }

    void test_02() {
        Twitter obj = new Twitter();
        System.out.println(obj.getNewsFeed(1));
    }

    void test_03() {
        Twitter obj = new Twitter();

        obj.postTweet(1, 5);
        System.out.println(obj.getNewsFeed(1));

        obj.follow(1, 2);
        obj.postTweet(2, 6);

        System.out.println(obj.getNewsFeed(1));

        obj.unfollow(1, 2);
        System.out.println(obj.getNewsFeed(1));
    }

    public static void main(String[] args) {
        TwitterDesign td = new TwitterDesign();
        td.test_01();
    }

    void scrap() {
        TwitterDesign obj = new TwitterDesign();
        // PriorityQueue<TweetTimePair> pq = new PriorityQueue<>(obj.new TimestampComparator());

        List<TweetTimePair> ttp = new ArrayList<>();

        TweetTimePair t1 = obj.new TweetTimePair(1, 1);
        TweetTimePair t2 = obj.new TweetTimePair(2, 2);
        TweetTimePair t3 = obj.new TweetTimePair(3, 3);
        TweetTimePair t4 = obj.new TweetTimePair(4, 4);
        TweetTimePair t5 = obj.new TweetTimePair(5, 5);
        TweetTimePair t6 = obj.new TweetTimePair(6, 6);
        TweetTimePair t7 = obj.new TweetTimePair(7, 7);
        TweetTimePair t8 = obj.new TweetTimePair(8, 8);
        TweetTimePair t9 = obj.new TweetTimePair(9, 9);

        ttp.add(t2);
        ttp.add(t3);
        ttp.add(t6);
        ttp.add(t7);
        ttp.add(t8);
        ttp.add(t9);
        ttp.add(t1);
        ttp.add(t4);
        ttp.add(t5);

        List<TweetTimePair> ttp2 = ttp.subList(ttp.size() - 5, ttp.size());
        ttp2.forEach(t -> System.out.println(t.tweetId));

        // ttp.forEach(t -> pq.add(t));
        // List<Integer> retVal = new ArrayList<>();
        // while (!pq.isEmpty()) {
        // retVal.add(pq.poll().tweetId);
        // }

        // retVal.forEach(r -> System.out.println(r));

        // Map<Integer, Integer> hm1 = new HashMap<>();
        // Map<Integer, Integer> hm2 = new HashMap<>();

        // // Map<Integer, Integer> hm2 = new HashMap<>();
        // hm1.put(1, 10);
        // hm1.put(3, 30);
        // hm1.put(5, 50);
        // hm1.put(7, 70);
        // hm1.put(9, 90);

        // // Map<Integer, Integer> hm2 = new HashMap<>();
        // hm2.put(2, 20);
        // hm2.put(4, 40);
        // hm2.put(6, 60);
        // hm2.put(8, 80);
        // hm2.put(10, 100);
    }
}
