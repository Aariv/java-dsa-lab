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
    void shouldGrowAutomatically_default() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);
        array.add(60);
        array.add(70);
        array.add(80);
        array.add(90);
        array.add(100);
        // New capacity should be 20 after adding the 11th element
        array.add(110);

        assertThat(array.capacity())
                .isEqualTo(20);
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

    @Test
    void add_at_index() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);
        array.add(60);
        array.add(70);
        array.add(80);
        array.add(90);
        array.add(100);
        // New capacity should be 20 after adding the 11th element
        array.add(110);

        array.add(4, 31);

        assertThat(array.capacity())
                .isEqualTo(20);
    }

    @Test
    public void shouldAddAtBeginning() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);

        array.add(0, 5);

        assertThat(array.get(0))
                .isEqualTo(5);

        assertThat(array.size())
                .isEqualTo(6);
    }

    @Test
    public void shouldAddAtEndUsingIndex() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);

        array.add(array.size(), 60);

        assertThat(array.get(array.size() - 1))
                .isEqualTo(60);

        assertThat(array.size())
                .isEqualTo(6);
    }

    @Test
    public void shouldReturnNegativeOneWhenValueMissing() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);

        assertThat(array.indexOf(100))
                .isEqualTo(-1);
    }

    @Test
    public void shouldFindLastOccurrence() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);
        array.add(30);

        assertThat(array.lastIndexOf(30))
                .isEqualTo(5);
    }

    @Test
    public void shouldRemoveValue() {

        DynamicArray<Integer> array =
                new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);

        array.remove(Integer.valueOf(30));

        assertThat(array.size())
                .isEqualTo(4);

        assertThat(array.contains(30))
                .isFalse();
    }
}
