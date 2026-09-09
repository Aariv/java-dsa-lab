package com.ariv.algorithms.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void shouldCalculateZero() {

        assertEquals(
                0,
                Fibonacci.calculate(0)
        );
    }

    @Test
    void shouldCalculateOne() {

        assertEquals(
                1,
                Fibonacci.calculate(1)
        );
    }

    @Test
    void shouldCalculateTwo() {

        assertEquals(
                1,
                Fibonacci.calculate(2)
        );
    }

    @Test
    void shouldCalculateFive() {

        assertEquals(
                5,
                Fibonacci.calculate(5)
        );
    }

    @Test
    void shouldCalculateTen() {

        assertEquals(
                55,
                Fibonacci.calculate(10)
        );
    }

    @Test
    void shouldRejectNegativeNumbers() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Fibonacci.calculate(-1)
        );
    }
}
