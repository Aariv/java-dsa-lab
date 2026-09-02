package com.ariv.dsa.datastructure.linkedlist;

/**
 * A generic node class for a doubly linked list.
 *
 * @param <T> the type of data stored in the node
 */
class DoublyNode<T> {

    T data;

    DoublyNode<T> next;

    DoublyNode<T> prev;

    DoublyNode(T data) {
        this.data = data;
    }
}