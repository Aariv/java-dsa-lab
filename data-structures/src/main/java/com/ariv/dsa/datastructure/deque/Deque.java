package com.ariv.dsa.datastructure.deque;

/**
 * A double-ended queue (deque) interface that defines the operations for adding, removing, and peeking elements from both ends of the deque.
 *
 * @param <T> the type of elements held in this deque
 */
public interface Deque<T> {

    void addFirst(T value);

    void addLast(T value);

    T removeFirst();

    T removeLast();

    T peekFirst();

    T peekLast();

    boolean isEmpty();

    int size();
}