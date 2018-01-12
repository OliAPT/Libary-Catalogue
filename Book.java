/**
 * Book Class represents the data we need to save a book. it has getters and setters to access the books attributes
 * it overrides the toString method in order to define how a book should be printed in the console
 *
 * Github.com/OliAPT
 * @OliAPT
 */
public class Book {

    private String title;
    private String author;
    private double price;
    private String publisher;
    private String isbn;

    public Book() {

    }

    public Book(String title, String author, double price, String publisher, String isbn) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
        this.setPublisher(publisher);
        this.setIsbn(isbn);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return String.format("%-19s %-20s \u00A3%-18s %-23s %s", title, author, String.format("%.2f", price), publisher, isbn);
    }
}
