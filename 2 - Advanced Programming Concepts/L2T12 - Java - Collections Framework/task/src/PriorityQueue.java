import java.util.ArrayList;

public class PriorityQueue {

  private String[] elements;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;

  public PriorityQueue(ArrayList<String> list) {
    // Manual conversion from ArrayList to array
    elements = new String[DEFAULT_CAPACITY];
    size = 0;
    
    // Manual iteration through ArrayList
    for (String item : list) {
      offer(item);
    }
}

  // OFFER: Add element to queue and maintain priority order. 
  // The next element added will be placed in the next available position in the array as size is incremented upwards with "size++".
  public void offer(String element) {
    
    if (size == elements.length) { // Check whether the array is full. If so, resize the array.
      resize();
    }

    elements[size++] = element;
    sort(); // Sort the elements in natural order (higher priority first)
  }

  // Doubles the array capacity when it's full
  private void resize() {
    String[] newElements = new String[elements.length * 2];
    for (int i = 0; i < elements.length; i++) {
      newElements[i] = elements[i];
    }
    elements = newElements;
  }

  // SORT: (Bubble) sort elements in "natural" order (higher priority first).
  // For strings, this is alphabetical order.
  private void sort() {
    for (int i = 0; i < size - 1; i++) {
      for (int j = 0; j < size - i - 1; j++) {
        if (elements[j].compareTo(elements[j + 1]) > 0) {
          // Swap elements
          String temp = elements[j];
          elements[j] = elements[j + 1];
          elements[j + 1] = temp;
        }
      }
    }
  }

  // REMOVE: Remove and return highest priority element (first element)
  public String remove() {
    if (size == 0) {
      throw new IllegalStateException("Queue is empty");
    }
    String element = elements[0]; // Access first element for return and removal

    // Shift elements left
    for (int i = 0; i < size - 1; i++) { // Loop up to the second last element because elements are shifting left
      elements[i] = elements[i + 1]; // Replace element with the next element
    }
    elements[--size] = null; // Update size and remove the element to the right of the last element
    return element;
  }

  // SIZE: Get current size of queue. 
  // The size is incremented when an element is added and decremented when an element is removed.
  public int size() {
    return size;
  }

  // Test the implementation
  public static void main(String[] args) {
    // Test 1: Initialize with ArrayList
    ArrayList<String> list = new ArrayList<>();
    list.add("Chris");
    list.add("Andile");
    PriorityQueue queue = new PriorityQueue(list);
    System.out.println("Initial queue size: " + queue.size());  // Should print 2

    // Test 2: Test offer method
    queue.offer("Bucie");
    System.out.println("Size after offer: " + queue.size());  // Should print 3

    // Test 3: Test ordering after offer
    System.out.println("\nRemoving elements to verify order:");
    System.out.println(queue.remove());  // Should print "Andile"
    System.out.println(queue.remove());  // Should print "Bucie"
    System.out.println(queue.remove());  // Should print "Chris"

    // Test 4: Test offer with resizing
    for (int i = 0; i < 11; i++) {  // Exceed DEFAULT_CAPACITY
      queue.offer("Test" + i);
    }
    System.out.println("\nSize after multiple offers: " + queue.size());  // Should print 11
  }
}
