package Library.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int counter = 1000;

    private String itemId;
    private String title;
    private String category;
    private ArrayList<String> authors;
    private LocalDate publishDate;
    private boolean isCheckedOut;
    private String publisherName;

    public Item(String title, String category, ArrayList<String> authors, LocalDate publishDate, boolean isCheckedOut, String publisherName) {
        this.itemId = String.format("I%04d", counter++);
        this.title = title;
        this.category = category;
        this.authors = authors;
        this.publishDate = publishDate;
        this.isCheckedOut = isCheckedOut;
        this.publisherName = publisherName;
    }

    public boolean isAnAuthor(String author) {
        for (String a : authors) {
            if (a.toLowerCase().contains(author.toLowerCase())) return true;
        }
        return false;
    }

    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public ArrayList<String> getAuthors() { return authors; }
    public LocalDate getPublishDate() { return publishDate; }
    public boolean getIsCheckedOut() { return isCheckedOut; }
    public String getPublisherName() { return publisherName; }

    public void setIsCheckedOut(boolean isCheckedOut) { this.isCheckedOut = isCheckedOut; }

    @Override
    public String toString() {
        return itemId + " - " + title + " - " + String.join(",", authors) + " - " + publishDate + " - " + isCheckedOut;
    }
}
