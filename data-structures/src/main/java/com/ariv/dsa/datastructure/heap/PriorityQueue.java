package com.ariv.dsa.datastructure.heap;

public interface PriorityQueue<T> {

    void enqueue(T value);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();

    void clear();
}