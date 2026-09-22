package com.ariv.problemsolving.patterns.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductOfArrayExceptSelfTest {

    @Test
    void shouldCalculateProducts() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, ProductOfArrayExceptSelf.calculate(new int[]{1, 2, 3, 4}));
    }

    @Test
    void shouldHandleZero() {
        assertArrayEquals(new int[]{24, 0, 0, 0}, ProductOfArrayExceptSelf.calculate(new int[]{0, 2, 3, 4}));
    }
}
