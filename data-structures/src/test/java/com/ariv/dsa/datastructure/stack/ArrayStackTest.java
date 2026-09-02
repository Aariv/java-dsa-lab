package com.ariv.dsa.datastructure.stack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArrayStackTest {

    @Test
    void shouldPopLastInsertedElement() {
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Integer value =
                stack.pop();

        assertThat(value)
                .isEqualTo(30);
    }

    @Test
    void shouldPush() {

        Stack<Integer> stack = new ArrayStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertThat(stack.size())
                .isEqualTo(3);
    }

    @Test
    void shouldPop() {

        Stack<Integer> stack = new ArrayStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Integer value =
                stack.pop();

        assertThat(value)
                .isEqualTo(30);
    }

    @Test
    void shouldPeek() {
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Integer value =
                stack.peek();

        assertThat(value)
                .isEqualTo(30);
    }

    @Test
    void shouldThrowWhenEmpty() {
        Stack<Integer> stack = new ArrayStack<>();

        try {
            stack.pop();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage())
                    .isEqualTo("Stack is empty");
        }
    }
}
