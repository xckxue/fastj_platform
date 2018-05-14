package com.wow.test;

import org.apache.xpath.SourceTree;

/**
 * Created by wow on 2018/3/15.
 */
public class MyLink {

    private Node current;
    private Node head;

    private int length;


    public void put(Object o) {
        Node node = new Node(o);
        if (head == null) {
            head = node;
            return;
        }
        current.setNext(node);
        current = node;
        length++;
    }

    public void print(){
        current = head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void insert(int i, Object o) {
        Node node = new Node(o);
    }

    public class Node {
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
