package com.vip.linkedlist;

// https://leetcode.com/problems/add-two-numbers/

// Problem: Add 2 numbers represented as linked list.
public class AddTwoNumbers {

    class OldImplementation {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode outPtr = null;
            ListNode outHead = null;

            ListNode ptr1 = l1;
            ListNode ptr2 = l2;
            ListNode newNode;
            int carry = 0;
            while (ptr1 != null && ptr2 != null) {
                int sum = ptr1.val + ptr2.val + carry;
                int reminder = sum % 10;
                carry = sum / 10;

                newNode = new ListNode(reminder, null);
                if (outHead == null) {
                    outHead = outPtr = newNode;
                } else {
                    outPtr.next = newNode;
                    outPtr = newNode;
                }

                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }

            while (ptr1 != null) {
                int sum = ptr1.val + carry;
                int reminder = sum % 10;
                carry = sum / 10;

                newNode = new ListNode(reminder, null);
                if (outHead == null) {
                    outHead = outPtr = newNode;
                } else {
                    outPtr.next = newNode;
                    outPtr = newNode;
                }

                ptr1 = ptr1.next;
            }

            while (ptr2 != null) {
                int sum = ptr2.val + carry;
                int reminder = sum % 10;
                carry = sum / 10;

                newNode = new ListNode(reminder, null);
                if (outHead == null) {
                    outHead = outPtr = newNode;
                } else {
                    outPtr.next = newNode;
                    outPtr = newNode;
                }

                ptr2 = ptr2.next;
            }

            if (carry != 0) {
                newNode = new ListNode(carry, null);
                if (outHead == null) {
                    outHead = outPtr = newNode;
                } else {
                    outPtr.next = newNode;
                    outPtr = newNode;
                }
            }

            return outHead;
        }
    }

}
