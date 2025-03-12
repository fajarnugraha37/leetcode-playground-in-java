package org.example.technique;

import org.example.data_structure.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListReversalTest {
    void q206_() {
    }

    void q143_() {
    }

    void q25_() {
    }

    void q92_() {
    }

    void q24_() {
    }

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode temp = reverse(slow);
        while (temp != null && head != null) {
            if (temp.val != head.val) return false;
            temp = temp.next;
            head = head.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode p = null;
        ListNode q = null;
        ListNode r = head;
        while (r != null) {
            p = q;
            q = r;
            r = r.next;
            q.next = p;
        }

        return q;
    }

    @Test
    void q234_PalindromeLinkedList() {
        var head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        var output = isPalindrome(head);
        assertTrue(output);
    }
}
