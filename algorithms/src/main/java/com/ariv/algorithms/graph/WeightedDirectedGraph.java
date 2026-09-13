package com.ariv.algorithms.graph;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Set;

/**
 * Represents a weighted directed graph using an adjacency list.
 *
 * @param <T> the type of the vertices in the graph
 */
public class WeightedDirectedGraph<T> {

    /**
     * Source vertex:
     *
     *     destination -> weight
     *
     * Example:
     *
     * A -> {
     *     B -> 5,
     *     C -> 2
     * }
     *
     * LinkedHashMap preserves insertion order, which gives
     * deterministic traversals and predictable JUnit tests.
     */
    private final Map<T, Map<T, Integer>> adjacencyList;

    private int edgeCount;

    public WeightedDirectedGraph() {
        this.adjacencyList = new LinkedHashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex vertex to add
     * @return true when added, false when already present
     */
    public boolean addVertex(T vertex) {
        validateVertex(vertex);

        if (adjacencyList.containsKey(vertex)) {
            return false;
        }

        adjacencyList.put(
                vertex,
                new LinkedHashMap<>()
        );

        return true;
    }

    /**
     * Removes a vertex and all incoming and outgoing edges
     * associated with that vertex.
     *
     * @param vertex vertex to remove
     * @return true when removed, false when not found
     */
    public boolean removeVertex(T vertex) {
        validateVertex(vertex);

        Map<T, Integer> outgoingEdges =
                adjacencyList.remove(vertex);

        if (outgoingEdges == null) {
            return false;
        }

        /*
         * Remove outgoing edges.
         */
        edgeCount -= outgoingEdges.size();

        /*
         * Remove incoming edges.
         */
        for (Map<T, Integer> edges
                : adjacencyList.values()) {

            Integer removedWeight =
                    edges.remove(vertex);

            if (removedWeight != null) {
                edgeCount--;
            }
        }

        return true;
    }

    /**
     * Checks whether a vertex exists.
     *
     * @param vertex vertex to check
     * @return true when the vertex exists
     */
    public boolean containsVertex(T vertex) {
        validateVertex(vertex);

        return adjacencyList.containsKey(vertex);
    }

    /**
     * Adds a directed weighted edge.
     *
     * Example:
     *
     * addEdge("A", "B", 5)
     *
     * creates:
     *
     * A --5--> B
     *
     * It does not create:
     *
     * B --5--> A
     *
     * Negative weights are permitted because they are useful for
     * Bellman-Ford. Dijkstra must reject graphs containing negative
     * edge weights.
     *
     * @param source source vertex
     * @param destination destination vertex
     * @param weight edge weight
     * @return true when added, false when the edge already exists
     */
    public boolean addEdge(
            T source,
            T destination,
            int weight) {

        validateExistingVertex(source);
        validateExistingVertex(destination);

        Map<T, Integer> outgoingEdges =
                adjacencyList.get(source);

        if (outgoingEdges.containsKey(destination)) {
            return false;
        }

        outgoingEdges.put(
                destination,
                weight
        );

        edgeCount++;

        return true;
    }

    /**
     * Updates the weight of an existing edge.
     *
     * @param source source vertex
     * @param destination destination vertex
     * @param newWeight replacement weight
     * @return true when updated, false when the edge does not exist
     */
    public boolean updateWeight(
            T source,
            T destination,
            int newWeight) {

        validateExistingVertex(source);
        validateExistingVertex(destination);

        Map<T, Integer> outgoingEdges =
                adjacencyList.get(source);

        if (!outgoingEdges.containsKey(destination)) {
            return false;
        }

        outgoingEdges.put(
                destination,
                newWeight
        );

        return true;
    }

    /**
     * Adds a new edge or replaces the weight of an existing edge.
     *
     * @return true when a new edge was created,
     *         false when an existing edge was updated
     */
    public boolean putEdge(
            T source,
            T destination,
            int weight) {

        validateExistingVertex(source);
        validateExistingVertex(destination);

        Map<T, Integer> outgoingEdges =
                adjacencyList.get(source);

        boolean isNewEdge =
                !outgoingEdges.containsKey(destination);

        outgoingEdges.put(
                destination,
                weight
        );

        if (isNewEdge) {
            edgeCount++;
        }

        return isNewEdge;
    }

    /**
     * Removes a directed edge.
     *
     * Removing A -> B does not remove B -> A.
     *
     * @param source source vertex
     * @param destination destination vertex
     * @return true when removed, false when the edge does not exist
     */
    public boolean removeEdge(
            T source,
            T destination) {

        validateExistingVertex(source);
        validateExistingVertex(destination);

        Integer removedWeight =
                adjacencyList
                        .get(source)
                        .remove(destination);

        if (removedWeight == null) {
            return false;
        }

        edgeCount--;

        return true;
    }

    /**
     * Checks whether a directed edge exists.
     *
     * Returns false when either vertex is missing.
     */
    public boolean containsEdge(
            T source,
            T destination) {

        validateVertex(source);
        validateVertex(destination);

        Map<T, Integer> outgoingEdges =
                adjacencyList.get(source);

        if (outgoingEdges == null
                || !adjacencyList.containsKey(destination)) {

            return false;
        }

        return outgoingEdges.containsKey(destination);
    }

    /**
     * Returns the weight of an edge.
     *
     * OptionalInt.empty() is returned when the edge does not exist.
     */
    public OptionalInt getWeight(
            T source,
            T destination) {

        validateVertex(source);
        validateVertex(destination);

        Map<T, Integer> outgoingEdges =
                adjacencyList.get(source);

        if (outgoingEdges == null
                || !adjacencyList.containsKey(destination)) {

            return OptionalInt.empty();
        }

        Integer weight =
                outgoingEdges.get(destination);

        return weight == null
                ? OptionalInt.empty()
                : OptionalInt.of(weight);
    }

    /**
     * Returns outgoing weighted edges in insertion order.
     *
     * The returned set is a detached, unmodifiable snapshot.
     */
    public Set<WeightedEdge<T>> getNeighbors(
            T vertex) {

        validateExistingVertex(vertex);

        Set<WeightedEdge<T>> neighbors =
                new LinkedHashSet<>();

        for (Map.Entry<T, Integer> edge
                : adjacencyList
                .get(vertex)
                .entrySet()) {

            neighbors.add(
                    new WeightedEdge<>(
                            edge.getKey(),
                            edge.getValue()
                    )
            );
        }

        return Collections.unmodifiableSet(neighbors);
    }

    /**
     * Returns outgoing destination vertices without weights.
     */
    public Set<T> getNeighborVertices(
            T vertex) {

        validateExistingVertex(vertex);

        return Collections.unmodifiableSet(
                new LinkedHashSet<>(
                        adjacencyList
                                .get(vertex)
                                .keySet()
                )
        );
    }

    /**
     * Returns all vertices in insertion order.
     */
    public Set<T> getVertices() {
        return Collections.unmodifiableSet(
                new LinkedHashSet<>(
                        adjacencyList.keySet()
                )
        );
    }

    /**
     * Returns the number of outgoing edges.
     */
    public int outDegree(T vertex) {
        validateExistingVertex(vertex);

        return adjacencyList
                .get(vertex)
                .size();
    }

    /**
     * Returns the number of incoming edges.
     */
    public int inDegree(T vertex) {
        validateExistingVertex(vertex);

        int incomingEdgeCount = 0;

        for (Map<T, Integer> outgoingEdges
                : adjacencyList.values()) {

            if (outgoingEdges.containsKey(vertex)) {
                incomingEdgeCount++;
            }
        }

        return incomingEdgeCount;
    }

    /**
     * Checks whether at least one edge has a negative weight.
     *
     * This will be useful when validating input for Dijkstra.
     */
    public boolean hasNegativeWeightEdge() {
        for (Map<T, Integer> outgoingEdges : adjacencyList.values()) {

            for (Integer weight : outgoingEdges.values()) {

                if (weight < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public int vertexCount() {
        return adjacencyList.size();
    }

    public int edgeCount() {
        return edgeCount;
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    public void clear() {
        adjacencyList.clear();
        edgeCount = 0;
    }

    @Override
    public String toString() {
        Map<T, Set<WeightedEdge<T>>> representation =
                new LinkedHashMap<>();

        for (T vertex : adjacencyList.keySet()) {
            representation.put(
                    vertex,
                    getNeighbors(vertex)
            );
        }

        return representation.toString();
    }

    private void validateVertex(T vertex) {
        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );
    }

    private void validateExistingVertex(T vertex) {
        validateVertex(vertex);

        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException(
                    "Vertex does not exist: " + vertex
            );
        }
    }
}