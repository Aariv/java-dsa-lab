package com.ariv.dsa.datastructure.linkedlist;

import java.util.Objects;

/**
 * A generic implementation of a doubly linked list.
 *
 * @param <T> the type of elements in this list
 */
public class DoublyLinkedList<T> {

    /**
     * The first node in the list.
     */
    private DoublyNode<T> head;
    /**
     * The last node in the list.
     */
    private DoublyNode<T> tail;
    /**
     * The number of elements in the list.
     */
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param data the element to be added
     */
    public void addFirst(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param data the element to be added
     */
    public void addLast(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns the first element of the list without removing it.
     *
     * @return the first element
     * @throws IllegalStateException if the list is empty
     */
    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    /**
     * Returns the last element of the list without removing it.
     *
     * @return the last element
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
        DoublyNode<T> curr = head;
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        if (head == tail) { // Only one element
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return curr.data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the removed element
     * @throws IllegalStateException if the list is empty
     */
    public T removeLast() {
        DoublyNode<T> curr = tail;
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        if (head == tail) { // Only one element
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return curr.data;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the list is empty, false otherwise.
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
     * Checks if the list contains the specified element.
     *
     * @param data the element to check for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(T data) {
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    public String toString() {
        DoublyNode<T> current = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Adds an element at the specified index in the list.
     *
     * @param index the index at which the element should be added
     * @param data  the element to be added
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    public void add(int index,T data) {
        validateIndexForAdd(index);
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            DoublyNode<T> newNode = new DoublyNode<>(data);
            DoublyNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }

    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T get(int index) {
        validateIndex(index);
        DoublyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Validates that the specified index is within the bounds of the list.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }
    }

    /**
     * Removes and returns the element at the specified index in the list.
     *
     * @param index the index of the element to be removed
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T remove(int index) {
        validateIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        DoublyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * or -1 if the list does not contain the element.
     *
     * @param data the element to search for
     * @return the index of the first occurrence of the element, or -1 if not found
     */
    public int indexOf(T data) {
        DoublyNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.data, data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in the list,
     * or -1 if the list does not contain the element.
     *
     * @param data the element to search for
     * @return the index of the last occurrence of the element, or -1 if not found
     */
    public int lastIndexOf(T data) {
        DoublyNode<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.data, data)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    /**
     * Reverses the order of the elements in the list.
     */
    public void reverse() {
        DoublyNode<T> current = head;
        DoublyNode<T> temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) {
            head = temp.prev;
        }
    }
}
