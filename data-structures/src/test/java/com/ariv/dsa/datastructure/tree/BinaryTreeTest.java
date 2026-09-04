package com.ariv.dsa.datastructure.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    /**
     * Let us build this tree
     *
     *           A
     *         /   \
     *        B     C
     *       / \   / \
     *      D   E F   G
     */
    @Test
    void shouldTraverseInOrder() {
        BinaryTree<Character> tree = new BinaryTree<>();
        tree.setRoot('A');
        tree.getRootNode().left = new TreeNode<>('B');
        tree.getRootNode().right = new TreeNode<>('C');
        tree.getRootNode().left.left = new TreeNode<>('D');
        tree.getRootNode().left.right = new TreeNode<>('E');
        tree.getRootNode().right.left = new TreeNode<>('F');
        tree.getRootNode().right.right = new TreeNode<>('G');

        List<Character> result = tree.preOrder();
        assertEquals(List.of('A', 'B', 'D', 'E', 'C', 'F', 'G'), result);

        List<Character> result2 = tree.inOrder();
        assertEquals(List.of('D', 'B', 'E', 'A', 'F', 'C', 'G'), result2);

        List<Character> result3 = tree.postOrder();
        assertEquals(List.of('D', 'E', 'B', 'F', 'G', 'C', 'A'), result3);

        List<Character> result4 = tree.levelOrder();
        assertEquals(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'), result4);
    }

}
