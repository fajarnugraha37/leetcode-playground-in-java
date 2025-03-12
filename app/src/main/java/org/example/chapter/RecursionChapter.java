package org.example.chapter;


import java.util.Stack;

public final class RecursionChapter {
    private RecursionChapter() {
    }

    public static int recur(int n) {
        if (n == 1)
            return 1;
        int res = recur(n - 1);
        return n + res;
    }

    public static int forLoopRecur(int n) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = n; i > 0; i--) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        // res = 1+2+3+...+n
        return res;
    }

    public static int tailRecur(int n, int res) {
        if (n == 0)
            return res;
        return tailRecur(n - 1, res + n);
    }

    public static int fib(int n) {
        // f(1) = 0, f(2) = 1
        if (n == 1 || n == 2)
            return n - 1;
        // f(n) = f(n-1) + f(n-2)
        int res = fib(n - 1) + fib(n - 2);
        // f(n)
        return res;
    }
}
