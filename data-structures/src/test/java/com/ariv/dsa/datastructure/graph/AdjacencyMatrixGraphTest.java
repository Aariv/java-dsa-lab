package com.ariv.dsa.datastructure.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyMatrixGraphTest {

    @Test
    void shouldAddVertex() {

        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();

        assertTrue(
                graph.addVertex("A")
        );

        assertEquals(
                1,
                graph.vertexCount()
        );
    }

    @Test
    void shouldAddUndirectedEdge() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertAll(
                () -> assertTrue(
                        graph.containsEdge("A", "B")
                ),
                () -> assertTrue(
                        graph.containsEdge("B", "A")
                )
        );
    }

    @Test
    void shouldCountEdges() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertEquals(
                1,
                graph.edgeCount()
        );
    }

}
