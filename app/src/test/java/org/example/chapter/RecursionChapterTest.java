package org.example.chapter;

import org.junit.jupiter.api.Test;

import static org.example.chapter.RecursionChapter.*;
import static org.junit.jupiter.api.Assertions.*;

class RecursionChapterTest {
    @Test
    void main() {
        int n = 5;
        int res;

        res = recur(n);
        System.out.println("\n递归函数的求和结果 res = " + res);

        res = forLoopRecur(n);
        System.out.println("\n使用迭代模拟递归求和结果 res = " + res);

        res = tailRecur(n, 0);
        System.out.println("\n尾递归函数的求和结果 res = " + res);

        res = fib(n);
        System.out.println("\n斐波那契数列的第 " + n + " 项为 " + res);
    }
}