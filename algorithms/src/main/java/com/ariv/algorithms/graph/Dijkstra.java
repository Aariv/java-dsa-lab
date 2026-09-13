package com.ariv.algorithms.graph;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public final class Dijkstra {

    private Dijkstra() {
    }

    /**
     * Calculates the shortest paths from the source vertex to every
     * reachable vertex in the graph.
     *
     * Dijkstra's algorithm requires all edge weights to be
     * non-negative.
     *
     * @param graph weighted directed graph
     * @param source source vertex
     * @param <T> vertex type
     * @return result containing distances and predecessors
     */
    public static <T> ShortestPathResult<T> shortestPaths(WeightedDirectedGraph<T> graph, T source) {

        Objects.requireNonNull(graph, "Graph cannot be null");

        Objects.requireNonNull(source, "Source cannot be null");

        if (!graph.containsVertex(source)) {
            throw new IllegalArgumentException(
                    "Source vertex does not exist: " + source
            );
        }

        if (graph.hasNegativeWeightEdge()) {
            throw new IllegalArgumentException(
                    "Dijkstra requires non-negative edge weights"
            );
        }

        Map<T, Long> distances = new HashMap<>();

        Map<T, T> predecessors = new HashMap<>();

        PriorityQueue<QueueEntry<T>> priorityQueue = new PriorityQueue<>();

        distances.put(source, 0L);

        priorityQueue.offer(new QueueEntry<>(source,0L));

        while (!priorityQueue.isEmpty()) {

            QueueEntry<T> currentEntry = priorityQueue.poll();

            T currentVertex = currentEntry.vertex();

            long currentDistance = currentEntry.distance();

            long bestKnownDistance = distances.getOrDefault(currentVertex, Long.MAX_VALUE);

            /*
             * Ignore an outdated priority queue entry.
             *
             * A vertex may have been inserted into the queue with
             * a longer distance before a shorter path was found.
             */
            if (currentDistance != bestKnownDistance) {
                continue;
            }

            for (WeightedEdge<T> edge : graph.getNeighbors(currentVertex)) {

                T neighbor = edge.destination();

                long candidateDistance = addWithoutOverflow(currentDistance, edge.weight());

                long knownNeighborDistance =
                        distances.getOrDefault(
                                neighbor,
                                Long.MAX_VALUE
                        );

                /*
                 * Relax the edge when the newly discovered path
                 * is shorter than the existing path.
                 */
                if (candidateDistance
                        < knownNeighborDistance) {

                    distances.put(
                            neighbor,
                            candidateDistance
                    );

                    predecessors.put(
                            neighbor,
                            currentVertex
                    );

                    priorityQueue.offer(
                            new QueueEntry<>(
                                    neighbor,
                                    candidateDistance
                            )
                    );
                }
            }
        }

        Map<T, Long> orderedDistances = orderDistances(
                        graph,
                        distances
                );

        Map<T, T> orderedPredecessors = orderPredecessors(
                        graph,
                        predecessors
                );

        return new ShortestPathResult<>(
                source,
                orderedDistances,
                orderedPredecessors
        );
    }

    /**
     * Returns the shortest distance between the source and destination.
     *
     * Long.MAX_VALUE is returned when the destination is unreachable.
     *
     * @param graph weighted directed graph
     * @param source source vertex
     * @param destination destination vertex
     * @param <T> vertex type
     * @return shortest distance or Long.MAX_VALUE when unreachable
     */
    public static <T> long shortestDistance(
            WeightedDirectedGraph<T> graph,
            T source,
            T destination) {

        Objects.requireNonNull(
                graph,
                "Graph cannot be null"
        );

        Objects.requireNonNull(
                source,
                "Source cannot be null"
        );

        Objects.requireNonNull(
                destination,
                "Destination cannot be null"
        );

        if (!graph.containsVertex(source)) {
            throw new IllegalArgumentException(
                    "Source vertex does not exist: " + source
            );
        }

        if (!graph.containsVertex(destination)) {
            throw new IllegalArgumentException(
                    "Destination vertex does not exist: "
                            + destination
            );
        }

        ShortestPathResult<T> result =
                shortestPaths(
                        graph,
                        source
                );

        return result.distanceTo(destination)
                .orElse(Long.MAX_VALUE);
    }

    /**
     * Returns the shortest path between the source and destination.
     *
     * An empty list is returned when the destination is unreachable.
     */
    public static <T> java.util.List<T> shortestPath(
            WeightedDirectedGraph<T> graph,
            T source,
            T destination) {

        Objects.requireNonNull(
                graph,
                "Graph cannot be null"
        );

        Objects.requireNonNull(
                source,
                "Source cannot be null"
        );

        Objects.requireNonNull(
                destination,
                "Destination cannot be null"
        );

        if (!graph.containsVertex(source)) {
            throw new IllegalArgumentException(
                    "Source vertex does not exist: " + source
            );
        }

        if (!graph.containsVertex(destination)) {
            throw new IllegalArgumentException(
                    "Destination vertex does not exist: "
                            + destination
            );
        }

        ShortestPathResult<T> result =
                shortestPaths(
                        graph,
                        source
                );

        return result.pathTo(destination);
    }

    /**
     * Adds an edge weight to a distance while protecting against
     * long overflow.
     */
    private static long addWithoutOverflow(long distance, int edgeWeight) {

        if (edgeWeight < 0) {
            throw new IllegalArgumentException(
                    "Dijkstra requires non-negative edge weights"
            );
        }

        if (distance > Long.MAX_VALUE - edgeWeight) {

            return Long.MAX_VALUE;
        }

        return distance + edgeWeight;
    }

    /**
     * Produces deterministic distance-map ordering based on the
     * graph's vertex insertion order.
     */
    private static <T> Map<T, Long> orderDistances(
            WeightedDirectedGraph<T> graph,
            Map<T, Long> distances) {

        Map<T, Long> orderedDistances =
                new LinkedHashMap<>();

        for (T vertex : graph.getVertices()) {

            Long distance =
                    distances.get(vertex);

            if (distance != null) {
                orderedDistances.put(
                        vertex,
                        distance
                );
            }
        }

        return orderedDistances;
    }

    /**
     * Produces deterministic predecessor-map ordering based on the
     * graph's vertex insertion order.
     */
    private static <T> Map<T, T> orderPredecessors(WeightedDirectedGraph<T> graph, Map<T, T> predecessors) {

        Map<T, T> orderedPredecessors = new LinkedHashMap<>();

        for (T vertex : graph.getVertices()) {

            T predecessor = predecessors.get(vertex);

            if (predecessor != null) {
                orderedPredecessors.put(
                        vertex,
                        predecessor
                );
            }
        }

        return orderedPredecessors;
    }

    /**
     * Represents one entry in the minimum priority queue.
     *
     * The vertex does not need to implement Comparable because
     * queue entries are compared only by their current distance.
     */
    private record QueueEntry<T>(
            T vertex,
            long distance)
            implements Comparable<QueueEntry<T>> {

        @Override
        public int compareTo(
                QueueEntry<T> other) {

            return Long.compare(
                    this.distance,
                    other.distance
            );
        }
    }
}