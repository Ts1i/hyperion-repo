public class bigo {
  
  private int[] list;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;

  private int[][] matrix;
  private int rows;
  private int cols;

  public bigo() {
    list = new int[DEFAULT_CAPACITY];
    size = 0;
    matrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    rows = cols = DEFAULT_CAPACITY;
  }

  // --------------------O(n) sequential insertion--------------------

  // Inserts an element at the stipulated position
  public void insert(int element, int position) {
    // Check if the list is full and we need to resize
    if (size == list.length) {
      resize();
    }

    // Shift elements to make room for new element
    for (int i = size; i > position; i--) {
      list[i] = list[i - 1];
    }

    // Insert the new element at the position
    list[position] = element;
    size++; // Increment the size of the list
  }

  // Resize the list by doubling its capacity
  private void resize() {
    int[] newList = new int[list.length * 2];
    for (int i = 0; i < list.length; i++) {
      newList[i] = list[i];
    }
    list = newList;
  }

  // Loops through and displays the list
  public void display() {
    System.out.print("List: ");
    for (int i = 0; i < size; i++) {
      System.out.print(list[i] + " ");
    }
    System.out.println();
  }

  // --------------------O(n^2) matrix insertion--------------------

  // Inserts an element at the stipulated position, given by the row and column
  public void insert2D(int element, int row, int col) {
    if (row >= rows || col >= cols || row < 0 || col < 0) { // Check if the position is valid
      throw new IllegalArgumentException("Invalid position: [" + row + "," + col + "]");
    }

    // Shift all elements after the insertion point down and to the right
    for (int i = rows - 1; i >= row; i--) {
      for (int j = cols - 1; j > col; j--) {
        if (i > 0 && j > 0) {  // Check bounds before accessing i-1, j-1
        matrix[i][j] = matrix[i-1][j-1];
        }
      }
    }

    // Insert the new element
    matrix[row][col] = element;
  }

  // Display 2D array
  public void display2D() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.printf("%4d", matrix[i][j]);
      }
      System.out.println();
    }
  }

  // --------------------O(log n) binary insertion--------------------
  
  // Insert an element into the list in sorted order
  public void insertSorted(int element) {
    // Check if resize needed
    if (size == list.length) {
      resize();
    }

    // Find insertion position using binary search: O(log n)
    int position = findInsertPosition(element, 0, size - 1);

    // Shift elements to make room: O(n)
    for (int i = size; i > position; i--) {
      list[i] = list[i - 1];
    }

    // Insert element at position
    list[position] = element;
    size++;
  }

  private int findInsertPosition(int element, int left, int right) {
    // If array is empty or we've searched whole range
    if (right < left) {
      return left;
    }

    // calculates the middle position for binary search. Avoids overflow.
    int mid = left + (right - left) / 2;

    // If we've reached the end of the array
    if (mid >= size) {
      return size;
    }

    // If element equals middle element
    if (element == list[mid]) {
      return mid;
    }
    // If element is greater than middle element
    else if (element > list[mid]) {
      return findInsertPosition(element, mid + 1, right);
    }
    // If element is less than middle element
    else {
      return findInsertPosition(element, left, mid - 1);
    }
  }

  public static void main(String[] args) {
    bigo demo = new bigo();

    // Test 1D insertions
    demo.insert(10, 0);
    demo.insert(20, 1);
    demo.insert(15, 1);
    demo.insert(5, 0);
    
    System.out.println("\n1D Array:");
    demo.display();

    // Test O(n^2) 2D insertions
    try {
      demo.insert2D(1, 1, 1);
      demo.insert2D(2, 2, 2);
      demo.insert2D(3, 0, 0);
      demo.insert2D(4, 3, 3);
      
      System.out.println("\n2D Array:");
      demo.display2D();

    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Test binary insertion (maintains sorted order)
    demo.insertSorted(5);   // [5]
    demo.insertSorted(10);  // [5, 10]
    demo.insertSorted(7);   // [5, 7, 10]
    demo.insertSorted(3);   // [3, 5, 7, 10]
    demo.insertSorted(8);   // [3, 5, 7, 8, 10]
    
    System.out.println("\nSorted Array:");
    demo.display();

  }

}