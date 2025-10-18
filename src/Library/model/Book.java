package Library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Book extends Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numberOfPages;
    private String isbn;

    public Book(String title, String category, ArrayList<String> authors,
                LocalDate publishDate, boolean isCheckedOut,
                String publisherName, int numberOfPages, String isbn) {
        super(title, category, authors, publishDate, isCheckedOut, publisherName);
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
    }

    public int getNumberOfPages() { return numberOfPages; }
    public String getIsbn() { return isbn; }

    public void setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String toString() {
        return super.toString() + " | ISBN: " + isbn + " | Pages: " + numberOfPages;
    }
}
