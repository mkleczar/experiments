package org.mk.experiments.financial;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Matrix {


    private final Map<String, Map<String, BigDecimal>> matrix = new HashMap<>();

    public Matrix add(String left, String up, BigDecimal value) {
        BigDecimal current = matrix
                .computeIfAbsent(left, k -> new HashMap<>())
                .computeIfAbsent(up, k -> BigDecimal.ZERO);
        matrix.get(left).put(up, current.add(value));
        return this;
    }

    public Matrix net(String left, String up, BigDecimal value) {

        if (left.equals(up)) {
            return this;
        }

        BigDecimal current = matrix
                .computeIfAbsent(left, k -> new HashMap<>())
                .computeIfAbsent(up, k -> BigDecimal.ZERO);
        BigDecimal reverse = matrix
                .computeIfAbsent(up, k -> new HashMap<>())
                .computeIfAbsent(left, k -> BigDecimal.ZERO);

        matrix.get(left).put(up, current.add(value));
        matrix.get(up).put(left, reverse.subtract(value));
        return this;
    }

    @Override
    public String toString() {
        List<String> users = matrix.keySet().stream().sorted().collect(Collectors.toList());
        int cellLength = 10;

        return "\n" +
                header(cellLength, users) +
                "\n" +
                rows(cellLength, users, "\n") +
                "\n";
    }

    private String header(int cellLength, List<String> names) {
        return format(cellLength, "|") +
                names.stream()
                        .map(s -> format(cellLength, s))
                        .collect(Collectors.joining("|"));
    }
    private String rows(int cellLength, List<String> names, String delimiter) {
        return names.stream()
                .map(r -> row(cellLength, r, names))
                .collect(Collectors.joining(delimiter));
    }

    private String row(int cellLength, String rowKey, List<String> names) {
        return format(cellLength, rowKey+"|") +
                names.stream()
                        .map(c -> format(cellLength, matrix.get(rowKey).getOrDefault(c, BigDecimal.ZERO)))
                        .collect(Collectors.joining("|"));
    }

    private String format(int size, Object value) {
        return String.format("%"+size+"s", value.toString());
    }
}
