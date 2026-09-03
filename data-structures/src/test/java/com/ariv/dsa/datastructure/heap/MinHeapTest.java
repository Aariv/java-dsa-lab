package com.ariv.dsa.datastructure.heap;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinHeapTest {

    @Test
    void shouldInsertSingleValue() {

        MinHeap heap = new MinHeap();

        heap.insert(10);

        assertThat(heap.size()).isEqualTo(1);
        assertThat(heap.peek()).isEqualTo(10);
    }

    @Test
    void shouldPlaceSmallestValueAtRoot() {

        MinHeap heap = new MinHeap();

        heap.insert(30);
        heap.insert(20);
        heap.insert(10);

        assertThat(heap.peek()).isEqualTo(10);
        assertThat(heap.size()).isEqualTo(3);
    }

    @Test
    void shouldMaintainMinimumAfterMultipleInsertions() {

        MinHeap heap = new MinHeap();

        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);
        heap.insert(20);

        assertThat(heap.peek()).isEqualTo(5);
    }

    @Test
    void shouldNotRemoveValueWhenPeeking() {

        MinHeap heap = new MinHeap();

        heap.insert(20);
        heap.insert(10);

        assertThat(heap.peek()).isEqualTo(10);
        assertThat(heap.peek()).isEqualTo(10);
        assertThat(heap.size()).isEqualTo(2);
    }

    @Test
    void shouldReportEmptyHeap() {

        MinHeap heap = new MinHeap();

        assertThat(heap.isEmpty()).isTrue();
        assertThat(heap.size()).isZero();
    }

    @Test
    void shouldThrowWhenPeekingEmptyHeap() {

        MinHeap heap = new MinHeap();

        assertThatThrownBy(heap::peek)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Heap is empty");
    }

    @Test
    void removeMinValueAndMaintainHeapProperty() {

        MinHeap heap = new MinHeap();

        heap.insert(30);
        heap.insert(10);
        heap.insert(20);

        int minValue = heap.remove();

        assertThat(minValue).isEqualTo(10);
        assertThat(heap.peek()).isEqualTo(20);
        assertThat(heap.size()).isEqualTo(2);
    }

    @Test
    void repeatedRemovalReturnsAscendingOrder() {

        MinHeap heap = new MinHeap();

        heap.insert(30);
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);

        assertThat(heap.remove()).isEqualTo(5);
        assertThat(heap.remove()).isEqualTo(10);
        assertThat(heap.remove()).isEqualTo(20);
        assertThat(heap.remove()).isEqualTo(30);
    }

    @Test
    void singleElementHeap() {
        MinHeap heap = new MinHeap();
        heap.insert(42);
        assertThat(heap.peek()).isEqualTo(42);
        assertThat(heap.remove()).isEqualTo(42);
        assertThat(heap.isEmpty()).isTrue();
    }

    @Test
    void duplicateElements() {
        MinHeap heap = new MinHeap();
        heap.insert(15);
        heap.insert(15);
        assertThat(heap.peek()).isEqualTo(15);
        assertThat(heap.remove()).isEqualTo(15);
        assertThat(heap.peek()).isEqualTo(15);
    }

    @Test
    void removeFromEmptyHeap() {
        MinHeap heap = new MinHeap();
        assertThatThrownBy(heap::remove)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Heap is empty");
    }
}