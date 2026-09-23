package com.ariv.problemsolving.patterns;

public class ValidAnagram {

    /**
     *
     * Input:- s = "anagram", t = "nagaram"
     *
     *
     */
    public boolean isAnagramBf(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = t.indexOf(c);
            if (index == -1) {
                return false;
            }
            t = t.substring(0, index) + t.substring(index + 1);
        }
        return true;
    }

    /**
     *
     * Input:- s = "anagram", t = "nagaram"
     *
     *
     */
    public boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        java.util.Arrays.sort(sArr);
        java.util.Arrays.sort(tArr);
        return java.util.Arrays.equals(sArr, tArr);
    }

    /**
     *
     * Input:- s = "anagram", t = "nagaram"
     *
     *
     */
    public boolean isAnagramCount(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *  Input:- s = "anagram", t = "nagaram"
     *
     */
    public boolean isAnagram_FrequencyMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        java.util.Map<Character, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }
        for (int count : map.values()) {
            if (count != 0) {
                return false;
            }
        }
        return false;
    }
}
