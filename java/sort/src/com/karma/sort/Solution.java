package com.karma.sort;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the sortedInsert function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     int data;
     *     DoublyLinkedListNode next;
     *     DoublyLinkedListNode prev;
     * }
     *
     */
    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        
        if(head == null){
            head = new DoublyLinkedListNode(data);
            return head;
        }


        DoublyLinkedListNode curr = head;
        while(curr.next != null && data > curr.data)
            curr = curr.next;
        
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);

        if(curr.prev == null && curr.next == null) {
            if(data < curr.data){
                node.next = curr;
                curr.prev = node;
                head = node;
            }else{
                curr.next = node;
                node.prev = curr;
            }
        }else if(curr.prev == null && curr.next != null){
            if(data < curr.data){
                node.next = curr;
                curr.prev = node;
                head = node;
            }else{
                node.next = curr.next;
                node.prev = curr;
                curr.next.prev = node;
                curr.next = node; 
            }
        }else if(curr.prev != null && curr.next == null){
            if(data < curr.data){
                node.next = curr;
                node.prev = curr.prev;
                curr.prev.next = node;
                curr.prev = node;
            }else{
                node.next = curr.next;
                node.prev = curr;
                curr.next = node;        
            }        
        }else{
        	node.next = curr;
        	node.prev = curr.prev;
        	curr.prev.next = node;
        	curr.prev = node; 
        }

        return head;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/singg/my_repos/datastructure-algo/java/sort/output.txt"));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            DoublyLinkedList llist = new DoublyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            int data = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            DoublyLinkedListNode llist1 = sortedInsert(llist.head, data);

            printDoublyLinkedList(llist1, " ", bufferedWriter);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

