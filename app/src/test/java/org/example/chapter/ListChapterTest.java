package org.example.chapter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListChapterTest {
    @Test
    void main() {
        Integer[] numbers = new Integer[] { 1, 3, 2, 5, 4 };
        List<Integer> nums = new ArrayList<>(Arrays.asList(numbers));
        System.out.println("nums = " + nums);

        int num = nums.get(1);
        System.out.println("#1 num = " + num);

        nums.set(1, 0);
        System.out.println("#1 set to 0 nums = " + nums);

        nums.clear();
        System.out.println("clear nums = " + nums);

        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(5);
        nums.add(4);
        System.out.println("add 5 items nums = " + nums);

        nums.add(3, 6);
        System.out.println("add on #3 with value 6 nums = " + nums);

        nums.remove(3);
        System.out.println("remove #3 nums = " + nums);

        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            count += nums.get(i);
        }
        for (int x : nums) {
            count += x;
        }

        List<Integer> nums1 = new ArrayList<>(Arrays.asList(new Integer[] { 6, 8, 7, 10, 9 }));
        nums.addAll(nums1);
        System.out.println("nums1 merge with nums; nums = " + nums);

        Collections.sort(nums);
        System.out.println("Sort nums = " + nums);
    }
}