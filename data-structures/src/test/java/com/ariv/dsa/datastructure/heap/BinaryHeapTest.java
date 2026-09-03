package com.ariv.dsa.datastructure.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinaryHeapTest {

    @Test
    void shouldBehaveAsMinHeapUsingNaturalOrder() {

        BinaryHeap<Integer> heap =
                new BinaryHeap<>(
                        Comparator.naturalOrder()
                );

        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);
        heap.insert(20);

        assertThat(heap.remove()).isEqualTo(5);
        assertThat(heap.remove()).isEqualTo(10);
        assertThat(heap.remove()).isEqualTo(20);
        assertThat(heap.remove()).isEqualTo(30);
        assertThat(heap.remove()).isEqualTo(40);
    }

    @Test
    void shouldBehaveAsMaxHeapUsingReverseOrder() {

        BinaryHeap<Integer> heap =
                new BinaryHeap<>(
                        Comparator.reverseOrder()
                );

        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);
        heap.insert(20);

        assertThat(heap.remove()).isEqualTo(40);
        assertThat(heap.remove()).isEqualTo(30);
        assertThat(heap.remove()).isEqualTo(20);
        assertThat(heap.remove()).isEqualTo(10);
        assertThat(heap.remove()).isEqualTo(5);
    }

    @Test
    void shouldHandleDuplicates() {

        BinaryHeap<Integer> heap =
                new BinaryHeap<>(
                        Comparator.naturalOrder()
                );

        heap.insert(10);
        heap.insert(5);
        heap.insert(10);
        heap.insert(5);

        assertThat(heap.remove()).isEqualTo(5);
        assertThat(heap.remove()).isEqualTo(5);
        assertThat(heap.remove()).isEqualTo(10);
        assertThat(heap.remove()).isEqualTo(10);
    }

    @Test
    void shouldThrowWhenPeekingEmptyHeap() {

        BinaryHeap<Integer> heap =
                new BinaryHeap<>(
                        Comparator.naturalOrder()
                );

        assertThatThrownBy(heap::peek)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Heap is empty");
    }

    @Test
    void shouldThrowWhenRemovingFromEmptyHeap() {

        BinaryHeap<Integer> heap =
                new BinaryHeap<>(
                        Comparator.naturalOrder()
                );

        assertThatThrownBy(heap::remove)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Heap is empty");
    }

    @Test
    void shouldRejectNullComparator() {

        assertThatThrownBy(
                () -> new BinaryHeap<Integer>(null)
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Comparator must not be null");
    }

    @Test
    void shouldRejectNullValue() {

        BinaryHeap<Integer> heap =
                new BinaryHeap<>(
                        Comparator.naturalOrder()
                );

        assertThatThrownBy(
                () -> heap.insert(null)
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Heap value must not be null");
    }

    @Test
    void shouldOrderCustomObjectsUsingComparator() {

        BinaryHeap<Task> heap =
                new BinaryHeap<>(
                        Comparator.comparingInt(
                                Task::priority
                        )
                );

        heap.insert(
                new Task("Generate report", 3)
        );

        heap.insert(
                new Task("Production incident", 1)
        );

        heap.insert(
                new Task("Update documentation", 5)
        );

        assertThat(heap.remove().name())
                .isEqualTo("Production incident");

        assertThat(heap.remove().name())
                .isEqualTo("Generate report");

        assertThat(heap.remove().name())
                .isEqualTo("Update documentation");
    }


    private record Task(
            String name,
            int priority
    ) {
    }
}