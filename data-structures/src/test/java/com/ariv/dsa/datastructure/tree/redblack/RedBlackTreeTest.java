package com.ariv.dsa.datastructure.tree.redblack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTest {

    RedBlackTree<Integer> tree = new RedBlackTree<>();

    @BeforeEach
    void setupTree() {

        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(50);
    }

    @Test
    void shouldCreateEmptyTree() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());

        assertTrue(
                tree.isValidRedBlackTree(),
                "RedBlackTree validation failed"
        );
    }

    @Test
    void shouldInsertSingleValue() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);

        assertEquals(1, tree.size());
        assertEquals(1, tree.height());

        assertTrue(
                tree.isValidRedBlackTree(),
                "RedBlackTree validation failed"
        );
    }

    @Test
    void shouldRejectDuplicates() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(10);

        assertEquals(1, tree.size());
        assertEquals(1, tree.height());

        assertTrue(
                tree.isValidRedBlackTree(),
                "RedBlackTree validation failed"
        );
    }

    @Test
    void shouldRemainValidAfterAscendingInsertions() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
            assertTrue(
                    tree.isValidRedBlackTree(),
                    "RedBlackTree validation failed after inserting: " + i
            );
        }
    }

    @Test
    void shouldRemainValidAfterDescendingInsertions() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for (int i = 100; i >= 1; i--) {
            tree.insert(i);
            assertTrue(
                    tree.isValidRedBlackTree(),
                    "RedBlackTree validation failed after inserting: " + i
            );
        }
    }

    @Test
    void shouldFindExistingValue() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assertTrue(tree.contains(20));
    }

    @Test
    void shouldReturnEmptyForUnknownValue() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assertFalse(tree.contains(40));
    }

    @Test
    void shouldCheckContains() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }

        for (int i = 1; i <= 100; i++) {
            assertTrue(tree.contains(i), "Tree should contain: " + i);
        }

        assertFalse(tree.contains(101), "Tree should not contain: 101");
    }

    @Test
    void shouldFindMinimum() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertEquals(5, tree.findMin());
    }

    @Test
    void shouldFindMaximum() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertEquals(15, tree.findMax());
    }

    @Test
    void shouldReturnSortedOrder() {

        assertEquals(
                List.of(10,20,25,30,40,50),
                tree.inOrder()
        );

        assertValidTree();
    }

    @Test
    void shouldReturnPreOrderTraversal() {
        assertFalse(
                tree.preOrder().isEmpty()
        );

        assertValidTree();
    }

    @Test
    void shouldReturnPostOrderTraversal() {

        assertFalse(
                tree.postOrder().isEmpty()
        );

        assertValidTree();
    }

    @Test
    void shouldReturnLevelOrderTraversal() {

        assertFalse(
                tree.levelOrder().isEmpty()
        );

        assertValidTree();
    }

    @Test
    void shouldDeleteLeafNode() {

        tree.delete(10);

        assertFalse(tree.contains(10));

        assertValidTree();
    }

    @Test
    void shouldDeleteNodeWithOneChild() {

        tree.insert(60);

        tree.delete(50);

        assertFalse(tree.contains(50));

        assertValidTree();
    }

    @Test
    void shouldDeleteNodeWithTwoChildren() {

        tree.delete(30);

        assertFalse(tree.contains(30));

        assertValidTree();
    }

    @Test
    void shouldDeleteRoot() {

        tree.delete(30);

        assertValidTree();
    }

    @Test
    void shouldDeleteEverything() {

        List<Integer> values = tree.inOrder();

        for (Integer value : values) {
            tree.delete(value);
        }

        assertTrue(tree.isEmpty());

        assertValidTree();
    }

    @Test
    void shouldTrackSize() {

        tree.insert(1);
        tree.insert(2);

        assertEquals(2, tree.size());

        tree.delete(1);

        assertEquals(1, tree.size());

        assertValidTree();
    }

    @Test
    void shouldClearTree() {

        tree.insert(1);
        tree.insert(2);

        tree.clear();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        assertValidTree();
    }

    @Test
    void rootMustAlwaysBeBlack() {

        for (int i = 1; i <= 100; i++) {
            tree.insert(i);

            assertValidTree();
        }
    }

    @Test
    void shouldMaintainAllRedBlackProperties() {

        for (int i = 1; i <= 1000; i++) {
            tree.insert(i);
        }

        assertValidTree();
    }

    @Test
    void shouldRemainValidAfterMassDeletion() {

        for (int i = 1; i <= 1000; i++) {
            tree.insert(i);
        }

        for (int i = 1; i <= 1000; i++) {
            tree.delete(i);

            assertValidTree();
        }

        assertTrue(tree.isEmpty());
    }

    record Employee(
            int id,
            String name
    ) {}

    @Test
    void shouldUseCustomComparator() {

        RedBlackTree<Employee> employees =
                new RedBlackTree<>(
                        Comparator.comparingInt(
                                Employee::id
                        )
                );

        employees.insert(
                new Employee(3, "Swetha")
        );

        employees.insert(
                new Employee(1, "Kamesh")
        );

        employees.insert(
                new Employee(2, "Sandeep")
        );

        assertEquals(
                3,
                employees.size()
        );

        assertTrue(
                employees.search(
                        new Employee(2, "")
                ).isPresent()
        );

        assertTrue(
                employees.isValidRedBlackTree()
        );
    }

    @Test
    void randomizedInsertDeleteShouldStayValid() {

        Random random = new Random(42);

        Set<Integer> expected =
                new HashSet<>();

        for (int i = 0; i < 5000; i++) {

            int value =
                    random.nextInt(1000);

            if (random.nextBoolean()) {

                tree.insert(value);
                expected.add(value);

            } else {

                tree.delete(value);
                expected.remove(value);
            }

            assertValidTree();
        }

        assertEquals(
                expected.size(),
                tree.size()
        );
    }

    private void assertValidTree() {
        assertTrue(
                tree.isValidRedBlackTree(),
                "RedBlackTree validation failed"
        );
    }
}
