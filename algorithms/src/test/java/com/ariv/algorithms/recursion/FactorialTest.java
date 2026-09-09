package com.ariv.algorithms.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {

    @Test
    void shouldCalculateFactorialOfZero() {
        assertEquals(
                1,
                Factorial.calculate(0)
        );
    }

    @Test
    void shouldCalculateFactorialOfOne() {

        assertEquals(
                1,
                Factorial.calculate(1)
        );
    }

    @Test
    void shouldCalculateFactorialOfFive() {

        assertEquals(
                120,
                Factorial.calculate(5)
        );
    }

    @Test
    void shouldRejectNegativeValues() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Factorial.calculate(-1)
        );
    }
}
