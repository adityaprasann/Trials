package org.firstproject.lru;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheProgram<K, V> {

    private final int limit;
    private final Map<K, V> internalCache;
    private final LinkedBlockingDeque<K> trackingQueue;
    private final CachingStrategy<K> cachePolicy;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public CacheProgram(int limit, CachePolicy policy){
        internalCache = new ConcurrentHashMap<>();
        trackingQueue = new LinkedBlockingDeque<>();
        this.limit = limit;
        if(policy == CachePolicy.LRU)
            cachePolicy = new LRUCachingPolicy<>(CachePolicy.LRU);
        else
            cachePolicy = new MRUCachingPolicy<>(CachePolicy.MRU);
    }

    public V get(K key) {
        this.readWriteLock.readLock().lock();
        try {
            V value = internalCache.get(key);
            if (value != null) {
                if (trackingQueue.remove(key))
                    cachePolicy.addElement(trackingQueue, key);
            }
            return value;
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        this.readWriteLock.writeLock().lock();
        try {
            if (internalCache.containsKey(key)) {
                trackingQueue.remove(key);
            }
            if (trackingQueue.size() == limit) {
                K expiredKey = cachePolicy.evictElement(trackingQueue);
                internalCache.remove(expiredKey);
            }
            internalCache.put(key, value);
            trackingQueue.add(key);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] arg) throws InterruptedException {
        CacheProgram<Integer, String> cacheLRU = new CacheProgram<>(4, CachePolicy.LRU);
        CacheProgram<Integer, String> cacheMRU = new CacheProgram<>(4, CachePolicy.MRU);

        Thread lruT1 = new Thread(() ->{
            cacheLRU.put(1, "Object1");
            cacheLRU.put(2, "Object2");
            cacheLRU.put(3, "Object3");
            cacheLRU.get(1);
            cacheLRU.put(4, "Object4");
            System.out.println(cacheLRU);
            cacheLRU.put(5, "Object5");
            cacheLRU.get(3);
        });

        Thread lruT2 = new Thread(() ->{
            cacheLRU.put(6, "Object6");
            System.out.println(cacheLRU);
            cacheLRU.get(4);
            cacheLRU.put(7, "Object7");
            cacheLRU.put(8, "Object8");
            System.out.println(cacheLRU);
        });

        Thread mruT1 = new Thread(() ->{
            cacheMRU.put(1, "Object1");
            cacheMRU.put(2, "Object2");
            cacheMRU.put(3, "Object3");
            cacheMRU.get(1);
            cacheMRU.put(4, "Object4");
            System.out.println(cacheMRU);
            cacheMRU.put(5, "Object5");
            cacheMRU.get(3);
        });

        Thread mruT2 = new Thread(() ->{
            cacheMRU.put(6, "Object6");
            System.out.println(cacheMRU);
            cacheMRU.get(4);
            cacheMRU.put(7, "Object7");
            cacheMRU.put(8, "Object8");
            System.out.println(cacheMRU);
        });
        lruT1.setName("LRU Thread 1");
        lruT2.setName("LRU Thread 2");
        mruT1.setName("MRU Thread 1");
        mruT2.setName("MRU Thread 2");

        lruT1.start();
        lruT2.start();
        mruT1.start();
        mruT2.start();

        lruT1.join();
        lruT2.join();
        mruT1.join();
        mruT2.join();
        System.out.println(cacheLRU);
        System.out.println(cacheMRU);
    }


    @Override
    public String toString() {
        return "CacheProgramThreadName -> " + Thread.currentThread().getName() + " Caching Policy - " + cachePolicy +
                " internalCache = " + internalCache;
    }
}
