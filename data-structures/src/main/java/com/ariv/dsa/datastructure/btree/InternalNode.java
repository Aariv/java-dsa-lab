package com.ariv.dsa.datastructure.btree;

import java.util.ArrayList;
import java.util.List;

public class InternalNode<T extends Comparable<? super T>>
        extends Node<T> {

    private final List<Node<T>> children;

    public InternalNode() {
        this.children = new ArrayList<>();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    /**
     * Adds a child and maintains its parent reference.
     */
    public void addChild(Node<T> child) {
        children.add(child);
        child.setParent(this);
    }
}