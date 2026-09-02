package com.ariv.dsa.datastructure.queue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CircularQueueTest {

    @Test
    void shouldMaintainFifoOrder() {
        CircularQueue<Integer> queue = new CircularQueue<>(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertThat(queue.dequeue())
                .isEqualTo(10);

        assertThat(queue.dequeue())
                .isEqualTo(20);
    }

    @Test
    void shouldWrapAround() {

        CircularQueue<Integer> queue = new CircularQueue<>(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        queue.dequeue();
        queue.dequeue();

        queue.enqueue(60);
        queue.enqueue(70);

        assertThat(queue.size())
                .isEqualTo(5);

        assertThat(queue.dequeue())
                .isEqualTo(30);
    }

    @Test
    void shouldThrowWhenFull() {

        CircularQueue<Integer> queue = new CircularQueue<>(3);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Assertions.assertThatThrownBy(() -> queue.enqueue(40))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Queue is full");
    }

    @Test
    void shouldThrowWhenEmpty() {

        CircularQueue<Integer> queue = new CircularQueue<>(3);

        Assertions.assertThatThrownBy(queue::dequeue)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Queue is empty");
    }
}
