package com.ariv.dsa.datastructure.btree;

public class LeafNode<T extends Comparable<? super T>>
        extends Node<T> {

    private LeafNode<T> next;

    @Override
    public boolean isLeaf() {
        return true;
    }

    public LeafNode<T> getNext() {
        return next;
    }

    public void setNext(LeafNode<T> next) {
        this.next = next;
    }
}