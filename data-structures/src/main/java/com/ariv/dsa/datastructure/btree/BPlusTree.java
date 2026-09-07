package com.ariv.dsa.datastructure.btree;

import java.util.Objects;

public class BPlusTree<T extends Comparable<? super T>> {

    private Node<T> root;
    private final int order;
    private int size;

    /**
     * Creates an empty B+ Tree.
     *
     * Order represents the maximum number of children an internal
     * node can contain.
     *
     * For example:
     *
     * order = 4
     * maximum children = 4
     * maximum keys = 3
     *
     * Overflow is not handled during Phase 10.3.
     *
     * @param order B+ Tree order
     */
    public BPlusTree(int order) {
        if (order < 3) {
            throw new IllegalArgumentException(
                    "Order must be greater than or equal to 3"
            );
        }

        this.order = order;
    }

    /**
     * Inserts a key into the B+ Tree.
     *
     * Phase 10.3 limitations:
     *
     * 1. Keys are inserted into a leaf in sorted order.
     * 2. Duplicate keys are rejected.
     * 3. Overflow and splitting are not yet handled.
     *
     * @param value value to insert
     * @return true when inserted, false when the value already exists
     */
    public boolean insert(T value) {
        Objects.requireNonNull(
                value,
                "B+ Tree does not support null values"
        );

        /*
         * The first inserted value creates a leaf root.
         */
        if (root == null) {
            LeafNode<T> leaf = new LeafNode<>();
            leaf.getKeys().add(value);

            root = leaf;
            size = 1;

            return true;
        }

        LeafNode<T> targetLeaf = findLeaf(value);

        int insertionIndex = findInsertionIndex(
                targetLeaf,
                value
        );

        /*
         * insertionIndex identifies either:
         *
         * 1. the duplicate's position, or
         * 2. the position where the value should be inserted.
         */
        if (insertionIndex < targetLeaf.getKeys().size()
                && compare(
                        value,
                        targetLeaf.getKeys().get(insertionIndex)
                ) == 0) {

            return false;
        }

        targetLeaf.getKeys().add(
                insertionIndex,
                value
        );

        size++;

        /*
         * Phase 10.4 will detect:
         *
         * targetLeaf.getKeys().size() > maximumKeys()
         *
         * Phase 10.5 will split the leaf.
         */

        return true;
    }

    /**
     * Alias for insert.
     *
     * @param value value to add
     * @return true when inserted, false when already present
     */
    public boolean add(T value) {
        return insert(value);
    }

    /**
     * Checks whether a value exists in the B+ Tree.
     *
     * @param value value to search
     * @return true when found
     */
    public boolean contains(T value) {
        Objects.requireNonNull(
                value,
                "Value cannot be null"
        );

        if (root == null) {
            return false;
        }

        LeafNode<T> leaf = findLeaf(value);

        int index = findInsertionIndex(
                leaf,
                value
        );

        return index < leaf.getKeys().size()
                && compare(
                        value,
                        leaf.getKeys().get(index)
                ) == 0;
    }

    /**
     * Navigates through internal nodes and returns the leaf where
     * the supplied value should exist or should be inserted.
     *
     * Package-private visibility allows direct testing during the
     * educational implementation.
     *
     * @param value search value
     * @return target leaf, or null when the tree is empty
     */
    LeafNode<T> findLeaf(T value) {
        Objects.requireNonNull(
                value,
                "Value cannot be null"
        );

        if (root == null) {
            return null;
        }

        Node<T> current = root;

        while (!current.isLeaf()) {
            InternalNode<T> internal =
                    (InternalNode<T>) current;

            int childIndex = findChildIndex(
                    internal,
                    value
            );

            current = internal.getChildren()
                    .get(childIndex);
        }

        return (LeafNode<T>) current;
    }

    /**
     * Finds the child that should be followed from an internal node.
     *
     * Separator semantics:
     *
     * value < separator       -> left child
     * value >= separator      -> right child
     */
    private int findChildIndex(
            InternalNode<T> node,
            T value) {

        int low = 0;
        int high = node.getKeys().size();

        /*
         * Upper-bound binary search.
         *
         * Find the first separator strictly greater than value.
         */
        while (low < high) {
            int middle = low + (high - low) / 2;

            T separator = node.getKeys().get(middle);

            if (compare(value, separator) >= 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }

        return low;
    }

    /**
     * Finds the first index whose key is greater than or equal
     * to the supplied value.
     *
     * This is a lower-bound binary search.
     */
    private int findInsertionIndex(
            LeafNode<T> leaf,
            T value) {

        int low = 0;
        int high = leaf.getKeys().size();

        while (low < high) {
            int middle = low + (high - low) / 2;

            T middleValue =
                    leaf.getKeys().get(middle);

            if (compare(middleValue, value) < 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }

        return low;
    }

    private int compare(T first, T second) {
        return first.compareTo(second);
    }

    /**
     * Returns the configured B+ Tree order.
     */
    public int order() {
        return order;
    }

    /**
     * Returns the maximum number of keys allowed before overflow.
     *
     * Overflow enforcement starts in Phase 10.4.
     */
    public int maximumKeys() {
        return order - 1;
    }

    /**
     * Returns the number of values stored in leaf nodes.
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the B+ Tree contains no values.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Test helper used while manually constructing the search-only
     * tree from Phase 10.2.
     *
     * The size is recalculated from the linked leaf level.
     */
    void setRoot(Node<T> root) {
        this.root = root;
        this.size = countLeafKeys(root);
    }

    private int countLeafKeys(Node<T> node) {
        if (node == null) {
            return 0;
        }

        Node<T> current = node;

        /*
         * Navigate to the leftmost leaf.
         */
        while (!current.isLeaf()) {
            InternalNode<T> internal =
                    (InternalNode<T>) current;

            if (internal.getChildren().isEmpty()) {
                return 0;
            }

            current = internal.getChildren().get(0);
        }

        int count = 0;
        LeafNode<T> leaf = (LeafNode<T>) current;

        /*
         * Count through the linked leaf level.
         */
        while (leaf != null) {
            count += leaf.getKeys().size();
            leaf = leaf.getNext();
        }

        return count;
    }
}