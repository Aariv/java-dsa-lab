package com.ariv.dsa.datastructure.tree;

/**
 * A generic class representing a node in a binary tree.
 *
 * @param <T> the type of data stored in the node
 */
class TreeNode<T> {

    T data;

    TreeNode<T> left;

    TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
    }
}