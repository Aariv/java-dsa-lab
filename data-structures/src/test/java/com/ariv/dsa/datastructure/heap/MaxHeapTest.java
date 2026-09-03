package com.ariv.dsa.datastructure.heap;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MaxHeapTest {

    @Test
    void shouldKeepLargestElementAtRoot() {

        MaxHeap heap = new MaxHeap();

        heap.insert(10);
        heap.insert(40);
        heap.insert(20);

        assertThat(heap.peek())
                .isEqualTo(40);
    }

    @Test
    void shouldRemoveInDescendingOrder() {

        MaxHeap heap = new MaxHeap();

        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);
        heap.insert(20);

        assertThat(heap.remove())
                .isEqualTo(40);

        assertThat(heap.remove())
                .isEqualTo(30);

        assertThat(heap.remove())
                .isEqualTo(20);

        assertThat(heap.remove())
                .isEqualTo(10);

        assertThat(heap.remove())
                .isEqualTo(5);
    }

    @Test
    void shouldHandleDuplicates() {

        MaxHeap heap = new MaxHeap();

        heap.insert(20);
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        assertThat(heap.remove())
                .isEqualTo(30);

        assertThat(heap.remove())
                .isEqualTo(20);

        assertThat(heap.remove())
                .isEqualTo(20);

        assertThat(heap.remove())
                .isEqualTo(10);
    }

    @Test
    void shouldHandleNegativeValues() {

        MaxHeap heap = new MaxHeap();

        heap.insert(-10);
        heap.insert(-20);
        heap.insert(-5);

        assertThat(heap.peek())
                .isEqualTo(-5);

        assertThat(heap.remove())
                .isEqualTo(-5);

        assertThat(heap.remove())
                .isEqualTo(-10);

        assertThat(heap.remove())
                .isEqualTo(-20);
    }

    @Test
    void shouldThrowWhenRemovingEmptyHeap() {

        MaxHeap heap = new MaxHeap();

        try {
            heap.remove();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage())
                    .isEqualTo("Heap is empty");
        }
    }
}
