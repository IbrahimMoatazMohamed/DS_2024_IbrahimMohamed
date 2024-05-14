package homework1;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static int[] search(Entry[] entries, String searchableName) {
        int foundIndex = search(entries, searchableName, false);

        if (foundIndex == -1) {
            return new int[0];
        }

        return resultArray(entries, searchableName, foundIndex);
    }

    private static int search(Entry[] entries, String searchableName, boolean justChangeTheSignature) {
        int low = 0;
        int high = entries.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int status = searchableName.compareTo(entries[mid].getFullName());

            if (status == 0) {
                return mid;
            } else if (status < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int[] resultArray(Entry[] entries, String sName, int fIndex){
        List<Integer> rIndices = new ArrayList<>();

        for (int i = fIndex + 1; i < entries.length && entries[i].getFullName().equals(sName); i++) {
            rIndices.add(i);
        }

        for (int i = fIndex; i >= 0 && entries[i].getFullName().equals(sName); i--) {
            rIndices.add(i);
        }

        int min = rIndices.get(0);
        int max = rIndices.get(0);

        for(Integer i : rIndices){
            if(i > max){
                max = i;
            }

            if(i < min){
                min = i;
            }
        }

        int[] result = new int[2];
        result[0] = min;
        result[1] = max;

        return result;
    }
}