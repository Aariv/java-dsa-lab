package com.ariv.dsa.datastructure.queue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LinkedListQueueTest {

    @Test
    void shouldDequeueInArrivalOrder() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Integer first =
                queue.dequeue();

        Assertions.assertThat(first)
                .isEqualTo(10);
    }

    @Test
    void shouldEnqueue() {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertThat(queue.size())
                .isEqualTo(3);
    }

    @Test
    void shouldDequeue() {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Integer value =
                queue.dequeue();

        assertThat(value)
                .isEqualTo(10);
    }

    @Test
    void shouldPeek() {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Integer value =
                queue.peek();

        assertThat(value)
                .isEqualTo(10);
    }

    @Test
    void shouldThrowWhenEmpty() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage())
                    .isEqualTo("Queue is empty");
        }
    }
}
