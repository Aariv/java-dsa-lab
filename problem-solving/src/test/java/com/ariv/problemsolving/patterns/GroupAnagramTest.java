package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupAnagramTest {

    @Test
    public void testGroupAnagrams() {
        GroupAnagram groupAnagram = new GroupAnagram();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        var list = groupAnagram.groupAnagrams_FrequencyMap(input);
        assertEquals(3, list.size());
    }
}
