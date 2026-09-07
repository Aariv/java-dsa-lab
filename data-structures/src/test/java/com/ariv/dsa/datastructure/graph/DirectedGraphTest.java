package com.ariv.dsa.datastructure.graph;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DirectedGraphTest {

    @Test
    void shouldCreateEmptyGraph() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        assertTrue(graph.isEmpty());
        assertEquals(0, graph.vertexCount());
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void shouldAddVertex() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        assertTrue(
                graph.addVertex("A")
        );

        assertEquals(
                1,
                graph.vertexCount()
        );
    }

    @Test
    void shouldRejectDuplicateVertex() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        assertTrue(graph.addVertex("A"));

        assertFalse(graph.addVertex("A"));
    }

    @Test
    void shouldCreateDirectedEdge() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertAll(
                () -> assertTrue(
                        graph.containsEdge("A", "B")
                ),
                () -> assertFalse(
                        graph.containsEdge("B", "A")
                )
        );
    }

    @Test
    void shouldRemoveEdge() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertTrue(
                graph.removeEdge("A", "B")
        );

        assertFalse(
                graph.containsEdge("A", "B")
        );
    }

    @Test
    void shouldCalculateOutDegree() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        assertEquals(
                2,
                graph.outDegree("A")
        );
    }

    @Test
    void shouldCalculateInDegree() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "C");
        graph.addEdge("B", "C");

        assertEquals(
                2,
                graph.inDegree("C")
        );
    }

    @Test
    void shouldReturnNeighbors() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        assertEquals(
                Set.of("B", "C"),
                graph.getNeighbors("A")
        );
    }

    @Test
    void shouldRemoveVertexAndAssociatedEdges() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("C", "B");

        graph.removeVertex("B");

        assertAll(
                () -> assertFalse(
                        graph.containsVertex("B")
                ),
                () -> assertEquals(
                        2,
                        graph.vertexCount()
                ),
                () -> assertEquals(
                        0,
                        graph.edgeCount()
                )
        );
    }
}
