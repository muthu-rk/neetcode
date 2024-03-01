package com.vip.linkedlist;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

// Problem: Remove nth node from end
// No recursion needed.
// Reverse the list and delete nth node in the rev list is possible. But requires O(n) space. Following is O(1) space.
// 1. Maintain 2 pointers left and right. 
// 2. Right is moved n positions away from left.
// 3. To get the node prev to the node to delete, maintain a dummy pointer at the start.
public class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;

        if (head.next == null)
            return null;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode left = dummy, right = head;
        while (n > 0 && right != null) {
            n--;
            right = right.next;
        }

        // Note: Wont work if head has to be deleted.
        
        // System.out.println("Right: " + right.val);

        while (right != null) {
            left = left.next;
            right = right.next;
        }

        // System.out.println("Left: " + left.val);

        left.next = left.next.next;
        return dummy.next;
    }

    void printlist(ListNode node) {
        System.out.println("List");
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEnd obj = new RemoveNthNodeFromEnd();
        ListNode head, node;
        node = new ListNode();
        head = null;
        boolean isOnce = true;
        int i = 1;
        while (i <= 5) {
            node.next = new ListNode();
            node = node.next;
            if (isOnce) {
                isOnce = false;
                head = node;
            }

            node.val = i++;
        }
        node = null;

        obj.removeNthFromEnd(head, 4);
        obj.printlist(head);
    }
}
