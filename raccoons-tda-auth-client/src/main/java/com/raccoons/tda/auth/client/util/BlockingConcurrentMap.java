package com.raccoons.tda.auth.client.util;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class BlockingConcurrentMap<K, V> {

    private final int CAPACITY = 1;

    private Map<K, BlockingQueue<V>> map = new ConcurrentHashMap<>();

    private BlockingQueue<V> getQueue(final K key, final boolean replace) {
        return map.compute(key, (k, v) -> replace || v == null ? new ArrayBlockingQueue<>(CAPACITY) : v);
    }

    public void put(K key, V value) {
        getQueue(key, true).add(value);
    }

    public V get(K key) throws InterruptedException {
        return getQueue(key, false).take();
    }

    public V get(K key, long timeout, TimeUnit unit) throws InterruptedException {
        return getQueue(key, false).poll(timeout, unit);
    }

}
