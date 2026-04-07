import java.util.Arrays;

public class RiskThresholdApp {

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        linearSearch(risks, 30);

        Arrays.sort(risks);

        int target = 30;
        int floor = findFloor(risks, target);
        int ceiling = findCeiling(risks, target);

        System.out.println("Binary floor(" + target + "): " + floor + ", ceiling: " + ceiling);
    }

    static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }
        System.out.println("Linear: threshold=" + target + " → " + (found ? "found" : "not found") + " (" + comparisons + " comps)");
    }

    static int findFloor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    static int findCeiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceiling = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] > target) {
                ceiling = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceiling;
    }
}