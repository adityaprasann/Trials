package org.firstproject.lru;

import java.util.concurrent.LinkedBlockingDeque;

public class LRUCachingPolicy<K> implements CachingStrategy<K> {

    public CachePolicy policy;

    public LRUCachingPolicy(CachePolicy policy) {
        this.policy = policy;
    }

    @Override
    public K evictElement(LinkedBlockingDeque<K> trackingQueue) {
        return trackingQueue.poll();
    }

    @Override
    public void addElement(LinkedBlockingDeque<K> trackingQueue, K element) {
        trackingQueue.add(element);
    }

}
