package org.example.data_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.example.util.PrintUtil;

public class MaxHeap {
    private List<Integer> maxHeap;

    public MaxHeap(List<Integer> nums) {
        maxHeap = new ArrayList<>(nums);
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        int tmp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(j));
        maxHeap.set(j, tmp);
    }

    public int size() {
        return maxHeap.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int peek() {
        return maxHeap.get(0);
    }

    public void push(int val) {
        maxHeap.add(val);
        siftUp(size() - 1);
    }

    private void siftUp(int i) {
        while (true) {
            int p = parent(i);
            if (p < 0 || maxHeap.get(i) <= maxHeap.get(p))
                break;
            swap(i, p);
            i = p;
        }
    }

    public int pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        swap(0, size() - 1);
        int val = maxHeap.remove(size() - 1);
        siftDown(0);
        return val;
    }

    private void siftDown(int i) {
        while (true) {
            int l = left(i), r = right(i), ma = i;
            if (l < size() && maxHeap.get(l) > maxHeap.get(ma))
                ma = l;
            if (r < size() && maxHeap.get(r) > maxHeap.get(ma))
                ma = r;
            if (ma == i)
                break;
            swap(i, ma);
            i = ma;
        }
    }

    public void print() {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> { return b - a; });
        queue.addAll(maxHeap);
        PrintUtil.printHeap(queue);
    }
}