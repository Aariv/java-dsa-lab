package com.ariv.dsa.datastructure.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    void shouldAddVertex() {

        Graph<String> graph = new Graph<>();

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
        Graph<String> graph = new Graph<>();
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
        Graph<String> graph = new Graph<>();

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertEquals(
                1,
                graph.edgeCount()
        );
    }

}
