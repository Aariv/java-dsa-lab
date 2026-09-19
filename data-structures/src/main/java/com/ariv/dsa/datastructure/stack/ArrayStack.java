package com.ariv.dsa.datastructure.stack;

import com.ariv.dsa.datastructure.array.DynamicArray;

/**
 * ArrayStack is a stack implementation that uses a dynamic array to store its elements.
 * It provides the standard stack operations: push, pop, peek, isEmpty, and size.
 *
 * @param <T> the type of elements in this stack
 */
public class ArrayStack<T> implements Stack<T> {

    /**
     * The dynamic array that holds the elements of the stack.
     */
    private final DynamicArray<T> elements = new DynamicArray<>();

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param value the element to be pushed onto the stack
     */
    @Override
    public void push(T value) {
        // Add the element to the end of the dynamic array, which represents the top of the stack
        elements.add(value);
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the element that was removed from the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public T pop() {
        // Remove and return the top element of the stack. If the stack is empty, throw an exception.
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        // Remove the last element from the dynamic array, which represents the top of the stack
        return elements.get(size()-1);
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public T peek() {
        // Return the top element of the stack without removing it. If the stack is empty, throw an exception.
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        // Return the last element from the dynamic array, which represents the top of the stack
        return elements.get(size()-1);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Check if the dynamic array is empty, which indicates that the stack is empty
        return elements.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        // Return the number of elements in the dynamic array, which represents the size of the stack
        return elements.size();
    }
}
