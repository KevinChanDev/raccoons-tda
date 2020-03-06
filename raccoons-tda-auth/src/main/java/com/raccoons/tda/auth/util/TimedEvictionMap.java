package com.raccoons.tda.auth.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TimedEvictionMap<K, V> {

    private long defaultDelay;
    private Map<K, V> data;
    private Map<V, LastUsageNode> lastUsageMap;
    private DelayQueue<LastUsageNode> delayQueue;

    public TimedEvictionMap(long defaultDelay) {
        this.defaultDelay = defaultDelay;
        this.data = new ConcurrentHashMap<>();
        this.lastUsageMap = new ConcurrentHashMap<>();
        this.delayQueue = new DelayQueue<>();
    }

    public V getAndNotify(final K key) {
        return null;
    }

    public boolean put(K key, V value) {
        return true;
    }

    private class LastUsageNode implements Delayed {

        final AtomicLong mostRecent;
        V data;

        public LastUsageNode(long mostRecent) {
            this.mostRecent = new AtomicLong(mostRecent);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = mostRecent.get() - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            final long delay = getDelay(TimeUnit.MILLISECONDS);
            final long delay2 = delayed.getDelay(TimeUnit.MILLISECONDS);
            if (delay < delay2) {
                return -1;
            } else if (delay > delay2) {
                return 1;
            }
            return 0;
        }

        void notify(final long time) {
            if (time > mostRecent.get()) {
                mostRecent.set(time);
            }
        }

    }

}
