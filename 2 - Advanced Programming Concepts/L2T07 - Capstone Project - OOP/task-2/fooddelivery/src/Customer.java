public class Customer {

  private String name;
  private String contactNumber;
  private String address;
  private String location;
  private String email;

  public Customer(String name, String contactNumber, String address, String location, String email) {
    this.name = name;
    this.contactNumber = contactNumber;
    this.address = address;
    this.location = location;
    this.email = email;
  }

  // Getters
  public String getLocation() {
    return location;
  }

  public String getName() {
    return name;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "Customer: " + name + "\nContact: " + contactNumber
      + "\nAddress: " + address + "\nLocation: " + location
      + "\nEmail: " + email;
  }
}
