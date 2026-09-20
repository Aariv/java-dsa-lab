package com.ariv.dsa.datastructure.hash;

import java.util.Objects;

/**
 * A simple implementation of a hash table (hash map) data structure.
 *
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
public class HashTable<K, V> {

    /**
     * The default initial capacity of the hash table.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * The load factor threshold for resizing the hash table.
     */
    private static final double LOAD_FACTOR = 0.75;

    /**
     * The array of buckets where entries are stored.
     */
    private Entry<K, V>[] buckets;

    /**
     * The number of key-value pairs in the hash table.
     */
    private int size;



    /**
     * Constructs a new, empty hash table with the default initial capacity.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        buckets =(Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }

    /**
     * Computes the index of the bucket for the given key.
     *
     * @param key the key for which to compute the bucket index
     * @return the index of the bucket
     */
    private int bucketIndex(K key) {
        return Math.abs( key.hashCode() ) % buckets.length;
    }

    /**
     * Associates the specified value with the specified key in this hash table.
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Deprecated
    public void put1(K key, V value) {

        // Check if the hash table needs to be resized before adding a new entry
        int bucket = bucketIndex(key);

        // Check if the bucket is empty
        Entry<K,V> entry = buckets[bucket];

        // If the bucket is empty, create a new entry and add it to the bucket
        if(entry == null) {
            buckets[bucket] = new Entry<>(key, value);
            size++;
            return;
        }

        // If the bucket is not empty, check if the key already exists in the bucket
        if(entry.key.equals(key)) {
            // If the key already exists, update the value
            entry.value = value;
            return;
        }

        // If the key does not exist, we have a collision. In this implementation, we do not handle collisions.
        throw new IllegalStateException(
                "Collision detected"
        );
    }

    /**
     * Associates the specified value with the specified key in this hash table.
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     * This method handles collisions by using a linked list to store multiple entries in the same bucket.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        // Check if the hash table needs to be resized before adding a new entry
        if(shouldResize()) {
            // Resize the hash table to accommodate more entries
            resize();
        }
        // Add the new entry to the hash table
        putEntry(key, value);

//        int bucket = bucketIndex(key);
//        Entry<K,V> current = buckets[bucket];
//        if(current == null) {
//            buckets[bucket] = new Entry<>(key, value);
//            size++;
//            return;
//        }
//        while(true) {
//            if(Objects.equals(current.key, key)) {
//                current.value = value;
//                return;
//            }
//            // Collision detected, move to the next entry in the linked list
//            if(current.next == null) {
//                current.next = new Entry<>(key, value);
//                size++;
//                return;
//            }
//            current = current.next;
//        }
    }

    @Deprecated
    public V get1(K key) {

        int bucket = bucketIndex(key);

        Entry<K,V> entry = buckets[bucket];

        if(entry == null) {
            return null;
        }

        if(entry.key.equals(key)) {
            return entry.value;
        }

        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this hash table contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this hash table contains no mapping for the key
     */
    public V get(K key) {

        // Check if the hash table needs to be resized before adding a new entry
        int bucket = bucketIndex(key);

        // Check if the bucket is empty
        Entry<K,V> current = buckets[bucket];

        // If the bucket is empty, return null
        while(current != null) {
            // If the key is found, return the associated value
            if(Objects.equals(current.key, key)) {
                return current.value;
            }
            // Move to the next entry in the linked list
            current = current.next;
        }
        return null;
    }

    /**
     * Returns {@code true} if this hash table contains a mapping for the specified key.
     *
     * @param key the key whose presence in this hash table is to be tested
     * @return {@code true} if this hash table contains a mapping for the specified key, {@code false} otherwise
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of key-value pairs in this hash table.
     *
     * @return the number of key-value pairs in this hash table
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this hash table contains no key-value pairs.
     *
     * @return {@code true} if this hash table contains no key-value pairs, {@code false} otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the mapping for the specified key from this hash table if present.
     *
     * @param key the key whose mapping is to be removed from the hash table
     * @return the previous value associated with the specified key, or {@code null} if there was no mapping for the key
     */
    public V remove(K key) {
        int bucket = bucketIndex(key);
        Entry<K,V> current = buckets[bucket];
        Entry<K,V> previous = null;

        while(current != null) {
            if(Objects.equals(current.key, key)) {
                if(previous == null) {
                    // Removing the first entry in the bucket
                    buckets[bucket] = current.next;
                } else {
                    // Bypass the current entry
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null; // Key not found
    }

    /**
     * Checks if the hash table needs to be resized based on the load factor.
     *
     * @return {@code true} if the hash table should be resized, {@code false} otherwise
     */
    private boolean shouldResize() {

        return (size + 1) > buckets.length * LOAD_FACTOR;
    }

    /**
     * Returns the current capacity of the hash table (the number of buckets).
     *
     * @return the current capacity of the hash table
     */
    public int capacity() {
        return buckets.length;
    }

    /**
     * Puts an entry into the hash table, handling collisions by using a linked list to store multiple entries in the same bucket.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    private void putEntry(K key, V value) {
        // Check if the hash table needs to be resized before adding a new entry
        int bucket = bucketIndex(key);
        // Check if the bucket is empty
        Entry<K,V> current = buckets[bucket];
        // If the bucket is empty, create a new entry and add it to the bucket
        if(current == null) {
            buckets[bucket] = new Entry<>(key, value);
            size++;
            return;
        }
        // If the bucket is not empty, check if the key already exists in the bucket
        while(true) {
            // If the key already exists, update the value
            if(Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            // Collision detected, move to the next entry in the linked list
            if(current.next == null) {
                current.next = new Entry<>(key, value);
                size++;
                return;
            }
            current = current.next;
        }
    }

    /**
     * Resizes the hash table by creating a new array of buckets with double the size and rehashing all existing entries into the new buckets.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K,V>[] oldBuckets = buckets;
        // Create a new array of buckets with double the size
        buckets = (Entry<K,V>[]) new Entry[oldBuckets.length * 2];
        // Rehash all existing entries and put them into the new buckets
        int oldSize = size;
        size = 0;
        for(Entry<K,V> bucket : oldBuckets) {
            Entry<K,V> current = bucket;
            while(current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
        size = oldSize;
    }

    /**
     * Removes all of the mappings from this hash table. The hash table will be empty after this call returns.
     */
    public void clear() {
        buckets = (Entry<K,V>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }
}