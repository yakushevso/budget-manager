package budget.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class FileManager {
    private static final File file = new File("purchases.txt");

    public static void save(List<String> data) {
        try (PrintWriter writer = new PrintWriter(file)) {
            data.forEach(writer::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> load() {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
