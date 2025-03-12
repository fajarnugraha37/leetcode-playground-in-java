package org.example.technique;

import org.example.data_structure.ListNode;
import org.example.util.PrintUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TopKElements {
    class SolutionFindKthLargestWithSelect {
        // Function to find the kth largest element in the array
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            // Find the (n-k)th smallest element because the kth largest is also the (n-k)th smallest when sorted in ascending order
            return quickSelect(nums, 0, n - 1, n - k);
        }

        // Helper function to perform quick select
        private int quickSelect(int[] nums, int left, int right, int kSmallest) {
            // When the left and right pointers meet, we've found the kSmallest element
            if (left == right) {
                return nums[left];
            }

            // Initialize two pointers for the partitioning step
            int i = left - 1;
            int j = right + 1;
            // Choose pivot as the middle element
            int pivot = nums[(left + right) >>> 1];

            while (i < j) {
                // Move i right past any elements less than the pivot
                do {
                    i++;
                } while (nums[i] < pivot);

                // Move j left past any elements greater than the pivot
                do {
                    j--;
                } while (nums[j] > pivot);

                // Swap elements at i and j if they are out of order with respect to the pivot
                if (i < j) {
                    swap(nums, i, j);
                }
            }

            // After partitioning, the pivot is now at index j
            // If we found the kSmallest element, return it
            if (j >= kSmallest) {
                return quickSelect(nums, left, j, kSmallest);
            }

            // Otherwise, continue the search in the right partition
            return quickSelect(nums, j + 1, right, kSmallest);
        }

        // Swap function to swap two elements in the array
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public int findKthLargest(int[] nums, int k) {
        //create a min heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        //iterate over the array
        for (int n : nums) {
            //first add the integer to heap
            heap.add(n);
            //if size of the heap is greater than k
            if (heap.size() > k) {
                //remove the root element (lowest of all)
                heap.poll();
            }
        }

        //finally heap has k largest elements left with root as the kth largest element
        return heap.peek();
    }

    @ParameterizedTest
    @CsvSource({
            "'3,2,1,5,6,4',2,5",
            "'3,2,3,1,2,4,5,5,6',4,4",
    })
    void q215_KthLargestElementInAnArray(String strArr, int k, int expectedOutput) {
        var arr = Arrays.stream(strArr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var output = findKthLargest(arr, k);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public int[] topKFrequent(int[] nums, int k) {
        var arr = new int[k];
        var map = new HashMap<Integer, Integer>();
        for (var num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        var priorityQueue = new PriorityQueue<Map.Entry<Integer, Integer>>((a, b) -> a.getValue() - b.getValue());
        for (var it : map.entrySet()) {
            priorityQueue.add(it);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }

        int i = k;
        while (!priorityQueue.isEmpty()) {
            arr[--i] = priorityQueue.poll().getKey();
        }

        return arr;
    }

    @ParameterizedTest
    @CsvSource({
            "'1,1,1,2,2,3',2,'1,2'",
            "'1',1,'1'",
    })
    void q347_(String strArr, int k, String strExpectedOutput) {
        var arr = Arrays.stream(strArr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var expectedOutput = Arrays.stream(strExpectedOutput.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var output = topKFrequent(arr, k);
        System.out.println(Arrays.toString(output));
        assertArrayEquals(expectedOutput, output);
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0)
            return Collections.emptyList();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] + a[1] - (b[0] + b[1]));
        for (int i = 0; i < nums1.length; i++) {
            queue.add(new int[]{nums1[i], nums2[0], 0});
        }
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        while (count < k && !queue.isEmpty()) {
            int[] cur = queue.remove();
            result.add(Arrays.asList(cur[0], cur[1]));
            int nextIndex = cur[2] + 1;
            if (nextIndex < nums2.length) {
                queue.add(new int[]{cur[0], nums2[nextIndex], nextIndex});
            }
            count++;
        }
        return result;
    }

    @Test
    void q373_FindKPairsWithSmallestSums() {
        {
            var nums1 = new int[]{1, 7, 11};
            var nums2 = new int[]{2, 4, 6};
            var k = 3;
            var output = kSmallestPairs(nums1, nums2, k);
            System.out.println("Output: " + output);
            assertEquals("[[1, 2], [1, 4], [1, 6]]", output.toString());
        }
        {
            var nums1 = new int[]{1, 1, 2};
            var nums2 = new int[]{1, 2, 3};
            var k = 2;
            var output = kSmallestPairs(nums1, nums2, k);
            System.out.println("Output: " + output);
            assertEquals("[[1, 1], [1, 1]]", output.toString());
        }
        {
            var nums1 = new int[]{1, 2};
            var nums2 = new int[]{3};
            var k = 3;
            var output = kSmallestPairs(nums1, nums2, k);
            System.out.println("Output: " + output);
            assertEquals("[[1, 3], [2, 3]]", output.toString());
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return dummy.next;
    }

    @Test
    void q23_MergeKSortedLists() {
        {
            var node1 = new ListNode(1);
            node1.next = new ListNode(4);
            node1.next.next = new ListNode(5);

            var node2 = new ListNode(1);
            node2.next = new ListNode(3);
            node2.next.next = new ListNode(4);

            var node3 = new ListNode(2);
            node3.next = new ListNode(6);

            var output = mergeKLists(new ListNode[]{node1, node2, node3});
            PrintUtil.printLinkedList(output);
        }
    }

}
