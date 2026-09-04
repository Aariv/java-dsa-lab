package com.ariv.dsa.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic class representing a binary search tree (BST).
 *
 * @param <T> the type of data stored in the tree nodes, which must be comparable
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    /**
     * The root node of the binary search tree.
     */
    private TreeNode<T> root;
    /**
     * The number of nodes in the binary search tree.
     */
    private int size;

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Inserts a value into the binary search tree.
     *
     * @param value the value to insert
     * @return true if the value was inserted, false if it already exists in the tree
     * @throws IllegalArgumentException if the value is null
     */
    public boolean insert(T value) {

        requireValue(value);

        TreeNode<T> newNode = new TreeNode<>(value);

        if (root == null) {
            root = newNode;
            size++;
            return true;
        }

        TreeNode<T> parent = null;
        TreeNode<T> current = root;
        int comparison = 0;

        while (current != null) {

            parent = current;
            comparison = value.compareTo(current.data);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return false;
            }
        }

        if (comparison < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;

        return true;
    }

    /**
     * Inserts a value into the binary search tree using an alternative approach.
     *
     * @param value the value to insert
     * @return true if the value was inserted, false if it already exists in the tree
     * @throws IllegalArgumentException if the value is null
     */
    public boolean insertAlternative(T value) {

        requireValue(value);

        if (root == null) {
            root = new TreeNode<>(value);
            size++;
            return true;
        }

        TreeNode<T> current = root;

        while (true) {

            int comparison = value.compareTo(current.data);

            if (comparison < 0) {

                if (current.left == null) {
                    current.left = new TreeNode<>(value);

                    size++;
                    return true;
                }
                current = current.left;

            } else if (comparison > 0) {

                if (current.right == null) {
                    current.right = new TreeNode<>(value);

                    size++;
                    return true;
                }
                current = current.right;

            } else {
                return false;
            }
        }
    }

    /**
     * Validates that the provided value is not null.
     *
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is null
     */
    private void requireValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "Value cannot be null"
            );
        }
    }

    /**
     * Checks if the binary search tree contains the specified value.
     *
     * @param value the value to check for
     * @return true if the value exists in the tree, false otherwise
     * @throws IllegalArgumentException if the value is null
     */
    public boolean contains(T value) {

        requireValue(value);

        TreeNode<T> current = root;

        while (current != null) {

            int comparison = value.compareTo(current.data);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Finds the minimum value in the binary search tree.
     *
     * @return the minimum value in the tree
     * @throws IllegalStateException if the tree is empty
     */
    public T minimum() {

        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        TreeNode<T> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    /**
     * Finds the maximum value in the binary search tree.
     *
     * @return the maximum value in the tree
     * @throws IllegalStateException if the tree is empty
     */
    public T maximum() {

        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        TreeNode<T> current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    /**
     * Returns the number of nodes in the binary search tree.
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the binary search tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the height of the binary search tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Recursively calculates the height of the binary search tree starting from the specified node.
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
     * Returns a list of values in the binary search tree in in-order traversal.
     *
     * @return a list of values in in-order traversal
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    /**
     * Recursively performs in-order traversal of the binary search tree starting from the specified node.
     *
     * @param root   the node to start in-order traversal from
     * @param result the list to store the values in in-order traversal
     */
    private void inorder(TreeNode<T> root, List<T> result) {
        if (root != null) {
            inorder(root.left, result);
            result.add(root.data);
            inorder(root.right, result);
        }
    }

    /**
     * Returns a list of values in the binary search tree in pre-order traversal.
     *
     * @return a list of values in pre-order traversal
     */
    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * Recursively performs pre-order traversal of the binary search tree starting from the specified node.
     *
     * @param root   the node to start pre-order traversal from
     * @param result the list to store the values in pre-order traversal
     */
    private void preorder(TreeNode<T> root, List<T> result) {
        if (root != null) {
            result.add(root.data);
            preorder(root.left, result);
            preorder(root.right, result);
        }
    }

    /**
     * Returns a list of values in the binary search tree in post-order traversal.
     *
     * @return a list of values in post-order traversal
     */
    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    /**
     * Recursively performs post-order traversal of the binary search tree starting from the specified node.
     *
     * @param root   the node to start post-order traversal from
     * @param result the list to store the values in post-order traversal
     */
    private void postorder(TreeNode<T> root, List<T> result) {
        if (root != null) {
            postorder(root.left, result);
            postorder(root.right, result);
            result.add(root.data);
        }
    }

    /**
     * Clears the binary search tree, removing all nodes and resetting its size to zero.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Deletes a value from the binary search tree.
     *
     * @param value the value to delete
     * @return true if the value was deleted, false if it does not exist in the tree
     * @throws IllegalArgumentException if the value is null
     */
    public boolean delete(T value) {

        requireValue(value);

        if (!contains(value)) {
            return false;
        }

        root = delete(root, value);

        size--;

        return true;
    }

    /**
     * Recursively deletes a value from the binary search tree starting from the specified node.
     *
     * @param node  the node to start deletion from
     * @param value the value to delete
     * @return the updated node after deletion
     */
    private TreeNode<T> delete(TreeNode<T> node, T value) {

        if (node == null) {
            return null;
        }

        int comparison = value.compareTo(node.data);

        if (comparison < 0) {
            node.left = delete(node.left, value);
        } else if (comparison > 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            TreeNode<T> minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = delete(node.right, minNode.data);
        }

        return node;
    }

    /**
     * Finds the node with the minimum value in the binary search tree starting from the specified node.
     *
     * @param right the node to start searching for the minimum value
     * @return the node with the minimum value
     */
    private TreeNode<T> findMin(TreeNode<T> right) {
        while (right.left != null) {
            right = right.left;
        }
        return right;
    }
}