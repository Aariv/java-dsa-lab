package com.ariv.dsa.datastructure.tree.avl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AVLTree<T> {

    private Node<T> root;
    private int size;

    private final Comparator<? super T> comparator;

    /**
     * Creates an AVL tree that uses the natural ordering of its elements.
     *
     * Elements inserted into this tree must implement Comparable.
     */
    public AVLTree() {
        this(null);
    }

    /**
     * Creates an AVL tree that uses the provided comparator.
     *
     * @param comparator comparator used to order values
     */
    public AVLTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Inserts a value into the AVL tree.
     *
     * Duplicate values are not inserted.
     *
     * @param value value to insert
     * @return true if the value was inserted,
     *         false if the value already exists
     */
    public boolean insert(T value) {
        Objects.requireNonNull(value, "AVL tree does not support null values");

        MutableBoolean inserted = new MutableBoolean();

        root = insert(root, value, inserted);

        if (inserted.value) {
            size++;
        }

        return inserted.value;
    }

    /**
     * Alias for insert.
     *
     * @param value value to add
     * @return true if inserted, otherwise false
     */
    public boolean add(T value) {
        return insert(value);
    }

    /**
     * Deletes a value from the AVL tree.
     *
     * @param value value to delete
     * @return true if the value was deleted,
     *         false if the value was not found
     */
    public boolean delete(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        MutableBoolean deleted = new MutableBoolean();

        root = delete(root, value, deleted);

        if (deleted.value) {
            size--;
        }

        return deleted.value;
    }

    /**
     * Alias for delete.
     *
     * @param value value to remove
     * @return true if removed, otherwise false
     */
    public boolean remove(T value) {
        return delete(value);
    }

    /**
     * Checks whether a value exists in the AVL tree.
     *
     * @param value value to search for
     * @return true if found, otherwise false
     */
    public boolean contains(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        return findNode(value) != null;
    }

    /**
     * Searches for a value in the AVL tree.
     *
     * @param value value to search for
     * @return Optional containing the stored value when found
     */
    public Optional<T> search(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        Node<T> node = findNode(value);

        return node == null
                ? Optional.empty()
                : Optional.of(node.value);
    }

    /**
     * Returns the smallest value in the tree.
     *
     * @return minimum value, or Optional.empty() for an empty tree
     */
    public Optional<T> min() {
        if (root == null) {
            return Optional.empty();
        }

        return Optional.of(minNode(root).value);
    }

    /**
     * Returns the largest value in the tree.
     *
     * @return maximum value, or Optional.empty() for an empty tree
     */
    public Optional<T> max() {
        if (root == null) {
            return Optional.empty();
        }

        Node<T> current = root;

        while (current.right != null) {
            current = current.right;
        }

        return Optional.of(current.value);
    }

    /**
     * Returns the number of values stored in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the tree is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the height of the complete tree.
     *
     * Empty tree height: 0
     * Leaf node height: 1
     */
    public int height() {
        return height(root);
    }

    /**
     * Removes every value from the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns values using in-order traversal.
     *
     * For an AVL tree, this produces values in sorted order.
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>(size);

        inOrder(root, result);

        return result;
    }

    /**
     * Returns values using pre-order traversal.
     */
    public List<T> preOrder() {
        List<T> result = new ArrayList<>(size);

        preOrder(root, result);

        return result;
    }

    /**
     * Returns values using post-order traversal.
     */
    public List<T> postOrder() {
        List<T> result = new ArrayList<>(size);

        postOrder(root, result);

        return result;
    }

    /**
     * Returns values using level-order traversal.
     */
    public List<T> levelOrder() {
        List<T> result = new ArrayList<>(size);

        if (root == null) {
            return result;
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();

            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    /**
     * Returns the values grouped by tree level.
     *
     * Example:
     *
     * [
     *   [30],
     *   [20, 40],
     *   [10, 25, 50]
     * ]
     */
    public List<List<T>> levelOrderByLevel() {
        List<List<T>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<T> level = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {
                Node<T> current = queue.poll();

                level.add(current.value);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(level);
        }

        return result;
    }

    /**
     * Validates the complete AVL tree.
     *
     * The validation checks:
     *
     * 1. Binary Search Tree ordering
     * 2. AVL balance condition
     * 3. Stored node heights
     * 4. Stored size
     */
    public boolean isValidAVLTree() {
        ValidationResult result = validate(root, null, null);

        return result.valid && result.nodeCount == size;
    }

    /**
     * Returns a readable multi-line tree representation.
     *
     * The right subtree is displayed above the node and
     * the left subtree is displayed below the node.
     */
    public String toTreeString() {
        if (root == null) {
            return "[empty]";
        }

        StringBuilder builder = new StringBuilder();

        buildTreeString(root, "", true, builder);

        return builder.toString();
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }

    /*
     * ============================================================
     * Insertion
     * ============================================================
     */

    private Node<T> insert(
            Node<T> node,
            T value,
            MutableBoolean inserted) {

        if (node == null) {
            inserted.value = true;
            return new Node<>(value);
        }

        int comparison = compare(value, node.value);

        if (comparison < 0) {
            node.left = insert(node.left, value, inserted);
        } else if (comparison > 0) {
            node.right = insert(node.right, value, inserted);
        } else {
            return node;
        }

        updateHeight(node);

        return rebalance(node);
    }

    /*
     * ============================================================
     * Deletion
     * ============================================================
     */

    private Node<T> delete(
            Node<T> node,
            T value,
            MutableBoolean deleted) {

        if (node == null) {
            return null;
        }

        int comparison = compare(value, node.value);

        if (comparison < 0) {
            node.left = delete(node.left, value, deleted);
        } else if (comparison > 0) {
            node.right = delete(node.right, value, deleted);
        } else {
            deleted.value = true;

            /*
             * Case 1: No left child
             *
             * Returning the right child also handles the leaf case,
             * because a leaf node's right child is null.
             */
            if (node.left == null) {
                return node.right;
            }

            /*
             * Case 2: No right child
             */
            if (node.right == null) {
                return node.left;
            }

            /*
             * Case 3: Two children
             *
             * Find the in-order successor, copy its value into this
             * node, and remove the successor from the right subtree.
             */
            Node<T> successor = minNode(node.right);

            node.value = successor.value;

            /*
             * Use a separate deletion marker because removing the
             * successor must not affect the public size twice.
             */
            node.right = delete(
                    node.right,
                    successor.value,
                    new MutableBoolean()
            );
        }

        updateHeight(node);

        return rebalance(node);
    }

    /*
     * ============================================================
     * Searching
     * ============================================================
     */

    private Node<T> findNode(T value) {
        Node<T> current = root;

        while (current != null) {
            int comparison = compare(value, current.value);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return current;
            }
        }

        return null;
    }

    private Node<T> minNode(Node<T> node) {
        Node<T> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /*
     * ============================================================
     * AVL balancing
     * ============================================================
     */

    private Node<T> rebalance(Node<T> node) {
        int balance = balanceFactor(node);

        /*
         * Left-heavy subtree
         */
        if (balance > 1) {
            /*
             * LR case
             *
             * The left child is right-heavy.
             */
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            /*
             * LL case after optional LR preparation.
             */
            return rotateRight(node);
        }

        /*
         * Right-heavy subtree
         */
        if (balance < -1) {
            /*
             * RL case
             *
             * The right child is left-heavy.
             */
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            /*
             * RR case after optional RL preparation.
             */
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Performs a right rotation.
     *
     * Before:
     *
     *          y
     *         / \
     *        x   T3
     *       / \
     *      T1  T2
     *
     * After:
     *
     *          x
     *         / \
     *        T1  y
     *           / \
     *          T2  T3
     */
    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> transferredSubtree = x.right;

        x.right = y;
        y.left = transferredSubtree;

        /*
         * Update the lower node first.
         */
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * Performs a left rotation.
     *
     * Before:
     *
     *        x
     *       / \
     *      T1  y
     *         / \
     *        T2  T3
     *
     * After:
     *
     *          y
     *         / \
     *        x   T3
     *       / \
     *      T1  T2
     */
    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> transferredSubtree = y.left;

        y.left = x;
        x.right = transferredSubtree;

        /*
         * Update the lower node first.
         */
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private int balanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    private void updateHeight(Node<T> node) {
        node.height =
                1 + Math.max(
                        height(node.left),
                        height(node.right)
                );
    }

    private int height(Node<T> node) {
        return node == null ? 0 : node.height;
    }

    /*
     * ============================================================
     * Traversals
     * ============================================================
     */

    private void inOrder(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    private void preOrder(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    private void postOrder(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.value);
    }

    /*
     * ============================================================
     * Validation
     * ============================================================
     */

    private ValidationResult validate(
            Node<T> node,
            T exclusiveMinimum,
            T exclusiveMaximum) {

        if (node == null) {
            return ValidationResult.validEmptyTree();
        }

        if (exclusiveMinimum != null
                && compare(node.value, exclusiveMinimum) <= 0) {
            return ValidationResult.invalid();
        }

        if (exclusiveMaximum != null
                && compare(node.value, exclusiveMaximum) >= 0) {
            return ValidationResult.invalid();
        }

        ValidationResult leftResult =
                validate(node.left, exclusiveMinimum, node.value);

        if (!leftResult.valid) {
            return ValidationResult.invalid();
        }

        ValidationResult rightResult =
                validate(node.right, node.value, exclusiveMaximum);

        if (!rightResult.valid) {
            return ValidationResult.invalid();
        }

        int calculatedHeight =
                1 + Math.max(
                        leftResult.calculatedHeight,
                        rightResult.calculatedHeight
                );

        int calculatedBalance =
                leftResult.calculatedHeight
                        - rightResult.calculatedHeight;

        boolean heightIsCorrect =
                node.height == calculatedHeight;

        boolean balanceIsCorrect =
                Math.abs(calculatedBalance) <= 1;

        boolean valid =
                heightIsCorrect && balanceIsCorrect;

        int nodeCount =
                1 + leftResult.nodeCount + rightResult.nodeCount;

        return new ValidationResult(
                valid,
                calculatedHeight,
                nodeCount
        );
    }

    /*
     * ============================================================
     * Tree visualization
     * ============================================================
     */

    private void buildTreeString(
            Node<T> node,
            String prefix,
            boolean isTail,
            StringBuilder builder) {

        if (node.right != null) {
            buildTreeString(
                    node.right,
                    prefix + (isTail ? "│   " : "    "),
                    false,
                    builder
            );
        }

        builder.append(prefix)
                .append(isTail ? "└── " : "┌── ")
                .append(node.value)
                .append(" [h=")
                .append(node.height)
                .append(", bf=")
                .append(balanceFactor(node))
                .append(']')
                .append(System.lineSeparator());

        if (node.left != null) {
            buildTreeString(
                    node.left,
                    prefix + (isTail ? "    " : "│   "),
                    true,
                    builder
            );
        }
    }

    /*
     * ============================================================
     * Comparison
     * ============================================================
     */

    @SuppressWarnings("unchecked")
    private int compare(T first, T second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        }

        if (!(first instanceof Comparable<?>)) {
            throw new IllegalStateException(
                    "No Comparator was provided and values do not "
                            + "implement Comparable"
            );
        }

        return ((Comparable<? super T>) first).compareTo(second);
    }

    /*
     * ============================================================
     * Internal classes
     * ============================================================
     */

    private static final class Node<T> {

        private T value;
        private int height;

        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }

    private static final class MutableBoolean {

        private boolean value;
    }

    private static final class ValidationResult {

        private final boolean valid;
        private final int calculatedHeight;
        private final int nodeCount;

        private ValidationResult(
                boolean valid,
                int calculatedHeight,
                int nodeCount) {

            this.valid = valid;
            this.calculatedHeight = calculatedHeight;
            this.nodeCount = nodeCount;
        }

        private static ValidationResult validEmptyTree() {
            return new ValidationResult(true, 0, 0);
        }

        private static ValidationResult invalid() {
            return new ValidationResult(false, 0, 0);
        }
    }
}