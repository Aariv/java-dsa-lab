package com.ariv.dsa.datastructure.tree.redblack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A self-balancing binary search tree.
 *
 * Red-Black Trees guarantee O(log n) time for insertion, deletion, and search.
 *
 * @param <T> type of values stored in the tree
 */
public class RedBlackTree<T> {

    /*
     * Red-Black Tree node color.
     */
    private enum Color {
        RED,
        BLACK
    }

    /*
     * One shared NIL node replaces all null child references.
     *
     * NIL must always be BLACK.
     */
    private final Node<T> nil;

    private Node<T> root;
    private int size;

    private final Comparator<? super T> comparator;

    /**
     * Creates a Red-Black Tree using natural ordering.
     *
     * Values must implement Comparable.
     */
    public RedBlackTree() {
        this(null);
    }

    /**
     * Creates a Red-Black Tree using the supplied comparator.
     *
     * @param comparator comparator defining the tree ordering
     */
    public RedBlackTree(Comparator<? super T> comparator) {
        this.comparator = comparator;

        nil = new Node<>(null, Color.BLACK);

        nil.left = nil;
        nil.right = nil;
        nil.parent = nil;

        root = nil;
    }

    /**
     * Inserts a value into the tree.
     *
     * Duplicate values are not inserted.
     *
     * @param value value to insert
     * @return true if inserted, false if already present
     */
    public boolean insert(T value) {
        Objects.requireNonNull(
                value,
                "Red-Black Tree does not support null values"
        );

        Node<T> parent = nil;
        Node<T> current = root;

        while (current != nil) {
            parent = current;

            int comparison = compare(value, current.value);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return false;
            }
        }

        /*
         * New BST nodes are initially RED.
         */
        Node<T> newNode = new Node<>(value, Color.RED);

        newNode.left = nil;
        newNode.right = nil;
        newNode.parent = parent;

        if (parent == nil) {
            root = newNode;
        } else if (compare(value, parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;

        fixAfterInsertion(newNode);

        return true;
    }

    /**
     * Alias for insert.
     */
    public boolean add(T value) {
        return insert(value);
    }

    /**
     * Deletes a value from the tree.
     *
     * @param value value to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        Node<T> nodeToDelete = findNode(value);

        if (nodeToDelete == nil) {
            return false;
        }

        deleteNode(nodeToDelete);
        size--;

        /*
         * Keep sentinel ownership predictable after deletion.
         */
        if (root != nil) {
            root.parent = nil;
        }

        nil.parent = nil;

        return true;
    }

    /**
     * Alias for delete.
     */
    public boolean remove(T value) {
        return delete(value);
    }

    /**
     * Checks whether a value exists.
     */
    public boolean contains(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        return findNode(value) != nil;
    }

    /**
     * Searches for a value.
     *
     * The stored object is returned, which can differ from the lookup
     * object when comparator equality defines identity.
     */
    public Optional<T> search(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        Node<T> node = findNode(value);

        return node == nil
                ? Optional.empty()
                : Optional.of(node.value);
    }

    /**
     * Returns the lowest value according to the tree comparator.
     */
    public Optional<T> min() {
        if (root == nil) {
            return Optional.empty();
        }

        return Optional.of(minimumNode(root).value);
    }

    /**
     * Returns the highest value according to the tree comparator.
     */
    public Optional<T> max() {
        if (root == nil) {
            return Optional.empty();
        }

        return Optional.of(maximumNode(root).value);
    }

    /**
     * Returns the number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree contains no elements.
     */
    public boolean isEmpty() {
        return root == nil;
    }

    /**
     * Calculates the current tree height.
     *
     * Empty tree height: 0
     * Leaf height: 1
     */
    public int height() {
        return height(root);
    }

    /**
     * Removes all elements.
     */
    public void clear() {
        root = nil;
        size = 0;

        nil.left = nil;
        nil.right = nil;
        nil.parent = nil;
        nil.color = Color.BLACK;
    }

    /**
     * Returns values in left-root-right order.
     *
     * The result follows the configured comparator ordering.
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>(size);

        inOrder(root, result);

        return result;
    }

    /**
     * Returns values in root-left-right order.
     */
    public List<T> preOrder() {
        List<T> result = new ArrayList<>(size);

        preOrder(root, result);

        return result;
    }

    /**
     * Returns values in left-right-root order.
     */
    public List<T> postOrder() {
        List<T> result = new ArrayList<>(size);

        postOrder(root, result);

        return result;
    }

    /**
     * Returns values in breadth-first order.
     */
    public List<T> levelOrder() {
        List<T> result = new ArrayList<>(size);

        if (root == nil) {
            return result;
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();

            result.add(current.value);

            if (current.left != nil) {
                queue.offer(current.left);
            }

            if (current.right != nil) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    /**
     * Returns values grouped by tree level.
     */
    public List<List<T>> levelOrderByLevel() {
        List<List<T>> result = new ArrayList<>();

        if (root == nil) {
            return result;
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            List<T> currentLevel = new ArrayList<>(currentLevelSize);

            for (int index = 0; index < currentLevelSize; index++) {
                Node<T> current = queue.poll();

                currentLevel.add(current.value);

                if (current.left != nil) {
                    queue.offer(current.left);
                }

                if (current.right != nil) {
                    queue.offer(current.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    /**
     * Validates:
     *
     * 1. Root is black
     * 2. NIL is black
     * 3. BST ordering is correct
     * 4. Red nodes have black children
     * 5. Every root-to-NIL path has equal black height
     * 6. Parent references are correct
     * 7. Stored size matches reachable nodes
     */
    public boolean isValidRedBlackTree() {
        if (nil.color != Color.BLACK) {
            return false;
        }

        if (root == nil) {
            return size == 0;
        }

        if (root.color != Color.BLACK) {
            return false;
        }

        if (root.parent != nil) {
            return false;
        }

        ValidationResult result =
                validate(root, null, null, nil);

        return result.valid && result.nodeCount == size;
    }

    /**
     * Returns a readable sideways tree.
     *
     * Example:
     *
     * │   ┌── 30(R)
     * └── 20(B)
     *     └── 10(R)
     */
    public String toTreeString() {
        if (root == nil) {
            return "[empty]";
        }

        StringBuilder builder = new StringBuilder();

        buildTreeString(root, "", true, builder);

        return builder.toString();
    }

    /**
     * Returns the comparator-ordered values.
     */
    @Override
    public String toString() {
        return inOrder().toString();
    }

    /*
     * ============================================================
     * Insertion balancing
     * ============================================================
     */

    private void fixAfterInsertion(Node<T> node) {
        /*
         * A violation is possible only when the parent is RED.
         */
        while (colorOf(node.parent) == Color.RED) {

            /*
             * Parent is the left child of grandparent.
             */
            if (node.parent == node.parent.parent.left) {
                Node<T> uncle = node.parent.parent.right;

                /*
                 * Case 1:
                 *
                 * Parent is RED and uncle is RED.
                 *
                 * Recolor parent and uncle to BLACK.
                 * Recolor grandparent to RED.
                 * Continue checking from grandparent.
                 */
                if (colorOf(uncle) == Color.RED) {
                    setColor(node.parent, Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(node.parent.parent, Color.RED);

                    node = node.parent.parent;
                } else {
                    /*
                     * Case 2:
                     *
                     * LR shape.
                     *
                     * Convert it into LL using a left rotation
                     * around the parent.
                     */
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }

                    /*
                     * Case 3:
                     *
                     * LL shape.
                     *
                     * Recolor and rotate right around grandparent.
                     */
                    setColor(node.parent, Color.BLACK);
                    setColor(node.parent.parent, Color.RED);

                    rotateRight(node.parent.parent);
                }
            } else {
                /*
                 * Mirror cases.
                 *
                 * Parent is the right child of grandparent.
                 */
                Node<T> uncle = node.parent.parent.left;

                /*
                 * Mirror Case 1:
                 *
                 * Parent and uncle are both RED.
                 */
                if (colorOf(uncle) == Color.RED) {
                    setColor(node.parent, Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(node.parent.parent, Color.RED);

                    node = node.parent.parent;
                } else {
                    /*
                     * Mirror Case 2:
                     *
                     * RL shape.
                     */
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }

                    /*
                     * Mirror Case 3:
                     *
                     * RR shape.
                     */
                    setColor(node.parent, Color.BLACK);
                    setColor(node.parent.parent, Color.RED);

                    rotateLeft(node.parent.parent);
                }
            }
        }

        /*
         * Red-Black rule:
         *
         * Root must always be BLACK.
         */
        root.color = Color.BLACK;
        root.parent = nil;
    }

    /*
     * ============================================================
     * Deletion
     * ============================================================
     */

    private void deleteNode(Node<T> nodeToDelete) {
        Node<T> movedOrRemovedNode = nodeToDelete;
        Color originalColor = movedOrRemovedNode.color;

        Node<T> replacement;

        /*
         * Case 1:
         *
         * No left child.
         */
        if (nodeToDelete.left == nil) {
            replacement = nodeToDelete.right;

            transplant(nodeToDelete, nodeToDelete.right);
        }

        /*
         * Case 2:
         *
         * No right child.
         */
        else if (nodeToDelete.right == nil) {
            replacement = nodeToDelete.left;

            transplant(nodeToDelete, nodeToDelete.left);
        }

        /*
         * Case 3:
         *
         * Two children.
         *
         * Replace the node with its in-order successor.
         */
        else {
            movedOrRemovedNode = minimumNode(nodeToDelete.right);
            originalColor = movedOrRemovedNode.color;

            replacement = movedOrRemovedNode.right;

            if (movedOrRemovedNode.parent == nodeToDelete) {
                /*
                 * replacement may be NIL.
                 *
                 * Its parent is needed by deletion fix-up.
                 */
                replacement.parent = movedOrRemovedNode;
            } else {
                transplant(
                        movedOrRemovedNode,
                        movedOrRemovedNode.right
                );

                movedOrRemovedNode.right = nodeToDelete.right;
                movedOrRemovedNode.right.parent = movedOrRemovedNode;
            }

            transplant(nodeToDelete, movedOrRemovedNode);

            movedOrRemovedNode.left = nodeToDelete.left;
            movedOrRemovedNode.left.parent = movedOrRemovedNode;

            movedOrRemovedNode.color = nodeToDelete.color;
        }

        /*
         * Removing a RED node does not alter black-height.
         *
         * Removing a BLACK node may violate Red-Black properties.
         */
        if (originalColor == Color.BLACK) {
            fixAfterDeletion(replacement);
        }
    }

    private void transplant(
            Node<T> nodeToReplace,
            Node<T> replacement) {

        if (nodeToReplace.parent == nil) {
            root = replacement;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = replacement;
        } else {
            nodeToReplace.parent.right = replacement;
        }

        /*
         * This intentionally updates NIL.parent too.
         *
         * Deletion fix-up needs to know NIL's temporary parent.
         */
        replacement.parent = nodeToReplace.parent;
    }

    /*
     * ============================================================
     * Deletion balancing
     * ============================================================
     */

    private void fixAfterDeletion(Node<T> node) {
        while (node != root && colorOf(node) == Color.BLACK) {

            /*
             * node is the left child.
             */
            if (node == node.parent.left) {
                Node<T> sibling = node.parent.right;

                /*
                 * Case 1:
                 *
                 * Sibling is RED.
                 *
                 * Convert the situation into one where the sibling
                 * is BLACK.
                 */
                if (colorOf(sibling) == Color.RED) {
                    setColor(sibling, Color.BLACK);
                    setColor(node.parent, Color.RED);

                    rotateLeft(node.parent);

                    sibling = node.parent.right;
                }

                /*
                 * Case 2:
                 *
                 * Sibling is BLACK and both sibling children
                 * are BLACK.
                 *
                 * Move the extra black condition upward.
                 */
                if (colorOf(sibling.left) == Color.BLACK
                        && colorOf(sibling.right) == Color.BLACK) {

                    setColor(sibling, Color.RED);

                    node = node.parent;
                } else {
                    /*
                     * Case 3:
                     *
                     * Sibling is BLACK.
                     * Near child is RED.
                     * Far child is BLACK.
                     *
                     * Convert into Case 4.
                     */
                    if (colorOf(sibling.right) == Color.BLACK) {
                        setColor(sibling.left, Color.BLACK);
                        setColor(sibling, Color.RED);

                        rotateRight(sibling);

                        sibling = node.parent.right;
                    }

                    /*
                     * Case 4:
                     *
                     * Sibling is BLACK and the far child is RED.
                     */
                    setColor(sibling, colorOf(node.parent));
                    setColor(node.parent, Color.BLACK);
                    setColor(sibling.right, Color.BLACK);

                    rotateLeft(node.parent);

                    node = root;
                }
            }

            /*
             * Mirror cases.
             *
             * node is the right child.
             */
            else {
                Node<T> sibling = node.parent.left;

                /*
                 * Mirror Case 1:
                 *
                 * Sibling is RED.
                 */
                if (colorOf(sibling) == Color.RED) {
                    setColor(sibling, Color.BLACK);
                    setColor(node.parent, Color.RED);

                    rotateRight(node.parent);

                    sibling = node.parent.left;
                }

                /*
                 * Mirror Case 2:
                 *
                 * Sibling and both of its children are BLACK.
                 */
                if (colorOf(sibling.right) == Color.BLACK
                        && colorOf(sibling.left) == Color.BLACK) {

                    setColor(sibling, Color.RED);

                    node = node.parent;
                } else {
                    /*
                     * Mirror Case 3:
                     *
                     * Sibling's far child is BLACK.
                     */
                    if (colorOf(sibling.left) == Color.BLACK) {
                        setColor(sibling.right, Color.BLACK);
                        setColor(sibling, Color.RED);

                        rotateLeft(sibling);

                        sibling = node.parent.left;
                    }

                    /*
                     * Mirror Case 4:
                     *
                     * Sibling's far child is RED.
                     */
                    setColor(sibling, colorOf(node.parent));
                    setColor(node.parent, Color.BLACK);
                    setColor(sibling.left, Color.BLACK);

                    rotateRight(node.parent);

                    node = root;
                }
            }
        }

        setColor(node, Color.BLACK);
    }

    /*
     * ============================================================
     * Rotations
     * ============================================================
     */

    /**
     * Left rotation:
     *
     *        x                       y
     *       / \                     / \
     *      T1  y        =>         x  T3
     *         / \                 / \
     *        T2 T3                T1 T2
     */
    private void rotateLeft(Node<T> node) {
        Node<T> promotedNode = node.right;

        node.right = promotedNode.left;

        if (promotedNode.left != nil) {
            promotedNode.left.parent = node;
        }

        promotedNode.parent = node.parent;

        if (node.parent == nil) {
            root = promotedNode;
        } else if (node == node.parent.left) {
            node.parent.left = promotedNode;
        } else {
            node.parent.right = promotedNode;
        }

        promotedNode.left = node;
        node.parent = promotedNode;
    }

    /**
     * Right rotation:
     *
     *          y                    x
     *         / \                  / \
     *        x  T3      =>        T1  y
     *       / \                     / \
     *      T1 T2                   T2 T3
     */
    private void rotateRight(Node<T> node) {
        Node<T> promotedNode = node.left;

        node.left = promotedNode.right;

        if (promotedNode.right != nil) {
            promotedNode.right.parent = node;
        }

        promotedNode.parent = node.parent;

        if (node.parent == nil) {
            root = promotedNode;
        } else if (node == node.parent.right) {
            node.parent.right = promotedNode;
        } else {
            node.parent.left = promotedNode;
        }

        promotedNode.right = node;
        node.parent = promotedNode;
    }

    /*
     * ============================================================
     * Searching
     * ============================================================
     */

    private Node<T> findNode(T value) {
        Node<T> current = root;

        while (current != nil) {
            int comparison = compare(value, current.value);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return current;
            }
        }

        return nil;
    }

    private Node<T> minimumNode(Node<T> node) {
        Node<T> current = node;

        while (current.left != nil) {
            current = current.left;
        }

        return current;
    }

    private Node<T> maximumNode(Node<T> node) {
        Node<T> current = node;

        while (current.right != nil) {
            current = current.right;
        }

        return current;
    }

    /*
     * ============================================================
     * Traversals
     * ============================================================
     */

    private void inOrder(Node<T> node, List<T> result) {
        if (node == nil) {
            return;
        }

        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    private void preOrder(Node<T> node, List<T> result) {
        if (node == nil) {
            return;
        }

        result.add(node.value);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    private void postOrder(Node<T> node, List<T> result) {
        if (node == nil) {
            return;
        }

        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.value);
    }

    private int height(Node<T> node) {
        if (node == nil) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    public int findMin() {
        if (root == nil) {
            throw new IllegalStateException("Tree is empty");
        }

        Node<T> current = root;
        while (current.left != nil) {
            current = current.left;
        }

        return (Integer) current.value;
    }

    public int findMax() {
        if (root == nil) {
            throw new IllegalStateException("Tree is empty");
        }

        Node<T> current = root;
        while (current.right != nil) {
            current = current.right;
        }

        return (Integer) current.value;
    }

    /*
     * ============================================================
     * Validation
     * ============================================================
     */

    private ValidationResult validate(
            Node<T> node,
            T exclusiveMinimum,
            T exclusiveMaximum,
            Node<T> expectedParent) {

        /*
         * NIL contributes one BLACK node to every path.
         */
        if (node == nil) {
            return ValidationResult.validNil();
        }

        if (node.parent != expectedParent) {
            return ValidationResult.invalid();
        }

        /*
         * Validate BST lower bound.
         */
        if (exclusiveMinimum != null
                && compare(node.value, exclusiveMinimum) <= 0) {
            return ValidationResult.invalid();
        }

        /*
         * Validate BST upper bound.
         */
        if (exclusiveMaximum != null
                && compare(node.value, exclusiveMaximum) >= 0) {
            return ValidationResult.invalid();
        }

        /*
         * A RED node cannot have a RED child.
         */
        if (node.color == Color.RED
                && (colorOf(node.left) == Color.RED
                || colorOf(node.right) == Color.RED)) {
            return ValidationResult.invalid();
        }

        ValidationResult leftResult = validate(
                node.left,
                exclusiveMinimum,
                node.value,
                node
        );

        if (!leftResult.valid) {
            return ValidationResult.invalid();
        }

        ValidationResult rightResult = validate(
                node.right,
                node.value,
                exclusiveMaximum,
                node
        );

        if (!rightResult.valid) {
            return ValidationResult.invalid();
        }

        /*
         * Both subtrees must have identical black-height.
         */
        if (leftResult.blackHeight != rightResult.blackHeight) {
            return ValidationResult.invalid();
        }

        int currentBlackContribution =
                node.color == Color.BLACK ? 1 : 0;

        int blackHeight =
                leftResult.blackHeight + currentBlackContribution;

        int nodeCount =
                1 + leftResult.nodeCount + rightResult.nodeCount;

        return new ValidationResult(
                true,
                blackHeight,
                nodeCount
        );
    }

    /*
     * ============================================================
     * Visualization
     * ============================================================
     */

    private void buildTreeString(
            Node<T> node,
            String prefix,
            boolean isTail,
            StringBuilder builder) {

        if (node.right != nil) {
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
                .append('(')
                .append(node.color == Color.RED ? 'R' : 'B')
                .append(')')
                .append(System.lineSeparator());

        if (node.left != nil) {
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
     * Color helpers
     * ============================================================
     */

    private Color colorOf(Node<T> node) {
        return node == nil ? Color.BLACK : node.color;
    }

    private void setColor(Node<T> node, Color color) {
        /*
         * NIL is permanently BLACK.
         *
         * Some textbook deletion cases conceptually recolor a NIL
         * sibling. With one shared sentinel, allowing that would
         * alter every NIL leaf, so it is intentionally ignored.
         */
        if (node != nil) {
            node.color = color;
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

        if (!(first instanceof Comparable<?> comparable)) {
            throw new IllegalStateException(
                    "No Comparator was provided and values do not "
                            + "implement Comparable"
            );
        }

        return ((Comparable<? super T>) comparable)
                .compareTo(second);
    }

    /*
     * ============================================================
     * Internal classes
     * ============================================================
     */

    private static final class Node<T> {

        private T value;
        private Color color;

        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        private Node(T value, Color color) {
            this.value = value;
            this.color = color;
        }
    }

    private static final class ValidationResult {

        private final boolean valid;
        private final int blackHeight;
        private final int nodeCount;

        private ValidationResult(
                boolean valid,
                int blackHeight,
                int nodeCount) {

            this.valid = valid;
            this.blackHeight = blackHeight;
            this.nodeCount = nodeCount;
        }

        private static ValidationResult validNil() {
            /*
             * NIL is BLACK, so its black-height is one.
             */
            return new ValidationResult(true, 1, 0);
        }

        private static ValidationResult invalid() {
            return new ValidationResult(false, 0, 0);
        }
    }
}