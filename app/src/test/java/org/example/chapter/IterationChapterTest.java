package org.example.chapter;

import org.junit.jupiter.api.Test;

import static org.example.chapter.IterationChapter.*;
import static org.junit.jupiter.api.Assertions.*;

class IterationChapterTest {
    @Test
    void main() {
        int n = 5;
        int res;

        res = forLoop(n);
        System.out.println("\nfor 循环的求和结果 res = " + res);

        res = whileLoop(n);
        System.out.println("\nwhile 循环的求和结果 res = " + res);

        res = whileLoopII(n);
        System.out.println("\nwhile 循环（两次更新）求和结果 res = " + res);

        String resStr = nestedForLoop(n);
        System.out.println("\n双层 for 循环的遍历结果 " + resStr);
    }
}