package com.ariv.dsa.datastructure.stack;

import com.ariv.dsa.datastructure.array.DynamicArray;

public class ArrayStack<T> implements Stack<T> {

    private final DynamicArray<T> elements = new DynamicArray<>();

    @Override
    public void push(T value) {
        elements.add(value);
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return elements.get(size()-1);
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return elements.get(size()-1);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }
}
