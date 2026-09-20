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
        // Check if the root already exists
        if(root != null) {
            throw new IllegalStateException(
                    "Root already exists"
            );
        }
        // Set the root of the binary tree with the specified value
        root = new TreeNode<>(value);
        // Increment the size of the binary tree
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
        // If the node is null, return 0 (base case)
        if (node == null) {
            return 0;
        }
        // Recursively calculate the height of the left and right subtrees
        int leftHeight = height(node.left);
        // Recursively calculate the height of the right subtree
        int rightHeight = height(node.right);
        // Return the maximum height of the left and right subtrees plus 1 for the current node
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
        // If the node is null, return 0 (base case)
        if (node == null) {
            return 0;
        }
        // Recursively count the number of nodes in the left and right subtrees
        int leftCount = countNodes(node.left);
        // Recursively count the number of nodes in the right subtree
        int rightCount = countNodes(node.right);
        // Return the total count of nodes in the left and right subtrees plus 1 for the current node
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
        // If the node is null, return false (base case)
        if (node == null) {
            return false;
        }
        // Check if the current node's data matches the specified value
        if (node.data.equals(value)) {
            return true;
        }
        // Recursively check the left and right subtrees for the specified value
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
        // Add the current node's data to the result list
        result.add(root.data);
        // Recursively traverse the left subtree
        preOrder(root.left, result);
        // Recursively traverse the right subtree
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
        // Recursively traverse the left subtree
        inOrder(root.left, result);
        // Add the current node's data to the result list
        result.add(root.data);
        // Recursively traverse the right subtree
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
        // Recursively traverse the left subtree
        postOrder(root.left, result);
        // Recursively traverse the right subtree
        postOrder(root.right, result);
        // Add the current node's data to the result list
        result.add(root.data);
    }

    /**
     * Performs a level-order traversal of the binary tree and returns a list of values.
     *
     * @return a list of values in level-order traversal
     */
    public List<T> levelOrder() {
        List<T> result = new ArrayList<>();
        // Check if the root is null, return an empty list if it is
        if(root == null) {
            return result;
        }

        // Use a queue to perform level-order traversal
        java.util.Queue<TreeNode<T>> queue = new LinkedList<>();
        // Add the root node to the queue to start the traversal
        queue.offer(root);
        // Continue traversing the tree until the queue is empty
        while(!queue.isEmpty()) {
            // Poll the next node from the queue and add its data to the result list
            TreeNode<T> current = queue.poll();
            // Add the current node's data to the result list
            result.add(current.data);
            // If the current node has a left child, add it to the queue for future processing
            if(current.left != null) {
                queue.offer(current.left);
            }
            // If the current node has a right child, add it to the queue for future processing
            if(current.right != null) {
                queue.offer(current.right);
            }
        }
        return result;
    }

}