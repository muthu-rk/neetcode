package com.vip.heaps;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/description/

// Problem: Find Kth largest element not Kth distinct element in a stream. Implement constructor and add()
// Use MinHeap. Complete binary tree. Root always contains min value. nth layer is always less than n+1 and so on.
// Minheap: In array representation, Left child: (2k+1), Right child: (2k+2), parent is at (k-1)/2
// In Java, implemented via PritorityQueue DS. pq.add () -> Insert, pq.poll() -> remove, pq.peek() -> get min without remove
// PQ DS is unbounded. So, QSIZE has to be explicitly maintained. Set QSIZE = k 

// Note: To find KthSmallestElement. Use MaxHeap DS.
// In java, same PQ is used. In Constructor pass new PriorityQueue<>(Collections.reverseOrder()) to make min to maxheap.
public class KthLargestElement {
    class KthLargest {

        PriorityQueue<Integer> pq;
        int QSIZE = 0;

        public KthLargest(int k, int[] nums) {
            pq = new PriorityQueue<>();
            QSIZE = k;
            for (int n : nums) {
                pq.add(n);
                System.out.println("Size after adding " + n + " is " + pq.size());
            }

            while (pq.size() > QSIZE) {
                System.out.println("Overflowed.. removing. : " + pq.poll());
                // pq.poll();
            }
        }

        public int add(int val) {
            pq.add(val);
            System.out.println("Size after adding " + val + " is " + pq.size());

            if (pq.size() > QSIZE) {
                // pq.poll();
                System.out.println("Q full.. removing. : " + pq.poll());
            }

            return pq.peek();
        }
    }

    void test_01() {
        int[] a = new int[] { 4, 5, 8, 2 };
        KthLargest kthLargest = new KthLargest(3, a);

        System.out.println("ADDING");
        System.out.println(kthLargest.add(3)); // return 4
        System.out.println(kthLargest.add(5)); // return 5
        System.out.println(kthLargest.add(10)); // return 5
        System.out.println(kthLargest.add(9)); // return 8
        System.out.println(kthLargest.add(4)); // return 8
    }

    void test_02() {
        // [[1,[]],[-3],[-2],[-4],[0],[4]]
        int[] a = new int[] {};
        KthLargest kthLargest = new KthLargest(1, a);

        System.out.println("ADDING");
        System.out.println(kthLargest.add(-3)); 
        System.out.println(kthLargest.add(-2)); 
        System.out.println(kthLargest.add(-4)); 
        System.out.println(kthLargest.add(0)); 
        System.out.println(kthLargest.add(4)); 

    }
    public static void main(String[] args) {
        KthLargestElement obj = new KthLargestElement();
        obj.test_01();
        // obj.test_02();
    }

    // Practicing MinHeap with PriorityQueues

    // static void view() {
    // System.out.println("Elements");
    // pq.forEach(p -> {
    // System.out.println(p);
    // });
    // }

    // static void insert(int i) {
    // if (pq.size() >= QSIZE) {
    // System.out.println("Q full.. removing. : " + pq.poll());
    // }

    // pq.add(i);
    // System.out.println("Size after adding " + i + " is " + pq.size());
    // }

    // static int QSIZE = 5;
    // static PriorityQueue<Integer> pq = new PriorityQueue<>();

    // public static void main(String[] args) {

    // System.out.println("Size: " + pq.size());

    // insert(11);
    // insert(2);
    // insert(7);
    // insert(3);
    // insert(5);
    // insert(8);
    // insert(5);

    // System.out.println("Size: " + pq.size());

    // view();

    // System.out.println("Min: " + pq.poll());

    // view();

    // System.out.println("Min: " + pq.poll());

    // }
}
