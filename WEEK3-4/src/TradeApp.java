class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeApp {

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeSorted = mergeSort(trades);

        System.out.print("MergeSort: [");
        printArray(mergeSorted);
        System.out.println("]");

        quickSort(trades, 0, trades.length - 1);

        System.out.print("QuickSort (desc): [");
        printArray(trades);
        System.out.println("]");

        Trade[] morning = {
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] afternoon = {
                new Trade("trade3", 500)
        };

        Trade[] merged = mergeArrays(morning, afternoon);

        int total = totalVolume(merged);

        System.out.println("Merged morning+afternoon total: " + total);
    }

    static Trade[] mergeSort(Trade[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;

        Trade[] left = new Trade[mid];
        Trade[] right = new Trade[arr.length - mid];

        for (int i = 0; i < mid; i++) left[i] = arr[i];
        for (int i = mid; i < arr.length; i++) right[i - mid] = arr[i];

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    static Trade[] merge(Trade[] left, Trade[] right) {
        Trade[] result = new Trade[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].volume <= right[j].volume) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];

        return result;
    }

    static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static Trade[] mergeArrays(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    static void printArray(Trade[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
    }
}