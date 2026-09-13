package com.ariv.algorithms.graph;

import java.util.Objects;

/**
 * Represents an immutable outgoing edge in a weighted graph.
 *
 * @param destination destination vertex
 * @param weight edge weight
 * @param <T> vertex type
 */
public record WeightedEdge<T>(
        T destination,
        int weight) {

    public WeightedEdge {
        Objects.requireNonNull(
                destination,
                "Destination cannot be null"
        );
    }
}