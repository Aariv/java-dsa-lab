package com.ariv.dsa.datastructure.queue;

import com.ariv.dsa.datastructure.linkedlist.SinglyLinkedList;

/**
 * A queue implementation using a singly linked list.
 *
 * @param <T> the type of elements held in this queue
 */
public class LinkedListQueue<T> implements Queue<T> {

    // The singly linked list that holds the elements of the queue.
    private final SinglyLinkedList<T> list = new SinglyLinkedList<>();

    /**
     * Adds an element to the end of the queue.
     *
     * @param value the element to be added to the queue
     */
    @Override
    public void enqueue(T value) {
        list.addLast(value);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element that was removed from the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T dequeue() {
        if(list.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return list.removeFirst();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T peek() {
        // Check if the queue is empty before attempting to peek
        if(list.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        // Return the first element of the linked list, which represents the front of the queue
        return list.getFirst();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return list.size();
    }
}
