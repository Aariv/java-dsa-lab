package com.ariv.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchRecursiveTest {

    @Test
    void shouldFindMiddleElement() {
        Integer[] values = {
                10, 20, 30, 40, 50
        };

        int index = BinarySearchRecursive.search(values, 30);

        assert index == 2;
    }

    @Test
    void shouldFindFirstElement() {
        Integer[] values = {
                10, 20, 30
        };

        int index = BinarySearchRecursive.search(values, 10);

        assert index == 0;
    }

    @Test
    void shouldFindLastElement() {
        Integer[] values = {
                10, 20, 30
        };

        int index = BinarySearchRecursive.search(values, 30);

        assert index == 2;
    }

    @Test
    void shouldReturnMinusOneWhenMissing() {
        Integer[] values = {
                10, 20, 30
        };

        int index = BinarySearchRecursive.search(values, 99);

        assert index == -1;
    }

    void shouldHandleEmptyArray() {
        Integer[] values = {};

        int index = BinarySearchRecursive.search(values, 10);

        assertEquals(-1, index);
    }

    void shouldHandleSingleElementFound() {
        Integer[] values = {10};

        int index = BinarySearchRecursive.search(values, 10);

        assertEquals(0, index);
    }

    @Test
    void shouldHandleSingleElementMissing() {
        Integer[] values = {10};

        int index = BinarySearchRecursive.search(values, 20);

        assertEquals(-1, index);
    }

    @Test
    void shouldWorkWithStrings() {
        String[] values = {
                "apple", "banana", "cherry"
        };

        int index = BinarySearchRecursive.search(values, "banana");

        assertEquals(1, index);
    }

    @Test
    void shouldWorkWithCustomObjects() {
        Person[] people = {
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        };

        int index = BinarySearchRecursive.search(people, new Person("Bob", 25));

        assertEquals(1, index);
    }

    @Test
    void shouldFindEveryElement() {

        Integer[] values = {
                10,20,30,40,50,60,70
        };

        for (int index = 0;
             index < values.length;
             index++) {

            assertEquals(
                    index,
                    BinarySearchRecursive.search(
                            values,
                            values[index]
                    )
            );
        }
    }

    record Person(String name, int age) implements Comparable<Person> {
        @Override
        public int compareTo(Person other) {
            return this.name.compareTo(other.name);
        }
    }
}
