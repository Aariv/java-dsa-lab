package com.ariv.dsa.datastructure.graph;

import java.util.*;

/**
 * Represents an undirected graph using an adjacency list.
 *
 * @param <T> the type of the vertices in the graph
 */
public class Graph<T> {

    /**
     * The adjacency list representing the graph.
     * Each vertex maps to a set of its neighboring vertices.
     */
    private final Map<T, Set<T>> adjacencyList;

    /**
     * Constructs an empty graph.
     */
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to be added
     * @return true if the vertex was added successfully, false if it already exists
     */
    public boolean addVertex(T vertex) {

        Objects.requireNonNull(vertex);

        if (adjacencyList.containsKey(vertex)) {
            return false;
        }

        adjacencyList.put(
                vertex,
                new HashSet<>()
        );

        return true;
    }

    /**
     * Checks if the graph contains a specific vertex.
     *
     * @param vertex the vertex to check for
     * @return true if the vertex exists in the graph, false otherwise
     */
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    /**
     * Adds an undirected edge between two vertices in the graph.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if the edge was added successfully, false if it already exists
     * @throws IllegalArgumentException if either vertex does not exist in the graph
     */
    public boolean addEdge(
            T source,
            T destination) {

        Objects.requireNonNull(source);
        Objects.requireNonNull(destination);

        if (!containsVertex(source)
                || !containsVertex(destination)) {

            throw new IllegalArgumentException(
                    "Both vertices must exist"
            );
        }

        boolean added =
                adjacencyList
                        .get(source)
                        .add(destination);

        adjacencyList
                .get(destination)
                .add(source);

        return added;
    }

    /**
     * Checks if an edge exists between two vertices in the graph.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if the edge exists, false otherwise
     */
    public boolean containsEdge(
            T source,
            T destination) {

        if (!containsVertex(source)
                || !containsVertex(destination)) {

            return false;
        }

        return adjacencyList
                .get(source)
                .contains(destination);
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges
     */
    public int edgeCount() {

        int edges = adjacencyList.values()
                .stream()
                .mapToInt(Set::size)
                .sum();

        return edges / 2;
    }

    /**
     * Returns the neighbors of a specific vertex in the graph.
     *
     * @param vertex the vertex whose neighbors are to be retrieved
     * @return an unmodifiable set of neighboring vertices, or an empty set if the vertex does not exist
     */
    public Set<T> getNeighbors(
            T vertex) {

        if (!containsVertex(vertex)) {
            return Set.of();
        }

        return Collections.unmodifiableSet(
                adjacencyList.get(vertex)
        );
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    @Override
    public String toString() {
        return adjacencyList.toString();
    }

    public int vertexCount() {
        return adjacencyList.size();
    }
}