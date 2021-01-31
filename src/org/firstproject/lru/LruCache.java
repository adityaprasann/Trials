package org.firstproject.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    private static final int CAPACITY = 4;
    private static DblLinkList list = new DblLinkList();
    private static Map<Integer, Integer> cacheMap = new HashMap<>(CAPACITY);

    public static void main(String[] args) {
        LruCache cache = new LruCache();
        cache.insertIntoCache(1);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(2);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(3);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(1);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(4);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(5);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(4);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
        cache.insertIntoCache(6);
        list.printElements();
        System.out.println("Map values -> " + cacheMap.values());
    }

    private void insertIntoCache(int data){
        if(cacheMap.size() >= CAPACITY){
            int removeElement = list.removeFromTail();
            cacheMap.remove(removeElement);
        }
        if(cacheMap.containsKey(data))
            list.updateHeadNode(data);
        else{
            list.insertAtHead(data);
            cacheMap.put(data, data);
        }
    }

    private int getFromCache(int data){
        return cacheMap.get(data);
    }

}
