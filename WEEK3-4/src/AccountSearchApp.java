import java.util.Arrays;

public class AccountSearchApp {

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        linearSearch(logs, "accB");

        Arrays.sort(logs);

        System.out.print("Sorted logs: [");
        printArray(logs);
        System.out.println("]");

        binarySearch(logs, "accB");
    }

    static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear first " + target + ": index " + first + " (" + comparisons + " comparisons)");
        System.out.println("Linear last " + target + ": index " + last);
    }

    static void binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;
        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                index = mid;
                break;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        int count = 0;
        if (index != -1) {
            int i = index;
            while (i >= 0 && arr[i].equals(target)) {
                count++;
                i--;
            }
            i = index + 1;
            while (i < arr.length && arr[i].equals(target)) {
                count++;
                i++;
            }
        }

        System.out.println("Binary " + target + ": index " + index + " (" + comparisons + " comparisons), count=" + count);
    }

    static void printArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
    }
}