package com.ariv.algorithms.graph;

import com.ariv.dsa.datastructure.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

public class TopologicalSortTest {

    @Test
    void shouldHandleEmptyGraph() {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        assert TopologicalSort.sort(graph).isEmpty();
    }
}
