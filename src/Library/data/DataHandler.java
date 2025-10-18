package Library.data;
import Library.model.Library;
import java.io.*;

public class DataHandler {

    private static final String FILE_NAME = "library_data.ser";


    public static void saveLibrary(Library library) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(library);
            System.out.println("Library data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }
    public static Library loadLibrary() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new Library();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Library) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library data: " + e.getMessage());
            return new Library();
        }
    }
}
