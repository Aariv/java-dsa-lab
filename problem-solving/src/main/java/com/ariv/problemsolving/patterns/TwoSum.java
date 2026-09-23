package com.ariv.problemsolving.patterns;

import java.util.HashMap;

public class TwoSum {

    /**
     * Input:- {2, 7, 11, 15} Target = 9
     * Input:- {2, 7, 11, 15} Target = 10
     *
     * O(n^2) time complexity and O(1) space complexity
     *
     */
    // Brute Force Approach
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 1};
    }

    /**
     * Input:- {2, 7, 11, 15} Target = 9
     * Input:- {2, 7, 11, 15} Target = 10
     *
     * O(n) time complexity and O(n) space complexity
     *
     */
    // HashMap Approach
    public int[] twoSumHashMap(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            // For every current element, calculate what I need and check if I had already seen it?
            int complement = target - nums[i]; // 9-2 = 7; 9-7 = 2;
            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i}; // 0, 1
            }
            map.put(nums[i], i); // 2->0;
        }
        return new int[] {-1, -1};
    }
}
