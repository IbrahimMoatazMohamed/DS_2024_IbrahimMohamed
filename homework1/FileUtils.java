package homework1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileUtils {

    public static Entry[] readFile(String filePath) throws FileNotFoundException {
        // implement the actual logic (remove next line)
        List<Entry> entryList = new ArrayList<>();
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

                entryList.add(new Entry(
                        name, surname,
                        street_address, city,
                        postcode, phone_number, country));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Entry[] entries = new Entry[entryList.size()];
        entries = entryList.toArray(entries);
        return entries;
    }

    public static void writeToFile(Entry[] entries, String filePath) throws IOException {
        // implement the actual logic
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        // Optionally, write the header
        // bw.write("name;street_address;city;postcode;country;phone_number\n");

        for (int i = 0; i < entries.length; i++) {
            Entry entry = entries[i];
            String line = entry.getLine();
            if (i < entries.length - 1) {
                bw.write(line + "\n");
            } else {
                bw.write(line);
            }
        }
        bw.close();
    }
}