package com.ariv.algorithms.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dijkstra")
class DijkstraTest {

    @Nested
    @DisplayName("shortestPaths")
    class ShortestPathsTests {

        @Test
        @DisplayName("should calculate shortest distances")
        void shouldCalculateShortestDistances() {

            WeightedDirectedGraph<String> graph =
                    createSampleGraph();

            ShortestPathResult<String> result =
                    Dijkstra.shortestPaths(
                            graph,
                            "A"
                    );

            assertAll(
                    () -> assertEquals(
                            OptionalLong.of(0),
                            result.distanceTo("A")
                    ),

                    () -> assertEquals(
                            OptionalLong.of(1),
                            result.distanceTo("C")
                    ),

                    () -> assertEquals(
                            OptionalLong.of(3),
                            result.distanceTo("B")
                    ),

                    () -> assertEquals(
                            OptionalLong.of(4),
                            result.distanceTo("D")
                    )
            );
        }

        @Test
        @DisplayName("should reconstruct shortest path")
        void shouldReconstructShortestPath() {

            WeightedDirectedGraph<String> graph =
                    createSampleGraph();

            ShortestPathResult<String> result =
                    Dijkstra.shortestPaths(
                            graph,
                            "A"
                    );

            assertEquals(
                    List.of(
                            "A",
                            "C",
                            "B",
                            "D"
                    ),
                    result.pathTo("D")
            );
        }

        @Test
        @DisplayName("should handle single vertex graph")
        void shouldHandleSingleVertexGraph() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");

            ShortestPathResult<String> result =
                    Dijkstra.shortestPaths(
                            graph,
                            "A"
                    );

            assertAll(
                    () -> assertTrue(
                            result.isReachable("A")
                    ),

                    () -> assertEquals(
                            OptionalLong.of(0),
                            result.distanceTo("A")
                    ),

                    () -> assertEquals(
                            List.of("A"),
                            result.pathTo("A")
                    )
            );
        }

        @Test
        @DisplayName("should handle unreachable vertex")
        void shouldHandleUnreachableVertex() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");

            graph.addEdge(
                    "A",
                    "B",
                    5
            );

            ShortestPathResult<String> result =
                    Dijkstra.shortestPaths(
                            graph,
                            "A"
                    );

            assertAll(
                    () -> assertFalse(
                            result.isReachable("C")
                    ),

                    () -> assertTrue(
                            result.distanceTo("C")
                                    .isEmpty()
                    ),

                    () -> assertTrue(
                            result.pathTo("C")
                                    .isEmpty()
                    )
            );
        }

        @Test
        @DisplayName("should choose cheaper indirect path")
        void shouldChooseCheaperIndirectPath() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");

            graph.addEdge(
                    "A",
                    "B",
                    10
            );

            graph.addEdge(
                    "A",
                    "C",
                    1
            );

            graph.addEdge(
                    "C",
                    "B",
                    2
            );

            ShortestPathResult<String> result =
                    Dijkstra.shortestPaths(
                            graph,
                            "A"
                    );

            assertAll(
                    () -> assertEquals(
                            OptionalLong.of(3),
                            result.distanceTo("B")
                    ),

                    () -> assertEquals(
                            List.of(
                                    "A",
                                    "C",
                                    "B"
                            ),
                            result.pathTo("B")
                    )
            );
        }

        @Test
        @DisplayName("should reject graph with negative weights")
        void shouldRejectGraphWithNegativeWeights() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");
            graph.addVertex("B");

            graph.addEdge(
                    "A",
                    "B",
                    -5
            );

            assertThrows(
                    IllegalArgumentException.class,
                    () -> Dijkstra.shortestPaths(
                            graph,
                            "A"
                    )
            );
        }

        @Test
        @DisplayName("should reject unknown source vertex")
        void shouldRejectUnknownSourceVertex() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");

            assertThrows(
                    IllegalArgumentException.class,
                    () -> Dijkstra.shortestPaths(
                            graph,
                            "B"
                    )
            );
        }

        @Test
        @DisplayName("should reject null graph")
        void shouldRejectNullGraph() {

            assertThrows(
                    NullPointerException.class,
                    () -> Dijkstra.shortestPaths(
                            null,
                            "A"
                    )
            );
        }

        @Test
        @DisplayName("should reject null source")
        void shouldRejectNullSource() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");

            assertThrows(
                    NullPointerException.class,
                    () -> Dijkstra.shortestPaths(
                            graph,
                            null
                    )
            );
        }
    }

    @Nested
    @DisplayName("shortestDistance")
    class ShortestDistanceTests {

        @Test
        @DisplayName("should return shortest distance")
        void shouldReturnShortestDistance() {

            WeightedDirectedGraph<String> graph =
                    createSampleGraph();

            long distance =
                    Dijkstra.shortestDistance(
                            graph,
                            "A",
                            "D"
                    );

            assertEquals(
                    4,
                    distance
            );
        }

        @Test
        @DisplayName("should return max value for unreachable destination")
        void shouldReturnMaxValueForUnreachableDestination() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");

            graph.addEdge(
                    "A",
                    "B",
                    1
            );

            long distance =
                    Dijkstra.shortestDistance(
                            graph,
                            "A",
                            "C"
                    );

            assertEquals(
                    Long.MAX_VALUE,
                    distance
            );
        }
    }

    @Nested
    @DisplayName("shortestPath")
    class ShortestPathTests {

        @Test
        @DisplayName("should return shortest path")
        void shouldReturnShortestPath() {

            WeightedDirectedGraph<String> graph =
                    createSampleGraph();

            List<String> path =
                    Dijkstra.shortestPath(
                            graph,
                            "A",
                            "D"
                    );

            assertEquals(
                    List.of(
                            "A",
                            "C",
                            "B",
                            "D"
                    ),
                    path
            );
        }

        @Test
        @DisplayName("should return empty path when unreachable")
        void shouldReturnEmptyPathWhenUnreachable() {

            WeightedDirectedGraph<String> graph =
                    new WeightedDirectedGraph<>();

            graph.addVertex("A");
            graph.addVertex("B");

            List<String> path =
                    Dijkstra.shortestPath(
                            graph,
                            "A",
                            "B"
                    );

            assertTrue(
                    path.isEmpty()
            );
        }
    }

    private WeightedDirectedGraph<String> createSampleGraph() {

        WeightedDirectedGraph<String> graph =
                new WeightedDirectedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge(
                "A",
                "B",
                4
        );

        graph.addEdge(
                "A",
                "C",
                1
        );

        graph.addEdge(
                "C",
                "B",
                2
        );

        graph.addEdge(
                "B",
                "D",
                1
        );

        graph.addEdge(
                "C",
                "D",
                5
        );

        return graph;
    }
}