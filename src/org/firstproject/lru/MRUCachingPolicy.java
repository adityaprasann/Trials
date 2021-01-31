package org.firstproject.lru;

import java.util.concurrent.LinkedBlockingDeque;

public class MRUCachingPolicy<K> implements CachingStrategy<K> {

    public CachePolicy policy;

    public MRUCachingPolicy(CachePolicy policy) {
        this.policy = policy;
    }

    @Override
    public K evictElement(LinkedBlockingDeque<K> trackingQueue) {
        return trackingQueue.removeFirst();
    }

    @Override
    public void addElement(LinkedBlockingDeque<K> trackingQueue, K element) {
        trackingQueue.addFirst(element);
    }
}
