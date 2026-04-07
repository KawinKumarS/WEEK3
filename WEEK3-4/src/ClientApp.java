class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientApp {

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 7000),
                new Client("clientB", 50, 6000)
        };

        bubbleSort(clients);

        insertionSort(clients);

        printTopRisks(clients, 3);
    }

    static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }

        System.out.print("Bubble (asc): [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("] // Swaps: " + swaps);
    }

    static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.print("Insertion (desc): [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return a.riskScore - b.riskScore;
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    static void printTopRisks(Client[] arr, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < topN && i < arr.length; i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ")");
            if (i < topN - 1 && i < arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
}