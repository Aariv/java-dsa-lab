package com.ariv.dsa.datastructure.heap;

import com.ariv.dsa.datastructure.array.DynamicArray;

/**
 * MinHeap is a data structure that maintains the minimum element at the root.
 * It supports insertion, removal of the minimum element, and peeking at the minimum element.
 */
public class MinHeap {

    /**
     * The underlying dynamic array that stores the elements of the heap.
     */
    private final DynamicArray<Integer> elements;

    /**
     * Constructs an empty MinHeap with default initial capacity.
     */
    public MinHeap() {
        this.elements = new DynamicArray<>();
    }

    /**
     * Constructs an empty MinHeap with the specified initial capacity.
     *
     * @param capacity the initial capacity of the heap
     */
    public MinHeap(int capacity) {
        this.elements = new DynamicArray<>(capacity);
    }

    /**
     * Inserts a new value into the heap and maintains the heap property.
     *
     * @param value the value to be inserted
     */
    public void insert(int value) {
        elements.add(value);

        heapifyUp();
    }

    /**
     * Restores the heap property by moving the last element up to its correct position.
     */
    private void heapifyUp() {
        int index = size() - 1;
        while (hasParent(index) && parent(index) > elements.get(index)) {
            swap(parentIndex(index), index);
            index = parentIndex(index);
        }
    }

    /**
     * Returns the minimum element in the heap without removing it.
     *
     * @return the minimum element
     * @throws IllegalStateException if the heap is empty
     */
    public int peek() {
        if(isEmpty()){
            throw new IllegalStateException("Heap is empty");
        }
        return elements.get(0);
    }

    /**
     * Removes and returns the minimum element from the heap, maintaining the heap property.
     *
     * @return the minimum element
     * @throws IllegalStateException if the heap is empty
     */
    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int minimum = elements.get(0);
        int lastIndex = size() - 1;
        int lastValue = elements.get(lastIndex);
        if(!isEmpty()) {
            elements.set(0, lastValue);
            elements.remove(lastIndex);
            heapifyDown();
        } else {
            elements.remove(lastIndex);
        }
        return minimum;
    }

    /**
     * Restores the heap property by moving the root element down to its correct position.
     */
    private void heapifyDown() {
        int index = 0;
        while(hasLeftChild(index)){
            int smallerChildIndex = leftChildIndex(index);
            if(hasRightChild(index) && rightChild(index) < leftChild(index)){
                smallerChildIndex = rightChildIndex(index);
            }

            if(elements.get(index) <= elements.get(smallerChildIndex)){
                break;
            }
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the size of the heap
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
     * Clears all elements from the heap.
     */
    public void clear() {
        elements.clear();
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
        return parentIndex(index) >= 0;
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
     * Returns the value of the parent of the element at the given index.
     *
     * @param index the index of the child
     * @return the value of the parent
     */
    private int parent(int index) {
        return elements.get(parentIndex(index));
    }

    /**
     * Returns the value of the left child of the element at the given index.
     *
     * @param index the index of the parent
     * @return the value of the left child
     */
    private int leftChild(int index) {
        return elements.get(leftChildIndex(index));
    }

    /**
     * Returns the value of the right child of the element at the given index.
     *
     * @param index the index of the parent
     * @return the value of the right child
     */
    private int rightChild(int index) {
        return elements.get(rightChildIndex(index));
    }

    /**
     * Swaps the elements at the two specified indices in the heap.
     *
     * @param indexOne the index of the first element
     * @param indexTwo the index of the second element
     */
    private void swap(int indexOne, int indexTwo) {
        int temp = elements.get(indexOne);
        elements.set(indexOne, elements.get(indexTwo));
        elements.set(indexTwo, temp);
    }
}
