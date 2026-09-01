package com.ariv.dsa.datastructure.linkedlist;

class Node<T> {

    T data;
    Node<T> next; // Each node points to the next node.

    Node(T data) {
        this.data = data;
    }
}