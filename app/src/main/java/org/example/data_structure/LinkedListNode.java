package org.example.data_structure;

public class LinkedListNode {
    public int val; // 节点值
    public LinkedListNode next; // 后继节点引用
    public LinkedListNode prev; // 前驱节点引用

    public LinkedListNode(int val) {
        this.val = val;
        prev = next = null;
    }

    public static LinkedListNode arrToLinkedList(int[] arr) {
        LinkedListNode dum = new LinkedListNode(0);
        LinkedListNode head = dum;
        for (int val : arr) {
            head.next = new LinkedListNode(val);
            head = head.next;
        }
        return dum.next;
    }
}