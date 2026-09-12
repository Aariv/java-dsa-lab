package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;

import java.util.HashSet;
import java.util.Set;

/**
 * The CycleDetection class provides a static method to detect cycles in a directed graph.
 * <p>
 * Cycle detection is an important problem in graph theory, as cycles can indicate issues such as deadlocks or infinite loops in various applications.
 * <p>
 * This implementation uses depth-first search (DFS) to explore the graph and track the recursion stack to identify back edges, which indicate the presence of a cycle.
 * <p>
 * The class is designed to be used with any directed graph implementation that adheres to the DirectedGraph interface, allowing for flexibility in the underlying graph representation.
 */
public final class CycleDetection {

    private CycleDetection() {
    }

    /**
     * Determines whether the given directed graph contains a cycle.
     *
     * @param graph the directed graph to check for cycles
     * @param <T>   the type of the vertices in the graph
     * @return true if the graph contains a cycle, false otherwise
     * @throws NullPointerException if the graph is null
     */
    public static <T> boolean hasCycle(DirectedGraph<T> graph) {
        if (graph == null) {
            throw new NullPointerException(
                    "Graph cannot be null"
            );
        }

        Set<T> visited = new HashSet<>();
        Set<T> recursionStack = new HashSet<>();

        for (T vertex : graph.getVertices()) {

            if (visited.contains(vertex)) {
                continue;
            }

            if (dfs(graph, vertex, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Performs a depth-first search (DFS) to detect cycles in the directed graph.
     *
     * @param graph          the directed graph to traverse
     * @param vertex         the current vertex being explored
     * @param visited        a set of vertices that have been visited
     * @param recursionStack a set of vertices currently in the recursion stack
     * @param <T>            the type of the vertices in the graph
     * @return true if a cycle is detected, false otherwise
     */
    private static <T> boolean dfs(DirectedGraph<T> graph, T vertex, Set<T> visited, Set<T> recursionStack) {
        visited.add(vertex);
        recursionStack.add(vertex);
        for (T neighbor : graph.getNeighbors(vertex)) {

            if (recursionStack.contains(neighbor)) {
                return true;
            }

            if (visited.contains(neighbor)) {
                continue;
            }

            if (dfs(graph, neighbor, visited, recursionStack)) {
                return true;
            }
        }
        recursionStack.remove(vertex);
        return false;
    }
}