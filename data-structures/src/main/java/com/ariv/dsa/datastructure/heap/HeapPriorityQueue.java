package com.ariv.dsa.datastructure.heap;

import java.util.Comparator;

/**
 * HeapPriorityQueue is a priority queue implementation that uses a binary heap as the underlying data structure.
 * It supports enqueueing, dequeueing, peeking at the highest priority element, and checking if the queue is empty.
 *
 * @param <T> the type of elements held in this priority queue
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * The underlying binary heap that stores the elements of the priority queue.
     */
    private final BinaryHeap<T> heap;

    /**
     * Constructs an empty HeapPriorityQueue with the specified comparator for ordering elements.
     *
     * @param comparator the comparator used to order the elements in the priority queue
     */
    public HeapPriorityQueue(Comparator<? super T> comparator) {
        this.heap = new BinaryHeap<>(comparator);
    }

    /**
     * Constructs an empty HeapPriorityQueue with the specified initial capacity and comparator for ordering elements.
     *
     * @param capacity   the initial capacity of the priority queue
     * @param comparator the comparator used to order the elements in the priority queue
     */
    public HeapPriorityQueue(
            int capacity,
            Comparator<? super T> comparator) {
        this.heap = new BinaryHeap<>(capacity, comparator);
    }

    /**
     * Constructs an empty HeapPriorityQueue that orders its elements according to their natural ordering.
     * The elements must implement the Comparable interface.
     */
    @Override
    public void enqueue(T value) {
        heap.insert(value);
    }

    /**
     * Removes and returns the highest priority element from the priority queue.
     *
     * @return the highest priority element
     * @throws IllegalStateException if the priority queue is empty
     */
    @Override
    public T dequeue() {
        return heap.remove();
    }

    /**
     * Returns the highest priority element without removing it from the priority queue.
     *
     * @return the highest priority element
     * @throws IllegalStateException if the priority queue is empty
     */
    @Override
    public T peek() {
        return heap.peek();
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return the number of elements in the priority queue
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Removes all elements from the priority queue.
     */
    @Override
    public void clear() {
        heap.clear();
    }
}