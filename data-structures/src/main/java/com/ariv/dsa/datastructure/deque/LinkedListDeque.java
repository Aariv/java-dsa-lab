package com.ariv.dsa.datastructure.deque;

import com.ariv.dsa.datastructure.linkedlist.DoublyLinkedList;

/**
 * A double-ended queue (deque) implementation using a doubly linked list.
 *
 * @param <T> the type of elements held in this deque
 */
public class LinkedListDeque<T> implements Deque<T> {

    // The doubly linked list that holds the elements of the deque.
    private final DoublyLinkedList<T> list = new DoublyLinkedList<>();

    /**
     * Adds an element to the front of the deque.
     *
     * @param value the element to be added to the front of the deque
     */
    @Override
    public void addFirst(T value) {
        list.addFirst(value);
    }

    /**
     * Adds an element to the end of the deque.
     *
     * @param value the element to be added to the end of the deque
     */
    @Override
    public void addLast(T value) {
        list.addLast(value);
    }

    /**
     * Removes and returns the element at the front of the deque.
     *
     * @return the element that was removed from the front of the deque
     * @throws IllegalStateException if the deque is empty
     */
    @Override
    public T removeFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.removeFirst();
    }

    /**
     * Removes and returns the element at the end of the deque.
     *
     * @return the element that was removed from the end of the deque
     * @throws IllegalStateException if the deque is empty
     */
    @Override
    public T removeLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.removeLast();
    }

    /**
     * Returns the element at the front of the deque without removing it.
     *
     * @return the element at the front of the deque
     * @throws IllegalStateException if the deque is empty
     */
    @Override
    public T peekFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.getFirst();
    }

    /**
     * Returns the element at the end of the deque without removing it.
     *
     * @return the element at the end of the deque
     * @throws IllegalStateException if the deque is empty
     */
    @Override
    public T peekLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.getLast();
    }

    /**
     * Checks if the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of elements in the deque.
     *
     * @return the number of elements in the deque
     */
    @Override
    public int size() {
        return list.size();
    }
}
