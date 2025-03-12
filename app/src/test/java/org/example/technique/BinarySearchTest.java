package org.example.technique;

import org.junit.jupiter.api.Test;

public class BinarySearchTest {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = {-1, -1};

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (nums[mid] == target) {
                result = findEdges(mid, nums, target);
                break;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }

        return result;
    }

    public int[] findEdges(int mid, int[] nums, int target) {
        int left = mid;
        int right = mid;
        int[] result = {left, right};

        while (left >= 0 && nums[left] == target) {
            result[0] = left;
            left--;
        }

        while (right <= nums.length - 1 && nums[right] == target) {
            result[1] = right;
            right++;
        }

        return result;
    }

    @Test
    void q34_FindFirstAndLastPositionOfElementInSortedArray() {
    }

    public int searchInsertPosition(int[] nums, int target) {
        //o(log n) and o(1)
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target > nums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    @Test
    void q35_SearchInsertPosition() {
    }

    public int findMinimumInRotatedSortedArray(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            if (nums[l] <= nums[r]) {
                return nums[l];
            }

            int mid = (l + r) / 2;
            if (nums[mid] >= nums[l]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return 0;
    }

    @Test
    void q153_FindMinimumInRotatedSortedArray() {
    }

    public int searchInRotatedSortedArray(int[] nums, int target) {

        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {

            int mid = (l + r) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            //left sorted
            if (nums[l] <= nums[mid]) {
                if (target > nums[mid] || target < nums[l]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {//right sorted
                if (target < nums[mid] || target > nums[r]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

        }

        return -1;
    }

    @Test
    void q33_SearchInRotatedSortedArray() {
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the number of rows and columns in the matrix
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        // Start from the bottom-left corner of the matrix
        int row = rowCount - 1;
        int col = 0;

        // Perform a staircase search
        while (row >= 0 && col < colCount) {
            if (matrix[row][col] == target) {
                // Target is found at the current position
                return true;
            }
            if (matrix[row][col] > target) {
                // Target is less than the current element, move up
                row--;
            } else {
                // Target is greater than the current element, move right
                col++;
            }
        }

        // Target was not found in the matrix
        return false;
    }

    @Test
    void q240_SearchA2DMatrix_II() {
    }
}
