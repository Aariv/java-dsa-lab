package com.ariv.dsa.datastructure.queue;

public interface Queue<T> {

    void enqueue(T value);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();
}