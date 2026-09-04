package com.ariv.dsa.datastructure.hash;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HashSetTest {

    @Test
    void shouldAddValue() {
        HashSet<String> set = new HashSet<>();

        assertThat(set.add("John")).isTrue();

        assertThat(set.contains("John")).isTrue();
    }

    @Test
    void shouldNotAllowDuplicates() {

        HashSet<String> set = new HashSet<>();

        assertThat(set.add("John")).isTrue();

        assertThat(set.add("John")).isFalse();

        assertThat(set.size()).isEqualTo(1);
    }

    @Test
    void shouldRemoveValue() {
        HashSet<String> set = new HashSet<>();

        set.add("John");

        assertThat(set.remove("John")).isTrue();

        assertThat(set.contains("John")).isFalse();
    }
}
