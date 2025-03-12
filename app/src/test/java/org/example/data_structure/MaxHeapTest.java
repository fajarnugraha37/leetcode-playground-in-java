package org.example.data_structure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {
    @Test
    void main() {
        MaxHeap maxHeap = new MaxHeap(Arrays.asList(0));
        maxHeap.print();

        int peek = maxHeap.peek();
        System.out.format("\npeek = %d\n", peek);

        maxHeap.push(7);
        maxHeap.push(8);
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(9);
        maxHeap.print();

        for (int i = 0; i < 3; i++) {
            int pop = maxHeap.pop();
            System.out.format("\npop %d = %d\n", i,  pop);
            maxHeap.print();
        }

        int size = maxHeap.size();
        System.out.format("\nsize =  %d\n", size);

        boolean isEmpty = maxHeap.isEmpty();
        System.out.format("\nis empty = %b\n", isEmpty);
    }
}