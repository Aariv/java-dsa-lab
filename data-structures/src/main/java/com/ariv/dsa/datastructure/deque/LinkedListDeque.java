package com.ariv.dsa.datastructure.deque;

import com.ariv.dsa.datastructure.linkedlist.DoublyLinkedList;

/**
 * A double-ended queue (deque) implementation using a doubly linked list.
 *
 * @param <T> the type of elements held in this deque
 */
public class LinkedListDeque<T> implements Deque<T> {

    private final DoublyLinkedList<T> list = new DoublyLinkedList<>();

    @Override
    public void addFirst(T value) {
        list.addFirst(value);
    }

    @Override
    public void addLast(T value) {
        list.addLast(value);
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.removeFirst();
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.removeLast();
    }

    @Override
    public T peekFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.getFirst();
    }

    @Override
    public T peekLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return list.getLast();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }
}
