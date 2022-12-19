package org.mk.experiments;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mk.experiments.graph.Graph;
import org.mk.experiments.graph.Node;
import org.mk.experiments.graph.Vertex;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GraphTest {

    @Test
    public void graphConstructionTest() {
        Node<String> node1 = Node.of("Node1");
        Node<String> node2 = Node.of("Node2");
        Node<String> node3 = Node.of("Node3");
        Graph<String, Integer> graph = Graph.<String, Integer>builder()
                .node(node1)
                .node(node2)
                .node(node3)
                .vertex(Vertex.of(node1, 10, node2))
                .vertex(Vertex.of(node2, 10, node3))
                .vertex(Vertex.of(node3, 10, node1))
                .build();
        log.info("Graph: {}", graph);
    }

    @Test
    public void fromNodeTest() {
        Node<String> node1 = Node.of("Node1");
        Node<String> node2 = Node.of("Node2");
        Node<String> node3 = Node.of("Node3");
        Node<String> node4 = Node.of("Node4");
        Graph<String, Integer> graph = Graph.<String, Integer>builder()
                .node(node1)
                .node(node2)
                .node(node3)
                .node(node4)
                .vertex(Vertex.of(node1, 10, node2))
                .vertex(Vertex.of(node1, 10, node3))
                .vertex(Vertex.of(node1, 10, node4))
                .vertex(Vertex.of(node2, 10, node3))
                .vertex(Vertex.of(node2, 10, node4))
                .vertex(Vertex.of(node4, 10, node1))
                .build();

        assertThat(graph.fromNode(node1)).hasSize(3);
        assertThat(graph.fromNode(node2)).hasSize(2);
        assertThat(graph.fromNode(node3)).hasSize(0);
        assertThat(graph.fromNode(node4)).hasSize(1);
    }


    @Test
    public void shortestPathTest() {
        Node<String> node1 = Node.of("Node1");
        Node<String> node2 = Node.of("Node2");
        Node<String> node3 = Node.of("Node3");
        Node<String> node4 = Node.of("Node4");
        Node<String> node5 = Node.of("Node5");
        Graph<String, Integer> graph = Graph.<String, Integer>builder()
                .node(node1)
                .node(node2)
                .node(node3)
                .node(node4)
                .node(node5)
                .vertex(Vertex.of(node1, 20, node2))
                .vertex(Vertex.of(node1, 10, node3))
                .vertex(Vertex.of(node2, 20, node4))
                .vertex(Vertex.of(node3, 10, node4))
                .vertex(Vertex.of(node4, 10, node1))
                .build();

        List<Vertex<Integer, String>> path = graph.shortestPath(node1, node4);
        log.info("Shortest path is: {}", path);
    }

    @Test
    public void shortestPathTest2() {
        Node<String> a = Node.of("a");
        Node<String> b = Node.of("b");
        Node<String> c = Node.of("c");
        Node<String> d = Node.of("d");
        Node<String> e = Node.of("e");
        Node<String> f = Node.of("f");
        Graph<String, Integer> graph = Graph.<String, Integer>builder()
                .node(a)
                .node(b)
                .node(c)
                .node(d)
                .node(e)
                .node(f)
                .vertex(Vertex.of(a, 4, b))
                .vertex(Vertex.of(a, 2, c))
                .vertex(Vertex.of(b, 5, c))
                .vertex(Vertex.of(b, 10, d))
                .vertex(Vertex.of(c, 3, e))
                .vertex(Vertex.of(d, 11, f))
                .vertex(Vertex.of(e, 4, d))
                .build();

        List<Vertex<Integer, String>> path = graph.shortestPath(a, f);
        log.info("Shortest path is: {}", path);
        assertThat(path).hasSize(4);
        assertThat(path.stream().mapToInt(Vertex::getValue).sum()).isEqualTo(20);
    }

    @Test
    public void shortestPathTest3() {
        Node<String> a = Node.of("a");
        Node<String> b = Node.of("b");
        Node<String> c = Node.of("c");
        Node<String> d = Node.of("d");
        Node<String> e = Node.of("e");
        Graph<String, Integer> graph = Graph.<String, Integer>builder()
                .node(a)
                .node(b)
                .node(c)
                .node(d)
                .node(e)
                .vertex(Vertex.of(a, 10, b))
                .vertex(Vertex.of(a, 3, c))
                .vertex(Vertex.of(b, 1, c))
                .vertex(Vertex.of(b, 2, d))
                .vertex(Vertex.of(c, 4, b))
                .vertex(Vertex.of(c, 8, d))
                .vertex(Vertex.of(c, 2, e))
                .vertex(Vertex.of(d, 7, e))
                .vertex(Vertex.of(e, 9, d))
                .build();

        List<Vertex<Integer, String>> path = graph.shortestPath(a, d);
        log.info("Shortest path is: {}", path);
        assertThat(path).hasSize(3);
        assertThat(path.stream().mapToInt(Vertex::getValue).sum()).isEqualTo(9);
    }
}
