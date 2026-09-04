package com.ariv.dsa.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A generic class representing a binary tree.
 *
 * @param <T> the type of data stored in the tree nodes
 */
public class BinaryTree<T> {

    /**
     * The root node of the binary tree.
     */
    private TreeNode<T> root;

    /**
     * The number of nodes in the binary tree.
     */
    private int size;

    /**
     * Constructs an empty binary tree.
     */
    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Sets the root of the binary tree with the specified value.
     *
     * @param value the value to set as the root
     * @throws IllegalStateException if the root already exists
     */
    public void setRoot(T value) {

        if(root != null) {
            throw new IllegalStateException(
                    "Root already exists"
            );
        }

        root = new TreeNode<>(value);

        size = 1;
    }

    /**
     * Returns the height of the binary tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Recursively calculates the height of the binary tree starting from the specified node.
     *
     * @param node the node to start calculating height from
     * @return the height of the tree starting from the specified node
     */
    private int height(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Returns the total number of nodes in the binary tree.
     *
     * @return the total number of nodes in the tree
     */
    public int countNodes() {
        return countNodes(root);
    }

    /**
     * Recursively counts the total number of nodes in the binary tree starting from the specified node.
     *
     * @param node the node to start counting from
     * @return the total number of nodes in the tree starting from the specified node
     */
    private int countNodes(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);

        return leftCount + rightCount + 1;
    }

    /**
     * Checks if the binary tree contains the specified value.
     *
     * @param value the value to search for
     * @return true if the value is found in the tree, false otherwise
     */
    public boolean contains(T value) {
        return contains(root, value);
    }

    /**
     * Recursively checks if the binary tree contains the specified value starting from the specified node.
     *
     * @param node  the node to start searching from
     * @param value the value to search for
     * @return true if the value is found in the tree starting from the specified node, false otherwise
     */
    private boolean contains(TreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (node.data.equals(value)) {
            return true;
        }

        return contains(node.left, value) || contains(node.right, value);
    }

    /**
     * Returns the root value of the binary tree.
     *
     * @return the root value, or null if the tree is empty
     */
    public T getRoot() {
        return root != null ? root.data : null;
    }

    /**
     * Returns the number of nodes in the binary tree.
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns the root node of the binary tree.
     *
     * @return the root node, or null if the tree is empty
     */
    public TreeNode<T> getRootNode() {
        return root;
    }

    /**
     * Performs a pre-order traversal of the binary tree and returns a list of values.
     *
     * @return a list of values in pre-order traversal
     */
    public List<T> preOrder() {

        List<T> result = new ArrayList<>();

        preOrder(root, result);

        return result;
    }

    /**
     * Recursively performs a pre-order traversal of the binary tree starting from the specified node.
     *
     * @param root   the node to start the traversal from
     * @param result the list to store the values in pre-order
     */
    private void preOrder(TreeNode<T> root, List<T> result) {
        if (root == null) {
            return;
        }

        result.add(root.data);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    /**
     * Performs an in-order traversal of the binary tree and returns a list of values.
     *
     * @return a list of values in in-order traversal
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    /**
     * Recursively performs an in-order traversal of the binary tree starting from the specified node.
     *
     * @param root   the node to start the traversal from
     * @param result the list to store the values in in-order
     */
    private void inOrder(TreeNode<T> root, List<T> result) {
        if (root == null) {
            return;
        }
        inOrder(root.left, result);
        result.add(root.data);
        inOrder(root.right, result);
    }

    /**
     * Performs a post-order traversal of the binary tree and returns a list of values.
     *
     * @return a list of values in post-order traversal
     */
    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    /**
     * Recursively performs a post-order traversal of the binary tree starting from the specified node.
     *
     * @param root   the node to start the traversal from
     * @param result the list to store the values in post-order
     */
    private void postOrder(TreeNode<T> root, List<T> result) {
        if (root == null) {
            return;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.data);
    }

    /**
     * Performs a level-order traversal of the binary tree and returns a list of values.
     *
     * @return a list of values in level-order traversal
     */
    public List<T> levelOrder() {
        List<T> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        java.util.Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            result.add(current.data);
            if(current.left != null) {
                queue.offer(current.left);
            }
            if(current.right != null) {
                queue.offer(current.right);
            }
        }
        return result;
    }

}