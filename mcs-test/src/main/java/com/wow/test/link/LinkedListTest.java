package com.wow.test.link;

/**
 * Created by wow on 2018/4/11.
 */
public class LinkedListTest {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        LinkedList list = new LinkedList();
        list.add(node1);
        list.add(node3);
        list.add(node2);
        list.sortList();
        list.print();

    }
}
