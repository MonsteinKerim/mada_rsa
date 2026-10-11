import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Dieses File haben wir direkt generieren lassen.
 * "create a java FileHandler class that has a method to write and read a file"
 *
 */
public class FileHandler {

    public void writeFile(String filename, String content)
            throws IOException {
        Files.writeString(Path.of(filename), content);
    }

    public String readFile(String filename)
            throws IOException {
        return Files.readString(Path.of(filename));
    }
}