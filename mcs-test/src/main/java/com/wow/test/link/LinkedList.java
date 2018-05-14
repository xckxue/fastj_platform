package com.wow.test.link;

/**
 * Created by wow on 2018/4/11.
 */
public class LinkedList {

    private ListNode head;
    private ListNode current;

    private int count;

    //增加
    public void add(ListNode node) {
        if (head == null) {
            head = new ListNode(0);
            current = head.next = node;
        }else{
            current.next = node;
            current = current.next;
        }
        count++;
    }

    public void insertNode(int index, ListNode node) {
        if (index < 1 || index > count) {
            throw new RuntimeException("位置错误");
        }

        int length = 1;
        ListNode cur = head;
        while (cur.next != null) {
            if (length++ == index) {
                node.next = cur.next;
                cur.next = node;
                return;
            }
            cur = cur.next;
        }
    }

    //删除
    public void remove(int index) {
        if (index < 1 || index > count) {
            throw new RuntimeException("位置错误");
        }

        int length = 1;

        ListNode cur = head;
        while (head.next != null) {
            if (index == length++) {
                cur.next = cur.next.next;
                cur.next.next = null;
                return;
            }
            cur = cur.next;
        }

    }

    //排序
    public void sortList() {
        ListNode p1 = head;
        while(p1.next != null){
            ListNode p2 = p1.next;
            while(p2.next != null){
                if(p1.next.data > p2.next.data){
                    int tmp = p1.next.data;
                    p1.next.data = p2.next.data;
                    p2.next.data = tmp;
                }
                p2 = p2.next;
            }
            p1 = p1.next;
        }
    }

    public int getLength() {
        return count;
    }

    public void print(){
        ListNode cur = head;
        while(cur.next != null){
            System.out.println(cur.next.data);
            cur = cur.next;
        }
    }

    public ListNode reverse(){
        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
