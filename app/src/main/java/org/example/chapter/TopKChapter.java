package org.example.chapter;

import java.util.PriorityQueue;
import java.util.Queue;

public class TopKChapter {
    public static Queue<Integer> topKHeap(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<Integer>();
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap;
    }
}
