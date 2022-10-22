package org.mk.experiments.financial;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
}
