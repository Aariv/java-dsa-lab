package com.ariv.dsa.datastructure.hash;

public class HashSet<T> {

    private static final Object PRESENT = new Object();

    private final HashTable<T, Object> table = new HashTable<>();

    public boolean add(T value) {
        if(table.containsKey(value)) {
            return false;
        }
        table.put(value, PRESENT);
        return true;
    }

    public boolean remove(T value) {
        return table.remove(value) != null;
    }

    public boolean contains(T value) {
        return table.containsKey(value);
    }

    public int size() {
        return table.size();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public void clear() {
        table.clear();
    }
}