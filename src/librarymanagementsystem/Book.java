package librarymanagementsystem;

/**
 * Book Class and Subclass
 * @author mark solomon
 */

public abstract class Book {
  protected int referenceId;
  protected String title;
  protected String author;
  protected String type;
  
  public Book(String title, String author, String type){
    this.title = title;
    this.author = author;
    this.type = type;
  }
  
  public int getReferenceId(){
    return referenceId;
  }
  
  public String getTitle(){
    return title;
  }
  
  public String getAuthor(){
    return author;
  }
  
  public String getType(){
    return type;
  }
  
  public void setReferenceId(int referenceId){
    this.referenceId = referenceId;
  } 
  
  public void printInfo(){
    System.out.println("\nBook Reference no. " +referenceId);
    System.out.println("Title: " +title);
    System.out.println("Author: " +author);
    System.out.println("Type: " +type);
  }
}

class Fiction extends Book{
  private final String genre;
  private final boolean isSeries;
  
  public Fiction(String title, String author, String type, String genre, boolean isSeries){
    super(title, author, type);
    this.genre = genre;
    this.isSeries = isSeries;
  }
  
  public String getGenre(){
    return genre;
  }
  
  public boolean isSeries(){
    return isSeries;
  }
  
  @Override
  public void printInfo(){
    super.printInfo();
    System.out.println("Genre: " +genre);
    if(isSeries){
      System.out.println("Series: Book is part of a series.");
    } else {
      System.out.println("Series: Book is not poart of a series.");
    }
  }
}

class NonFiction extends Book{
  private final String topic;
  private final String edition;
  
  public NonFiction(String title, String author, String type, String topic, String edition){
    super(title, author, type);
    this.topic = topic;
    this.edition = edition;
  }
  
  public String getTopic(){
    return topic;
  }
  
  public String getEdition(){
    return edition;
  }
  
  @Override
  public void printInfo(){
    super.printInfo();
    System.out.println("Topic: " +topic);
    System.out.println("Edition: " +edition);
  }
}

class Academic extends Book{
  private final String subject;
  private final String edition;
  
  public Academic(String title, String author, String type, String subject, String edition){
    super(title, author, type);
    this.subject = subject;
    this.edition = edition;
  }
  
  public String getSubject(){
    return subject;
  }
  
  public String getEdition(){
    return edition;
  }
  
  @Override
  public void printInfo(){
    super.printInfo();
    System.out.println("Subject: " +subject);
    System.out.println("Edition: " +edition);
  }
}