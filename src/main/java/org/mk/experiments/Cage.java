package org.mk.experiments;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Cage<T> {
    private T t;

    public T get() {
        return t;
    }

    public void put(T t) {
        this.t = t;
    }
}
