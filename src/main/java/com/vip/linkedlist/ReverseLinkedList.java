package com.vip.linkedlist;

// https://leetcode.com/problems/reverse-linked-list/description/

// 2 pointer solution. prev = head, curr = head.next while (curr!=null) {swap pointers; if curr.next != null, set newhead}
// Recursive and iterative solution possible.
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head.next;
        ListNode prev = head;

        ListNode newHead = null;
        while (curr != null) {
            if (curr.next == null)
                newHead = curr;
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        head.next = null;

        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }


        return prev;
    }

    class CrudeImplementation {

        ListNode newHead = null;

        public ListNode reverseList(ListNode head) {
            if (head == null)
                return null;

            if (head.next == null)
                return head;

            recurseList(head);
            head.next = null;

            return newHead;
        }

        public ListNode recurseList(ListNode node) {
            if (node.next == null) {
                newHead = node;
                return node;
            }

            ListNode currNode = recurseList(node.next);
            currNode.next = node;

            return node;
        }
    }

    void printlist(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList obj = new ReverseLinkedList();
        ListNode head, node;
        node = new ListNode();
        head = null;
        boolean isOnce = true;
        int i = 1;
        while (i <= 11) {
            node.next = new ListNode();
            node = node.next;
            if (isOnce) {
                isOnce = false;
                head = node;
            }

            node.val = i++;
        }
        node = null;

        // obj.printlist(head);

        obj.printlist(obj.reverseList1(head));
    }
}
