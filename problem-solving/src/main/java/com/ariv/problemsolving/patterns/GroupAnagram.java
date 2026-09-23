package com.ariv.problemsolving.patterns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {

    /**
     * Input:- ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Output:- [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * O(n*k log k) time complexity and O(n * k) space complexity
     *
     */
    public List<List<String>> groupAnagramsBf(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            java.util.Arrays.sort(chars); //
            String sortedStr = new String(chars);
            map.computeIfAbsent(sortedStr, k -> new java.util.ArrayList<>()).add(str);
            // tae -> [eat, tea, ate]

        }
        return new java.util.ArrayList<>(map.values());
    }

    /**
     * Input:- ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Output:- [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * O(n*k) time complexity and O(n * k) space complexity
     *
     */
    public List<List<String>> groupAnagramsDp(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        // Count the frequency of each character in the string and use it as a key
        for (String str : strs) {
            //
            int[] count = new int[26];
            //
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            //
            StringBuilder sb = new StringBuilder();
            //
            for (int i : count) {
                sb.append(i).append('#');
            }
            //
            String key = sb.toString();
            //
            map.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(str);
        }
        return new java.util.ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams_FrequencyMap(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char c : str.toCharArray()) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
            String key = freqMap.toString();
            map.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(str);
        }
        return new java.util.ArrayList<>(map.values());
    }
}
