package com.ariv.algorithms.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

public final class ShortestPathResult<T> {

    private final T source;
    private final Map<T, Long> distances;
    private final Map<T, T> predecessors;

    ShortestPathResult(
            T source,
            Map<T, Long> distances,
            Map<T, T> predecessors) {

        this.source = Objects.requireNonNull(
                source,
                "Source cannot be null"
        );

        this.distances = Collections.unmodifiableMap(
                new LinkedHashMap<>(distances)
        );

        this.predecessors = Collections.unmodifiableMap(
                new LinkedHashMap<>(predecessors)
        );
    }

    /**
     * Returns the source vertex used to calculate shortest paths.
     */
    public T source() {
        return source;
    }

    /**
     * Checks whether a vertex is reachable from the source.
     */
    public boolean isReachable(T vertex) {
        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        return distances.containsKey(vertex);
    }

    /**
     * Returns the shortest distance from the source to the vertex.
     *
     * Empty means the vertex is unreachable or was not part
     * of the graph when the algorithm was executed.
     */
    public OptionalLong distanceTo(T vertex) {
        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        Long distance = distances.get(vertex);

        return distance == null
                ? OptionalLong.empty()
                : OptionalLong.of(distance);
    }

    /**
     * Returns the predecessor of a vertex on its shortest path.
     *
     * The source has no predecessor.
     */
    public Optional<T> predecessorOf(T vertex) {
        Objects.requireNonNull(
                vertex,
                "Vertex cannot be null"
        );

        return Optional.ofNullable(
                predecessors.get(vertex)
        );
    }

    /**
     * Reconstructs the shortest path from the source to the
     * supplied destination.
     *
     * Example:
     *
     * source = A
     * destination = D
     *
     * result = [A, C, B, D]
     *
     * An empty list is returned when the destination is unreachable.
     */
    public List<T> pathTo(T destination) {
        Objects.requireNonNull(
                destination,
                "Destination cannot be null"
        );

        if (!isReachable(destination)) {
            return List.of();
        }

        Deque<T> reversedPath =
                new ArrayDeque<>();

        T current = destination;

        while (current != null) {
            reversedPath.push(current);

            if (Objects.equals(current, source)) {
                break;
            }

            current = predecessors.get(current);
        }

        /*
         * This protects against an inconsistent predecessor map.
         */
        if (reversedPath.isEmpty()
                || !Objects.equals(
                        reversedPath.peek(),
                        source
                )) {

            return List.of();
        }

        return Collections.unmodifiableList(
                new ArrayList<>(reversedPath)
        );
    }

    /**
     * Returns shortest distances for all reachable vertices.
     */
    public Map<T, Long> distances() {
        return distances;
    }

    /**
     * Returns the shortest-path predecessor map.
     */
    public Map<T, T> predecessors() {
        return predecessors;
    }

    @Override
    public String toString() {
        return "ShortestPathResult{"
                + "source=" + source
                + ", distances=" + distances
                + ", predecessors=" + predecessors
                + '}';
    }
}