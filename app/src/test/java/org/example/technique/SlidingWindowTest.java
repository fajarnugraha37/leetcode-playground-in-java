package org.example.technique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlidingWindowTest {
    int lengthOfLongestSubstring(String s) {
        var substringL = new ArrayList<Character>();
        var largestlength = 0;
        for (var right = 0; right < s.length(); right++) {
            if (substringL.contains(s.charAt(right))) {
                // get the index of the char
                var index = substringL.indexOf(s.charAt(right));
                substringL.remove(index);
                if (index > 0)
                    substringL.subList(0, index).clear();
            }
            substringL.add(s.charAt(right));
            largestlength = Math.max(largestlength, substringL.size());
        }

        return largestlength;
    }

    @ParameterizedTest
    @CsvSource({
            "geeksforgeeks,7",
            "aaa,1",
            "abcdefabcbb,6",
            "aabbbccdddd,2",
    })
    void q3_lengthOfLongestSubstring(String input, int expectedOutput) {
        var output = lengthOfLongestSubstring(input);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public int characterReplacement(String s, int k) {
        int[] arr = new int[26];
        int ans = 0;
        int max = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            arr[s.charAt(j) - 'A']++;
            max = Math.max(max, arr[s.charAt(j) - 'A']);
            if (j - i + 1 - max > k) {
                arr[s.charAt(i) - 'A']--;
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    @ParameterizedTest
    @CsvSource({
            "ABAB,2,4",
            "AABABBA,1,4",
    })
    void q424_LongestRepeatingCharacterReplacement(String input1, int input2, int expectedOutput) {
        var output = characterReplacement(input1, input2);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public String minWindowSubstring(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char x : t.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int matched = 0;
        int start = 0;
        int minLen = s.length() + 1;
        int subStr = 0;
        for (int endWindow = 0; endWindow < s.length(); endWindow++) {
            char right = s.charAt(endWindow);
            if (map.containsKey(right)) {
                map.put(right, map.get(right) - 1);
                if (map.get(right) == 0) matched++;
            }

            while (matched == map.size()) {
                if (minLen > endWindow - start + 1) {
                    minLen = endWindow - start + 1;
                    subStr = start;
                }
                char deleted = s.charAt(start++);
                if (map.containsKey(deleted)) {
                    if (map.get(deleted) == 0) matched--;
                    map.put(deleted, map.get(deleted) + 1);
                }
            }
        }
        return minLen > s.length() ? "" : s.substring(subStr, subStr + minLen);
    }

    @ParameterizedTest
    @CsvSource({
            "ADOBECODEBANC,ABC,BANC",
            "a,a,a",
    })
    void q76_Minimum_WindowSubstring(String input1, String input2, String expectedOutput) {
        var output = minWindowSubstring(input1, input2);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public int numOfSubArrays(int[] arr, int k, int threshold) {
        int res = 0, currSum = 0;

        for (int i = 0; i < k - 1; i++) {
            currSum += arr[i];
        }

        for (int l = 0; l < arr.length - k + 1; l++) {
            currSum += arr[l + k - 1];
            if ((currSum / k) >= threshold) {
                res++;
            }
            currSum -= arr[l];
        }
        return res;
    }

    @ParameterizedTest
    @CsvSource({
            "3,3,4,'2,2,2,2,5,5,5,8'",
            "6,3,5,'11,13,17,23,29,31,7,5,2,3'",
    })
    void q1343_NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold(int expectedOutput, int k, int threshold, String strArr) {
        var arr = Arrays.stream(strArr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        var output = numOfSubArrays(arr, k, threshold);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }
}
