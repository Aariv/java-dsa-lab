package com.ariv.dsa.datastructure.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a directed graph using an adjacency list.
 *
 * @param <T> the type of the vertices in the graph
 */
public class DirectedGraph<T> {

    /**
     * The adjacency list representing the graph.
     * Each vertex maps to a set of its neighboring vertices.
     */
    private final Map<T, Set<T>> adjacencyList;

    /**
     * The count of edges in the graph.
     */
    private int edgeCount;

    /**
     * Constructs an empty directed graph.
     */
    public DirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to be added
     * @return true if the vertex was added successfully, false if it already exists
     */
    public boolean addVertex(T vertex) {

        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        if (adjacencyList.containsKey(vertex)) {
            return false;
        }

        adjacencyList.put(
                vertex,
                new LinkedHashSet<>()
        );

        return true;
    }

    /**
     * Removes a vertex and all its associated edges from the graph.
     *
     * @param vertex the vertex to be removed
     * @return true if the vertex was removed successfully, false if it does not exist
     */
    public boolean removeVertex(T vertex) {

        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        if (!adjacencyList.containsKey(vertex)) {
            return false;
        }

        /*
         * Remove outgoing edges
         */

        edgeCount -= adjacencyList
                .get(vertex)
                .size();

        adjacencyList.remove(vertex);

        /*
         * Remove incoming edges
         */

        for (Set<T> neighbors
                : adjacencyList.values()) {

            if (neighbors.remove(vertex)) {
                edgeCount--;
            }
        }

        return true;
    }

    /**
     * Checks if a vertex exists in the graph.
     *
     * @param vertex the vertex to be checked
     * @return true if the vertex exists, false otherwise
     */
    public boolean containsVertex(T vertex) {

        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        return adjacencyList.containsKey(vertex);
    }

    /**
     * Adds a directed edge from the source vertex to the destination vertex.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if the edge was added successfully, false if it already exists
     */
    public boolean addEdge(
            T source,
            T destination) {

        validateExistingVertex(source);
        validateExistingVertex(destination);

        boolean added =
                adjacencyList
                        .get(source)
                        .add(destination);

        if (added) {
            edgeCount++;
        }

        return added;
    }

    /**
     * Removes a directed edge from the source vertex to the destination vertex.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if the edge was removed successfully, false if it does not exist
     */
    public boolean removeEdge(
            T source,
            T destination) {

        validateExistingVertex(source);
        validateExistingVertex(destination);

        boolean removed =
                adjacencyList
                        .get(source)
                        .remove(destination);

        if (removed) {
            edgeCount--;
        }

        return removed;
    }

    /**
     * Checks if a directed edge exists from the source vertex to the destination vertex.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if the edge exists, false otherwise
     */
    public boolean containsEdge(
            T source,
            T destination) {

        Objects.requireNonNull(source);
        Objects.requireNonNull(destination);

        if (!containsVertex(source)
                || !containsVertex(destination)) {

            return false;
        }

        return adjacencyList
                .get(source)
                .contains(destination);
    }

    /**
     * Retrieves the neighbors of a given vertex.
     *
     * @param vertex the vertex whose neighbors are to be retrieved
     * @return an unmodifiable set of neighboring vertices
     */
    public Set<T> getNeighbors(T vertex) {

        validateExistingVertex(vertex);

        return Collections.unmodifiableSet(
                adjacencyList.get(vertex)
        );
    }

    /**
     * Returns the out-degree of a given vertex.
     *
     * @param vertex the vertex whose out-degree is to be calculated
     * @return the out-degree of the vertex
     */
    public int outDegree(T vertex) {

        validateExistingVertex(vertex);

        return adjacencyList
                .get(vertex)
                .size();
    }

    /**
     * Returns the in-degree of a given vertex.
     *
     * @param vertex the vertex whose in-degree is to be calculated
     * @return the in-degree of the vertex
     */
    public int inDegree(T vertex) {

        validateExistingVertex(vertex);

        int count = 0;

        for (Set<T> neighbors
                : adjacencyList.values()) {

            if (neighbors.contains(vertex)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int vertexCount() {
        return adjacencyList.size();
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges
     */
    public int edgeCount() {
        return edgeCount;
    }

    /**
     * Checks if the graph is empty (contains no vertices).
     *
     * @return true if the graph is empty, false otherwise
     */
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    /**
     * Clears the graph by removing all vertices and edges.
     */
    public void clear() {

        adjacencyList.clear();

        edgeCount = 0;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        return adjacencyList.toString();
    }

    /**
     * Validates that a vertex exists in the graph.
     *
     * @param vertex the vertex to be validated
     * @throws NullPointerException     if the vertex is null
     * @throws IllegalArgumentException if the vertex does not exist in the graph
     */
    private void validateExistingVertex(
            T vertex) {

        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException(
                    "Vertex does not exist: " + vertex
            );
        }
    }
}