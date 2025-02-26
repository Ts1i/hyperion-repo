import java.sql.*;

public class Main {
  public static void main(String[] args) {
    // Explicit loading of the MySQL JDBC driver
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC Driver not found.");
      e.printStackTrace();
      return;
    }
    
    // Database credentials
    String url = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
    String username = "otheruser";
    String password = "swordfish";
    
    try {
      // Create connection to mysql server
      Connection connection = DriverManager.getConnection(url, username, password);
      Statement statement = connection.createStatement();
      
      // Create database if it doesn't exist
      statement.executeUpdate("CREATE DATABASE IF NOT EXISTS library_db");
      statement.executeUpdate("USE library_db");

      // Drop existing table if it exists
      statement.executeUpdate("DROP TABLE IF EXISTS books");
      
      // Create table
      // This table will store book information
      // id: unique identifier for each book
      String createTableSQL = "CREATE TABLE IF NOT EXISTS books ("
        + "id INT PRIMARY KEY,"
        + "title VARCHAR(50),"
        + "author VARCHAR(50),"
        + "qty INT)";
      statement.executeUpdate(createTableSQL);
      
      // Insert data
      String[] insertSQL = {
        "INSERT INTO books (id, title, author, qty) VALUES (1001, 'Java for Beginners', 'John Holder', 5)",
        "INSERT INTO books (id, title, author, qty) VALUES (1002, 'Java Fundamentals', 'Sally Williams', 5)",
        "INSERT INTO books (id, title, author, qty) VALUES (1003, 'A Cup of Java', 'Peter Jones', 6)",
        "INSERT INTO books (id, title, author, qty) VALUES (1004, 'Introduction to Java', 'Kumar Singh', 6)",
        "INSERT INTO books (id, title, author, qty) VALUES (1005, 'Advanced Java', 'Kelly Fields', 7)"
      };
      
      // Execute insert statements to add data to the table
      for (String sql : insertSQL) {
        statement.executeUpdate(sql);
      }

      // Update quantity for 'Introduction to Java'
      String updateSQL = "UPDATE books SET qty = 0 WHERE title = 'Introduction to Java'";
      statement.executeUpdate(updateSQL);

      // Delete books with ID > 8000
      String deleteSQL = "DELETE FROM books WHERE id > 8000";
      statement.executeUpdate(deleteSQL);
      
      // Insert new books
      String[] newBooksSQL = {
        "INSERT INTO books (id, title, author, qty) VALUES (8001, 'Java ABC', 'Kevin Jones', 3)",
        "INSERT INTO books (id, title, author, qty) VALUES (8002, 'Java XYZ', 'Kevin Jones', 5)"
      };
      
      // Execute insert statements to add new books to the table
      for (String sql : newBooksSQL) {
        statement.executeUpdate(sql);
      }
      
      // Verify data by selecting and printing
      ResultSet rs = statement.executeQuery("SELECT * FROM books");
      System.out.println("Data in books table:");
      while (rs.next()) {
        System.out.printf("ID: %d, Title: %s, Author: %s, Quantity: %d%n",
          rs.getInt("id"),
          rs.getString("title"),
          rs.getString("author"),
          rs.getInt("qty"));
      }
      
      // Close resources
      rs.close();
      statement.close();
      connection.close();
        
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}