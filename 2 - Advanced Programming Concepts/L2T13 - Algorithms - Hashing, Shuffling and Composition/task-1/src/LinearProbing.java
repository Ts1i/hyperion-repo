
public class LinearProbing {

  private String[] hashTable;
  private int size;
  private static final String DELETED = "DELETED"; // Marker for deleted slots

  // Constructor to initialize the hash table
  public LinearProbing(int capacity) {
    hashTable = new String[capacity];
    size = 0;
  }

  // Converts a string key into a valid array index for the hash table.
  private int hash(String key) {
    return Math.abs(key.hashCode() % hashTable.length); // Take the absolute value so that the array index is non-negative
  }

  // Remove values using linear probing
  public boolean remove(String value) {
    int index = hash(value);
    int startIndex = index;

    // Probe until we find the value or an empty slot
    while (hashTable[index] != null) {
      if (hashTable[index].equals(value)) {
        hashTable[index] = DELETED; // Mark the slot as "DELETED"
        size--; // Decrement the size
        return true; // Return that removal was successful
      }
      index = (index + 1) % hashTable.length; // Increments the index and wraps around to 0 if necessary.
      if (index == startIndex) { // Break if we've searched the entire table
        break; 
      }
    }
    
    return false;
  }

  // Display the hash table
  public void display() {
    System.out.println("\nHash Table:");
    for (int i = 0; i < hashTable.length; i++) {
      System.out.printf("[%d] %s%n", i, hashTable[i] == null ? "null" : hashTable[i]); // Print the keys and values of the hash table. Null if the slot is empty.
    }
  }

  public static void main(String[] args) {
    LinearProbing table = new LinearProbing(10);

    // Manually inserting values into the hash table for testing
    table.hashTable[table.hash("banana")] = "banana";
    table.hashTable[table.hash("orange")] = "orange";
    table.size = 2; // Update the size accordingly
    table.display();

    // Test removal
    System.out.println("\nRemoving 'banana'...");
    table.remove("banana");
    table.display();

  }
}
