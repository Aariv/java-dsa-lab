package com.ariv.dsa.datastructure.btree;

import java.util.ArrayList;
import java.util.List;

public abstract class Node<T extends Comparable<? super T>> {

    protected final List<T> keys;
    protected InternalNode<T> parent;

    protected Node() {
        this.keys = new ArrayList<>();
    }

    public List<T> getKeys() {
        return keys;
    }

    public InternalNode<T> getParent() {
        return parent;
    }

    public void setParent(InternalNode<T> parent) {
        this.parent = parent;
    }

    public abstract boolean isLeaf();
}