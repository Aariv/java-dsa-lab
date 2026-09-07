package com.ariv.dsa.datastructure.btree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BPlusTree Phase 10.3 insertion")
class BPlusTreeTest {

    private BPlusTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BPlusTree<>(4);
    }

    @Nested
    @DisplayName("insert")
    class InsertTests {

        @Test
        @DisplayName("should insert the first value into a leaf root")
        void shouldInsertFirstValueIntoLeafRoot() {
            boolean inserted = tree.insert(10);

            assertAll(
                    () -> assertTrue(inserted),
                    () -> assertTrue(tree.contains(10)),
                    () -> assertEquals(1, tree.size()),
                    () -> assertFalse(tree.isEmpty())
            );
        }

        @Test
        @DisplayName("should insert values in sorted order")
        void shouldInsertValuesInSortedOrder() {
            tree.insert(30);
            tree.insert(10);
            tree.insert(20);
            tree.insert(40);

            LeafNode<Integer> rootLeaf =
                    tree.findLeaf(10);

            assertAll(
                    () -> assertEquals(
                            List.of(10, 20, 30, 40),
                            rootLeaf.getKeys()
                    ),
                    () -> assertEquals(4, tree.size())
            );
        }

        @Test
        @DisplayName("should insert at the beginning")
        void shouldInsertAtBeginning() {
            tree.insert(20);
            tree.insert(30);
            tree.insert(10);

            assertEquals(
                    List.of(10, 20, 30),
                    tree.findLeaf(10).getKeys()
            );
        }

        @Test
        @DisplayName("should insert between existing values")
        void shouldInsertBetweenExistingValues() {
            tree.insert(10);
            tree.insert(30);
            tree.insert(20);

            assertEquals(
                    List.of(10, 20, 30),
                    tree.findLeaf(20).getKeys()
            );
        }

        @Test
        @DisplayName("should insert at the end")
        void shouldInsertAtEnd() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            assertEquals(
                    List.of(10, 20, 30),
                    tree.findLeaf(30).getKeys()
            );
        }

        @Test
        @DisplayName("should reject a duplicate")
        void shouldRejectDuplicate() {
            assertTrue(tree.insert(20));
            assertFalse(tree.insert(20));

            assertAll(
                    () -> assertEquals(1, tree.size()),
                    () -> assertEquals(
                            List.of(20),
                            tree.findLeaf(20).getKeys()
                    )
            );
        }

        @Test
        @DisplayName("should not modify size after duplicate insertion")
        void shouldNotModifySizeAfterDuplicateInsertion() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            assertEquals(3, tree.size());

            tree.insert(20);

            assertEquals(3, tree.size());
        }

        @Test
        @DisplayName("should reject null")
        void shouldRejectNull() {
            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> tree.insert(null)
                    );

            assertEquals(
                    "B+ Tree does not support null values",
                    exception.getMessage()
            );

            assertTrue(tree.isEmpty());
        }

        @Test
        @DisplayName("should temporarily allow leaf overflow")
        void shouldTemporarilyAllowLeafOverflow() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(40);

            LeafNode<Integer> leaf =
                    tree.findLeaf(40);

            assertAll(
                    () -> assertEquals(3, tree.maximumKeys()),
                    () -> assertEquals(4, leaf.getKeys().size()),
                    () -> assertEquals(
                            List.of(10, 20, 30, 40),
                            leaf.getKeys()
                    )
            );
        }
    }

    @Nested
    @DisplayName("add")
    class AddTests {

        @Test
        @DisplayName("should behave as an alias for insert")
        void shouldBehaveAsInsertAlias() {
            assertTrue(tree.add(10));
            assertFalse(tree.add(10));

            assertAll(
                    () -> assertTrue(tree.contains(10)),
                    () -> assertEquals(1, tree.size())
            );
        }
    }

    @Nested
    @DisplayName("contains after insertion")
    class ContainsTests {

        @Test
        @DisplayName("should find every inserted value")
        void shouldFindEveryInsertedValue() {
            tree.insert(30);
            tree.insert(10);
            tree.insert(20);
            tree.insert(40);

            assertAll(
                    () -> assertTrue(tree.contains(10)),
                    () -> assertTrue(tree.contains(20)),
                    () -> assertTrue(tree.contains(30)),
                    () -> assertTrue(tree.contains(40))
            );
        }

        @Test
        @DisplayName("should return false for a missing value")
        void shouldReturnFalseForMissingValue() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            assertFalse(tree.contains(25));
        }
    }

    @Nested
    @DisplayName("size and empty state")
    class StateTests {

        @Test
        @DisplayName("should initially be empty")
        void shouldInitiallyBeEmpty() {
            assertAll(
                    () -> assertTrue(tree.isEmpty()),
                    () -> assertEquals(0, tree.size())
            );
        }

        @Test
        @DisplayName("should track successful insertions")
        void shouldTrackSuccessfulInsertions() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            assertAll(
                    () -> assertFalse(tree.isEmpty()),
                    () -> assertEquals(3, tree.size())
            );
        }
    }

    @Nested
    @DisplayName("tree configuration")
    class ConfigurationTests {

        @Test
        @DisplayName("should expose order and maximum key count")
        void shouldExposeTreeConfiguration() {
            assertAll(
                    () -> assertEquals(4, tree.order()),
                    () -> assertEquals(3, tree.maximumKeys())
            );
        }
    }
}