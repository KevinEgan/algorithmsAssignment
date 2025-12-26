
import java.util.HashMap;

class LRUCache {
    // Node class for doubly linked list
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> cache;
    private int capacity, size;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.size = 0;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Move a node to the front (most recently used)
    private void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }

    // Add a node to the front of the list
    private void addToFront(Node node) {
        // inserts a node right after the dummy head
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Remove a node from the list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Get the value of a key if it exists in the cache
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key not found
        }
        moveToFront(node);
        return node.value;
    }

    // Insert a key-value pair into the cache
    public void put(int key, int value) {
        Node node = cache.get(key);

        //if the key exists, update its value and move it to the front
        if (node != null) {
            node.value = value;
            moveToFront(node);
        } 
        //if it's a new key, remove the least recently used from linked list & hashmap
        else {
            //make sure cache is not full
            if (size == capacity) {
                //remove the least recently used item
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key);
                size--;
            }
            //add new node to the front & hashmap
            Node newNode = new Node(key, value);
            addToFront(newNode);
            cache.put(key, newNode);
            size++;
        }
    }

    // Print the cache for debugging
    public void printCache() {
        Node current = head.next;
        while (current != tail) {
            System.out.print("(" + current.key + ", " + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method to test the LRU Cache implementation
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3); // Cache capacity of 3
        // Insert items
        lruCache.put(1, 102345);
        lruCache.put(2, 102342);
        lruCache.put(3, 102303);
        lruCache.printCache();
        // Access key 2 (this will make key 2 the most recently used)
        System.out.println("Get 2: " + lruCache.get(2)); // Should return 2
        lruCache.printCache();
        // Insert a new key, which will evict key 1 (the LRU)
        lruCache.put(4, 105444);
        lruCache.printCache();
        // Access key 3
        System.out.println("Get 3: " + lruCache.get(3));
        lruCache.printCache();
        // Insert another new key, which will evict key 2 (the LRU)
        lruCache.put(5, 103455);
        lruCache.printCache();
    }
}