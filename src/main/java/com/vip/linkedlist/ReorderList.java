package com.vip.linkedlist;

// https://leetcode.com/problems/reorder-list/description/

// Problem: You are given the head of a singly linked-list. The list can be represented as:
//      L0 → L1 → … → Ln - 1 → Ln
//      Reorder the list to be on the following form:
//      L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …

// 1. Split the list into 2. Find middle and split into 2. To find middle, run fast and slow pointers.
// 2. Reverse the second list
// 3. Merge 2 lists.
// 4. Take care of odd/even list size.
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = findMiddle(head);
        // Split into 2 lists.
        ListNode list2 = mid.next;
        mid.next = null;
        list2 = reverseList(list2);

        // printlist(head, "1st list");
        // printlist(list2, "2nd list");

        // Merge lists
        ListNode ptr1 = head, ptr2 = list2;
        while (ptr1 != null && ptr2 != null) {
            ListNode l1tmp = ptr1.next;
            ListNode l2tmp = ptr2.next;

            ptr1.next = ptr2;
            ptr2.next = l1tmp;
            ptr1 = l1tmp;
            ptr2 = l2tmp;
        }

        // Note: List 1.size > List2.size. So, ptr2 will hit null first always. Leaving remaining List1 entries as-is should work.

        // printlist(head, "Merged list");
    }

    ListNode reverseList(ListNode head) {
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

    ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
                // System.out.println("Fast: " + fast.val);
            } else
                break;
            slow = slow.next;
            // System.out.println("Slow: " + slow.val);
        }

        return slow;
    }

    void printlist(ListNode node, String msg) {
        System.out.println(">>>" + msg);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ReorderList obj = new ReorderList();

        ListNode head, node;
        node = new ListNode();
        head = null;
        boolean isOnce = true;
        int i = 1;
        while (i <= 9) {
            node.next = new ListNode();
            node = node.next;
            if (isOnce) {
                isOnce = false;
                head = node;
            }

            node.val = i++;
        }
        node = null;

        obj.reorderList(head);
    }

    class OldImplementation {
        public void reorderList(ListNode head) {
            int count = 0;
            ListNode ptr = head;
            while (ptr != null) {
                ptr = ptr.next;
                count++;
            }
            swapRec(head, count, head, 0);
        }

        void swapRec(ListNode head, int total, ListNode ptr, int pos) {
            if (ptr == null)
                return;
            swapRec(head, total, ptr.next, ++pos);

            if (total % 2 != 0) {
                if (pos > (total / 2)) {
                    ptr.next = null;
                    int c = (total - pos) * 2;
                    ListNode tmp1 = head;
                    while (c != 0) {
                        tmp1 = tmp1.next;
                        c--;
                    }
                    ListNode tmp2 = tmp1.next;
                    tmp1.next = ptr;
                    ptr.next = tmp2;
                }
            } else {
                if (pos > (total / 2)) {
                    ptr.next = null;
                    int c = (total - pos) * 2;
                    ListNode tmp1 = head;
                    int end = 0;
                    if (total - c == 2) {
                        return;
                        // end = 1;
                    }
                    while (c != end) {
                        tmp1 = tmp1.next;
                        c--;
                    }
                    ListNode tmp2 = tmp1.next;
                    // if (end == 0) {
                    tmp1.next = ptr;
                    ptr.next = tmp2;
                    // }
                    if (end == 1) {
                        tmp2.next = null;
                    }
                }
            }

            // printRec(head);
        }
    }

}
