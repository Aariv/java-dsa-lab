package com.ariv.dsa.datastructure.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HeapPriorityQueueTest {

    @Test
    void shouldProcessInPriorityOrder() {

        PriorityQueue<Integer> queue =
                new HeapPriorityQueue<>(
                        Comparator.naturalOrder()
                );

        queue.enqueue(30);
        queue.enqueue(10);
        queue.enqueue(20);

        assertThat(queue.dequeue())
                .isEqualTo(10);

        assertThat(queue.dequeue())
                .isEqualTo(20);

        assertThat(queue.dequeue())
                .isEqualTo(30);
    }

    @Test
    void shouldProcessHighestPriorityTaskFirst() {

        PriorityQueue<Task> queue =
                new HeapPriorityQueue<>(
                        Comparator.comparingInt(Task::priority)
                );

        queue.enqueue(new Task("Task 1", 3));
        queue.enqueue(new Task("Task 2", 1));
        queue.enqueue(new Task("Task 3", 2));

        assertThat(queue.dequeue().name())
                .isEqualTo("Task 2");

        assertThat(queue.dequeue().name())
                .isEqualTo("Task 3");

        assertThat(queue.dequeue().name())
                .isEqualTo("Task 1");
    }

    @Test
    void shouldPeekWithoutRemoving() {

        PriorityQueue<Integer> queue =
                new HeapPriorityQueue<>(
                        Comparator.naturalOrder()
                );

        queue.enqueue(20);
        queue.enqueue(10);

        assertThat(queue.peek())
                .isEqualTo(10);

        assertThat(queue.peek())
                .isEqualTo(10);

        assertThat(queue.dequeue())
                .isEqualTo(10);
    }

    @Test
    void shouldThrowWhenEmpty() {

        PriorityQueue<Integer> queue =
                new HeapPriorityQueue<>(
                        Comparator.naturalOrder()
                );

        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage())
                    .isEqualTo("Heap is empty");
        }
    }

    private record Task(
            String name,
            int priority
    ) {
    }
}
