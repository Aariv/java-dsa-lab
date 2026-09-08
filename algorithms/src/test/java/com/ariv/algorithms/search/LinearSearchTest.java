package com.ariv.algorithms.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinearSearch")
class LinearSearchTest {

    @Nested
    @DisplayName("search")
    class SearchTests {

        @Test
        @DisplayName("should find value in middle")
        void shouldFindValueInMiddle() {

            Integer[] values = {
                    10, 20, 30, 40, 50
            };

            int index = LinearSearch.search(values,30);

            assertEquals(2, index);
        }

        @Test
        @DisplayName("should find first element")
        void shouldFindFirstElement() {

            Integer[] values = {
                    10, 20, 30
            };

            int index = LinearSearch.search(values,10);

            assertEquals(0, index);
        }

        @Test
        @DisplayName("should find last element")
        void shouldFindLastElement() {
            Integer[] values = {10, 20, 30};
            int index = LinearSearch.search(values,30);
            assertEquals(2, index);
        }

        @Test
        @DisplayName("should return minus one when value is missing")
        void shouldReturnMinusOneWhenMissing() {

            Integer[] values = {
                    10, 20, 30
            };

            int index = LinearSearch.search(values,99);

            assertEquals(-1, index);
        }

        @Test
        @DisplayName("should work with empty array")
        void shouldWorkWithEmptyArray() {

            Integer[] values = {};

            int index =
                    LinearSearch.search(
                            values,
                            10
                    );

            assertEquals(-1, index);
        }

        @Test
        @DisplayName("should work with single element array")
        void shouldWorkWithSingleElementArray() {

            Integer[] values = {10};

            int index =
                    LinearSearch.search(
                            values,
                            10
                    );

            assertEquals(0, index);
        }

        @Test
        @DisplayName("should return minus one for missing single element")
        void shouldReturnMinusOneForMissingSingleElement() {

            Integer[] values = {10};

            int index =
                    LinearSearch.search(
                            values,
                            20
                    );

            assertEquals(-1, index);
        }

        @Test
        @DisplayName("should return first occurrence of duplicate value")
        void shouldReturnFirstOccurrenceOfDuplicateValue() {

            Integer[] values = {
                    10,
                    20,
                    20,
                    20,
                    30
            };

            int index =
                    LinearSearch.search(
                            values,
                            20
                    );

            assertEquals(1, index);
        }
    }

    @Nested
    @DisplayName("generic support")
    class GenericSupportTests {

        @Test
        @DisplayName("should work with strings")
        void shouldWorkWithStrings() {

            String[] values = {
                    "A",
                    "B",
                    "C"
            };

            int index =
                    LinearSearch.search(
                            values,
                            "B"
                    );

            assertEquals(1, index);
        }

        @Test
        @DisplayName("should work with custom objects")
        void shouldWorkWithCustomObjects() {

            Employee[] employees = {
                    new Employee(1, "John"),
                    new Employee(2, "Raja"),
                    new Employee(3, "Swetha")
            };

            int index =
                    LinearSearch.search(
                            employees,
                            new Employee(
                                    2,
                                    "Raja"
                            )
                    );

            assertEquals(1, index);
        }
    }

    @Nested
    @DisplayName("null handling")
    class NullHandlingTests {

        @Test
        @DisplayName("should throw exception for null array")
        void shouldThrowExceptionForNullArray() {

            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> LinearSearch.search(
                                    null,
                                    10
                            )
                    );

            assertEquals(
                    "Array cannot be null",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("should find null value")
        void shouldFindNullValue() {

            String[] values = {
                    "A",
                    null,
                    "C"
            };

            int index =
                    LinearSearch.search(
                            values,
                            null
                    );

            assertEquals(1, index);
        }

        @Test
        @DisplayName("should return minus one when null not found")
        void shouldReturnMinusOneWhenNullNotFound() {

            String[] values = {
                    "A",
                    "B",
                    "C"
            };

            int index =
                    LinearSearch.search(
                            values,
                            null
                    );

            assertEquals(-1, index);
        }
    }

    /*
     * Test Fixture
     */

    private record Employee(
            int id,
            String name
    ) {
    }
}