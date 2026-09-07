package com.ariv.dsa.datastructure.graph;

/**
 * Represents a vertex in a graph.
 *
 * @param <T> the type of the value associated with the vertex
 */
public class Vertex<T> {

    private final T value;

    public Vertex(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
