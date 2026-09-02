package com.ariv.dsa.datastructure.deque;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LinkedListDequeTest {

    @Test
    void shouldAddAndRemoveFromFront() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        assertThat(deque.removeFirst())
                .isEqualTo(30);

        assertThat(deque.removeFirst())
                .isEqualTo(20);
    }

    @Test
    void shouldAddAndRemoveFromRear() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        assertThat(deque.removeLast())
                .isEqualTo(30);

        assertThat(deque.removeLast())
                .isEqualTo(20);
    }

    @Test
    void shouldSupportOperationsAtBothEnds() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);
        deque.addLast(40);

        assertThat(deque.removeFirst())
                .isEqualTo(30);

        assertThat(deque.removeLast())
                .isEqualTo(40);

        assertThat(deque.peekFirst())
                .isEqualTo(10);

        assertThat(deque.peekLast())
                .isEqualTo(20);
    }
}
