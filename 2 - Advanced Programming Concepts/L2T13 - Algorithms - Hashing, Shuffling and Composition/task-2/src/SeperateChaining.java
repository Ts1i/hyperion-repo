public class SeperateChaining {
  // Node class for linked list implementation
  private class Node {
    String value;
    Node next;
    
    Node(String value) {
      this.value = value;
      this.next = null;
    }
  }

  private Node[] hashTable;
  private int size;

  // Constructor: Initializes a hash table with a given capacity
  public SeperateChaining(int capacity) {
    hashTable = new Node[capacity];
    size = 0;
  }

  // Hash function to determine index
  private int hash(String key) {
    return Integer.parseInt(key) % hashTable.length;
  }

  // Inserts a value into the hash table using separate chaining
  public void insert(String value) {
    int index = hash(value);
    
    // Create new node
    Node newNode = new Node(value);
    
    // If bucket is empty
    if (hashTable[index] == null) {
      hashTable[index] = newNode;
    } else {
      // Add to front of chain (like a linked list)
      newNode.next = hashTable[index];
      hashTable[index] = newNode;
    }
    size++;
  }

  // Display method to show chains at each index
  public void display() {
    System.out.println("\nHash Table:");
    for (int i = 0; i < hashTable.length; i++) {
      System.out.printf("[%d] ", i);
      Node current = hashTable[i];
      if (current == null) {
        System.out.println("null");
      } else {
        while (current != null) { // Traverse chain and print values
          System.out.printf("%s -> ", current.value);
          current = current.next;
        }
        System.out.println("null"); // End of chain
      }
    }
  }

  public static void main(String[] args) {
    SeperateChaining table = new SeperateChaining(9);
    
    // Manual insertions as per task requirements
    table.insert("55");
    table.insert("22");
    table.insert("19");
    table.insert("1");
    table.insert("111");
    table.insert("39");
    table.insert("72");
    table.insert("3");
    table.display();
  }
}