 package librarymanagementsystem;

/**
 * Library Management System
 * @author mark solomon
 */
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
import java.util.*;
 
public class LibraryManagementSystem {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    Library library = new Library();
    library.load();
    
    while(true){
      System.out.println("\nLibrary Management System*");
      System.out.println("1. Book menu.");
      System.out.println("2. Borrower menu.");
      System.out.println("3. Save and Exit.");
      System.out.print("Choose an option: ");
      int option_main = reader.nextInt(); 
      reader.nextLine(); 
      
      switch(option_main){
        case 1:{
          System.out.println("\nBook Menu&");
          System.out.println("1. Add book.");
          System.out.println("2. Search for a book.");
          System.out.println("3. Show all books.");
          System.out.println("4. Return to home page.");
          System.out.print("Choose an option: ");
          int option_book = reader.nextInt();
          reader.nextLine(); 
          
          switch(option_book){
            case 1:{
              System.out.println("\nAdd book.");
              
              System.out.print("Enter Book Title: ");
              String title = reader.nextLine();

              System.out.print("Enter Book Author: ");
              String author = reader.nextLine();

              System.out.print("What type of book is it? (fiction/nonfiction/academic): ");
              String type = reader.nextLine().toLowerCase();

              try {
                if (type.equals("fiction")) {
                  System.out.print("Enter Book Genre: ");
                  String genre = reader.nextLine();

                  System.out.print("Is it a series? (yes/no): ");
                  boolean isSeries;
                  try {
                    String query = reader.nextLine().toLowerCase();
                    if (query.equalsIgnoreCase("yes")){
                      isSeries = true;
                    } else if (query.equalsIgnoreCase("no")){
                      isSeries = false;
                    } else {
                      throw new IllegalArgumentException("Invalid type! Please enter either 'Yes', or 'No'.");
                    }
                  } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    break;
                  }
                    
                    library.addBook(new Fiction(title, author, "Fiction", genre, isSeries));
                  } else if (type.equalsIgnoreCase("nonfiction")) {
                    
                    System.out.print("Enter Book Topic: ");
                    String topic = reader.nextLine();
                    
                    System.out.print("Enter Book Edition: ");
                    String edition = reader.nextLine();
                    
                    library.addBook(new NonFiction(title, author, "NonFiction", topic, edition));
                  } else if (type.equalsIgnoreCase("academic")){
                    
                    System.out.print("Enter Book Subject: ");
                    String subject = reader.nextLine();
                    
                    System.out.print("Enter Book Edition: ");
                    String edition = reader.nextLine();
                    
                    library.addBook(new Academic(title, author, "Academic", subject, edition));
                  } else {
                    throw new IllegalArgumentException("Invalid type! Please enter either 'Fiction', 'NonFiction, or 'Academic'.");
                  }
              } catch (IllegalArgumentException e) {
                  System.out.println(e.getMessage());
              }
              break;
            }
            case 2:{
              System.out.println("\nSearch for a book.");
              
              System.out.print("Enter book title: ");
              String title = reader.nextLine().toLowerCase();
              
              library.searchBook(title);
              break;
            }
            case 3:{
              System.out.println("\nShow all books.");
              
              library.showBooks();
              break;
            }
            case 4:{
              System.out.println("\nReturning to home page.");
              break;
            }
            default:{
              System.out.println("Not a valid option.");
              break;
            }
          }
          break;
        }
        case 2:{
          System.out.println("\nBorrower Menu&");
          System.out.println("1. Add borrower.");
          System.out.println("2. Borrow a book.");
          System.out.println("3. Return a book.");
          System.out.println("4. Search for a borrower.");
          System.out.println("5. Show all borrowers.");
          System.out.println("6. Print borrower reciept (as text file).");
          System.out.println("7. Return to home page.");
          System.out.print("Choose an option: ");
          int option_borrower = reader.nextInt();
          reader.nextLine();
          
          switch(option_borrower){
            case 1:{
              System.out.println("\nAdd borrower.");
              
              System.out.print("Enter borrower's Name: ");
              String name = reader.nextLine();
              
              System.out.print("Enter borrower's Address: ");
              String address = reader.nextLine();
              
              System.out.print("Enter borrower's Email: ");
              String email = reader.nextLine();
              
              System.out.print("Enter borrower's Phone Number: ");
              String phoneNumber = reader.nextLine();
              
              library.addBorrower(new Borrower(name, address, email, phoneNumber));
              break;
            }
            case 2:{
              System.out.println("\nBorrow a book.");
              
              System.out.print("Enter borrower's name: ");
              String name = reader.nextLine().toLowerCase();
              
              System.out.print("Enter book title: ");
              String title = reader.nextLine().toLowerCase();
              
              System.out.print("Enter due date (dd-mm-yyyy): ");
              String dueDate = reader.nextLine();
              
              library.borrowBook(name, title, dueDate);
              break;
            }
            case 3:{
              System.out.println("\nReturn a book.");
              
              System.out.print("Enter borrower's name: ");
              String name = reader.nextLine().toLowerCase();
              
              System.out.print("Enter book title: ");
              String title = reader.nextLine().toLowerCase();
              
              library.returnBook(name, title);
              break;
            }
            case 4:{
              System.out.println("\nSearch for a borrower.");  
              
              System.out.print("Enter borrower name: ");
              String name = reader.nextLine().toLowerCase();
              
              library.searchBorrower(name);
              break;
            }
            case 5:{
              System.out.println("\nShow all borrowers.");
              
              library.showBorrowers();
              break;
            }
            case 6:{
              System.out.println("\nPrint borrower reciept.");
              
              System.out.print("Enter borrower name: ");
              String name = reader.nextLine();
              
              library.printReciept(name);
              break;
            }
            case 7:{
              System.out.println("\nReturning to home page.");
              break;
            }
            default:{
              System.out.println("\nNot a valid option.");
              break;
            }
          }
          break;
        }
        case 3:{
          System.out.println("\nExiting program.");
          library.save();
          reader.close();
          return;
        }
        default:{
          System.out.println("\nNot a valid option.");
          break;
        }
      }
    }
  }
  
  private static class Library{
   private List<Book> books;
   private List<Borrower> borrowers;

   public Library(){
     this.books = new ArrayList<>();
     this.borrowers = new ArrayList<>();
   }
   
   public void addBook(Book book){
     setBookReferenceId(book);
     books.add(book);
     System.out.println("\nBook added: " +book.getTitle());
   }

   private void setBookReferenceId(Book book){
     book.setReferenceId(books.size()); // uses index of book in list as id
   }
   
   public void searchBook(String title){
    try {
      Book book = searchFromBooks(title);
      book.printInfo();
    } catch (NullPointerException e){
      System.out.println(e.getMessage());
    }
  }
   
   public Book searchFromBooks (String title) throws NullPointerException {
    if (books.isEmpty())
      throw new NullPointerException("\nThere are currently no books.");
     
     for (Book book : books){
      if (book.getTitle().equalsIgnoreCase(title)){
          return book;
        }
      }
    throw new NullPointerException("\nBook titled '" +title +"' not found in the library.");
   }

   public void showBooks(){
    if (!books.isEmpty()){
      for (Book book : books){
        book.printInfo();
      }  
    } else {
      System.out.println("\nThere are currently no books in the library.");
    }
  }

    public void addBorrower(Borrower borrower){
      setBorrowerReferenceId(borrower);
      borrowers.add(borrower);
      System.out.println("\nBorrower added: " +borrower.getName());
    }
    
    private void setBorrowerReferenceId(Borrower borrower){
      borrower.setReferenceId(borrowers.size());
    }

    public void borrowBook(String name, String title, String dueDate){
      Borrower borrower;
      try {
        borrower = searchFromBorrowers(name);
      } catch (NullPointerException e){
        System.out.println(e.getMessage());
        return;
      }

      Book book; 
      try {
        book = searchFromBooks(title);
      } catch (NullPointerException e){
        System.out.println(e.getMessage());
        return;
      }

      if (borrower.isBookBorrowed(title)){
        System.out.println("\n" +borrower.getName() +" has already borrowed '" +book.title +"'.");
      } else {
        borrower.borrowBook(book, dueDate);
        System.out.println("\n" +borrower.getName() +" has borrowed '" +book.getTitle() +"'. Due Date: " +dueDate +".");
      }
    }

    public void returnBook(String name, String title){
      Borrower borrower;
      try {
        borrower = searchFromBorrowers(name);
      } catch (NullPointerException e){
        System.out.println(e.getMessage());
        return;
      }
      
      borrower.returnBook(title);
    }

    public void searchBorrower(String name){    
      try {
        Borrower borrower = searchFromBorrowers(name);
        borrower.printInfo();
      } catch (NullPointerException e){
        System.out.println(e.getMessage());      
      }
    }

    private Borrower searchFromBorrowers(String name) throws NullPointerException {
      if (borrowers.isEmpty())
        throw new NullPointerException("\nThere are currently no borrowers.");
      
      for(Borrower borrower : borrowers){
        if(borrower.getName().equalsIgnoreCase(name)){
          return borrower;
        } 
      }
      throw new NullPointerException("\nThere are no borrowers found with the name: '" +name +"'.");
    }

    public void showBorrowers(){
      if(!borrowers.isEmpty()){
        for(Borrower borrower : borrowers){
          borrower.printInfo();
          System.out.println();
        }
      } else {
        System.out.println("\nThere are currently no borrowers.");
      }
    }

    public void printReciept(String name){
      Borrower borrower;
      try {
        borrower = searchFromBorrowers(name);
      } catch (NullPointerException e){
        System.out.println(e.getMessage());
        return;
      }
      
      String fileName = name +".txt";
      File file = new File(fileName);
      try(FileWriter writer = new FileWriter(file)){
        
        writer.write("Library Card&" +"\n");
        writer.write("Borrower Reference No. " +borrower.getReferenceId() +"\n");
        writer.write("Borrower's Name: " +borrower.getName() +"\n");
        writer.write("Address: " +borrower.getAddress() +"\n");
        writer.write("Email: " +borrower.getEmail() +"\n");
        writer.write("Phone Number: " +borrower.getPhoneNumber() +"\n");
        writer.write(System.lineSeparator());
        
        if (!borrower.getBorrowedBooksMap().isEmpty()){
          for (Map.Entry<Book, String> entry : borrower.getBorrowedBooksMap().entrySet()) {
            Book book = entry.getKey(); String dueDate = entry.getValue();
            
            writer.write("Book Reference No. " +book.getReferenceId() +"\n");
            writer.write("Title: " +book.getTitle() +"\n");
            writer.write("Author: " +book.getAuthor() +"\n");
            writer.write("Due Date& : " +dueDate +"\n");
            writer.write("Type: " +book.getType() +"\n");
            
            switch(book.getType()){
              case "Fiction":{
                if (book instanceof Fiction fiction){
                  writer.write("Genre: " +fiction.getGenre() +"\n");
                  writer.write(fiction.isSeries() ? "Series: Book is part of a series." : "Series: Book is not part of a series." +"\n");
                }
                break;
              }
              case "NonFiction":{
                if (book instanceof NonFiction nonFiction){
                  writer.write("Topic: " +nonFiction.getTopic() +"\n");
                  writer.write("Edition: " +nonFiction.getEdition() +"\n");
                }
                break;
              }
              case "Academic":{
                if (book instanceof Academic academic){
                  writer.write("Topic: " +academic.getSubject() +"\n");
                  writer.write("Edition: " +academic.getEdition() +"\n");
                }
                break;
              }
            }
            writer.write(System.lineSeparator());
         }
        } else {
          writer.write("\nThere are no books currently borrowed.");
        }
      } catch (IOException e){
        System.out.println("\nError: There was an issue creating the file.");
      }
      System.out.println("\nReciept printed." +" (as '" +fileName +"').");
      System.out.println(file.getAbsoluteFile());
    }
    
    public void load(){
      loadBooks(); loadBorrowers();
    }
    
    private void loadBooks(){
      File file = new File("books.txt");
      if(!file.exists()){
        System.out.println("\nNo existing books file found. (save and exit to create books.txt file)");
        return;
      }
      
      try{
        Scanner fileReader = new Scanner(file);
        while(fileReader.hasNextLine()){
          Book book;

          String title = fileReader.nextLine().split(": ")[1];
          String author = fileReader.nextLine().split(": ")[1];
          String type = fileReader.nextLine().split(": ")[1];

          switch (type) {
            case "Fiction":{
              String genre = fileReader.nextLine().split(": ")[1];
              String isPart = fileReader.nextLine().split(": ")[1];
              boolean isSeries = isPart.contains("Book is part of a series.");
              book = new Fiction(title, author, "Fiction", genre, isSeries);
              setBookReferenceId(book);
              books.add(book);
              break;
            }
            case "NonFiction": {
              String topic = fileReader.nextLine().split(": ")[1];
              String edition = fileReader.nextLine().split(": ")[1];
              book = new NonFiction(title, author, "NonFiction", topic, edition);
              setBookReferenceId(book);
              books.add(book);
              break;
            }
            case "Academic": {
              String subject = fileReader.nextLine().split(": ")[1];
              String edition = fileReader.nextLine().split(": ")[1];
              book = new Academic(title, author, "Academic", subject, edition);
              setBookReferenceId(book);
              books.add(book);
              break;
            }
            default:{
              break;
            } 
          }
          fileReader.nextLine();
        } 
      } catch(IOException e) {
        System.out.println("\nError loading books.");
      }
      System.out.println("\nBooks were loaded successfully.");
    }
    
    private void loadBorrowers(){
      File file = new File("borrowers.txt");
      if(!file.exists()){
        System.out.println("\nNo existing borrowers file found. (save and exit to create borrower.txt file)");
        return;
      }
      
      try (Scanner fileReader = new Scanner(file)) {
        while (fileReader.hasNextLine()) {
          Borrower borrower;
          {
            String line = fileReader.nextLine();
            if (line.isBlank()){
              continue;
            }

            String name = line.split(": ")[1];
            String address = fileReader.nextLine().split(": ")[1];
            String email = fileReader.nextLine().split(": ")[1];
            String phoneNumber = fileReader.nextLine().split(": ")[1];
            borrower = new Borrower(name, address, email, phoneNumber);
            borrowers.add(borrower);
          }
          fileReader.nextLine(); // skips "Borrowed Books:"
          
          String line = fileReader.nextLine();
          if (line.contains("No borrowed books.")){
            continue;
          }
          
          while(true){
            if(line.isBlank()){
              break;
            }
            
            String title = line.split(": ")[1];
            String dueDate = fileReader.nextLine().split(": ")[1];
            
            if (!line.isBlank()){
             line = fileReader.nextLine();
            }
            
            try{
              Book book = searchFromBooks(title);
              borrower.borrowBook(book, dueDate);
            } catch (NullPointerException e){
              System.out.println(e.getMessage());
            }
          }
        }
        System.out.println("\nBorrowers loaded successfully.");
      } catch (IOException e) {
        System.out.println("\nError loading borrowers from file: " + e.getMessage());
      }
    }
    
    public void save(){
      saveBooks(); saveBorrowers();
    }
    
    private void saveBooks(){
      if(books.isEmpty())
        return;
      
      File file = new File("books.txt");
      try (FileWriter writer = new FileWriter(file)) {
        for (Book book : books) {
          writer.write("Title: " + book.getTitle() + "\n");
          writer.write("Author: " + book.getAuthor() + "\n");
          writer.write("Type: " + book.getType() + "\n");

          switch(book.getType()){
            case "Fiction":{
              if (book instanceof Fiction fiction){
                writer.write("Genre: " +fiction.getGenre() +"\n");
                writer.write(fiction.isSeries() ? "Series: Book is part of a series." 
                                      : "Series: Book is not part of a series." +"\n");
              }
              break;
            }
            case "NonFiction":{
              if (book instanceof NonFiction nonFiction){
                writer.write("Topic: " +nonFiction.getTopic() +"\n");
                writer.write("Edition: " +nonFiction.getEdition() +"\n");
              }
              break;
            }
            case "Academic":{
              if (book instanceof Academic academic){
                writer.write("Topic: " +academic.getSubject() +"\n");
                writer.write("Edition: " +academic.getEdition() +"\n");
              }
              break;
            }
          }
          writer.write(System.lineSeparator());
        }
        System.out.println("\nAll books saved to 'books.txt'.");
        System.out.println(file.getAbsoluteFile());
       } catch (IOException e) {
      System.out.println("\nError saving books to file: " + e.getMessage());
      }
    }
    
    private void saveBorrowers(){
      if (borrowers.isEmpty())
        return;
      
      File file = new File("borrowers.txt");
        try (FileWriter writer = new FileWriter(file)) {
          for (Borrower borrower : borrowers) {
            writer.write("Name: " + borrower.getName() + "\n");
            writer.write("Address: " + borrower.getAddress() + "\n");
            writer.write("Email: " + borrower.getEmail() + "\n");
            writer.write("Phone Number: " + borrower.getPhoneNumber() + "\n");
            writer.write("Borrowed Books:\n");
            
          if (!borrower.getBorrowedBooksMap().isEmpty()) {
            for (Map.Entry<Book, String> entry : borrower.getBorrowedBooksMap().entrySet()) {
              Book book = entry.getKey();
              String dueDate = entry.getValue();
              writer.write("Title: " +book.getTitle() +"\n"); 
              writer.write("Due Date: " +dueDate +"\n");
            }
          } else {
            writer.write("No borrowed books.\n");
          }
          writer.write(System.lineSeparator());
        }
        System.out.println("\nAll borrowers saved to 'borrowers.txt'.");
        System.out.println(file.getAbsoluteFile());
      } catch (IOException e) {
        System.out.println("\nError saving borrowers to file: " + e.getMessage());
      }
    }
  }
}
