
package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Topological sort is a linear ordering of vertices in a directed graph such that for every directed edge (u, v),
 * vertex u comes before vertex v in the ordering. Topological sorting is only possible for Directed Acyclic Graphs (DAGs).
 * <p>
 * The algorithm uses Depth-First Search (DFS) to perform the topological sort. It maintains a stack to store the vertices
 * in the order they are finished processing. The algorithm starts from each unvisited vertex and explores its neighbors
 * recursively. Once all neighbors of a vertex are visited, the vertex is pushed onto the stack. Finally, the stack is
 * popped to obtain the topological order.
 */
public final class TopologicalSort {

    private TopologicalSort() {
    }

    /**
     * Performs topological sort on the given directed graph.
     *
     * @param graph the directed graph to be sorted
     * @param <T>   the type of vertices in the graph
     * @return a list of vertices in topologically sorted order
     * @throws NullPointerException     if the graph is null
     * @throws IllegalArgumentException if the graph contains a cycle (not a DAG)
     */
    public static <T> List<T> sort(DirectedGraph<T> graph) {

        if (graph == null) {
            throw new NullPointerException(
                    "Graph cannot be null"
            );
        }

        if (CycleDetection.hasCycle(graph)) {

            throw new IllegalArgumentException(
                    "Topological sort requires a DAG"
            );
        }

        Set<T> visited = new HashSet<>();

        Deque<T> stack = new ArrayDeque<>();

        for (T vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, visited, stack);
            }
        }

        List<T> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * Performs a depth-first search (DFS) on the graph starting from the given vertex.
     *
     * @param graph   the directed graph
     * @param vertex  the current vertex being visited
     * @param visited a set of visited vertices
     * @param stack   a stack to store the vertices in topological order
     * @param <T>     the type of vertices in the graph
     */
    private static <T> void dfs(DirectedGraph<T> graph, T vertex, Set<T> visited, Deque<T> stack) {
        visited.add(vertex);
        for (T neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }
}