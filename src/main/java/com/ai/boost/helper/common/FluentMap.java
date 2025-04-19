package com.ai.boost.helper.common;

import java.util.HashMap;

public class FluentMap<K,V> extends HashMap<K, V> {

    public FluentMap<K, V> chainPut(K key, V value) {
        super.put(key, value);
        return this;
    }
}
