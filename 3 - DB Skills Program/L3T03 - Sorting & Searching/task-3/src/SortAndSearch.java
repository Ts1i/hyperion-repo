

public class SortAndSearch {

    // ## Question 1: Which searching algorithm would be appropriate to use on the given list?
    // Answer: Linear Search & Binary search would be appropriate to use.
    // Linear search has a time complexity of O(n), which is fine in the case of this small array.
    // Binary search has a time complexity of O(log n), which is better than linear search. But, in the case of this small array, the difference is negligible.
  
    // ## Question 2: Why is linear search a good choice?
    // Linear Search works well for a one-time search on this small array.
    // Linear search also does not require the array to be sorted.

    // Linear Search - best for one-time search on a small array
    public static int linearSearch(int[] arr, int target) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
          return i;
        }
      }
      return -1;  // If the element is not found
    }

  // ## Question 3: Insertion Sort
  public static void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      int key = arr[i];
      int j = i - 1;

      /* Take a section of your list (from the beginning up to a certain point).
      If any items in that section are bigger than a specific value (called 'key'),
      shift those bigger items over to the right to make space for 'key' to be inserted in the correct sorted position. */
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }

    // ## Question 4: Binary Search - in the real world, this is great for multiple searches after sorting
    public static int binarySearch(int[] arr, int target) {
      
      // Sort array
      insertionSort(arr);

      int left = 0;
      int right = arr.length - 1;

      while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
          return mid;
        }
        if (arr[mid] < target) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
      return -1;  // Element not found
    }

  public static void main(String[] args) {
    int[] numbers = {27, -3, 4, 5, 35, 2, 1, -40, 7, 18, 9, -1, 16, 100};
    int target = 9;  // Number to search for

    // Print original array
    System.out.println("Original array: " + java.util.Arrays.toString(numbers));

    // Using Linear Search
    int linearResult = linearSearch(numbers, target);
    System.out.println("\nLinear Search: Number " + target + " found at index " + linearResult);

    // Print sorted array using Insertion Sort
    insertionSort(numbers);
    System.out.println("\nSorted array: " + java.util.Arrays.toString(numbers));

    // Using Binary Search
    int[] numbersCopy = numbers.clone();  // Create copy to preserve original array
    int binaryResult = binarySearch(numbersCopy, target);
    System.out.println("\nBinary Search: Number " + target + " found at index " + binaryResult);
  }
}
