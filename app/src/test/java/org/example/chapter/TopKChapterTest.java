package org.example.chapter;

import org.example.util.PrintUtil;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.example.chapter.TopKChapter.topKHeap;
import static org.junit.jupiter.api.Assertions.*;

class TopKChapterTest {
    @Test
    void main() {
        int[] nums = { 1, 7, 6, 3, 2 };
        int k = 2;

        Queue<Integer> res = topKHeap(nums, k);
        System.out.println("k = " + k);
        PrintUtil.printHeap(res);
    }
}