package Library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Publication extends Item implements Serializable {
    private static final long serialVersionUID = 1L;

    public Publication(String title, String category, ArrayList<String> authors,
                       LocalDate publishDate, boolean isCheckedOut,
                       String publisherName) {
        super(title, category, authors, publishDate, isCheckedOut, publisherName);
    }

    @Override
    public String toString() {
        return "Publication: " + super.toString();
    }
}
