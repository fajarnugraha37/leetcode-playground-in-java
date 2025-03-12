package org.example.chapter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.example.chapter.ArrayChapter.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayChapterTest {
    @Test
    void main() {
        int[] arr = new int[5];
        System.out.println("arr = " + Arrays.toString(arr));
        int[] nums = {1, 3, 2, 5, 4};
        System.out.println("nums = " + Arrays.toString(nums));

        int randomNum = ArrayChapter.randomAccess(nums);
        System.out.println("nums random access " + randomNum);

        nums = extend(nums, 3);
        System.out.println("nums extend by 3 = " + Arrays.toString(nums));
        assertEquals(8, nums.length);

        insert(nums, 6, 3);
        System.out.println("nums insert on #3 with value 6 nums = " + Arrays.toString(nums));
        assertEquals(6, nums[3]);

        remove(nums, 2);
        System.out.println("remove #2 nums = " + Arrays.toString(nums));
        assertNotEquals(2, nums[2]);

        traverse(nums);

        int index = find(nums, 3);
        System.out.println("nums find 3 result = " + index);
        assertEquals(1, index);
    }
}