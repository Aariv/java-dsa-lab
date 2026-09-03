package com.ariv.dsa.datastructure.heap;

import com.ariv.dsa.datastructure.array.DynamicArray;

public class MinHeap {

    private final DynamicArray<Integer> elements;

    public MinHeap() {
        this.elements = new DynamicArray<>();
    }

    public MinHeap(int capacity) {
        this.elements = new DynamicArray<>(capacity);
    }

    public void insert(int value) {
        elements.add(value);

        heapifyUp();
    }

    private void heapifyUp() {
        int index = size() - 1;
        while (hasParent(index) && parent(index) > elements.get(index)) {
            swap(parentIndex(index), index);
            index = parentIndex(index);
        }
    }

    public int peek() {
        if(isEmpty()){
            throw new IllegalStateException("Heap is empty");
        }
        return elements.get(0);
    }

    public int remove() {
        return 0;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void clear() {
        elements.clear();
    }

    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private boolean hasParent(int index) {
        return parentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size();
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size();
    }

    private int parent(int index) {
        return elements.get(parentIndex(index));
    }

    private int leftChild(int index) {
        return elements.get(leftChildIndex(index));
    }

    private int rightChild(int index) {
        return elements.get(rightChildIndex(index));
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = elements.get(indexOne);
        elements.set(indexOne, elements.get(indexTwo));
        elements.set(indexTwo, temp);
    }
}
