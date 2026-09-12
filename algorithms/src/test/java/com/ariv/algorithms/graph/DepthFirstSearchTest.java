package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstSearchTest {

    @Test
    void shouldTraverseSingleVertex() {

        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addVertex("A");

        List<String> result = DepthFirstSearch.traverse(graph, "A");

        assertEquals(List.of("A"), result);
    }

    @Test
    void shouldTraverseLinearGraph() {

        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        List<String> result = DepthFirstSearch.traverse(graph,"A");

        assertEquals(
                List.of(
                        "A",
                        "B",
                        "C"
                ),
                result
        );
    }

    @Test
    void shouldTraverseBranchingGraph() {

        DirectedGraph<String> graph =
                new DirectedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");

        List<String> result =
                DepthFirstSearch.traverse(
                        graph,
                        "A"
                );

        assertEquals(
                List.of(
                        "A",
                        "B",
                        "D",
                        "C"
                ),
                result
        );
    }

    @Test
    void shouldHandleCycle() {

        DirectedGraph<String> graph =
                new DirectedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        List<String> result =
                DepthFirstSearch.traverse(
                        graph,
                        "A"
                );

        assertEquals(
                3,
                result.size()
        );

        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
        assertTrue(result.contains("C"));
    }

    @Test
    void shouldRejectUnknownStartVertex() {

        DirectedGraph<String> graph =
                new DirectedGraph<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> DepthFirstSearch.traverse(
                        graph,
                        "A"
                )
        );
    }

    @Test
    void shouldRejectNullGraph() {

        assertThrows(
                NullPointerException.class,
                () -> DepthFirstSearch.traverse(
                        null,
                        "A"
                )
        );
    }

    @Test
    void shouldRejectNullStartVertex() {

        DirectedGraph<String> graph =
                new DirectedGraph<>();

        graph.addVertex("A");

        assertThrows(
                NullPointerException.class,
                () -> DepthFirstSearch.traverse(
                        graph,
                        null
                )
        );
    }
}