package com.ariv.dsa.datastructure.trie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    /*
     * ============================================================
     * Constructor
     * ============================================================
     */

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        void shouldCreateEmptyTrie() {

            Trie trie = new Trie();

            assertAll(
                    () -> assertTrue(trie.isEmpty()),
                    () -> assertEquals(0, trie.size())
            );
        }
    }

    /*
     * ============================================================
     * Insert Tests
     * ============================================================
     */

    @Nested
    @DisplayName("Insert Tests")
    class InsertTests {

        @Test
        void shouldInsertSingleWord() {

            Trie trie = new Trie();

            assertTrue(
                    trie.insert("apple")
            );

            assertAll(
                    () -> assertEquals(1, trie.size()),
                    () -> assertTrue(trie.contains("apple"))
            );
        }

        @Test
        void shouldInsertMultipleWords() {

            Trie trie = new Trie();

            trie.insert("app");
            trie.insert("apple");
            trie.insert("application");
            trie.insert("apply");

            assertEquals(4, trie.size());
        }

        @Test
        void shouldRejectDuplicateWords() {

            Trie trie = new Trie();

            assertTrue(
                    trie.insert("apple")
            );

            assertFalse(
                    trie.insert("apple")
            );

            assertEquals(1, trie.size());
        }

        @Test
        void shouldSupportSharedPrefixes() {

            Trie trie = new Trie();

            trie.insert("app");
            trie.insert("apple");
            trie.insert("application");
            trie.insert("apply");

            assertAll(
                    () -> assertTrue(trie.contains("app")),
                    () -> assertTrue(trie.contains("apple")),
                    () -> assertTrue(trie.contains("application")),
                    () -> assertTrue(trie.contains("apply"))
            );
        }
    }

    /*
     * ============================================================
     * Contains Tests
     * ============================================================
     */

    @Nested
    @DisplayName("Contains Tests")
    class ContainsTests {

        @Test
        void shouldFindExistingWord() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertTrue(
                    trie.contains("apple")
            );
        }

        @Test
        void shouldReturnFalseForMissingWord() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertFalse(
                    trie.contains("banana")
            );
        }

        @Test
        void shouldReturnFalseForPrefixOnly() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertFalse(
                    trie.contains("app")
            );
        }

        @Test
        void shouldReturnTrueWhenPrefixAlsoInserted() {

            Trie trie = new Trie();

            trie.insert("app");
            trie.insert("apple");

            assertTrue(
                    trie.contains("app")
            );

            assertTrue(
                    trie.contains("apple")
            );
        }
    }

    /*
     * ============================================================
     * StartsWith Tests
     * ============================================================
     */

    @Nested
    @DisplayName("StartsWith Tests")
    class StartsWithTests {

        @Test
        void shouldFindValidPrefix() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertTrue(
                    trie.startsWith("app")
            );
        }

        @Test
        void shouldReturnTrueForFullWord() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertTrue(
                    trie.startsWith("apple")
            );
        }

        @Test
        void shouldReturnFalseForUnknownPrefix() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertFalse(
                    trie.startsWith("xyz")
            );
        }

        @Test
        void shouldSupportSingleCharacterPrefix() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertTrue(
                    trie.startsWith("a")
            );
        }
    }

    /*
     * ============================================================
     * Size Tests
     * ============================================================
     */

    @Nested
    @DisplayName("Size Tests")
    class SizeTests {

        @Test
        void shouldIncreaseWhenNewWordInserted() {

            Trie trie = new Trie();

            trie.insert("apple");
            trie.insert("app");

            assertEquals(
                    2,
                    trie.size()
            );
        }

        @Test
        void shouldIgnoreDuplicateInsertions() {

            Trie trie = new Trie();

            trie.insert("apple");
            trie.insert("apple");

            assertEquals(
                    1,
                    trie.size()
            );
        }

        @Test
        void shouldReturnZeroForEmptyTrie() {

            Trie trie = new Trie();

            assertEquals(
                    0,
                    trie.size()
            );
        }
    }

    /*
     * ============================================================
     * IsEmpty Tests
     * ============================================================
     */

    @Nested
    @DisplayName("IsEmpty Tests")
    class IsEmptyTests {

        @Test
        void shouldReturnTrueForNewTrie() {

            Trie trie = new Trie();

            assertTrue(
                    trie.isEmpty()
            );
        }

        @Test
        void shouldReturnFalseAfterInsertion() {

            Trie trie = new Trie();

            trie.insert("apple");

            assertFalse(
                    trie.isEmpty()
            );
        }
    }

    /*
     * ============================================================
     * Clear Tests
     * ============================================================
     */

    @Nested
    @DisplayName("Clear Tests")
    class ClearTests {

        @Test
        void shouldClearEntireTrie() {

            Trie trie = new Trie();

            trie.insert("apple");
            trie.insert("app");
            trie.insert("apt");

            trie.clear();

            assertAll(
                    () -> assertTrue(trie.isEmpty()),
                    () -> assertEquals(0, trie.size()),
                    () -> assertFalse(trie.contains("apple")),
                    () -> assertFalse(trie.startsWith("a"))
            );
        }

        @Test
        void shouldAllowReuseAfterClear() {

            Trie trie = new Trie();

            trie.insert("apple");

            trie.clear();

            trie.insert("banana");

            assertAll(
                    () -> assertTrue(trie.contains("banana")),
                    () -> assertEquals(1, trie.size())
            );
        }
    }

    /*
     * ============================================================
     * Validation Tests
     * ============================================================
     */

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        void shouldRejectNullWordOnInsert() {

            Trie trie = new Trie();

            assertThrows(
                    IllegalArgumentException.class,
                    () -> trie.insert(null)
            );
        }

        @Test
        void shouldRejectBlankWordOnInsert() {

            Trie trie = new Trie();

            assertThrows(
                    IllegalArgumentException.class,
                    () -> trie.insert("")
            );
        }

        @Test
        void shouldRejectBlankContains() {

            Trie trie = new Trie();

            assertThrows(
                    IllegalArgumentException.class,
                    () -> trie.contains("")
            );
        }

        @Test
        void shouldRejectBlankPrefix() {

            Trie trie = new Trie();

            assertThrows(
                    IllegalArgumentException.class,
                    () -> trie.startsWith("")
            );
        }
    }

    @Test
    void shouldDeleteLeafWord() {

        Trie trie = new Trie();

        trie.insert("apple");

        assertTrue(
                trie.delete("apple")
        );

        assertFalse(
                trie.contains("apple")
        );

        assertEquals(
                0,
                trie.size()
        );
    }

    @Test
    void shouldDeleteAppleWithoutDeletingApp() {

        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("apple");

        trie.delete("apple");

        assertAll(
                () -> assertTrue(trie.contains("app")),
                () -> assertFalse(trie.contains("apple")),
                () -> assertEquals(1, trie.size())
        );
    }

    @Test
    void shouldReturnFalseForMissingWord() {

        Trie trie = new Trie();

        trie.insert("apple");

        assertFalse(
                trie.delete("banana")
        );
    }

    @Test
    void shouldDeleteBatchOnly() {

        Trie trie = new Trie();

        trie.insert("bat");
        trie.insert("batch");
        trie.insert("bath");

        trie.delete("batch");

        assertAll(
                () -> assertTrue(trie.contains("bat")),
                () -> assertTrue(trie.contains("bath")),
                () -> assertFalse(trie.contains("batch"))
        );
    }

    @Test
    void shouldReturnMatchingWords() {

        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("apple");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("apt");

        List<String> suggestions =
                trie.autoComplete("app");

        assertEquals(
                List.of(
                        "app",
                        "apple",
                        "application",
                        "apply"
                ),
                suggestions
        );
    }

    @Test
    void shouldReturnSingleMatch() {

        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("banana");

        assertEquals(
                List.of("banana"),
                trie.autoComplete("banana")
        );
    }

    @Test
    void shouldReturnEmptyListWhenPrefixDoesNotExist() {

        Trie trie = new Trie();

        trie.insert("apple");

        assertTrue(
                trie.autoComplete("xyz")
                        .isEmpty()
        );
    }

    @Test
    void shouldIncludePrefixWord() {

        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("apple");

        assertEquals(
                List.of(
                        "app",
                        "apple"
                ),
                trie.autoComplete("app")
        );
    }

    @Test
    void shouldNotReturnDeletedWord() {

        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("apple");
        trie.insert("apply");

        trie.delete("apple");

        assertEquals(
                List.of(
                        "app",
                        "apply"
                ),
                trie.autoComplete("app")
        );
    }

}