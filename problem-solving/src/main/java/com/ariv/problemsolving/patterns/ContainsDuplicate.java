package com.ariv.problemsolving.patterns;

import java.util.HashMap;
import java.util.HashSet;

public class ContainsDuplicate {

    /**
     * Input:- {1, 2, 3, 1}
     *
     */
    public boolean containsDuplicateBf(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Input:- {1, 2, 3, 1}
     */
    public boolean containsDuplicateHM(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * Input:- {1, 2, 3, 1}
     */
    public boolean containsDuplicateHS(int[] nums) {
        var map = new HashSet<Integer>();
        for (int num : nums) {
            if (!map.add(num)) {
                return true;
            }
        }
        return false;
    }
}
