package com.ariv.dsa.datastructure.array;

import java.util.Objects;

/**
 * A generic implementation of a dynamic array.
 *
 * @param <T> the type of elements in this array
 */
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
            throw new IllegalArgumentException("Capacity must be greater than zero");
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
        // If the current size is less than the length of the underlying array, no resizing is needed
        if (size < elements.length) {
            return;
        }

        // Double the capacity of the array
        int newCapacity = elements.length * 2;

        // Create a new array with the new capacity
        Object[] newArray = new Object[newCapacity];

        // Copy the elements from the old array to the new array
        System.arraycopy(elements, 0, newArray, 0, size);

        // Update the reference to the new array
        elements = newArray;
    }

    /**
     * Returns the element at the specified position in this array.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this array
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T get(int index) {

        // Validate the index to ensure it is within the bounds of the array
        validateIndex(index);

        // Return the element at the specified index, casting it to the generic type T
        return (T) elements[index];
    }

    /**
     * Replaces the element at the specified position in this array with the specified element.
     *
     * @param index the index of the element to replace
     * @param value the element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public void set(int index, T value) {

        // Validate the index to ensure it is within the bounds of the array
        validateIndex(index);

        // Replace the element at the specified index with the new value
        elements[index] = value;
    }

    /**
     * Returns true if this array contains the specified element.
     *
     * @param value the element whose presence in this array is to be tested
     * @return true if this array contains the specified element, false otherwise
     */
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    /**
     * Removes the element at the specified position in this array.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the array.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    public T remove(int index) {

        // Validate the index to ensure it is within the bounds of the array
        validateIndex(index);

        // Store the element to be removed for returning later
        T removed = (T) elements[index];

        // Calculate the number of elements to move after the removed element
        int elementsToMove = size - index - 1;

        // If there are elements to move, shift them to the left to fill the gap left by the removed element
        if (elementsToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsToMove);
        }
        // Decrease the size of the array to reflect the removal of the element
        size--;
        // Set the last element to null to avoid memory leaks
        elements[size] = null;
        // Return the removed element
        return removed;
    }

    /**
     * Removes all the elements from this array.
     * The array will be empty after this call returns.
     */
    public void clear() {
        // Set all elements to null to avoid memory leaks
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        // Reset the size to 0 to indicate that the array is now empty
        size = 0;
    }

    /**
     * Returns a string representation of this array.
     * The string representation consists of a list of the array's elements in the order they are stored,
     * enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
     *
     * @return a string representation of this array
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
     * @param index the index at which the specified element is to be inserted
     * @param value the element to be inserted
     */
    public void add(int index, T value) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index : " + index
            );
        }
        // Ensure that the array has enough capacity to accommodate the new element
        ensureCapacity();
        // Calculate the number of elements to move after the specified index
        int elementsToMove = size - index;
        // If there are elements to move, shift them to the right to make space for the new element
        if (elementsToMove > 0) {
            // Use System.arraycopy to efficiently move the elements in the underlying array
            System.arraycopy(elements, index, elements, index + 1, elementsToMove);
        }
        // Insert the new element at the specified index
        elements[index] = value;
        // Increment the size of the array to reflect the addition of the new element
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from this array, if it is present.
     * If the array does not contain the element, it is unchanged.
     *
     * @param value the element to be removed from this array, if present
     * @return true if the array contained the specified element
     */
    public boolean remove(T value) {
        // Find the index of the first occurrence of the specified element in the array
        int index = indexOf(value);
        // If the element is found (index >= 0), remove it from the array and return true
        if (index >= 0) {
            // Call the remove method to remove the element at the found index
            remove(index);
            return true;
        }
        // If the element is not found (index < 0), return false to indicate that the array was unchanged
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this array,
     * or -1 if this array does not contain the element.
     *
     * @param value the element to search for in this array
     * @return the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element
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
     * @param value the element to search for in this array
     * @return the index of the last occurrence of the specified element in this array, or -1 if this array does not contain the element
     */
    public int lastIndexOf(T value) {
        // Iterate through the array in reverse order to find the last occurrence of the specified element
        for(int i = size -1; i >= 0; i--) {
            // Use Objects.equals to compare the elements, which handles null values correctly
            if(Objects.equals(elements[i], value)) {
                // If a match is found, return the index of the last occurrence
                return i;
            }
        }
        return -1;
    }

    /**
     * Validates the specified index to ensure it is within the bounds of the array.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    private void validateIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index : " + index
            );
        }
    }
}