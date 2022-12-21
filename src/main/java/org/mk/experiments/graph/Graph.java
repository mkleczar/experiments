package org.mk.experiments.graph;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Graph<N,V extends Comparable<V>> {
    @Singular
    private final List<Node<N>> nodes;
    @Singular
    private final List<Vertex<V,N>> vertices;

    public List<Vertex<V,N>> fromNode(Node<N> node) {
        return vertices.stream()
                .filter(v -> v.getFrom().equals(node))
                .sorted(Comparator.comparing(Vertex::getValue))
                .collect(Collectors.toList());
    }

    public List<Vertex<V,N>> shortestPath(Node<N> from, Node<N> to) {
        if (from.equals(to)) {
            return Collections.emptyList();
        }

        Set<Node<N>> visited = new HashSet<>();
        PriorityQueue<Vertex<V,N>> next = new PriorityQueue<>(Comparator.comparing(Vertex::getValue));
        Map<Node<N>, Vertex<V,N>> pathMap = new HashMap<>();
        visited.add(from);
        next.addAll(fromNode(from));
        while (!next.isEmpty()) {
            Vertex<V,N> current = next.remove();
            if (current.getTo().equals(to)) {
                pathMap.put(current.getTo(), current);
                return extractPath(pathMap, from, to);
            }
            if (visited.contains(current.getTo())) {
                continue;
            }
            visited.add(current.getTo());
            pathMap.put(current.getTo(), current);
            next.addAll(fromNode(current.getTo()));
        }
        return Collections.emptyList();
    }

    private List<Vertex<V,N>> extractPath(Map<Node<N>, Vertex<V,N>> pathMap, Node<N> from, Node<N> to) {
        Stack<Vertex<V,N>> result = new Stack<>();
        Node<N> current = to;
        while (!current.equals(from)) {
            var v = pathMap.get(current);
            result.push(v);
            current = v.getFrom();
        }
        Collections.reverse(result);
        return result;

    }

    @Override
    public String toString() {
        return "" + nodes.stream().map(Node::toString).collect(Collectors.joining("\n")) +
                "\n" +
                vertices.stream().map(Vertex::toString).collect(Collectors.joining("\n"));
    }
}
