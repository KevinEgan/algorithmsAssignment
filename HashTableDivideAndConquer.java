
import java.util.Arrays;

public class HashTableDivideAndConquer {
    private String[] table;
    private int capacity;
    private int size;
    // Constructor to initialize the hash table with a given capacity
    public HashTableDivideAndConquer(int capacity) {
    //<<fill in this method>>
    this.table = new String[capacity];
    this.capacity = capacity;
    this.size = 0;
    }
    // Simple hash function to map a string key to an index
    private int hash(String key) {
    //iterate through the array to find the first empty slot, which also includes a slot that was deleted (null)
    for (int i = 0; i<capacity; i++){
        if (table[i] == null){
            return i;
        }
        
    }
    //if the table is full return -1 (used in insert method to trigger resize)
    return -1;
        

    }
    // Insert a key into the hash table
    public void insert(String key) {
        if((double)size / capacity >= 0.75){
            resize();
        }

        //use the hash function to find the index and insert the key there
        table[hash(key)] = key;
        size ++;

    }
    //search for a key in the hash table
    public boolean search(String key) {
    for (int i =0;i < capacity; i++){
        if (table[i] != null && table[i].equals(key)){
            return true;
        }
    }
    return false;
    }
    // Delete a key from the hash table
    public void delete(String key) {
    //this will iterate through the array to find the key and set that index to null
    for (int i =0;i < capacity; i++){
        if (table[i] != null && table[i].equals(key)){
            table[i] = null;
            size --;
            return;
        }
    }
    }
    // Divide and Conquer Resize: Rehash the table by dividing the task into smaller chunks
    private void resize() {
        capacity *= 2;
        rehash();
    }
    // Rehash remaining elements to fill gaps after a deletion (Divide and Conquer Approach)
    private void rehash() {
    //create a new table and reinsert all non-null elements
    //note that in the case of a deleted node, the null value that is created will eventually get filled in by the insert (which calls the hash method) method.
    String[] oldTable = table;
    table = new String[capacity];
    size = 0;
    for (String key : oldTable) {
        if (key != null) {
            insert(key);
        }
    }

    }
    // Print the hash table for debugging
    public void printTable() {
    System.out.println(Arrays.toString(table));
    }

    public static void main(String[] args) {
    HashTableDivideAndConquer unihashTable = new HashTableDivideAndConquer(20);
    // Insert keys
    unihashTable.insert("ATU Letterkenny");
    unihashTable.insert("ATU Killybegs");
    unihashTable.insert("ATU Sligo");
    unihashTable.insert("ATU Galway Mayo");
    unihashTable.insert("ATU Killybegs");
    
    //add 15 more sample university campuses
    unihashTable.insert("DCU");
    unihashTable.insert("TCD");
    unihashTable.insert("UCD");
    unihashTable.insert("UCC");
    unihashTable.insert("DIT");
    unihashTable.insert("NUIG");
    unihashTable.insert("UL");
    unihashTable.insert("MU");
    unihashTable.insert("QUB");
    unihashTable.insert("UU");
    unihashTable.insert("Maynooth");
    unihashTable.insert("LIT");
    unihashTable.insert("WIT");
    unihashTable.insert("GMIT");
    unihashTable.insert("IT Sligo");
    unihashTable.insert("IT Tallaght");
    unihashTable.insert("IT Carlow");

    // Print the hash table
    unihashTable.printTable();
    // Search for a key
    System.out.println("Is 'ATU Sligo' in the table? " + unihashTable.search("ATU Sligo")); // true
    System.out.println("Is 'ATU Dundalk' in the table? " + unihashTable.search("ATU Dundalk"));// false
    
    // Delete a key
    unihashTable.delete("ATU Galway Mayo");
    unihashTable.printTable();
    }
}