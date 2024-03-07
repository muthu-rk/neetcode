package com.vip.linkedlist;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/copy-list-with-random-pointer/description/

// Problem: Singly linked list with a random pointer either null or pointing to other node or itself. Perform deepcopy.
// 1. Perform 2 passes. 
// 2. Maintain hashmap of old nodes and new nodes. 
// 3. In first pass copy single linked list, store old and new nodes into HM.
// 4. In second pass, refer the HM and map to new node.
public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node newHead = null, node = head;
        Map<Node, Node> hm = new HashMap<>();
        Node dummyNode = new Node(-1);

        while (node != null) {
            dummyNode.next = new Node(node.val);
            if (node == head) {
                newHead = dummyNode.next;
            }

            hm.put(node, dummyNode.next);

            dummyNode = dummyNode.next;
            node = node.next;
        }

        node = head;
        while (node != null) {
            Node rNode = node.random;
            Node entry = hm.get(node);

            if (rNode != null) {
                entry.random = hm.get(rNode);
            } else {
                entry.random = null;
            }

            node = node.next;
        }
        return newHead;
    }

    void printlist(Node node) {
        System.out.println("List");
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    void printlistRandom(Node node) {
        System.out.println("List");
        while (node != null) {
            System.out.println(">>" + node.val);
            System.out.println("    Random: ");
            if (node.random != null)
                System.out.println("        " + node.random.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer obj = new CopyListWithRandomPointer();
        Node n1 = obj.new Node(1);
        Node n2 = obj.new Node(2);
        Node n3 = obj.new Node(3);
        Node n4 = obj.new Node(4);
        Node n5 = obj.new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        n1.random = n2;
        n2.random = null;
        n3.random = n5;
        n4.random = n4;
        n5.random = n1;

        // obj.printlist(n1);
        // obj.printlistRandom(n1);

        Node newHead = obj.copyRandomList(n1);
        obj.printlistRandom(newHead);
    }
}
