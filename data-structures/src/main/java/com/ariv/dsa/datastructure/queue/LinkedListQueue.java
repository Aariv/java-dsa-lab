package com.ariv.dsa.datastructure.queue;

import com.ariv.dsa.datastructure.linkedlist.SinglyLinkedList;

/**
 * A queue implementation using a singly linked list.
 *
 * @param <T> the type of elements held in this queue
 */
public class LinkedListQueue<T> implements Queue<T> {

    private final SinglyLinkedList<T> list = new SinglyLinkedList<>();

    @Override
    public void enqueue(T value) {
        list.addLast(value);
    }

    @Override
    public T dequeue() {
        if(list.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return list.removeFirst();
    }

    @Override
    public T peek() {
        if(list.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
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
