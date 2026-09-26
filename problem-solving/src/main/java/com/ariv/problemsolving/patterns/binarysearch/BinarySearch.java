package com.ariv.problemsolving.patterns.binarysearch;

/**
 * Binary Search is a search algorithm that finds the position of a target value within a sorted array.
 * It compares the target value to the middle element of the array; if they are not equal,
 * it eliminates half of the array from consideration and continues the search on the remaining half,
 * repeating this process until the target value is found or the remaining array is empty.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class BinarySearch {

    /**
     * Performs binary search on a sorted array to find the target value.
     *
     * @param nums   The sorted array of integers.
     * @param target The target value to search for.
     * @return true if the target is found in the array, false otherwise.
     */
    public boolean binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
