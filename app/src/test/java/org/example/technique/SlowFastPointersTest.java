package org.example.technique;

import org.example.data_structure.ListNode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class SlowFastPointersTest {
    public boolean hasCycle(ListNode head) {
        var fast = head;
        var slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    @Test
    void q141_LinkedListCycle() {
    }

    public ListNode detectCycle(ListNode head) {
        var slow = head;
        var fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        ListNode slow2 = head;
        while (slow != slow2) {
            slow = slow.next;
            slow2 = slow2.next;
        }
        return slow;
    }

    @Test
    void q142_LinkedListCycle_II() {
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;

        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode first = temp, second = temp;

        while (n > 0) {
            second = second.next;
            n--;
        }

        while (second.next != null) {
            second = second.next;
            first = first.next;
        }

        first.next = first.next.next;
        return temp.next;
    }

    @Test
    void q19_RemoveNTHNodeFromEndOfList() {
    }

    public boolean isHappy(int n) {
        if (n == 1 || n == -1) {
            return true;
        }

        Function<Integer, Integer> sumOfSquare = (Integer nx) -> {
            int output = 0;
            while (nx != 0) {
                int digit = nx % 10;
                digit = digit * digit;
                output += digit;
                nx = nx / 10;
            }

            return output;
        };

        Set<Integer> visit = new HashSet<Integer>();

        // compute square until getting duplicate value
        while (!visit.contains(n)) {
            visit.add(n);
            // using helper function to compute the sum of squares
            n = sumOfSquare.apply(n);

            if (n == 1) return true;
        }

        return false;
    }

    @Test
    void q202_HappyNumber() {
    }

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int slow2 = 0;
        do {
            slow = nums[slow];
            slow2 = nums[slow2];
        } while (slow != slow2);

        return slow2;
    }

    @Test
    void q287_FindTheDuplicateNumber() {
    }
}
