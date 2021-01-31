package org.firstproject.lru;

import java.util.concurrent.LinkedBlockingDeque;

public interface CachingStrategy<K> {
    K evictElement(LinkedBlockingDeque<K> trackingQueue);
    void addElement(LinkedBlockingDeque<K> trackingQueue, K element);
}

enum CachePolicy{
    LRU,
    MRU
}
