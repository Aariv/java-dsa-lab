package com.ariv.dsa.datastructure.heap;

import com.ariv.dsa.datastructure.array.DynamicArray;

import java.util.Comparator;
import java.util.Objects;

/**
 * A generic binary heap implementation that can function as either a min-heap or a max-heap
 * based on the provided comparator.
 *
 * @param <T> the type of elements in the heap
 */
public class BinaryHeap<T> {

    /**
     * The default initial capacity of the heap.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The underlying dynamic array that stores the elements of the heap.
     */
    private final DynamicArray<T> elements;

    /**
     * The comparator used to determine the priority of elements in the heap.
     */
    private final Comparator<? super T> comparator;

    /**
     * Constructs an empty binary heap with the default initial capacity and the specified comparator.
     *
     * @param comparator the comparator used to determine the priority of elements
     */
    public BinaryHeap(
            Comparator<? super T> comparator
    ) {
        this(DEFAULT_CAPACITY, comparator);
    }

    /**
     * Constructs an empty binary heap with the specified initial capacity and comparator.
     *
     * @param initialCapacity the initial capacity of the heap
     * @param comparator      the comparator used to determine the priority of elements
     * @throws IllegalArgumentException if the initial capacity is less than or equal to zero
     * @throws NullPointerException     if the comparator is null
     */
    public BinaryHeap(
            int initialCapacity,
            Comparator<? super T> comparator
    ) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than zero");
        }

        this.comparator = Objects.requireNonNull(comparator, "Comparator must not be null");

        this.elements = new DynamicArray<>(initialCapacity);
    }

    /**
     * Inserts a new value into the heap and maintains the heap property.
     *
     * @param value the value to be inserted
     * @throws NullPointerException if the value is null
     */
    public void insert(T value) {

        Objects.requireNonNull(value, "Heap value must not be null");

        elements.add(value);

        heapifyUp();
    }

    /**
     * Returns the element with the highest priority in the heap without removing it.
     *
     * @return the element with the highest priority
     * @throws IllegalStateException if the heap is empty
     */
    public T peek() {

        ensureNotEmpty();

        return elements.get(0);
    }

    /**
     * Removes and returns the element with the highest priority from the heap, maintaining the heap property.
     *
     * @return the element with the highest priority
     * @throws IllegalStateException if the heap is empty
     */
    public T remove() {

        ensureNotEmpty();

        T root = elements.get(0);
        int lastIndex = size() - 1;

        if (lastIndex == 0) {
            elements.remove(0);
            return root;
        }

        T lastValue = elements.remove(lastIndex);

        elements.set(0, lastValue);

        heapifyDown();

        return root;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the number of elements in the heap
     */
    public int size() {
        return elements.size();
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Removes all elements from the heap.
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Restores the heap property by moving the last inserted element up to its correct position.
     */
    private void heapifyUp() {

        int index = size() - 1;

        while (hasParent(index)
                && hasHigherPriority(
                elements.get(index),
                parent(index)
        )) {

            int parentIndex = parentIndex(index);

            swap(index, parentIndex);

            index = parentIndex;
        }
    }

    /**
     * Restores the heap property by moving the root element down to its correct position.
     */
    private void heapifyDown() {

        int index = 0;

        while (hasLeftChild(index)) {

            int higherPriorityChildIndex =
                    leftChildIndex(index);

            if (hasRightChild(index)
                    && hasHigherPriority(
                    rightChild(index),
                    leftChild(index)
            )) {

                higherPriorityChildIndex =
                        rightChildIndex(index);
            }

            if (!hasHigherPriority(
                    elements.get(higherPriorityChildIndex),
                    elements.get(index)
            )) {
                break;
            }

            swap(index, higherPriorityChildIndex);

            index = higherPriorityChildIndex;
        }
    }

    /**
     * Compares two elements to determine if the first has a higher priority than the second.
     *
     * @param first  the first element
     * @param second the second element
     * @return true if the first element has a higher priority, false otherwise
     */
    private boolean hasHigherPriority(
            T first,
            T second
    ) {
        return comparator.compare(first, second) < 0;
    }

    /**
     * Returns the index of the parent of the given child index.
     *
     * @param childIndex the index of the child
     * @return the index of the parent
     */
    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * Returns the index of the left child of the given parent index.
     *
     * @param parentIndex the index of the parent
     * @return the index of the left child
     */
    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * Returns the index of the right child of the given parent index.
     *
     * @param parentIndex the index of the parent
     * @return the index of the right child
     */
    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * Checks if the element at the given index has a parent.
     *
     * @param index the index to check
     * @return true if the element has a parent, false otherwise
     */
    private boolean hasParent(int index) {
        return index > 0;
    }

    /**
     * Checks if the element at the given index has a left child.
     *
     * @param index the index to check
     * @return true if the element has a left child, false otherwise
     */
    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size();
    }

    /**
     * Checks if the element at the given index has a right child.
     *
     * @param index the index to check
     * @return true if the element has a right child, false otherwise
     */
    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size();
    }

    /**
     * Returns the parent element of the given index.
     *
     * @param index the index of the child
     * @return the parent element
     */
    private T parent(int index) {
        return elements.get(
                parentIndex(index)
        );
    }

    /**
     * Returns the left child element of the given index.
     *
     * @param index the index of the parent
     * @return the left child element
     */
    private T leftChild(int index) {
        return elements.get(
                leftChildIndex(index)
        );
    }

    /**
     * Returns the right child element of the given index.
     *
     * @param index the index of the parent
     * @return the right child element
     */
    private T rightChild(int index) {
        return elements.get(
                rightChildIndex(index)
        );
    }

    /**
     * Swaps the elements at the two specified indices in the heap.
     *
     * @param firstIndex  the index of the first element
     * @param secondIndex the index of the second element
     */
    private void swap(
            int firstIndex,
            int secondIndex
    ) {
        T temporary =
                elements.get(firstIndex);

        elements.set(
                firstIndex,
                elements.get(secondIndex)
        );

        elements.set(
                secondIndex,
                temporary
        );
    }

    /**
     * Ensures that the heap is not empty before performing operations that require at least one element.
     *
     * @throws IllegalStateException if the heap is empty
     */
    private void ensureNotEmpty() {

        if (isEmpty()) {
            throw new IllegalStateException(
                    "Heap is empty"
            );
        }
    }
}
