package com.ariv.dsa.datastructure.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BinarySearchTreeTest {

    @Test
    void shouldCreateEmptyTree() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertThat(tree.isEmpty()).isTrue();
        assertThat(tree.size()).isZero();
        assertThat(tree.height()).isZero();
    }

    @Test
    void shouldInsertRoot() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertThat(tree.insert(50)).isTrue();

        assertThat(tree.size()).isEqualTo(1);
        assertThat(tree.contains(50)).isTrue();
        assertThat(tree.minimum()).isEqualTo(50);
        assertThat(tree.maximum()).isEqualTo(50);
    }

    @Test
    void shouldInsertValuesUsingBstOrdering() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        assertThat(tree.size()).isEqualTo(7);
        assertThat(tree.contains(20)).isTrue();
        assertThat(tree.contains(40)).isTrue();
        assertThat(tree.contains(60)).isTrue();
    }

    @Test
    void shouldFindExistingValues() {

        BinarySearchTree<Integer> tree = createTree();

        assertThat(tree.contains(20)).isTrue();
        assertThat(tree.contains(50)).isTrue();
        assertThat(tree.contains(80)).isTrue();
    }

    private BinarySearchTree<Integer> createTree() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        return tree;
    }

    @Test
    void shouldReturnFalseForMissingValue() {

        BinarySearchTree<Integer> tree = createTree();

        assertThat(tree.contains(35)).isFalse();
    }

    @Test
    void shouldRejectDuplicateValues() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertThat(tree.insert(50)).isTrue();
        assertThat(tree.insert(50)).isFalse();

        assertThat(tree.size()).isEqualTo(1);

    }

    @Test
    void shouldFindMinimumAndMaximum() {

        BinarySearchTree<Integer> tree = createTree();

        assertThat(tree.minimum()).isEqualTo(20);
        assertThat(tree.maximum()).isEqualTo(80);
    }

    @Test
    void shouldCalculateHeight() {

        BinarySearchTree<Integer> tree = createTree();

        assertThat(tree.height()).isEqualTo(3);
    }

    @Test
    void shouldThrowWhenFindingMinimumOfEmptyTree() {

        BinarySearchTree<Integer> tree =
                new BinarySearchTree<>();

        assertThatThrownBy(tree::minimum)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Tree is empty");
    }

    @Test
    void shouldThrowWhenFindingMaximumOfEmptyTree() {

        BinarySearchTree<Integer> tree =
                new BinarySearchTree<>();

        assertThatThrownBy(tree::maximum)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Tree is empty");
    }

    @Test
    void shouldRejectNullValue() {

        BinarySearchTree<Integer> tree =
                new BinarySearchTree<>();

        assertThatThrownBy(() -> tree.insert(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value cannot be null");
    }
}
