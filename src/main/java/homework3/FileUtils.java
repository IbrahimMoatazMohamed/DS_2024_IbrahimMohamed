package homework3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static RedBlackTree readFile(String filePath) throws FileNotFoundException {
        RedBlackTree entryRedBlackTree = new RedBlackTree();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String[] names = parts[0].split(", ");

                String name = names[1];
                String surname = names[0];
                String street_address = parts[1];
                String city = parts[2];
                String postcode = parts[3];
                String country = parts[4];
                String phone_number = parts[5];

                Entry entry = new Entry(
                        name, surname,
                        street_address, city,
                        postcode, phone_number, country);
                entryRedBlackTree.put(entry.getFullName(), entry);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryRedBlackTree;
    }

    public static void writeToFile(ArrayList<Entry> entries, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            String line = entry.getLine();
            if (i < entries.size() - 1) {
                bw.write(line + "\n");
            } else {
                bw.write(line);
            }
        }
        bw.close();
    }
}