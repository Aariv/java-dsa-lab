package com.ariv.dsa.datastructure.stack;

public interface Stack<T> {

    void push(T value);

    T pop();

    T peek();

    boolean isEmpty();

    int size();
}