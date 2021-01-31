package org.firstproject.lru;

public class DblLinkList {
    private CacheNode head;
    private CacheNode tail;

    public void insertAtHead(int data){
        CacheNode newNode = new CacheNode(data, null, null);
        if(head == null){
            head = tail = newNode;
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
        }
    }

    public int removeFromTail() {
        int tailVal = tail.payload;
        if(head.payload == tail.payload){
            head = tail = null;
        } else {
            CacheNode temp = tail.previous;
            temp.setNext(null);
            tail = temp;
        }
        return tailVal;
    }

    public void updateHeadNode(int data) {
        CacheNode temp = head;
        while(temp.payload != data)
            temp = temp.next;
        CacheNode previousNode = temp.previous;
        CacheNode nextNode = temp.next;
        if(previousNode !=  null)
            previousNode.setNext(nextNode);
        else
            head = nextNode;
        if(nextNode !=  null)
            nextNode.previous = previousNode;
        else
            tail = previousNode;
        insertAtHead(data);

    }

    public void printElements() {
        CacheNode temp = head;
        while(temp !=  null) {
            System.out.print(temp.payload + " -> ");
            temp = temp.next;
        }
        System.out.println();
    }
}
