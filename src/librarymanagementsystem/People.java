package librarymanagementsystem;

/**
 * People Class and Subclasses
 * @author mark solomon
 */

import java.util.*;

public abstract class People {
  protected String name;
  protected String address;
  protected String email;
  protected String phoneNumber;
  
  public People(String name, String address, String email, String phoneNumber){
    this.name = name;
    this.address = address;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
  
  public String getName(){
    return name;
  }
  
  public String getAddress(){
    return address;
  }
  
  public String getEmail(){
    return email;
  }
  
  public String getPhoneNumber(){
    return phoneNumber;
  }
  
  public void printInfo(){
    System.out.println("Name: " +name);
    System.out.println("Address: " +address);
    System.out.println("Email: " +email);
    System.out.println("Phone Number: " +phoneNumber);
  }
}

class Borrower extends People{  
  private int referenceId;
  Map<Book, String> borrowedBooksMap; // <Book, Due date>
  
  public Borrower(String name, String address, String email, String phone){
    super(name, address, email, phone);
    borrowedBooksMap = new HashMap<>();
  }
  
  public int getReferenceId(){
    return referenceId;
  }
  
  public void setReferenceId(int referenceId){
    this.referenceId = referenceId;
  }
  
  public Map<Book, String> getBorrowedBooksMap(){
    return borrowedBooksMap;
  }
  
  public void borrowBook(Book book, String date){
    borrowedBooksMap.put(book, date);
  }
  
  public void returnBook(String title){
    Book book;
    try {
      book = searchFromBorrowedBooks(title);
    } catch (NullPointerException e){
      System.out.println(e.getMessage());
      return;
    }
    
    if(isBookBorrowed(title)){
      borrowedBooksMap.remove(book);
      System.out.println(name +" has returned '" +book.getTitle() +"'.");
    }
  }
  
  public boolean isBookBorrowed(String title){
    try{
      return (searchFromBorrowedBooks(title)) != null;
    } catch (NullPointerException e) {
      return false;
    }
  }
  
  private Book searchFromBorrowedBooks(String title) throws NullPointerException {
    if(borrowedBooksMap.isEmpty())
      throw new NullPointerException("\nThere are currently no borrowed books.");
    
    for (Map.Entry<Book, String> entry : borrowedBooksMap.entrySet()){
      Book book = entry.getKey();
      if (book.getTitle().equalsIgnoreCase(title)){
        return book; 
      }
    }
    throw new NullPointerException("\nThere are no borrowed copies of the book titled '" +title +"'.");
  }
 
  @Override
  public void printInfo(){
    System.out.println("\nBorrower Reference no. " +referenceId);
    super.printInfo();
    printBorrowedBooks();
  }

  private void printBorrowedBooks() {
    System.out.println("\nBorrowed Books.");
      if (!borrowedBooksMap.isEmpty()) {
      System.out.println("Title                Due Date");

      for (Map.Entry<Book, String> entry : borrowedBooksMap.entrySet()) {
        Book book = entry.getKey();
        String dueDate = entry.getValue();

        String title = book.getTitle().length() > 20 ? book.getTitle().substring(0, 20) : book.getTitle();
        System.out.printf("%-20s %-10s%n", title, dueDate);
      }
    } else {
      System.out.println("\nThere are no books currently borrowed.");
    }
  }
}
