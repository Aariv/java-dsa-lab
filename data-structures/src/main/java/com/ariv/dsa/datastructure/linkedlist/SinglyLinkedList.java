package com.ariv.dsa.datastructure.linkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A generic implementation of a singly linked list.
 *
 * @param <T> the type of elements in this list
 */
public class SinglyLinkedList<T> {

    private Node<T> head; // The first node in the list.
    private int size; // The number of elements in the list.

    /**
     * Constructs an empty singly linked list.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param data the element to be added
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head; // Point the new node to the current head.
        head = newNode; // Update the head to the new node.
        size++; // Increment the size of the list.
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param data the element to be added
     */
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode; // If the list is empty, set the new node as the head.
            size++; // Increment the size of the list.
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next; // Traverse to the last node.
        }
        current.next = newNode; // Link the last node to the new node.
        size++; // Increment the size of the list.
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        if(head == null){
            throw new NoSuchElementException();
        }
        T data = head.data; // Store the data of the head node.
        head = head.next; // Update the head to the next node.
        size--; // Decrement the size of the list.
        return data; // Return the data of the removed node.
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the removed element
     * @throws NoSuchElementException if the list is empty
     */
    public T removeLast() {
        if (head == null){
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            T data = head.data; // Store the data of the head node.
            head = null; // If there's only one node, set head to null.
            size--; // Decrement the size of the list.
            return data; // Return the data of the removed node.
        }
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next; // Traverse to the second last node.
        }
        T data = current.next.data; // Store the data of the last node.
        current.next = null; // Remove the last node by setting the next of second last node to null.
        size--; // Decrement the size of the list.
        return data; // Return the data of the removed node.
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
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param data the element to check for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, data)) {
                return true; // Data found in the list.
            }
            current = current.next; // Move to the next node.
        }
        return false; // Data not found in the list.
    }

    /**
     * Returns the first element of the list without removing it.
     *
     * @return the first element, or null if the list is empty
     */
    public T getFirst() {
        Node<T> current = head;
        if (current == null) {
            return null; // List is empty, return null.
        }
        return current.data; // Return the data of the head node.
    }

    /**
     * Returns the last element of the list without removing it.
     *
     * @return the last element, or null if the list is empty
     */
    public T getLast() {
        Node<T> current = head;
        if (current == null) {
            return null; // List is empty, return null.
        }
        while (current.next != null) {
            current = current.next; // Traverse to the last node.
        }
        return current.data; // Return the data of the last node.
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = null;
        size = 0;
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
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    public void add(int index,T data){
        validatePosition(index);
        if (index == 0) {
            addFirst(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> previous = getNode(index - 1);
        newNode.next = previous.next;
        previous.next = newNode;
        size++;
    }

    private void validatePosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index index: " + index );
        }
    }

    public T remove(int index) {
        validateIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        Node<T> previous = getNode(index - 1);
        T removedData = previous.next.data;
        previous.next = previous.next.next;
        size--;
        return removedData;
    }

    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(current.data, data)) {
                return index; // Data found, return the index.
            }
            current = current.next; // Move to the next node.
            index++; // Increment the index.
        }
        return -1; // Data not found, return -1.
    }

    public T get(int index){
        return getNode(index).data;
    }

    public void reverse() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = null;
        while (current != null) {
            next = current.next; // Store the next node.
            current.next = previous; // Reverse the link.
            previous = current; // Move previous to current.
            current = next; // Move to the next node.
        }
        head = previous; // Update head to the new first node.
    }

    private Node<T> getNode(int index){
        validateIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index index: " + index );
        }
    }
}
