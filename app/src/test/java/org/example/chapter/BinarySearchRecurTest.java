package org.example.chapter;

import org.junit.jupiter.api.Test;

import static org.example.chapter.BinarySearchRecur.binarySearch;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchRecurTest {
    @Test
    void main() {
        int target = 6;
        int[] nums = { 1, 3, 6, 8, 12, 15, 23, 26, 31, 35 };

        int index = binarySearch(nums, target);
        assertEquals(2, index);
    }
}