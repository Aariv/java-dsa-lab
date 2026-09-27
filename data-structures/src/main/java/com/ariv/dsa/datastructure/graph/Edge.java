package com.ariv.dsa.datastructure.graph;

/**
 * Represents an edge in a graph, connecting two vertices with an optional weight.
 *
 * @param <T> the type of the vertices in the graph
 */
public class Edge<T> {

    private final Vertex<T> source;

    private final Vertex<T> destination;

    private final int weight;

    public Edge(Vertex<T> source, Vertex<T> destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}