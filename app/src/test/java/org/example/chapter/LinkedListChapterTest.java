package org.example.chapter;

import org.example.data_structure.ListNode;
import org.example.util.PrintUtil;
import org.junit.jupiter.api.Test;

import static org.example.chapter.LinkedListChapter.*;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListChapterTest {
    @Test
    void main() {
        ListNode n0 = new ListNode(1);
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(5);
        ListNode n4 = new ListNode(4);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        System.out.println("initialize; count nodes = " + count(n0));
        PrintUtil.printLinkedList(n0);

        insert(n0, new ListNode(0));
        System.out.println("insert operations; count nodes = " + count(n0));
        PrintUtil.printLinkedList(n0);

        remove(n0);
        System.out.println("remove operations; count nodes = " + count(n0));
        PrintUtil.printLinkedList(n0);

        ListNode node = access(n0, 3);
        System.out.println("access #3  = " + node.val);

        int index = find(n0, 2);
        System.out.println("find node with value 2 = " + index);
    }
}