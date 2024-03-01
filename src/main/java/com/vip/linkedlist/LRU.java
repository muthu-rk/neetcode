package com.vip.linkedlist;

import java.util.HashMap;

// https://leetcode.com/problems/lru-cache/description/

// Problem: LRU Cache limited to a capacity. Evict using LRU policy. Implement get() and set(). Both operations should be in O(1)
// Double linked list of nodes. Head points to MRU and tail points to LRU. Only DLL will yield O(n) 
// Use Hashmap to store pointers to node. HashMap< key, Node>.
// 
// In get() 
//      a) Check in hm. 
//      b) if exists, move node to head in DLL. 
//      c) return val or -1
// In set()
//      b) Check in hm, 
//      c) if exist, remove node from DLL and hm
//      a) if capacity reached, evict tail node and remove from hm.
//      d) create new DLL node and move to head. 
//      e) Add entry to hm
public class LRU {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = next = null;
        }

        Node() {
        }
    }

    class LRUCache {
        HashMap<Integer, Node> hm;
        int capacity;
        int currCount;
        Node head, tail; // head points to MRU, tail points to LRU node.

        public LRUCache(int capacity) {
            hm = new HashMap<>();
            head = tail = null;
            this.capacity = capacity;
            currCount = 0;
        }

        public int get(int key) {
            System.out.println(">>> GET: " + key);
            if (hm.containsKey(key)) {
                Node curr = hm.get(key);
                moveToHead(curr);
                return curr.val;
            }

            return -1;
        }

        public void put(int key, int value) {
            System.out.println(">>> PUT: " + key);

            if (hm.containsKey(key)) {
                Node rn = hm.get(key);
                hm.remove(key);
                removeNode(rn);
                currCount--;
            }

            if (currCount == capacity) {
                int keyToRemove = removeTail();
                hm.remove(keyToRemove);
                currCount--;
            }

            Node newNode = new Node(key, value);
            addToHead(newNode);

            hm.put(key, newNode);

            currCount = Math.min(currCount + 1, capacity);
        }

        void addToHead(Node node) {
            if (head != null) {
                head.prev = node;
            }

            node.prev = null;
            node.next = head;
            head = node;

            if (tail == null)
                tail = head;

            printList("Add to head");
        }

        void moveToHead(Node node) {
            if (head == node) { // do nothing
                printList("Move to head");
                return;
            } else if (tail == node) {
                tail = node.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;

            printList("Move to head");
        }

        void removeNode(Node node) {
            if (head == tail && head == node) { // only one node
                head = tail = null;
                return;
            }

            if (node == head) {
                head = node.next;
                head.prev.next = null; // freeing prev head
                head.prev = null;
            } else if (node == tail) {
                tail = tail.prev;
                tail.next.prev = null; // freeing prev tail
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            printList("Remove node");
        }

        int removeTail() {
            int key = tail.key;
            if (head != tail) {
                tail = tail.prev;
                tail.next.prev = null; // freeup prev tail
                tail.next = null;
            } else {
                head = tail = null;
            }

            printList("Remove tail");

            return key;
        }

        void printList(String msg) {
            // if (true)
            //     return;
            System.out.println(msg + "****");
            Node n = head;

            System.out.println("From head");
            while (n != null) {
                System.out.println("Key: " + n.key + " Val: " + n.val);
                n = n.next;
            }

            n = tail;
            System.out.println("From tail:");
            while (n != null) {
                System.out.println("Key: " + n.key + " Val: " + n.val);
                n = n.prev;
            }
        }
    }

    void test_01() {
        LRU lru = new LRU();

        LRUCache lRUCache = lru.new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1)); // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2)); // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1)); // return -1 (not found)
        System.out.println(lRUCache.get(3)); // return 3
        System.out.println(lRUCache.get(4)); // return 4

    }

    void test_02() {
        LRU lru = new LRU();

        LRUCache lRUCache = lru.new LRUCache(1);
        lRUCache.put(2, 1); // cache is {2=1}
        System.out.println(lRUCache.get(2)); // return 1
    }

    void test_03() {
        // [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]

        LRU lru = new LRU();
        LRUCache lRUCache = lru.new LRUCache(2);
        lRUCache.put(2, 1); // cache is {2=1}
        lRUCache.put(2, 2); // cache is {2=2}
        System.out.println(lRUCache.get(2)); // return 2
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(4, 1); // cache is {4=1}
        System.out.println(lRUCache.get(2)); // return -1
    }

    public static void main(String[] args) {
        LRU lru = new LRU();
        // lru.test_01();
        // lru.test_02();
        lru.test_03();
        // testDLL();
    }

    static void testDLL() {
        LRU lru = new LRU();
        LRUCache obj = lru.new LRUCache(5);

        Node n1 = lru.new Node(1, 1);
        Node n2 = lru.new Node(2, 2);
        Node n3 = lru.new Node(3, 3);
        Node n4 = lru.new Node(4, 4);

        obj.addToHead(n1);
        obj.addToHead(n2);
        obj.addToHead(n3);
        obj.addToHead(n4);

        obj.moveToHead(n3);
        obj.moveToHead(n1);
        obj.moveToHead(n4);

        obj.removeTail();
        // obj.removeTail();
        // obj.removeTail();
        // obj.removeTail();
        // obj.removeTail();
        obj.removeNode(n4);

        Node n5 = lru.new Node(5, 5);
        Node n6 = lru.new Node(6, 6);
        Node n7 = lru.new Node(7, 7);
        Node n8 = lru.new Node(8, 8);
        obj.addToHead(n5);
        obj.addToHead(n6);
        obj.addToHead(n7);
        obj.addToHead(n8);

        obj.removeNode(n5);
        obj.removeNode(n3);
        obj.removeNode(n8);
    }
}
