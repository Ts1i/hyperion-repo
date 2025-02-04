public class palindromeRecursion {
  
  public static boolean isPalindrome(String[] arr, int start, int end) {
    
    // Base case: array of length 0 or 1 is palindrome
    if (start >= end) {
      return true;
    }
    
    // If first and last elements don't match, not palindrome. 
    // Start and end values are shifted towards the center in the next recursive call.
    if (!arr[start].equals(arr[end])) {
      return false;
    }
    
    // Check the remaining elements by moving the start and end pointers towards the center
    return isPalindrome(arr, start + 1, end - 1);
  }  
  
  // Helper method that the user interacts with. 
  // Has one parameter so that you don't need to independently calculate the length of the array.
  public static boolean isPalindrome(String[] arr) {
    return isPalindrome(arr, 0, arr.length - 1);
  }

  public static void main(String[] args) {
    // Test cases
    String[] test1 = {"m", "a", "d", "a", "m"};
    String[] test2 = {"p", "a", "r", "e", "n", "t"};
    
    // Print results
    System.out.println("Test 1 (madam): " + isPalindrome(test1));
    System.out.println("Test 2 (parent): " + isPalindrome(test2));
  }

}