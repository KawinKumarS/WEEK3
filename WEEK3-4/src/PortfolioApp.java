import java.util.Random;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioApp {

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        Asset[] mergeSorted = mergeSort(assets);

        System.out.print("Merge: [");
        printArray(mergeSorted);
        System.out.println("]");

        quickSort(assets, 0, assets.length - 1);

        System.out.print("Quick (desc): [");
        printArray(assets);
        System.out.println("]");
    }

    static Asset[] mergeSort(Asset[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;

        Asset[] left = new Asset[mid];
        Asset[] right = new Asset[arr.length - mid];

        for (int i = 0; i < mid; i++) left[i] = arr[i];
        for (int i = mid; i < arr.length; i++) right[i - mid] = arr[i];

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    static Asset[] merge(Asset[] left, Asset[] right) {
        Asset[] result = new Asset[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].returnRate <= right[j].returnRate) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];

        return result;
    }

    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        int pivotIndex = randomPivot(low, high);
        swap(arr, pivotIndex, high);

        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) > 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(a.returnRate, b.returnRate);
        }
        return Double.compare(b.volatility, a.volatility);
    }

    static int randomPivot(int low, int high) {
        Random rand = new Random();
        return rand.nextInt(high - low + 1) + low;
    }

    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void printArray(Asset[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
    }
}