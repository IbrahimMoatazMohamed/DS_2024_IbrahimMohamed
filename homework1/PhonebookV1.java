package homework1;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class PhonebookV1 {
    public static void main(String[] args) {
        try{
            String filePath = "raw_phonebook_data.csv";

            System.out.println("Loading the entries...");
            Entry[] entries = FileUtils.readFile(filePath);

            System.out.println("Sorting the entries...");
//            MergeSort.sort(entries);

            // part 4
            Comparator<Entry> comparator = new Comparator<Entry>() {
                @Override
                public int compare(Entry e1, Entry e2) {
                    int fullNameCompare = e1.getFullName().compareTo(e2.getFullName());
                    if(fullNameCompare == 0){
                        int streetCompare = e1.getStreet_address().compareTo(e2.getStreet_address());
                        if(streetCompare == 0){
                            int cityCompare = e1.getCity().compareTo(e2.getCity());
                            if(cityCompare == 0){
                                int postcodeCompare = e1.getPostcode().compareTo(e2.getPostcode());
                                if(postcodeCompare == 0) {
                                    int countryCompare = e1.getCountry().compareTo(e2.getCountry());
                                    if(countryCompare == 0) {
                                        return e1.getPhone_number().compareTo(e2.getPhone_number());
                                    }
                                    return countryCompare;
                                }
                                return postcodeCompare;
                            }
                            return cityCompare;
                        }
                        return streetCompare;
                    }
                    return fullNameCompare;
                }
            };
            MergeSort.sort(entries, comparator);




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
