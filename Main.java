import java.io.File;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * the main class uses 4 functions and all will be explained in this writeup.
 * //readFile\\ prompts user for a file name and reads the data in it
 * ~validatebook makes sure that the data read from file is valid for each line
 * until the user types endinput. //getBook\\ is responsible for getting a single books data.
 * //displayResults\\ prints the output in the table format
 * you can alos search book by title && author.
 *
 * Github.com/eulo08
 * @eulo_apt
 */
public class Main {
    // declared a scanner object to read input and point it to the standard input device aka the keyboard
    static Scanner scanner = new Scanner(System.in);
    static String line = "";
    static  int validBooksCount = 0; //hold number of valid books
    static int invalidBooksCount = 0; //hold number of invalid books

    public static void main(String[] args){
        ArrayList<Book> books = readFile(); // prompt the user to enter book data
        displayResults(books); // display the result
        searchBook(books);
    }

    public static ArrayList<Book> readFile(){
        ArrayList<Book> books = new ArrayList<>();
        System.out.println("This program will try to read data from a text file ");
        System.out.print("Type in the file name with extension: ");
        String fileName = scanner.nextLine();
        String currentPath = System.getProperty("user.dir");

        File fileObject = new File (currentPath+"\\"+fileName);
        if (!fileObject.exists())
        {
            System.out.println("Could not find " + currentPath+"\\"+fileName);
            System.out.println("Error - File does not exist");
            System.exit(0);
        }

        try {
            Scanner fileReader  = new Scanner(fileObject);
            int lineCount = 0; //keep count of current line to ease error referencing

            while(fileReader.hasNext()){
                lineCount++;
                Book book = new Book();
                line = fileReader.nextLine();// Read a line of data from text file
                String[] splitArray = line.split("-");

                // check to make sure there are 5 parts in splitArray
                if(splitArray.length == 5){
                    // remove spaces while setting feilds
                    book.setTitle(splitArray[0].trim());
                    book.setAuthor(splitArray[1].trim());
                    try {
                        book.setPrice(Double.parseDouble(splitArray[2].trim()));
                    }catch (NumberFormatException nfe){
                        System.out.println("Error on line " + lineCount + " - The book price may not be a numeric value.");
                        invalidBooksCount++;
                        continue;
                    }
                    book.setPublisher(splitArray[3].trim());
                    book.setIsbn(splitArray[4].trim());
                }else{
                    System.out.println("Error on line " + lineCount + " - The field delimiter may be missing or wrong field delimiter is used");
                    invalidBooksCount++;
                    continue;
                }

                if(validateBook(book, lineCount)){
                    validBooksCount++;
                    books.add(book);
                }else{
                    invalidBooksCount++;
                }
            }
        }
        catch (Exception e) {
            // IO error while reading from the file.
            System.err.println("Error while reading from file " + e);
        }
        return books;
    }

    public static boolean validateBook(Book book, int count){
        boolean isValid = true;
        if(book.getTitle().trim().length() == 0){
            System.out.println("Error on line " + count + " - The book title may be missing.");
            isValid = false;
        }
        if(book.getAuthor().trim().length() == 0){
            System.out.println("Error on line " + count + " - The book author may be missing.");
            isValid = false;
        }
        if(book.getPrice()== 0){
            System.out.println("Error on line " + count + " - The book price may be missing.");
            isValid = false;
        }
        if(book.getPublisher().trim().length() == 0){
            System.out.println("Error on line " + count + " - The book publisher may be missing.");
            isValid = false;
        }
        if(book.getIsbn().trim().length() == 0){
            System.out.println("Error on line " + count + " - The book ISBN may be missing.");
            isValid = false;
        }
        if(!StringUtils.isNumeric(book.getIsbn())){
            System.out.println("Error on line " + count + " - The book ISBN may not be a numeric value.");
            isValid = false;
        }
        return isValid;
    }

    public static void displayResults(ArrayList<Book> books){ //display formatted results
        double totalCost = 0;
        double minCost = 0;
        double maxCost = 0;
        if(books.size()>0){
            minCost = books.get(0).getPrice();
            maxCost = books.get(0).getPrice();
        }

        System.out.println("\nTitle               Author               Price               Publisher               ISBN");
        System.out.println("=====               ======               =====               =========               ====");
        for(Book book: books) {
            totalCost += book.getPrice();
            System.out.println(book);

            if(maxCost < book.getPrice()){
                maxCost = book.getPrice();
            }

            if(minCost > book.getPrice()){
                minCost = book.getPrice();
            }
        }

        System.out.println("\nTotals\n--------------------------\nTotal number of books: " + books.size());
        System.out.println("Total cost of books: \u00A3" + String.format("%.2f", totalCost));
        System.out.println("Maximum cost of a book: \u00A3" + String.format("%.2f", maxCost));
        System.out.println("Minimum cost of a book: \u00A3" + String.format("%.2f", minCost));
        System.out.println("Average cost of a book: \u00A3" + String.format("%.2f", (totalCost/books.size())));
        System.out.println("Total number of Valid books: " + validBooksCount);
        System.out.println("Total number of invalid books: "+ invalidBooksCount);
    }

    public static void searchBook(ArrayList<Book> books){
        System.out.println("\nEnter \"title\" to search book by title or \"author\" to list all books by a specific author. Enter anything else to exit.");
        System.out.print("(title/author): ");
        String response = scanner.nextLine();
        if(response.toString().equals("title")){
            System.out.print("Enter a book title: ");
            response = scanner.nextLine();
            for(Book book : books){
                if(response.toString().equals(book.getTitle())){
                    System.out.println("\nTitle               Author               Price               Publisher               ISBN");
                    System.out.println("=====               ======               =====               =========               ====");
                    System.out.println(book);
                }
            }
        }else if(response.toString().equals("author")){
            System.out.print("Enter an author name: ");
            response = scanner.nextLine();
            for(Book book : books){
                if(response.toString().equals(book.getAuthor())){
                    System.out.println("\nTitle               Author               Price               Publisher               ISBN");
                    System.out.println("=====               ======               =====               =========               ====");
                    System.out.println(book);
                }
            }
        }else {
            System.exit(0);
        }
    }

}
