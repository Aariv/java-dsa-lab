package com.ariv.dsa.datastructure.queue;

/**
 * A circular queue implementation using an array.
 *
 * @param <T> the type of elements held in this queue
 */
public class CircularQueue<T> {

    /**
     * The array that holds the elements of the queue.
     */
    private final Object[] elements;

    /**
     * The index of the front element in the queue.
     */
    private int front;

    /**
     * The index of the next available position in the queue.
     */
    private int rear;

    /**
     * The current number of elements in the queue.
     */
    private int size;

    /**
     * Constructs a new CircularQueue with the specified capacity.
     *
     * @param capacity the maximum number of elements that the queue can hold
     * @throws IllegalArgumentException if the specified capacity is less than or equal to zero
     */
    public CircularQueue(int capacity) {

        if(capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive"
            );
        }

        elements = new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return size == elements.length;
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param value the element to be added
     * @throws IllegalStateException if the queue is full
     */
    public void enqueue(T value) {
        if(isFull()) {

            throw new IllegalStateException(
                    "Queue is full"
            );
        }
        elements[rear] = value;
        rear = (rear + 1) % elements.length;
        size++;
    }

    /**
     * Returns the front element of the queue without removing it.
     *
     * @return the front element of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T peek() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (T) elements[front];
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * @return the front element of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T value =(T) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return value;
    }

    /**
     * Returns the current number of elements in the queue.
     *
     * @return the current number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the maximum number of elements that the queue can hold.
     *
     * @return the maximum number of elements that the queue can hold
     */
    public int capacity() {
        return elements.length;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < size; i++) {
            int index = (front + i) % elements.length;
            sb.append(elements[index]);
            if(i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}