package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;

import java.util.*;

/**
 * Implements the Breadth-First Search (BFS) algorithm for traversing a directed graph.
 * <p>
 * The BFS algorithm explores the graph level by level, starting from a specified vertex.
 * It visits all vertices reachable from the starting vertex in breadth-first order.
 */
public final class BreadthFirstSearch {

    private BreadthFirstSearch() {
    }

    /**
     * Performs a breadth-first traversal of the given directed graph starting from the specified vertex.
     *
     * @param graph       the directed graph to traverse
     * @param startVertex the vertex from which to start the traversal
     * @param <T>         the type of the vertices in the graph
     * @return a list containing the vertices in the order they were visited during the traversal
     * @throws NullPointerException     if the graph or startVertex is null
     * @throws IllegalArgumentException if the startVertex does not exist in the graph
     */
    public static <T> List<T> traverse(
            DirectedGraph<T> graph,
            T startVertex) {

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

        List<T> traversal =
                new ArrayList<>();

        Set<T> visited =
                new HashSet<>();

        Queue<T> queue =
                new ArrayDeque<>();

        visited.add(startVertex);

        queue.offer(startVertex);

        while (!queue.isEmpty()) {

            T vertex = queue.poll();

            traversal.add(vertex);

            for (T neighbor : graph.getNeighbors(vertex)) {

                if (visited.contains(neighbor)) {
                    continue;
                }

                visited.add(neighbor);

                queue.offer(neighbor);
            }
        }

        return traversal;
    }
}