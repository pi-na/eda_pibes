import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    private final static String path = "src/main/resources/amazon.txt";
    private static ArrayList<Element> products = new ArrayList<>();

    public static ArrayList<Element> read() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split("#");
                products.add(new Element(parts[0], parts[1] + parts[2]));
            }
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", path);
            e.printStackTrace();
        }
        return products;
    }
}
