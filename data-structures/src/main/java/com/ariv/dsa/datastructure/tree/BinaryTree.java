package com.ariv.dsa.datastructure.tree;

public class BinaryTree<T> {

    private TreeNode<T> root;

    int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public void setRoot(T value) {

        if(root != null) {
            throw new IllegalStateException(
                    "Root already exists"
            );
        }

        root = new TreeNode<>(value);

        size = 1;
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);

        return leftCount + rightCount + 1;
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(TreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (node.data.equals(value)) {
            return true;
        }

        return contains(node.left, value) || contains(node.right, value);
    }

    public T getRoot() {
        return root != null ? root.data : null;
    }

    public int size() {
        return size;
    }

    public TreeNode<T> getRootNode() {
        return root;
    }
}