package com.ariv.dsa.datastructure.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AdjacencyMatrixGraph<T> {

    private static final int DEFAULT_CAPACITY = 4;

    /*
     * Maintains vertices in index order.
     *
     * Example:
     *
     * vertices[0] = A
     * vertices[1] = B
     * vertices[2] = C
     */
    private final List<T> vertices;

    /*
     * Provides O(1) average-time lookup from a vertex to its
     * matrix index.
     *
     * Example:
     *
     * A -> 0
     * B -> 1
     * C -> 2
     */
    private final Map<T, Integer> vertexIndexes;

    /*
     * matrix[i][j] is true when an edge exists between the
     * vertices at indexes i and j.
     */
    private boolean[][] adjacencyMatrix;

    private int edgeCount;

    /**
     * Creates an empty adjacency-matrix graph using the default
     * initial capacity.
     */
    public AdjacencyMatrixGraph() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty adjacency-matrix graph using the supplied
     * initial capacity.
     *
     * @param initialCapacity initial matrix capacity
     */
    public AdjacencyMatrixGraph(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException(
                    "Initial capacity must be greater than zero"
            );
        }

        this.vertices = new ArrayList<>(initialCapacity);
        this.vertexIndexes = new LinkedHashMap<>(initialCapacity);
        this.adjacencyMatrix =
                new boolean[initialCapacity][initialCapacity];
    }

    /**
     * Adds a new vertex.
     *
     * Duplicate vertices are not added.
     *
     * @param vertex vertex to add
     * @return true when added, false when already present
     */
    public boolean addVertex(T vertex) {
        validateVertex(vertex);

        if (vertexIndexes.containsKey(vertex)) {
            return false;
        }

        ensureCapacity(vertices.size() + 1);

        int newIndex = vertices.size();

        vertices.add(vertex);
        vertexIndexes.put(vertex, newIndex);

        return true;
    }

    /**
     * Adds an undirected edge between two existing vertices.
     *
     * Both matrix directions are updated:
     *
     * matrix[source][destination] = true
     * matrix[destination][source] = true
     *
     * @param source source vertex
     * @param destination destination vertex
     * @return true when added, false when the edge already exists
     */
    public boolean addEdge(
            T source,
            T destination) {

        validateVertex(source);
        validateVertex(destination);

        int sourceIndex = requireVertexIndex(source);
        int destinationIndex = requireVertexIndex(destination);

        if (adjacencyMatrix[sourceIndex][destinationIndex]) {
            return false;
        }

        adjacencyMatrix[sourceIndex][destinationIndex] = true;
        adjacencyMatrix[destinationIndex][sourceIndex] = true;

        edgeCount++;

        return true;
    }

    /**
     * Removes an undirected edge.
     *
     * @param source source vertex
     * @param destination destination vertex
     * @return true when removed, false when the edge does not exist
     */
    public boolean removeEdge(
            T source,
            T destination) {

        validateVertex(source);
        validateVertex(destination);

        Integer sourceIndex = vertexIndexes.get(source);
        Integer destinationIndex = vertexIndexes.get(destination);

        if (sourceIndex == null || destinationIndex == null) {
            return false;
        }

        if (!adjacencyMatrix[sourceIndex][destinationIndex]) {
            return false;
        }

        adjacencyMatrix[sourceIndex][destinationIndex] = false;
        adjacencyMatrix[destinationIndex][sourceIndex] = false;

        edgeCount--;

        return true;
    }

    /**
     * Removes a vertex and all edges connected to it.
     *
     * Removing a vertex requires rebuilding the compact matrix
     * because vertex indexes after the removed index change.
     *
     * @param vertex vertex to remove
     * @return true when removed, false when not found
     */
    public boolean removeVertex(T vertex) {
        validateVertex(vertex);

        Integer removedIndex = vertexIndexes.get(vertex);

        if (removedIndex == null) {
            return false;
        }

        /*
         * Count edges connected to the vertex before rebuilding.
         *
         * For an undirected graph, each neighbor represents one
         * logical edge connected to the removed vertex.
         */
        int removedEdges = 0;

        for (int index = 0; index < vertices.size(); index++) {
            if (adjacencyMatrix[removedIndex][index]) {
                removedEdges++;
            }
        }

        /*
         * A self-loop appears once in the row, so it is correctly
         * counted as one logical edge.
         */
        edgeCount -= removedEdges;

        List<T> remainingVertices =
                new ArrayList<>(vertices);

        remainingVertices.remove((int) removedIndex);

        rebuildGraphWithoutVertex(
                remainingVertices,
                removedIndex
        );

        return true;
    }

    /**
     * Checks whether a vertex exists.
     *
     * @param vertex vertex to inspect
     */
    public boolean containsVertex(T vertex) {
        validateVertex(vertex);

        return vertexIndexes.containsKey(vertex);
    }

    /**
     * Checks whether an edge exists.
     *
     * Returns false when either vertex is missing.
     *
     * @param source source vertex
     * @param destination destination vertex
     */
    public boolean containsEdge(
            T source,
            T destination) {

        validateVertex(source);
        validateVertex(destination);

        Integer sourceIndex = vertexIndexes.get(source);
        Integer destinationIndex = vertexIndexes.get(destination);

        if (sourceIndex == null || destinationIndex == null) {
            return false;
        }

        return adjacencyMatrix[sourceIndex][destinationIndex];
    }

    /**
     * Returns the neighbors of a vertex in vertex insertion order.
     *
     * The returned set cannot be modified by the caller.
     *
     * @param vertex vertex whose neighbors should be retrieved
     * @throws IllegalArgumentException when the vertex does not exist
     */
    public Set<T> getNeighbors(T vertex) {
        validateVertex(vertex);

        int vertexIndex = requireVertexIndex(vertex);

        Set<T> neighbors = new LinkedHashSet<>();

        for (int index = 0; index < vertices.size(); index++) {
            if (adjacencyMatrix[vertexIndex][index]) {
                neighbors.add(vertices.get(index));
            }
        }

        return Collections.unmodifiableSet(neighbors);
    }

    /**
     * Returns vertices in insertion order.
     *
     * The returned list cannot modify the graph.
     */
    public List<T> getVertices() {
        return Collections.unmodifiableList(
                new ArrayList<>(vertices)
        );
    }

    /**
     * Returns a snapshot of the active portion of the matrix.
     *
     * Internal unused capacity is not exposed.
     * The returned matrix is a defensive copy.
     */
    public boolean[][] getAdjacencyMatrix() {
        int vertexCount = vertices.size();

        boolean[][] snapshot =
                new boolean[vertexCount][vertexCount];

        for (int row = 0; row < vertexCount; row++) {
            System.arraycopy(
                    adjacencyMatrix[row],
                    0,
                    snapshot[row],
                    0,
                    vertexCount
            );
        }

        return snapshot;
    }

    /**
     * Returns the number of vertices.
     */
    public int vertexCount() {
        return vertices.size();
    }

    /**
     * Returns the number of logical undirected edges.
     *
     * Although a normal undirected edge occupies two cells in the
     * matrix, it is counted only once.
     */
    public int edgeCount() {
        return edgeCount;
    }

    /**
     * Returns whether the graph has no vertices.
     */
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    /**
     * Removes all vertices and edges.
     */
    public void clear() {
        int retainedCapacity = Math.max(
                DEFAULT_CAPACITY,
                adjacencyMatrix.length
        );

        vertices.clear();
        vertexIndexes.clear();

        adjacencyMatrix =
                new boolean[retainedCapacity][retainedCapacity];

        edgeCount = 0;
    }

    /**
     * Returns the current internal matrix capacity.
     *
     * This is useful for validating dynamic resizing.
     */
    public int capacity() {
        return adjacencyMatrix.length;
    }

    /**
     * Produces an easy-to-read adjacency matrix.
     */
    public String toMatrixString() {
        if (vertices.isEmpty()) {
            return "[empty]";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("    ");

        for (T vertex : vertices) {
            builder.append(vertex).append(' ');
        }

        builder.append(System.lineSeparator());

        for (int row = 0; row < vertices.size(); row++) {
            builder.append(vertices.get(row))
                    .append(" : ");

            for (int column = 0;
                    column < vertices.size();
                    column++) {

                builder.append(
                        adjacencyMatrix[row][column] ? "1 " : "0 "
                );
            }

            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Returns an adjacency-list-style representation.
     */
    @Override
    public String toString() {
        Map<T, Set<T>> representation =
                new LinkedHashMap<>();

        for (T vertex : vertices) {
            representation.put(
                    vertex,
                    getNeighbors(vertex)
            );
        }

        return representation.toString();
    }

    /*
     * ============================================================
     * Internal helpers
     * ============================================================
     */

    private void validateVertex(T vertex) {
        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );
    }

    private int requireVertexIndex(T vertex) {
        Integer index = vertexIndexes.get(vertex);

        if (index == null) {
            throw new IllegalArgumentException(
                    "Vertex does not exist: " + vertex
            );
        }

        return index;
    }

    /**
     * Doubles matrix capacity when the next vertex does not fit.
     */
    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= adjacencyMatrix.length) {
            return;
        }

        int newCapacity = Math.max(
                requiredCapacity,
                adjacencyMatrix.length * 2
        );

        boolean[][] expandedMatrix =
                new boolean[newCapacity][newCapacity];

        for (int row = 0; row < vertices.size(); row++) {
            System.arraycopy(
                    adjacencyMatrix[row],
                    0,
                    expandedMatrix[row],
                    0,
                    vertices.size()
            );
        }

        adjacencyMatrix = expandedMatrix;
    }

    private void rebuildGraphWithoutVertex(
            List<T> remainingVertices,
            int removedIndex) {

        boolean[][] oldMatrix = adjacencyMatrix;

        int retainedCapacity = Math.max(
                DEFAULT_CAPACITY,
                oldMatrix.length
        );

        vertices.clear();
        vertices.addAll(remainingVertices);

        vertexIndexes.clear();

        for (int index = 0;
                index < vertices.size();
                index++) {

            vertexIndexes.put(
                    vertices.get(index),
                    index
            );
        }

        adjacencyMatrix =
                new boolean[retainedCapacity][retainedCapacity];

        int newRow = 0;

        for (int oldRow = 0;
                oldRow < remainingVertices.size() + 1;
                oldRow++) {

            if (oldRow == removedIndex) {
                continue;
            }

            int newColumn = 0;

            for (int oldColumn = 0;
                    oldColumn < remainingVertices.size() + 1;
                    oldColumn++) {

                if (oldColumn == removedIndex) {
                    continue;
                }

                adjacencyMatrix[newRow][newColumn] =
                        oldMatrix[oldRow][oldColumn];

                newColumn++;
            }

            newRow++;
        }
    }
}