package homework1;
import java.io.IOException;
import java.util.Scanner;

public class PhonebookV1 {
    public static void main(String[] args) {
        try{
            String filePath = "raw_phonebook_data.csv";

            System.out.println("Loading the entries...");
            Entry[] entries = FileUtils.readFile(filePath);

            System.out.println("Sorting the entries...");
            MergeSort.sort(entries);

            System.out.println("Saving the entries...");
            FileUtils.writeToFile(entries, "sorted_entries.csv");

            System.out.println(
                "==============================\n" +
                "System is ready.\n"
            );

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the name that you wish to search for, or -1 to exit: ");


            String userName = scanner.nextLine();
            while (!userName.equals("-1")){
                int[] result = BinarySearch.search(entries, userName);

                getEntries(entries, result);

                System.out.print("Enter the name that you wish to search for, or -1 to exit: ");
                userName = scanner.nextLine();
            }
            System.out.println("Thank you for using the phonebook.");
        }  catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void getEntries(Entry[] entries, int[] indexes){
        if(indexes.length == 0){
            System.out.println("No entries with the given name exist in the phonebook.");
            System.out.println();
        } else if (indexes[0] == indexes[1]) {
            System.out.println("Entries found: 1");
            System.out.println();
            System.out.println(entries[indexes[0]]);
        } else {
            System.out.println(
                "Entries found: "
                        + Integer.toString(indexes[1] - indexes[0] + 1)
            );
            System.out.println();

            for(int i = indexes[0]; i <= indexes[1]; i++){
                System.out.println(entries[i]);
            }
        }
    }
}
