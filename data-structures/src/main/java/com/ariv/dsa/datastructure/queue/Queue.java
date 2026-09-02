package com.ariv.dsa.datastructure.queue;

/**
 * A queue interface that defines the operations for adding, removing, and peeking elements in a first-in-first-out (FIFO) manner.
 *
 * @param <T> the type of elements held in this queue
 */
public interface Queue<T> {

    void enqueue(T value);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();
}