package com.raccoons.tda.auth.util;

import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.*;

/**
 * This data structure provides the following behavior. When a key is submitted with a time, it will disappear
 * from the queue and will not be retrieved until the time has expired, similar to a DelayQueue. This structure
 * extends that behavior by also allowing for the re modification of delays, allowing elements to change queueing
 * in real-time.
 *
 * @param <T>
 */

public class DelayedModificationQueue<T extends Comparable<T>> {

    private static final Object EMPTY = new Object();

    private final DelayQueue<DelayedNode<T>> delayedNodes = new DelayQueue<>();
    private final NavigableMap<T, Object> doneMap = new ConcurrentSkipListMap<>();
    private final Map<T, DelayedNode<T>> enqueuedDelayNodes = new ConcurrentHashMap<>();

    public DelayedModificationQueue() {
    }

    public long put(final T value, long delay) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        if (delay <= 0) {
            throw new IllegalArgumentException("Delay must be greater than 0, provided " + delay);
        }

        // Remove from list of done entries if it exists
        final Object removed = doneMap.remove(value);

        // If value is already enqueued, store it in duplicates as well
        DelayedNode<T> activeNode = enqueuedDelayNodes.get(value);

        // If the value already exists, then check if this new entry comes after
        long expiration = System.currentTimeMillis() + delay;

        if (activeNode == null) {
            final DelayedNode<T> node = createNode(value, expiration);
            delayedNodes.put(node);
            return expiration;
        } else if (expiration < activeNode.expiration) {
            delayedNodes.remove(activeNode);
            final DelayedNode<T> node = createNode(value, expiration);
            delayedNodes.put(node);
            return expiration;
        }
        return -1;
    }

    public T poll() {
        final Map.Entry<T, Object> entry = doneMap.pollFirstEntry();
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    public T take() throws InterruptedException {
        balance();
        final Map.Entry<T, Object> entry = doneMap.pollFirstEntry();
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    private void balance() throws InterruptedException {
        DelayedNode<T> element = delayedNodes.take();
        T value = element.value;
        enqueuedDelayNodes.remove(value);
        doneMap.put(value, EMPTY);
    }

    private DelayedNode<T> createNode(T value, long delayTime) {
        return new DelayedNode<>(value, delayTime);
    }

    private static class DelayedNode<T> implements Delayed {

        private T value;
        private long expiration;

        public DelayedNode(T value, long expiration) {
            this.value = value;
            this.expiration = expiration;
        }

        @Override
        public long getDelay(TimeUnit t) {
            return t.convert(expiration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed d) {
            if (d == null) {
                return 0;
            }
            if (this.expiration < ((DelayedNode<?>) d).expiration) {
                return -1;
            } else if (this.expiration > ((DelayedNode<?>) d).expiration) {
                return 1;
            }
            return 0;
        }
    }
}

