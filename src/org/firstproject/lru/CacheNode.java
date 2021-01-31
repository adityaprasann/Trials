package org.firstproject.lru;

public class CacheNode {
    int payload;
    CacheNode previous;
    CacheNode next;

    public CacheNode(int payload, CacheNode previous, CacheNode next) {
        this.payload = payload;
        this.previous = previous;
        this.next = next;
    }

    public void setPrevious(CacheNode previous) {
        this.previous = previous;
    }

    public void setNext(CacheNode next) {
        this.next = next;
    }
}
