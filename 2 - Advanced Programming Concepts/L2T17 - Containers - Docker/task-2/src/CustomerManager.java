import java.util.Scanner;

public class CustomerManager {

  private DeliverySystem system;
  private DataStore dataStore;
  private Scanner scanner;

  public CustomerManager(DeliverySystem system, Scanner scanner) {
    this.system = system;
    this.scanner = scanner;
    this.dataStore = system.getDataStore();
  }

  // Get customer details from the user
  public Customer getCustomer() {
    System.out.print("Are you a new customer? (y/n): ");
    String isNew = scanner.nextLine().trim().toLowerCase();

    return isNew.equals("y") ? createNewCustomer() : getExistingCustomer();
  }

  // Create new customer
  private Customer createNewCustomer() {
    System.out.println("\nPlease enter your details:");
    System.out.print("Name: ");
    String name = scanner.nextLine().trim();
    System.out.print("Contact number: ");
    String contact = scanner.nextLine().trim();
    System.out.print("Address: ");
    String address = scanner.nextLine().trim();
    System.out.print("Location: ");
    String location = scanner.nextLine().trim();
    System.out.print("Email: ");
    String email = scanner.nextLine().trim().toLowerCase();

    Customer customer = new Customer(name, contact, address, location, email);
    dataStore.addCustomer(customer);
    return customer;
  }

  // Get existing customer details from email
  private Customer getExistingCustomer() {
    while (true) {
      System.out.print("Enter your email: ");
      String email = scanner.nextLine().trim().toLowerCase();
      Customer customer = dataStore.findCustomerByEmail(email);
      if (customer != null) {
        return customer;
      }
      System.out.println("Customer not found. Please try again.");
    }
  }

}
