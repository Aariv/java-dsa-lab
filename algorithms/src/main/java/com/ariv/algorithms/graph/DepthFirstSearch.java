package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The DepthFirstSearch class provides a static method to perform a depth-first traversal of a directed graph.
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root (or an arbitrary node in the case of a graph) and explores as far as possible along each branch before backtracking.
 *
 * This implementation uses recursion to explore the graph, keeping track of visited vertices to avoid cycles and ensure that each vertex is processed only once. The traversal order is recorded in a list, which is returned to the caller.
 *
 * The class is designed to be used with any directed graph implementation that adheres to the DirectedGraph interface, allowing for flexibility in the underlying graph representation.
 */
public final class DepthFirstSearch {

    private DepthFirstSearch() {
    }

    /**
     * Performs a depth-first traversal of the given directed graph starting from the specified vertex.
     *
     * @param graph       the directed graph to traverse
     * @param startVertex the vertex from which to start the traversal
     * @param <T>         the type of the vertices in the graph
     * @return a list containing the vertices in the order they were visited during the traversal
     * @throws NullPointerException     if the graph or startVertex is null
     * @throws IllegalArgumentException if the startVertex does not exist in the graph
     */
    public static <T> List<T> traverse(DirectedGraph<T> graph, T startVertex) {
        if (graph == null) {
            throw new NullPointerException(
                    "Graph cannot be null"
            );
        }

        if (startVertex == null) {
            throw new NullPointerException(
                    "Start vertex cannot be null"
            );
        }

        if (!graph.containsVertex(startVertex)) {
            throw new IllegalArgumentException(
                    "Start vertex does not exist: "
                            + startVertex
            );
        }

        List<T> traversalOrder = new ArrayList<>();

        Set<T> visited = new HashSet<>();

        dfs(graph, startVertex, visited, traversalOrder);

        return traversalOrder;
    }

    private static <T> void dfs(DirectedGraph<T> graph, T vertex, Set<T> visited, List<T> traversalOrder) {

        // Mark the current vertex as visited
        visited.add(vertex);

        // Add the current vertex to the traversal order
        traversalOrder.add(vertex);

        // Recursively visit all unvisited neighbors of the current vertex
        for (T neighbor : graph.getNeighbors(vertex)) {
            // If the neighbor has already been visited, skip it to avoid cycles
            if (visited.contains(neighbor)) {
                continue;
            }
            // Recursively call dfs for the unvisited neighbor
            dfs(graph, neighbor, visited, traversalOrder);
        }
    }
}