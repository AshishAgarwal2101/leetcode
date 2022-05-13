/**
 *
 * 146. LRU Cache::::::::
 * 
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * 
 * Implement the LRUCache class:
 * 
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *  
 * 
 * Constraints:
 * 
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 * 
*/



import java.util.*;

class Node {
    int key;
    int val;
    Node next;
    Node prev;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    Map<Integer, Node> data;
    Node head;
    Node tail;
    int capacity;
    int filled;

    public LRUCache(int capacity) {
        data = new HashMap<>();
        this.capacity = capacity;
        this.filled = 0;
    }
    
    public int get(int key) {
        Node node = getExistingNode(key);
        if(node == null){
            return -1;
        }
        
        return node.val;
    }
    
    public void put(int key, int value) {
        Node existingNode = getExistingNode(key);
        if(existingNode != null){
            existingNode.val = value;
            return;
        }
        
        Node node = new Node(key, value);
        if(filled == capacity){
            removeNode(tail);
            filled--;
        }
        addNode(node);
        filled++;
    }
    
    private Node getExistingNode(int key){
        Node node = data.get(key);
        if(node == null){
            return null;
        }
        
        removeNode(node);
        addNode(node);
        
        return node;
    }
    
    private  void addNode(Node node){
        data.put(node.key, node);
        
        if(tail == null){ //head==tail==null
            tail = node;
        }
        
        node.next = head;
        if(head != null){
            head.prev = node;
        }
        head = node;
    }
    
    private void removeNode(Node node){
        data.remove(node.key);
        
        if(head == tail){ //head==tail==node
            head = null;
            tail = null;
            return;
        }
        
        if(node == head){
            head = node.next;
        }
        else{
            node.prev.next = node.next;
        }
        
        if(node == tail){
            tail = node.prev;
        }
        else {
            node.next.prev = node.prev;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
