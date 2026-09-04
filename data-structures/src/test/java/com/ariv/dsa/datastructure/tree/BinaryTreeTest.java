package com.ariv.dsa.datastructure.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    @Test
    void shouldCreateEmptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
    }

    @Test
    void shouldSetRoot() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(10);
        assertEquals(10, tree.getRoot());
        assertEquals(1, tree.size());
    }

    @Test
    void shouldCalculateHeight() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(10);
        assertEquals(1, tree.height());

        tree.getRootNode().left = new TreeNode<>(5);
        assertEquals(2, tree.height());

        tree.getRootNode().right = new TreeNode<>(15);
        assertEquals(2, tree.height());

        tree.getRootNode().left.left = new TreeNode<>(3);
        assertEquals(3, tree.height());
    }

    @Test
    void shouldCountNodes() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(10);
        assertEquals(1, tree.countNodes());

        tree.getRootNode().left = new TreeNode<>(5);
        assertEquals(2, tree.countNodes());

        tree.getRootNode().right = new TreeNode<>(15);
        assertEquals(3, tree.countNodes());

        tree.getRootNode().left.left = new TreeNode<>(3);
        assertEquals(4, tree.countNodes());
    }

    @Test
    void shouldFindValue() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(10);
        tree.getRootNode().left = new TreeNode<>(5);
        tree.getRootNode().right = new TreeNode<>(15);
        tree.getRootNode().left.left = new TreeNode<>(3);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(20));
    }
}
