package com.ariv.dsa.datastructure.tree.avl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AVLTree")
class AVLTreeTest {

    private AVLTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree<>();
    }

    /*
     * ============================================================
     * Constructors
     * ============================================================
     */

    @Nested
    @DisplayName("Constructors")
    class ConstructorTests {

        @Test
        @DisplayName("should create an empty tree using natural ordering")
        void shouldCreateEmptyTreeUsingNaturalOrdering() {
            AVLTree<Integer> naturalOrderTree = new AVLTree<>();

            assertAll(
                    () -> assertTrue(naturalOrderTree.isEmpty()),
                    () -> assertEquals(0, naturalOrderTree.size()),
                    () -> assertEquals(0, naturalOrderTree.height()),
                    () -> assertTrue(naturalOrderTree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should create a tree using a custom comparator")
        void shouldCreateTreeUsingCustomComparator() {
            AVLTree<Integer> descendingTree =
                    new AVLTree<>(Comparator.reverseOrder());

            descendingTree.insert(10);
            descendingTree.insert(20);
            descendingTree.insert(5);

            assertEquals(
                    List.of(20, 10, 5),
                    descendingTree.inOrder()
            );

            assertTrue(descendingTree.isValidAVLTree());
        }

        @Test
        @DisplayName("should support non-comparable objects when comparator is provided")
        void shouldSupportNonComparableObjectsWithComparator() {
            Comparator<Employee> comparator =
                    Comparator.comparingInt(Employee::id);

            AVLTree<Employee> employeeTree =
                    new AVLTree<>(comparator);

            Employee employee30 = new Employee(30, "Swetha");
            Employee employee10 = new Employee(10, "Kamesh");
            Employee employee20 = new Employee(20, "Sandeep");

            employeeTree.insert(employee30);
            employeeTree.insert(employee10);
            employeeTree.insert(employee20);

            assertEquals(
                    List.of(employee10, employee20, employee30),
                    employeeTree.inOrder()
            );

            assertTrue(employeeTree.isValidAVLTree());
        }

        @Test
        @DisplayName("should fail when non-comparable objects are used without comparator")
        void shouldFailForNonComparableObjectsWithoutComparator() {
            AVLTree<Employee> employeeTree = new AVLTree<>();

            employeeTree.insert(new Employee(10, "Kamesh"));

            IllegalStateException exception =
                    assertThrows(
                            IllegalStateException.class,
                            () -> employeeTree.insert(
                                    new Employee(20, "Sandeep")
                            )
                    );

            assertTrue(
                    exception.getMessage()
                            .contains("do not implement Comparable")
            );
        }
    }

    /*
     * ============================================================
     * insert()
     * ============================================================
     */

    @Nested
    @DisplayName("insert")
    class InsertTests {

        @Test
        @DisplayName("should insert a value")
        void shouldInsertValue() {
            boolean inserted = tree.insert(10);

            assertAll(
                    () -> assertTrue(inserted),
                    () -> assertEquals(1, tree.size()),
                    () -> assertFalse(tree.isEmpty()),
                    () -> assertTrue(tree.contains(10)),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should insert multiple values")
        void shouldInsertMultipleValues() {
            assertTrue(tree.insert(30));
            assertTrue(tree.insert(20));
            assertTrue(tree.insert(40));
            assertTrue(tree.insert(10));
            assertTrue(tree.insert(25));

            assertAll(
                    () -> assertEquals(5, tree.size()),
                    () -> assertEquals(
                            List.of(10, 20, 25, 30, 40),
                            tree.inOrder()
                    ),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should not insert duplicate values")
        void shouldNotInsertDuplicateValues() {
            assertTrue(tree.insert(10));
            assertFalse(tree.insert(10));

            assertAll(
                    () -> assertEquals(1, tree.size()),
                    () -> assertEquals(List.of(10), tree.inOrder()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should reject a null value")
        void shouldRejectNullValueDuringInsert() {
            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> tree.insert(null)
                    );

            assertEquals(
                    "AVL tree does not support null values",
                    exception.getMessage()
            );

            assertTrue(tree.isEmpty());
        }

        @Test
        @DisplayName("should perform LL rotation")
        void shouldPerformLlRotation() {
            tree.insert(30);
            tree.insert(20);
            tree.insert(10);

            assertAll(
                    () -> assertEquals(
                            List.of(20, 10, 30),
                            tree.preOrder()
                    ),
                    () -> assertEquals(2, tree.height()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should perform RR rotation")
        void shouldPerformRrRotation() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            assertAll(
                    () -> assertEquals(
                            List.of(20, 10, 30),
                            tree.preOrder()
                    ),
                    () -> assertEquals(2, tree.height()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should perform LR rotation")
        void shouldPerformLrRotation() {
            tree.insert(30);
            tree.insert(10);
            tree.insert(20);

            assertAll(
                    () -> assertEquals(
                            List.of(20, 10, 30),
                            tree.preOrder()
                    ),
                    () -> assertEquals(2, tree.height()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should perform RL rotation")
        void shouldPerformRlRotation() {
            tree.insert(10);
            tree.insert(30);
            tree.insert(20);

            assertAll(
                    () -> assertEquals(
                            List.of(20, 10, 30),
                            tree.preOrder()
                    ),
                    () -> assertEquals(2, tree.height()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }
    }

    /*
     * ============================================================
     * add()
     * ============================================================
     */

    @Nested
    @DisplayName("add")
    class AddTests {

        @Test
        @DisplayName("should behave as an alias for insert")
        void shouldBehaveAsInsertAlias() {
            assertTrue(tree.add(10));
            assertFalse(tree.add(10));

            assertAll(
                    () -> assertEquals(1, tree.size()),
                    () -> assertTrue(tree.contains(10)),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should reject null")
        void shouldRejectNullValue() {
            assertThrows(
                    NullPointerException.class,
                    () -> tree.add(null)
            );
        }
    }

    /*
     * ============================================================
     * delete()
     * ============================================================
     */

    @Nested
    @DisplayName("delete")
    class DeleteTests {

        @Test
        @DisplayName("should delete a leaf node")
        void shouldDeleteLeafNode() {
            insertValues(20, 10, 30);

            boolean deleted = tree.delete(10);

            assertAll(
                    () -> assertTrue(deleted),
                    () -> assertFalse(tree.contains(10)),
                    () -> assertEquals(List.of(20, 30), tree.inOrder()),
                    () -> assertEquals(2, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should delete a node containing only a left child")
        void shouldDeleteNodeWithOnlyLeftChild() {
            insertValues(30, 20, 40, 10);

            assertTrue(tree.delete(20));

            assertAll(
                    () -> assertEquals(
                            List.of(10, 30, 40),
                            tree.inOrder()
                    ),
                    () -> assertFalse(tree.contains(20)),
                    () -> assertEquals(3, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should delete a node containing only a right child")
        void shouldDeleteNodeWithOnlyRightChild() {
            insertValues(30, 20, 40, 25);

            assertTrue(tree.delete(20));

            assertAll(
                    () -> assertEquals(
                            List.of(25, 30, 40),
                            tree.inOrder()
                    ),
                    () -> assertFalse(tree.contains(20)),
                    () -> assertEquals(3, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should delete a node containing two children")
        void shouldDeleteNodeWithTwoChildren() {
            insertValues(30, 20, 40, 10, 25, 35, 50);

            assertTrue(tree.delete(30));

            assertAll(
                    () -> assertFalse(tree.contains(30)),
                    () -> assertEquals(
                            List.of(10, 20, 25, 35, 40, 50),
                            tree.inOrder()
                    ),
                    () -> assertEquals(6, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should delete the root when root is the only node")
        void shouldDeleteSingleRootNode() {
            tree.insert(10);

            assertTrue(tree.delete(10));

            assertAll(
                    () -> assertTrue(tree.isEmpty()),
                    () -> assertEquals(0, tree.size()),
                    () -> assertEquals(0, tree.height()),
                    () -> assertEquals(List.of(), tree.inOrder()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should return false when value does not exist")
        void shouldReturnFalseWhenValueDoesNotExist() {
            insertValues(10, 20, 30);

            boolean deleted = tree.delete(100);

            assertAll(
                    () -> assertFalse(deleted),
                    () -> assertEquals(3, tree.size()),
                    () -> assertEquals(
                            List.of(10, 20, 30),
                            tree.inOrder()
                    ),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should return false when deleting from an empty tree")
        void shouldReturnFalseWhenDeletingFromEmptyTree() {
            assertFalse(tree.delete(10));

            assertAll(
                    () -> assertEquals(0, tree.size()),
                    () -> assertTrue(tree.isEmpty()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should reject null")
        void shouldRejectNullValue() {
            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> tree.delete(null)
                    );

            assertEquals(
                    "Value cannot be null",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("should rebalance tree after deletion")
        void shouldRebalanceAfterDeletion() {
            insertValues(9, 5, 10, 0, 6, 11, -1, 1, 2);

            assertTrue(tree.delete(10));

            assertAll(
                    () -> assertEquals(
                            List.of(-1, 0, 1, 2, 5, 6, 9, 11),
                            tree.inOrder()
                    ),
                    () -> assertEquals(8, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should decrement size only once when deleting node with two children")
        void shouldDecrementSizeOnceForTwoChildDeletion() {
            insertValues(20, 10, 30, 25, 40);

            assertEquals(5, tree.size());

            assertTrue(tree.delete(20));

            assertAll(
                    () -> assertEquals(4, tree.size()),
                    () -> assertFalse(tree.contains(20)),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }
    }

    /*
     * ============================================================
     * remove()
     * ============================================================
     */

    @Nested
    @DisplayName("remove")
    class RemoveTests {

        @Test
        @DisplayName("should behave as an alias for delete")
        void shouldBehaveAsDeleteAlias() {
            tree.insert(10);

            assertTrue(tree.remove(10));
            assertFalse(tree.remove(10));

            assertAll(
                    () -> assertTrue(tree.isEmpty()),
                    () -> assertEquals(0, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should reject null")
        void shouldRejectNullValue() {
            assertThrows(
                    NullPointerException.class,
                    () -> tree.remove(null)
            );
        }
    }

    /*
     * ============================================================
     * contains()
     * ============================================================
     */

    @Nested
    @DisplayName("contains")
    class ContainsTests {

        @Test
        @DisplayName("should return true when value exists")
        void shouldReturnTrueWhenValueExists() {
            insertValues(20, 10, 30);

            assertTrue(tree.contains(10));
            assertTrue(tree.contains(20));
            assertTrue(tree.contains(30));
        }

        @Test
        @DisplayName("should return false when value does not exist")
        void shouldReturnFalseWhenValueDoesNotExist() {
            insertValues(20, 10, 30);

            assertFalse(tree.contains(100));
        }

        @Test
        @DisplayName("should return false for an empty tree")
        void shouldReturnFalseForEmptyTree() {
            assertFalse(tree.contains(10));
        }

        @Test
        @DisplayName("should reject null")
        void shouldRejectNullValue() {
            assertThrows(
                    NullPointerException.class,
                    () -> tree.contains(null)
            );
        }
    }

    /*
     * ============================================================
     * search()
     * ============================================================
     */

    @Nested
    @DisplayName("search")
    class SearchTests {

        @Test
        @DisplayName("should return stored value when found")
        void shouldReturnStoredValueWhenFound() {
            insertValues(20, 10, 30);

            Optional<Integer> result = tree.search(10);

            assertEquals(Optional.of(10), result);
        }

        @Test
        @DisplayName("should return empty when value is not found")
        void shouldReturnEmptyWhenNotFound() {
            insertValues(20, 10, 30);

            assertEquals(Optional.empty(), tree.search(100));
        }

        @Test
        @DisplayName("should return empty for an empty tree")
        void shouldReturnEmptyForEmptyTree() {
            assertTrue(tree.search(10).isEmpty());
        }

        @Test
        @DisplayName("should return original stored object with comparator lookup")
        void shouldReturnOriginalStoredObject() {
            AVLTree<Employee> employeeTree =
                    new AVLTree<>(Comparator.comparingInt(Employee::id));

            Employee storedEmployee =
                    new Employee(20, "Sandeep");

            employeeTree.insert(storedEmployee);

            Optional<Employee> result =
                    employeeTree.search(new Employee(20, "Lookup key"));

            assertAll(
                    () -> assertTrue(result.isPresent()),
                    () -> assertSame(storedEmployee, result.orElseThrow())
            );
        }

        @Test
        @DisplayName("should reject null")
        void shouldRejectNullValue() {
            assertThrows(
                    NullPointerException.class,
                    () -> tree.search(null)
            );
        }
    }

    /*
     * ============================================================
     * min()
     * ============================================================
     */

    @Nested
    @DisplayName("min")
    class MinTests {

        @Test
        @DisplayName("should return minimum value")
        void shouldReturnMinimumValue() {
            insertValues(30, 10, 50, 5, 20);

            assertEquals(Optional.of(5), tree.min());
        }

        @Test
        @DisplayName("should return empty for an empty tree")
        void shouldReturnEmptyForEmptyTree() {
            assertEquals(Optional.empty(), tree.min());
        }

        @Test
        @DisplayName("should respect custom comparator")
        void shouldRespectCustomComparator() {
            AVLTree<Integer> descendingTree =
                    new AVLTree<>(Comparator.reverseOrder());

            descendingTree.insert(10);
            descendingTree.insert(30);
            descendingTree.insert(20);

            /*
             * Under reverse ordering, 30 is the smallest value
             * according to the comparator.
             */
            assertEquals(Optional.of(30), descendingTree.min());
        }
    }

    /*
     * ============================================================
     * max()
     * ============================================================
     */

    @Nested
    @DisplayName("max")
    class MaxTests {

        @Test
        @DisplayName("should return maximum value")
        void shouldReturnMaximumValue() {
            insertValues(30, 10, 50, 40, 60);

            assertEquals(Optional.of(60), tree.max());
        }

        @Test
        @DisplayName("should return empty for an empty tree")
        void shouldReturnEmptyForEmptyTree() {
            assertEquals(Optional.empty(), tree.max());
        }

        @Test
        @DisplayName("should respect custom comparator")
        void shouldRespectCustomComparator() {
            AVLTree<Integer> descendingTree =
                    new AVLTree<>(Comparator.reverseOrder());

            descendingTree.insert(10);
            descendingTree.insert(30);
            descendingTree.insert(20);

            /*
             * Under reverse ordering, 10 is the largest value
             * according to the comparator.
             */
            assertEquals(Optional.of(10), descendingTree.max());
        }
    }

    /*
     * ============================================================
     * size()
     * ============================================================
     */

    @Nested
    @DisplayName("size")
    class SizeTests {

        @Test
        @DisplayName("should initially return zero")
        void shouldInitiallyReturnZero() {
            assertEquals(0, tree.size());
        }

        @Test
        @DisplayName("should increase after successful insertion")
        void shouldIncreaseAfterInsertion() {
            tree.insert(10);
            tree.insert(20);

            assertEquals(2, tree.size());
        }

        @Test
        @DisplayName("should not increase after duplicate insertion")
        void shouldNotIncreaseAfterDuplicateInsertion() {
            tree.insert(10);
            tree.insert(10);

            assertEquals(1, tree.size());
        }

        @Test
        @DisplayName("should decrease after successful deletion")
        void shouldDecreaseAfterDeletion() {
            insertValues(10, 20);

            tree.delete(10);

            assertEquals(1, tree.size());
        }

        @Test
        @DisplayName("should not decrease after unsuccessful deletion")
        void shouldNotDecreaseAfterUnsuccessfulDeletion() {
            insertValues(10, 20);

            tree.delete(100);

            assertEquals(2, tree.size());
        }
    }

    /*
     * ============================================================
     * isEmpty()
     * ============================================================
     */

    @Nested
    @DisplayName("isEmpty")
    class IsEmptyTests {

        @Test
        @DisplayName("should return true for a new tree")
        void shouldReturnTrueForNewTree() {
            assertTrue(tree.isEmpty());
        }

        @Test
        @DisplayName("should return false after insertion")
        void shouldReturnFalseAfterInsertion() {
            tree.insert(10);

            assertFalse(tree.isEmpty());
        }

        @Test
        @DisplayName("should return true after deleting all elements")
        void shouldReturnTrueAfterDeletingAllElements() {
            tree.insert(10);
            tree.delete(10);

            assertTrue(tree.isEmpty());
        }
    }

    /*
     * ============================================================
     * height()
     * ============================================================
     */

    @Nested
    @DisplayName("height")
    class HeightTests {

        @Test
        @DisplayName("should return zero for an empty tree")
        void shouldReturnZeroForEmptyTree() {
            assertEquals(0, tree.height());
        }

        @Test
        @DisplayName("should return one for a leaf node")
        void shouldReturnOneForLeafNode() {
            tree.insert(10);

            assertEquals(1, tree.height());
        }

        @Test
        @DisplayName("should remain logarithmic after sorted insertion")
        void shouldRemainBalancedAfterSortedInsertion() {
            for (int value = 1; value <= 15; value++) {
                tree.insert(value);
            }

            assertAll(
                    () -> assertEquals(4, tree.height()),
                    () -> assertEquals(15, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should update after deletions")
        void shouldUpdateAfterDeletion() {
            insertValues(20, 10, 30);

            assertEquals(2, tree.height());

            tree.delete(10);
            tree.delete(30);

            assertEquals(1, tree.height());
        }
    }

    /*
     * ============================================================
     * clear()
     * ============================================================
     */

    @Nested
    @DisplayName("clear")
    class ClearTests {

        @Test
        @DisplayName("should remove all values")
        void shouldRemoveAllValues() {
            insertValues(10, 20, 30, 40, 50);

            tree.clear();

            assertAll(
                    () -> assertTrue(tree.isEmpty()),
                    () -> assertEquals(0, tree.size()),
                    () -> assertEquals(0, tree.height()),
                    () -> assertEquals(List.of(), tree.inOrder()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should be safe on an empty tree")
        void shouldBeSafeOnEmptyTree() {
            tree.clear();

            assertAll(
                    () -> assertTrue(tree.isEmpty()),
                    () -> assertEquals(0, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should allow insertion after clearing")
        void shouldAllowInsertionAfterClear() {
            insertValues(10, 20, 30);

            tree.clear();
            tree.insert(100);

            assertAll(
                    () -> assertEquals(1, tree.size()),
                    () -> assertEquals(List.of(100), tree.inOrder()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }
    }

    /*
     * ============================================================
     * inOrder()
     * ============================================================
     */

    @Nested
    @DisplayName("inOrder")
    class InOrderTests {

        @Test
        @DisplayName("should return values in sorted order")
        void shouldReturnValuesInSortedOrder() {
            insertValues(30, 20, 40, 10, 25, 50);

            assertEquals(
                    List.of(10, 20, 25, 30, 40, 50),
                    tree.inOrder()
            );
        }

        @Test
        @DisplayName("should return empty list for an empty tree")
        void shouldReturnEmptyListForEmptyTree() {
            assertEquals(List.of(), tree.inOrder());
        }

        @Test
        @DisplayName("should return a new list for each invocation")
        void shouldReturnNewListForEachInvocation() {
            insertValues(10, 20, 30);

            List<Integer> first = tree.inOrder();
            List<Integer> second = tree.inOrder();

            assertNotSame(first, second);
            assertEquals(first, second);
        }
    }

    /*
     * ============================================================
     * preOrder()
     * ============================================================
     */

    @Nested
    @DisplayName("preOrder")
    class PreOrderTests {

        @Test
        @DisplayName("should return root-left-right order")
        void shouldReturnRootLeftRightOrder() {
            insertValues(30, 20, 40, 10, 25, 50);

            assertEquals(
                    List.of(30, 20, 10, 25, 40, 50),
                    tree.preOrder()
            );
        }

        @Test
        @DisplayName("should return empty list for an empty tree")
        void shouldReturnEmptyListForEmptyTree() {
            assertEquals(List.of(), tree.preOrder());
        }
    }

    /*
     * ============================================================
     * postOrder()
     * ============================================================
     */

    @Nested
    @DisplayName("postOrder")
    class PostOrderTests {

        @Test
        @DisplayName("should return left-right-root order")
        void shouldReturnLeftRightRootOrder() {
            insertValues(30, 20, 40, 10, 25, 50);

            assertEquals(
                    List.of(10, 25, 20, 50, 40, 30),
                    tree.postOrder()
            );
        }

        @Test
        @DisplayName("should return empty list for an empty tree")
        void shouldReturnEmptyListForEmptyTree() {
            assertEquals(List.of(), tree.postOrder());
        }
    }

    /*
     * ============================================================
     * levelOrder()
     * ============================================================
     */

    @Nested
    @DisplayName("levelOrder")
    class LevelOrderTests {

        @Test
        @DisplayName("should return breadth-first order")
        void shouldReturnBreadthFirstOrder() {
            insertValues(30, 20, 40, 10, 25, 50);

            assertEquals(
                    List.of(30, 20, 40, 10, 25, 50),
                    tree.levelOrder()
            );
        }

        @Test
        @DisplayName("should return empty list for an empty tree")
        void shouldReturnEmptyListForEmptyTree() {
            assertEquals(List.of(), tree.levelOrder());
        }

        @Test
        @DisplayName("should return single value for a one-node tree")
        void shouldReturnSingleValueForOneNodeTree() {
            tree.insert(10);

            assertEquals(List.of(10), tree.levelOrder());
        }
    }

    /*
     * ============================================================
     * levelOrderByLevel()
     * ============================================================
     */

    @Nested
    @DisplayName("levelOrderByLevel")
    class LevelOrderByLevelTests {

        @Test
        @DisplayName("should group values by tree level")
        void shouldGroupValuesByLevel() {
            insertValues(30, 20, 40, 10, 25, 50);

            assertEquals(
                    List.of(
                            List.of(30),
                            List.of(20, 40),
                            List.of(10, 25, 50)
                    ),
                    tree.levelOrderByLevel()
            );
        }

        @Test
        @DisplayName("should return empty list for an empty tree")
        void shouldReturnEmptyListForEmptyTree() {
            assertEquals(List.of(), tree.levelOrderByLevel());
        }

        @Test
        @DisplayName("should return one level for a single-node tree")
        void shouldReturnOneLevelForSingleNodeTree() {
            tree.insert(10);

            assertEquals(
                    List.of(List.of(10)),
                    tree.levelOrderByLevel()
            );
        }
    }

    /*
     * ============================================================
     * isValidAVLTree()
     * ============================================================
     */

    @Nested
    @DisplayName("isValidAVLTree")
    class IsValidAVLTreeTests {

        @Test
        @DisplayName("should return true for an empty tree")
        void shouldReturnTrueForEmptyTree() {
            assertTrue(tree.isValidAVLTree());
        }

        @Test
        @DisplayName("should return true after every insertion")
        void shouldReturnTrueAfterEveryInsertion() {
            int[] values = {
                    50, 25, 75, 10, 30,
                    60, 90, 5, 15, 27,
                    35, 55, 65, 85, 95
            };

            for (int value : values) {
                tree.insert(value);

                assertTrue(
                        tree.isValidAVLTree(),
                        "Tree became invalid after inserting " + value
                );
            }
        }

        @Test
        @DisplayName("should return true after every deletion")
        void shouldReturnTrueAfterEveryDeletion() {
            insertValues(
                    50, 25, 75, 10, 30,
                    60, 90, 5, 15, 27,
                    35, 55, 65, 85, 95
            );

            int[] deletionOrder = {
                    5, 15, 10, 25, 75,
                    50, 95, 90, 85, 60,
                    65, 55, 30, 27, 35
            };

            for (int value : deletionOrder) {
                assertTrue(tree.delete(value));

                assertTrue(
                        tree.isValidAVLTree(),
                        "Tree became invalid after deleting " + value
                );
            }

            assertTrue(tree.isEmpty());
        }

        @Test
        @DisplayName("should remain valid during mixed operations")
        void shouldRemainValidDuringMixedOperations() {
            tree.insert(30);
            tree.insert(20);
            tree.insert(40);
            tree.delete(20);
            tree.insert(10);
            tree.insert(5);
            tree.delete(40);
            tree.insert(50);

            assertAll(
                    () -> assertEquals(
                            List.of(5, 10, 30, 50),
                            tree.inOrder()
                    ),
                    () -> assertEquals(4, tree.size()),
                    () -> assertTrue(tree.isValidAVLTree())
            );
        }
    }

    /*
     * ============================================================
     * toTreeString()
     * ============================================================
     */

    @Nested
    @DisplayName("toTreeString")
    class ToTreeStringTests {

        @Test
        @DisplayName("should return empty marker for an empty tree")
        void shouldReturnEmptyMarkerForEmptyTree() {
            assertEquals("[empty]", tree.toTreeString());
        }

        @Test
        @DisplayName("should include values, heights, and balance factors")
        void shouldIncludeNodeMetadata() {
            insertValues(20, 10, 30);

            String result = tree.toTreeString();

            assertAll(
                    () -> assertTrue(result.contains("20")),
                    () -> assertTrue(result.contains("10")),
                    () -> assertTrue(result.contains("30")),
                    () -> assertTrue(result.contains("[h=2, bf=0]")),
                    () -> assertTrue(result.contains("[h=1, bf=0]"))
            );
        }

        @Test
        @DisplayName("should contain one line per node")
        void shouldContainOneLinePerNode() {
            insertValues(20, 10, 30);

            String[] lines =
                    tree.toTreeString().lines().toArray(String[]::new);

            assertEquals(3, lines.length);
        }
    }

    /*
     * ============================================================
     * toString()
     * ============================================================
     */

    @Nested
    @DisplayName("toString")
    class ToStringTests {

        @Test
        @DisplayName("should return in-order values as a string")
        void shouldReturnInOrderValuesAsString() {
            insertValues(30, 10, 20);

            assertEquals("[10, 20, 30]", tree.toString());
        }

        @Test
        @DisplayName("should return empty list representation for empty tree")
        void shouldReturnEmptyListForEmptyTree() {
            assertEquals("[]", tree.toString());
        }
    }

    /*
     * ============================================================
     * Comparator behavior
     * ============================================================
     */

    @Nested
    @DisplayName("Custom comparator behavior")
    class ComparatorTests {

        @Test
        @DisplayName("should detect duplicates using comparator equality")
        void shouldDetectDuplicatesUsingComparatorEquality() {
            AVLTree<Employee> employeeTree =
                    new AVLTree<>(Comparator.comparingInt(Employee::id));

            Employee first = new Employee(10, "Kamesh");
            Employee duplicateId = new Employee(10, "Different Name");

            assertTrue(employeeTree.insert(first));
            assertFalse(employeeTree.insert(duplicateId));

            assertAll(
                    () -> assertEquals(1, employeeTree.size()),
                    () -> assertSame(
                            first,
                            employeeTree.search(duplicateId).orElseThrow()
                    ),
                    () -> assertTrue(employeeTree.isValidAVLTree())
            );
        }

        @Test
        @DisplayName("should delete an object using comparator identity")
        void shouldDeleteUsingComparatorIdentity() {
            AVLTree<Employee> employeeTree =
                    new AVLTree<>(Comparator.comparingInt(Employee::id));

            employeeTree.insert(new Employee(10, "Kamesh"));
            employeeTree.insert(new Employee(20, "Sandeep"));

            boolean deleted =
                    employeeTree.delete(
                            new Employee(10, "Search key")
                    );

            assertAll(
                    () -> assertTrue(deleted),
                    () -> assertEquals(1, employeeTree.size()),
                    () -> assertFalse(
                            employeeTree.contains(
                                    new Employee(10, "Another key")
                            )
                    ),
                    () -> assertTrue(employeeTree.isValidAVLTree())
            );
        }
    }

    /*
     * ============================================================
     * Test helpers
     * ============================================================
     */

    private void insertValues(int... values) {
        for (int value : values) {
            tree.insert(value);
        }
    }

    private record Employee(int id, String name) {
    }
}