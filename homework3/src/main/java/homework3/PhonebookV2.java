package homework3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhonebookV2 {
    public static void main(String[] args) {
        try {
            String filePath = "raw_phonebook_data.csv";

            System.out.println("Loading the entries...");
            RedBlackTree entries = FileUtils.readFile(filePath);

            System.out.println("Saving the entries...");
            FileUtils.writeToFile(entries.getEntriesInOrder(), "sorted_entries.csv");

            System.out.println(
                    "==============================\n" +
                            "System is ready.\n"
            );
            List<Integer> links = entries.countRedAndBlackEdges();
            System.out.println("Total red edges in the tree: " + links.get(1));
            System.out.println("Total black edges in the tree: " + links.get(0) + '\n');

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the name that you wish to search for, or -1 to exit: ");


            String userName = scanner.nextLine();
            while (!userName.equals("-1")) {
                ArrayList<Entry> result = entries.get(userName);
                getEntries(result);

                System.out.print("Enter the name that you wish to search for, or -1 to exit: ");
                userName = scanner.nextLine();
            }
            System.out.println("Thank you for using the phonebook.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getEntries(ArrayList<Entry> entries) {
        if (entries == null) {
            System.out.println("No entries with the given name exist in the phonebook.\n");
            return;
        }
        if (entries.isEmpty()) {
            System.out.println("No entries with the given name exist in the phonebook.");
            System.out.println();
        } else {
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        }
    }

}
