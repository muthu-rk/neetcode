package com.vip.misc;

// https://leetcode.com/problems/merge-two-sorted-lists/description/
public class MergeSortedList_05 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;

        if (list2 == null)
            return list1;

        ListNode mergeHead = new ListNode();
        ListNode mergePtr = mergeHead;

        ListNode l1Ptr = list1;
        ListNode l2Ptr = list2;
        ListNode prev = null;

        while (l1Ptr != null && l2Ptr != null) {
            if (l1Ptr.val < l2Ptr.val) {
                mergePtr.val = l1Ptr.val;
                l1Ptr = l1Ptr.next;
            } else {
                mergePtr.val = l2Ptr.val;
                l2Ptr = l2Ptr.next;
            }
            mergePtr.next = new ListNode();
            prev = mergePtr;
            mergePtr = mergePtr.next;
        }

        while (l1Ptr != null) {
            mergePtr.val = l1Ptr.val;
            l1Ptr = l1Ptr.next;
            mergePtr.next = new ListNode();
            prev = mergePtr;
            mergePtr = mergePtr.next;
        }

        while (l2Ptr != null) {
            mergePtr.val = l2Ptr.val;
            l2Ptr = l2Ptr.next;
            mergePtr.next = new ListNode();
            prev = mergePtr;
            mergePtr = mergePtr.next;
        }
        // mergePtr = null;
        prev.next = null;

        return mergeHead;
    }

    public ListNode mergeTwoLists_NoCopy(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;

        if (list2 == null)
            return list1;

        ListNode head = new ListNode();
        ListNode newNode = head;
        ListNode tmp;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tmp = list1;
                list1 = list1.next;

            } else {
                tmp = list2;
                list2 = list2.next;
            }

            tmp.next = null;
            newNode.next = tmp;
            newNode = newNode.next;
        }

        while (list1 != null) {
            newNode.next = list1;
            newNode = newNode.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            newNode.next = list2;
            newNode = newNode.next;
            list2 = list2.next;
        }

        newNode.next = null;

        return head.next;
    }
}