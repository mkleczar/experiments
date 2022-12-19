package org.mk.experiments.graph;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Node<T> {
    @EqualsAndHashCode.Include
    private final T value;

    public static <T> Node<T> of(T t) {
        return new Node<>(t);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
