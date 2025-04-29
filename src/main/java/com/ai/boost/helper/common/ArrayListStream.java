package com.ai.boost.helper.common;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStream<T> extends ArrayList {
    private T t;
    public <E> ArrayListStream<T> addAll(List<E> arr) {
        super.add(arr);
        return this;
    }
}
