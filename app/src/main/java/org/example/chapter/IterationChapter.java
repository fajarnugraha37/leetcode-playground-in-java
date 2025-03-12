package org.example.chapter;

public final class IterationChapter {
    private IterationChapter() {
    }

    public static int forLoop(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        return res;
    }

    public static int whileLoop(int n) {
        int res = 0;
        int i = 1;
        while (i <= n) {
            res += i;
            i++;
        }
        return res;
    }

    public static int whileLoopII(int n) {
        int res = 0;
        int i = 1;
        while (i <= n) {
            res += i;
            i++;
            i *= 2;
        }
        return res;
    }

    public static String nestedForLoop(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                res.append("(" + i + ", " + j + "), ");
            }
        }
        return res.toString();
    }
}
