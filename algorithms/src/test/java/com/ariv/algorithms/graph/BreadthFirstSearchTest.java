package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstSearchTest {

    @Test
    void shouldTraverseSingleVertex() {

        DirectedGraph<String> graph =
                new DirectedGraph<>();

        graph.addVertex("A");

        List<String> result =
                BreadthFirstSearch.traverse(
                        graph,
                        "A"
                );

        assertEquals(
                List.of("A"),
                result
        );
    }
}
