package com.ariv.dsa.datastructure.hash;

/**
 * Represents a key-value pair (entry) in a hash table.
 *
 * @param <K> the type of keys maintained by this entry
 * @param <V> the type of mapped values
 */
class Entry<K,V> {

        K key;

        V value;

        Entry<K,V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }