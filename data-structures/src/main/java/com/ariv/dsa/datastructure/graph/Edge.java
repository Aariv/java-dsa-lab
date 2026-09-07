package com.ariv.dsa.datastructure.graph;

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