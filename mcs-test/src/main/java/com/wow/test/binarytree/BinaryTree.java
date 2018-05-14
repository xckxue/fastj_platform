package com.wow.test.binarytree;

/**
 * Created by wow on 2018/3/22.
 */
public class BinaryTree {

    class Node{
        private Node leftNode;
        private Node rightNode;
        private int data;
        Node(int data){
            this.data = data;
        }
    }

    private Node root;

    public void buildTree(Node node,int data){
        if(root == null){
            root = new Node(data);
        }else{
            if(node.data > data){
                if(node.leftNode == null){
                    node.leftNode = new Node(data);
                }else{
                    buildTree(node.leftNode,data);
                }
            }
            if(node.data < data){
                if(node.rightNode == null){
                    node.rightNode = new Node(data);
                }else{
                    buildTree(node.rightNode,data);
                }
            }
        }

    }

    public void preOrder(Node node){
        if (node != null){
            System.out.print(node.data+",");
            preOrder(node.leftNode);
            preOrder(node.rightNode);
        }
    }

    public static void main(String[] args) {
        int[] a = {33,4,5,66,1,7,7,6,4,3,3};
        BinaryTree binaryTree = new BinaryTree();
        for(int i : a){
            binaryTree.buildTree(binaryTree.root,i);
        }
        binaryTree.preOrder(binaryTree.root);
    }
}
