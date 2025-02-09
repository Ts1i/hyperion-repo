
public class PriorityQueue {

  private String[] elements;
  private int size;
  private static final int INITIAL_CAPACITY = 10;

  public PriorityQueue() {
    elements = new String[INITIAL_CAPACITY];
    size = 0;
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
    PriorityQueue queue = new PriorityQueue();

    // Add elements
    queue.offer("Chris");
    queue.offer("Andile");
    queue.offer("Bucie");

    System.out.println("Queue size: " + queue.size());  // Should print the full queue size of 3 

    // Remove elements (should come out in alphabetical order)
    System.out.println(queue.remove());  // Should print "Andile"
    System.out.println(queue.remove());  // Should print "Bucie"
    System.out.println(queue.remove());  // Should print "Chris"

    System.out.println("Queue size: " + queue.size());  // Should print the empty queue size of 0
  }
}
