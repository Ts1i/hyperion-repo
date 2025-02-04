public class LCMRecursion {
    
  // Finds the greatest common divisor of two integers.
  private static int gcd(int a, int b) {
      if (b == 0) {
          return a;
      }
      return gcd(b, a % b);
  }
  
  // Calculate LCM using the greatest common divisor.
  public static int lcm(int x, int y) {
      return Math.abs(x * y) / gcd(x, y);
  }
  
  public static void main(String[] args) {
      // Test cases
      System.out.println("LCM of 20 and 25: " + lcm(20, 25));
      System.out.println("LCM of 9 and 14: " + lcm(9, 14));
  }
}