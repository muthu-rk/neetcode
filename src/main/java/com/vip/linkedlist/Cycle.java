package com.vip.linkedlist;

// https://leetcode.com/problems/linked-list-cycle/

// Floyd's Tortoise and hare technique. Maintain a fast and slow pointer.
// They eventually meet. Time taken to meet is the distance between the fast and slow pointers.
// If there are 10 steps between F and S, then they meet in 10 steps.
// Time complexity: O(n), Space: O(1)
public class Cycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;

        if (head == null || head.next == null)
            return false;

        do {
            if (fast.next == null)
                return false;
            fast = fast.next.next; // Move 2 steps;
            slow = slow.next;
        } while (fast != null && fast != slow);

        return ((fast == null) ? false : true);
    }

    void printlist(ListNode node) {
        System.out.println("List");
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Cycle obj = new Cycle();

        ListNode node = new ListNode(1);
        ListNode head = node;

        node.next = new ListNode(2);
        node = node.next;
        node.next = null;

        obj.printlist(head);
        System.out.println(obj.hasCycle(head));
    }
}
