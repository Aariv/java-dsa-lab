package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CycleDetectionTest {

    @Test
    void shouldReturnFalseForEmptyGraph() {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        assertFalse(CycleDetection.hasCycle(graph));
    }
}
