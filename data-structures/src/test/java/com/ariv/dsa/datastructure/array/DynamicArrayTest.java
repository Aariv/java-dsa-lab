package com.ariv.dsa.datastructure.array;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicArrayTest {

    @Test
    void shouldAddElements() {

        DynamicArray<String> array =
                new DynamicArray<>();

        array.add("A");
        array.add("B");

        assertThat(array.size()).isEqualTo(2);
    }

    @Test
    void shouldGrowAutomatically() {

        DynamicArray<Integer> array =
                new DynamicArray<>(2);

        array.add(1);
        array.add(2);
        array.add(3);

        assertThat(array.capacity())
                .isEqualTo(4);
    }

    @Test
    void shouldRemoveElement() {

        DynamicArray<String> array =
                new DynamicArray<>();

        array.add("A");
        array.add("B");
        array.add("C");

        array.remove(1);

        assertThat(array.size())
                .isEqualTo(2);

        assertThat(array.get(1))
                .isEqualTo("C");
    }
}
