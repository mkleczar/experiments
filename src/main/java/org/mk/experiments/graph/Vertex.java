package org.mk.experiments.graph;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Vertex<T, N> {
    private final Node<N> from;
    private final T value;
    private final Node<N> to;

    public static <T,N> Vertex<T,N> of(Node<N> from, T value, Node<N> to) {
        return new Vertex<>(from, value, to);
    }

    @Override
    public String toString() {
        return String.format("%s -- %s --> %s", from, value, to);
    }
}
