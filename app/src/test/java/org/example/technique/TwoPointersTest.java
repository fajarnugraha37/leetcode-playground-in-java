package org.example.technique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoPointersTest {

    /**
     * Check if a given string is a palindrome, considering alphanumeric characters only and ignoring cases.
     *
     * @param s The input string to be checked for palindrome.
     * @return A boolean indicating if the input string is a palindrome.
     */
    public boolean isPalindrome(String s) {
        // Initialize two pointers
        int leftIndex = 0;
        int rightIndex = s.length() - 1;

        // Continue comparing characters while left index is less than right index
        while (leftIndex < rightIndex) {
            // If the character at the left index is not alphanumeric, move the left pointer to the right
            if (!Character.isLetterOrDigit(s.charAt(leftIndex))) {
                leftIndex++;
            }
            // If the character at the right index is not alphanumeric, move the right pointer to the left
            else if (!Character.isLetterOrDigit(s.charAt(rightIndex))) {
                rightIndex--;
            }
            // If the characters at both indexes are alphanumeric, compare them ignoring case
            else if (Character.toLowerCase(s.charAt(leftIndex)) != Character.toLowerCase(s.charAt(rightIndex))) {
                // If characters do not match, it's not a palindrome
                return false;
            } else {
                // If characters match, move both pointers
                leftIndex++;
                rightIndex--;
            }
        }

        // If all alphanumeric characters were matched successfully, it is a palindrome
        return true;
    }

    @ParameterizedTest
    @CsvSource({
            "a,true",
            "bb,true",
            "ccc,true",
            "cac,true",
            "acc,false",
            "ccd,false",
            "xcx,true",
    })
    void q125_ValidPalindrom(String input, boolean expectedOutput) {
        var output = isPalindrome(input);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array to make the two-pointer technique applicable
        Arrays.sort(nums);

        // Initialize the list to store the triplets
        List<List<Integer>> triplets = new ArrayList<>();

        // Get the length of the array
        int length = nums.length;

        // Iterate over the array, looking for the first element of the triplet
        for (int first = 0; first < length - 2 && nums[first] <= 0; ++first) {
            // Skip duplicate elements to avoid duplicate triplets
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // Initialize the second and third pointers
            int second = first + 1;
            int third = length - 1;

            // Use two-pointer technique to find the remaining two elements
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];

                // If the sum is less than zero, move the second pointer to the right
                if (sum < 0) {
                    ++second;
                }
                // If the sum is greater than zero, move the third pointer to the left
                else if (sum > 0) {
                    --third;
                }
                // If the sum is zero, we've found a valid triplet
                else {
                    triplets.add(List.of(nums[first], nums[second], nums[third]));

                    // Move the second pointer to the right and skip duplicates
                    while (second < third && nums[second] == nums[second + 1]) {
                        ++second;
                    }
                    // Move the third pointer to the left and skip duplicates
                    while (second < third && nums[third] == nums[third - 1]) {
                        --third;
                    }

                    // Move both pointers for the next potential triplet
                    ++second;
                    --third;
                }
            }
        }
        // Return the list of triplets
        return triplets;
    }

    @ParameterizedTest
    @CsvSource({
            "'-1,0,1,2,-1,-4'",
            "'0,1,1'",
            "'0,0,0'",
    })
    void q15_ThreeSum(String strArr) {
        var arr = Arrays.stream(strArr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var output = threeSum(arr);
        System.out.println(output);
    }


    // Method to find the maximum area formed between the vertical lines
    public int maxArea(int[] height) {
        // Initialize two pointers at the beginning and end of the array
        int left = 0;
        int right = height.length - 1;
        // Variable to keep track of the maximum area
        int maxArea = 0;

        // Iterate until the two pointers meet
        while (left < right) {
            // Calculate the area with the shorter line as the height and the distance between the lines as the width
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            // Update the maximum area if the current area is larger
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer that points to the shorter line towards the center
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximum area found
        return maxArea;
    }

    @ParameterizedTest
    @CsvSource({
            "'1,8,6,2,5,4,8,3,7',49",
            "'1,1',1",
    })
    void q11_ContainerWithMostWater(String strArr, int expectedOutput) {
        var arr = Arrays.stream(strArr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var output = maxArea(arr);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public int[] twoSum2InputArrayIsSorted(int[] numbers, int target) {
        // Initialize pointers for the two indices to be checked
        int left = 0;                           // Starting from the beginning of the array
        int right = numbers.length - 1;         // Starting from the end of the array

        // Loop continues until the correct pair is found
        while (left < right) {
            // Calculate the sum of the elements at the left and right indices
            int sum = numbers[left] + numbers[right];

            // Check if the sum is equal to the target
            if (sum == target) {
                // Return the indices of the two numbers,
                // incremented by one to match the problem's one-based indexing requirement
                return new int[]{left + 1, right + 1};
            }

            // If the sum is less than the target, increment the left index to increase the sum
            if (sum < target) {
                left++;
            } else {
                // If the sum is greater than the target, decrement the right index to decrease the sum
                right--;
            }
        }

        // The problem statement guarantees that exactly one solution exists,
        // so the following statement is unreachable. This return is used to satisfy the syntax requirements.
        return new int[]{-1, -1};
    }

    @ParameterizedTest
    @CsvSource({
            "'2,7,11,15',9,'1,2'",
            "'2,3,4',6,'1,3'",
            "'-1,0',-1,'1,2'",
    })
    void q167_TwoSum2InputArrayIsSorted(String strArr, int target, String strExpectedOutput) {
        var arr = Arrays.stream(strArr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var expectedOutput = Arrays.stream(strExpectedOutput.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var output = twoSum2InputArrayIsSorted(arr, target);
        System.out.println(Arrays.toString(output));
        assertArrayEquals(expectedOutput, output);
    }
}
