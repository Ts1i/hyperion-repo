import java.util.ArrayList;

public class BubbleSort {
  public static void bubbleSort(ArrayList<String> list) {
    int n = list.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        // Compare adjacent elements
        if (list.get(j).compareToIgnoreCase(list.get(j + 1)) > 0) {
          // Swap elements
          String temp = list.get(j);
          list.set(j, list.get(j + 1));
          list.set(j + 1, temp);
        }
      }
    }
  }

  public static void main(String[] args) {
    
    // Create an ArrayList and manually add the elements
    ArrayList<String> words = new ArrayList<>();
    words.add("blue");
    words.add("six");
    words.add("game");
    words.add("unorthodox");
    words.add("hello");
    words.add("referee");
    words.add("ask");
    words.add("zebra");
    words.add("run");
    words.add("flex");

    System.out.println("Original list:");
    System.out.println(words);

    // Sort the list
    bubbleSort(words);

    System.out.println("\nSorted list:");
    System.out.println(words);
  }
}