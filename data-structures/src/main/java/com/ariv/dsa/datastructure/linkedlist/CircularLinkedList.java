package com.ariv.dsa.datastructure.linkedlist;

import java.util.Objects;

/**
 * A generic implementation of a circular linked list.
 *
 * @param <T> the type of elements in this list
 */
public class CircularLinkedList<T> {

    /**
     * The first node in the list.
     */
    private Node<T> head;

    /**
     * The last node in the list.
     */
    private Node<T> tail;

    /**
     * The number of elements in the list.
     */
    private int size;

    /**
     * Constructs an empty circular linked list.
     */
    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param data the element to be added
     */
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Point tail to head to make it circular
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Point tail to head to maintain circularity
        }
        size++;
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param data the element to be added
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Point tail to head to make it circular
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head; // Point tail to head to maintain circularity
        }
        size++;
    }

    /**
     * Checks if the list contains a specific element.
     *
     * @param data the element to check for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(T data) {
        Node<T> current = head;
        if (current == null) {
            return false;
        }
        do {
            if (Objects.equals(current.data, data)) {
                return true;
            }
            current = current.next;
        } while (current != head);
        return false;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element in the list
     * @throws IllegalStateException if the list is empty
     */
    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    /**
     * Returns the last element in the list.
     *
     * @return the last element in the list
     * @throws IllegalStateException if the list is empty
     */
    public T getLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element
     * @throws IllegalStateException if the list is empty
     */
    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        T data = head.data;
        if (head == tail) { // Only one element
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head; // Point tail to new head to maintain circularity
        }
        size--;
        return data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the removed element
     * @throws IllegalStateException if the list is empty
     */
    public T removeLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        T data = tail.data;
        if (head == tail) { // Only one element
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            tail.next = head; // Point tail to head to maintain circularity
        }
        size--;
        return data;
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
            if (current != head) {
                sb.append(" -> ");
            } else {
                break;
            }
        }
        return sb.toString();
    }

    /**
     * Adds an element at a specific index in the list.
     *
     * @param index the index at which the element should be added
     * @param data  the element to be added
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> current = head;
        do {
            if (index == 1) {
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
            index--;
        } while (current != head);

    }

    /**
     * Returns the element at a specific index in the list.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        if (index == 0) {
            return removeFirst();
        }
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        T data = current.next.data;
        if (current.next == tail) {
            tail = current;
        }
        current.next = current.next.next;
        size--;
        return data;
    }

    /**
     * Reverses the order of elements in the list.
     */
    public void reverse() {
        if (head == null || head.next == head) {
            return; // Empty list or single element, no need to reverse
        }
        Node<T> prev = tail;
        Node<T> current = head;
        Node<T> next;
        do {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } while (current != head);
        tail = head;
        head = prev;
    }
}
