package org.example.chapter;

import java.util.concurrent.ThreadLocalRandom;

public final class ArrayChapter {
    private ArrayChapter() {
    }

    public static int randomAccess(int[] nums) {
        int randomIndex = ThreadLocalRandom.current()
                .nextInt(0, nums.length);
        int randomNum = nums[randomIndex];

        return randomNum;
    }

    public static int[] extend(int[] nums, int enlarge) {
        int[] res = new int[nums.length + enlarge];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }

        return res;
    }

    public static void insert(int[] nums, int num, int index) {
        for (int i = nums.length - 1; i > index; i--) {
            nums[i] = nums[i - 1];
        }
        nums[index] = num;
    }

    public static void remove(int[] nums, int index) {
        for (int i = index; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }

    public static void traverse(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }

        for (int num : nums) {
            count += num;
        }
    }

    public static int find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }
}
