package com.ariv.dsa.datastructure.array;

import java.util.Objects;

public class DynamicArray<T> {

    /**
     * The default initial capacity of the array.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The underlying array that stores the elements.
     */
    private Object[] elements; // Why Object[]? - Because Java cannot create: new T[] due to type erasure.

    /**
     * The number of elements in the array.
     */
    private int size;

    /**
     * Constructs a DynamicArray with the default initial capacity.
     */
    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a DynamicArray with the specified initial capacity.
     *
     * @param capacity the initial capacity of the array
     * @throws IllegalArgumentException if the specified capacity is less than or equal to zero
     */
    public DynamicArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than zero");
        }

        elements = new Object[capacity];
    }

    /**
     * Returns the number of elements in this array.
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this array contains no elements.
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current capacity of this array.
     *
     * @return
     */
    public int capacity() {
        return elements.length;
    }

    /**
     * Appends the specified element to the end of this array.
     *
     * @param value
     */
    public void add(T value) {
        ensureCapacity();

        elements[size] = value;

        size++;
    }

    /**
     * Ensures that the array has enough capacity to accommodate new elements.
     * If the current size is equal to the length of the underlying array,
     * the capacity is doubled.
     */
    private void ensureCapacity() {

        if (size < elements.length) {
            return;
        }

        // Double the capacity of the array
        int newCapacity = elements.length * 2;

        Object[] newArray = new Object[newCapacity];

        // Copy the elements from the old array to the new array
        System.arraycopy(
                elements,
                0,
                newArray,
                0,
                size
        );

        elements = newArray;
    }

    /**
     * Returns the element at the specified position in this array.
     *
     * @param index
     * @return
     */
    public T get(int index) {

        validateIndex(index);

        return (T) elements[index];
    }

    /**
     * Replaces the element at the specified position in this array with the specified element.
     *
     * @param index
     * @param value
     */
    public void set(int index, T value) {

        validateIndex(index);

        elements[index] = value;
    }

    /**
     * Returns true if this array contains the specified element.
     *
     * @param value
     * @return
     */
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    /**
     * Removes the element at the specified position in this array.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the array.
     *
     * @param index
     * @return
     */
    public T remove(int index) {

        validateIndex(index);

        T removed = (T) elements[index];

        int elementsToMove = size - index - 1;

        if (elementsToMove > 0) {

            System.arraycopy(
                    elements,
                    index + 1,
                    elements,
                    index,
                    elementsToMove
            );
        }

        size--;

        elements[size] = null;

        return removed;
    }

    /**
     * Removes all of the elements from this array.
     * The array will be empty after this call returns.
     */
    public void clear() {

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    /**
     * Returns a string representation of this array.
     * The string representation consists of a list of the array's elements in the order they are stored,
     * enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
     *
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < size; i++) {

            sb.append(elements[i]);

            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * Inserts the specified element at the specified position in this array.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index
     * @param value
     */
    public void add(int index, T value) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index : " + index
            );
        }

        ensureCapacity();

        int elementsToMove = size - index;

        if (elementsToMove > 0) {
            System.arraycopy(
                    elements,
                    index,
                    elements,
                    index + 1,
                    elementsToMove
            );
        }

        elements[index] = value;
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from this array, if it is present.
     * If the array does not contain the element, it is unchanged.
     *
     * @param value
     * @return true if the array contained the specified element
     */
    public boolean remove(T value) {
        int index = indexOf(value);

        if (index >= 0) {
            remove(index);
            return true;
        }

        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this array,
     * or -1 if this array does not contain the element.
     *
     * @param value
     * @return
     */
    public int indexOf(T value) {
        for(int i = 0; i < size; i++) {
            if(Objects.equals(elements[i], value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this array,
     * or -1 if this array does not contain the element.
     *
     * @param value
     * @return
     */
    public int lastIndexOf(T value) {
        for(int i = size -1; i >= 0; i--) {
            if(Objects.equals(elements[i], value)) {
                return i;
            }
        }
        return -1;
    }

    private void validateIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index : " + index
            );
        }
    }
}