package com.ariv.algorithms.sorting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MergeSort")
class MergeSortTest {

    @Nested
    @DisplayName("sort")
    class SortTests {

        @Test
        @DisplayName("should sort random values")
        void shouldSortRandomValues() {

            Integer[] values = {
                    5,
                    3,
                    8,
                    2,
                    1
            };

            MergeSort.sort(values);

            assertArrayEquals(
                    new Integer[]{
                            1,
                            2,
                            3,
                            5,
                            8
                    },
                    values
            );
        }

        @Test
        @DisplayName("should sort already sorted array")
        void shouldSortAlreadySortedArray() {

            Integer[] values = {
                    1,
                    2,
                    3,
                    4,
                    5
            };

            MergeSort.sort(values);

            assertArrayEquals(
                    new Integer[]{
                            1,
                            2,
                            3,
                            4,
                            5
                    },
                    values
            );
        }

        @Test
        @DisplayName("should sort reverse sorted array")
        void shouldSortReverseSortedArray() {

            Integer[] values = {
                    5,
                    4,
                    3,
                    2,
                    1
            };

            MergeSort.sort(values);

            assertArrayEquals(
                    new Integer[]{
                            1,
                            2,
                            3,
                            4,
                            5
                    },
                    values
            );
        }

        @Test
        @DisplayName("should sort array containing duplicates")
        void shouldSortArrayContainingDuplicates() {

            Integer[] values = {
                    5,
                    2,
                    2,
                    3,
                    1
            };

            MergeSort.sort(values);

            assertArrayEquals(
                    new Integer[]{
                            1,
                            2,
                            2,
                            3,
                            5
                    },
                    values
            );
        }

        @Test
        @DisplayName("should handle single element array")
        void shouldHandleSingleElementArray() {

            Integer[] values = {
                    10
            };

            MergeSort.sort(values);

            assertArrayEquals(
                    new Integer[]{
                            10
                    },
                    values
            );
        }

        @Test
        @DisplayName("should handle empty array")
        void shouldHandleEmptyArray() {

            Integer[] values = {};

            MergeSort.sort(values);

            assertArrayEquals(
                    new Integer[]{},
                    values
            );
        }
    }

    @Nested
    @DisplayName("generic support")
    class GenericSupportTests {

        @Test
        @DisplayName("should sort string values")
        void shouldSortStringValues() {

            String[] values = {
                    "Charlie",
                    "Alice",
                    "Bob"
            };

            MergeSort.sort(values);

            assertArrayEquals(
                    new String[]{
                            "Alice",
                            "Bob",
                            "Charlie"
                    },
                    values
            );
        }

        @Test
        @DisplayName("should sort custom objects")
        void shouldSortCustomObjects() {

            Employee[] employees = {
                    new Employee(3, "Swetha"),
                    new Employee(1, "Kamesh"),
                    new Employee(2, "Sandeep")
            };

            MergeSort.sort(employees);

            assertArrayEquals(
                    new Employee[]{
                            new Employee(1, "Kamesh"),
                            new Employee(2, "Sandeep"),
                            new Employee(3, "Swetha")
                    },
                    employees
            );
        }
    }

    @Nested
    @DisplayName("validation")
    class ValidationTests {

        @Test
        @DisplayName("should reject null array")
        void shouldRejectNullArray() {

            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> MergeSort.sort(null)
                    );

            assertEquals(
                    "Array cannot be null",
                    exception.getMessage()
            );
        }
    }

    private record Employee(
            int id,
            String name
    ) implements Comparable<Employee> {

        @Override
        public int compareTo(Employee other) {
            return Integer.compare(
                    this.id,
                    other.id
            );
        }
    }
}