package com.ariv.dsa.datastructure.trie;

import java.util.*;

/**
 * A Trie (prefix tree) implementation for storing and searching strings.
 */
public class Trie {

    /**
     * The root node of the Trie.
     */
    private final TrieNode root;
    /**
     * The number of words stored in the Trie.
     */
    private int size;

    /**
     * Constructs an empty Trie.
     */
    public Trie() {
        this.root = new TrieNode();
        this.size = 0;
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word the word to insert
     * @return true if the word was inserted, false if it already exists
     * @throws IllegalArgumentException if the word is null or empty
     */
    boolean insert(String word) {
        validateWord(word);
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        if (!current.endOfWord) {
            current.endOfWord = true;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Validates that the given word is not null or empty.
     *
     * @param word the word to validate
     * @throws IllegalArgumentException if the word is null or empty
     */
    private void validateWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
    }

    /**
     * Checks if the Trie contains the given word.
     *
     * @param word the word to check
     * @return true if the Trie contains the word, false otherwise
     * @throws IllegalArgumentException if the word is null or empty
     */
    boolean contains(String word) {
        validateWord(word);
        TrieNode node = findNode(word);
        return node != null && node.endOfWord;
    }

    /**
     * Finds the node corresponding to the given word in the Trie.
     *
     * @param word the word to find
     * @return the TrieNode corresponding to the last character of the word, or null if not found
     */
    private TrieNode findNode(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     *
     * @param prefix the prefix to check
     * @return true if there is a word that starts with the prefix, false otherwise
     * @throws IllegalArgumentException if the prefix is null or empty
     */
    boolean startsWith(String prefix) {
        validateWord(prefix);
        return findNode(prefix) != null;
    }

    /**
     * Deletes a word from the Trie.
     *
     * @param word the word to delete
     * @return true if the word was deleted, false if it did not exist
     * @throws IllegalArgumentException if the word is null or empty
     */
//    boolean delete(String word) {
//        validateWord(word);
//        if (deleteNode(root, word, 0)) {
//            size--;
//            return true;
//        }
//        return false;
//    }

    /**
     * Recursively deletes a word from the Trie starting from the given node.
     *
     * @param root the current TrieNode
     * @param word the word to delete
     * @param i    the current index in the word
     * @return true if the current node should be deleted, false otherwise
     */
//    private boolean deleteNode(TrieNode root, String word, int i) {
//        if (i == word.length()) {
//            if (!root.endOfWord) {
//                return false;
//            }
//            root.endOfWord = false;
//            return root.children.isEmpty();
//        }
//        char ch = word.charAt(i);
//        TrieNode node = root.children.get(ch);
//        if (node == null) {
//            return false;
//        }
//        boolean shouldDeleteCurrentNode = deleteNode(node, word, i + 1);
//        if (shouldDeleteCurrentNode) {
//            root.children.remove(ch);
//            return root.children.isEmpty() && !root.endOfWord;
//        }
//        return false;
//    }

    /**
     * Deletes a word from the Trie.
     *
     * @param word the word to delete
     * @return true if the word was deleted, false if it did not exist
     * @throws IllegalArgumentException if the word is null or empty
     */
    public boolean delete(String word) {

        validateWord(word);

        if (!contains(word)) {
            return false;
        }

        delete(root, word, 0);

        size--;

        return true;
    }

    /**
     * Recursively deletes a word from the Trie starting from the given node.
     *
     * @param current the current TrieNode
     * @param word    the word to delete
     * @param index   the current index in the word
     * @return true if the current node should be deleted, false otherwise
     */
    private boolean delete(
            TrieNode current,
            String word,
            int index) {

        /*
         * Reached last character
         */
        if (index == word.length()) {

            current.endOfWord = false;

            return current.children.isEmpty();
        }

        char character =
                word.charAt(index);

        TrieNode child =
                current.children.get(character);

        if (child == null) {
            return false;
        }

        boolean shouldRemoveChild =
                delete(
                        child,
                        word,
                        index + 1
                );

        if (shouldRemoveChild) {

            current.children.remove(character);

            return !current.endOfWord
                    && current.children.isEmpty();
        }

        return false;
    }

    /**
     * Returns a list of all words in the Trie that start with the given prefix.
     *
     * @param prefix the prefix to search for
     * @return a list of words that start with the prefix
     * @throws IllegalArgumentException if the prefix is null or empty
     */
    public List<String> autoComplete(String prefix) {

        validateWord(prefix);

        TrieNode prefixNode =
                findNode(prefix);

        if (prefixNode == null) {
            return List.of();
        }

        List<String> suggestions =
                new ArrayList<>();

        collectWords(
                prefixNode,
                new StringBuilder(prefix),
                suggestions
        );

        return suggestions;
    }

    /**
     * Recursively collects all words in the Trie starting from the given node.
     *
     * @param current          the current TrieNode
     * @param currentWord a StringBuilder to build the current word
     * @param results       a list to store the collected words
     */
    private void collectWords(
            TrieNode current,
            StringBuilder currentWord,
            List<String> results) {

        if (current.endOfWord) {
            results.add(
                    currentWord.toString()
            );
        }

        for (Map.Entry<Character, TrieNode> entry
                : current.children.entrySet()) {

            currentWord.append(
                    entry.getKey()
            );

            collectWords(
                    entry.getValue(),
                    currentWord,
                    results
            );

            currentWord.deleteCharAt(
                    currentWord.length() - 1
            );
        }
    }

    /**
     * Returns the number of words stored in the Trie.
     *
     * @return the size of the Trie
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the Trie is empty.
     *
     * @return true if the Trie is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears all words from the Trie.
     */
    public void clear() {
        root.children.clear();
        size = 0;
    }

    /**
     * Represents a node in the Trie.
     */
    private static class TrieNode {

        private final Map<Character, TrieNode> children;

        private boolean endOfWord;

        public TrieNode() {

//            this.children = new HashMap<>();
            this.children = new TreeMap<>();
        }
    }
}