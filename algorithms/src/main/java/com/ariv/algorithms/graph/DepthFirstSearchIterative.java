package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * The DepthFirstSearchIterative class provides a static method to perform an iterative depth-first traversal of a directed graph.
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root (or an arbitrary node in the case of a graph) and explores as far as possible along each branch before backtracking.
 *
 * This implementation uses an explicit stack to explore the graph iteratively, keeping track of visited vertices to avoid cycles and ensure that each vertex is processed only once. The traversal order is recorded in a list, which is returned to the caller.
 *
 * The class is designed to be used with any directed graph implementation that adheres to the DirectedGraph interface, allowing for flexibility in the underlying graph representation.
 */
public final class DepthFirstSearchIterative {

    private DepthFirstSearchIterative() {
    }

    /**
     * Performs an iterative depth-first traversal of the given directed graph starting from the specified vertex.
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

        List<T> traversal = new ArrayList<>();

        Set<T> visited = new HashSet<>();

        Stack<T> stack = new Stack<>();

        // Push the starting vertex onto the stack to begin the traversal
        stack.push(startVertex);

        // Iteratively process vertices until the stack is empty
        while (!stack.isEmpty()) {
            // Pop a vertex from the stack to visit
            T vertex = stack.pop();
            // If the vertex has already been visited, skip it to avoid cycles
            if (visited.contains(vertex)) {
                continue;
            }
            // Mark the current vertex as visited
            visited.add(vertex);
            // Add the current vertex to the traversal order
            traversal.add(vertex);

            /*
             * Push neighbors.
             *
             * Reverse iteration may be required
             * depending on your graph implementation
             * if you want exact recursive DFS order.
             */
            List<T> neighbors = new ArrayList<>(graph.getNeighbors(vertex));
            // Reverse iteration may be required depending on your graph implementation if you want exact recursive DFS order.
            for (int index = neighbors.size() - 1; index >= 0; index--) {

                T neighbor = neighbors.get(index);

                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }

        return traversal;
    }
}