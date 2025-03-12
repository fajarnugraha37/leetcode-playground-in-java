package org.example.chapter;

import org.example.data_structure.CustomList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomListChapterTest {
    @Test
    void main() {
        CustomList nums = new CustomList();
        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(5);
        nums.add(4);
        System.out.println("nums = " + Arrays.toString(nums.toArray()));
        System.out.println("capacity = " + nums.capacity());
        System.out.println("size = " + nums.size());

        nums.insert(3, 6);
        System.out.println("insert on #3 with value 6 nums = " + Arrays.toString(nums.toArray()));

        nums.remove(3);
        System.out.println("remove on #3 nums = " + Arrays.toString(nums.toArray()));

        int num = nums.get(1);
        System.out.println("get #1 num = " + num);

        nums.set(1, 0);
        System.out.println("set #1 with value 0 nums = " + Arrays.toString(nums.toArray()));

        for (int i = 0; i < 10; i++) {
            nums.add(i);
        }
        System.out.println("nums = " + Arrays.toString(nums.toArray()));
        System.out.println("capacity = " + nums.capacity());
        System.out.println("size = " + nums.size());
    }
}