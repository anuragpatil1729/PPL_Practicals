import java.util.Scanner;

public class Tutorial2 {

    // Find missing number from 1 to n
    static void findMissingNumber(int[] arr, int n) {
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;

        for (int num : arr) {
            if (num < 1 || num > n) {
                System.out.println("Invalid number entered! Allowed range: 1 to " + n);
                return;
            }
            actualSum += num;
        }

        if (arr.length != n - 1) {
            System.out.println("Error: You must enter exactly " + (n - 1) + " numbers!");
            return;
        }

        long missing = expectedSum - actualSum;
        System.out.println("Missing Number: " + missing);
    }

    // Find largest & smallest
    static void findLargestSmallest(int[] arr) {
        int largest = arr[0];
        int smallest = arr[0];

        for (int num : arr) {
            if (num > largest)
                largest = num;
            if (num < smallest)
                smallest = num;
        }

        System.out.println("Largest : " + largest);
        System.out.println("Smallest : " + smallest);
    }

    // Sort without Arrays.toString()
    static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            printArray(arr);
        }
    }

    // Search element
    static void searchElement(int[] arr, int key) {
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println("Element found at index: " + i);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Element not found!");
    }

    // Find sum pairs
    static void findSumPairs(int[] arr, int sum) {
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    System.out.println("Pair found at indices " + i + " and " + j +
                            "  (" + arr[i] + " + " + arr[j] + ")");
                    found = true;
                }
            }
        }

        if (!found)
            System.out.println("No sum pair found!");
    }

    // Reverse array
    static void reverseArray(int[] arr) {
        System.out.print("Reversed Array: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Merge two arrays manually
    static int[] mergeArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int index = 0;

        for (int num : a)
            merged[index++] = num;
        for (int num : b)
            merged[index++] = num;

        return merged;
    }

    // Manual print array
    static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of the array: ");
        int size = sc.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter " + size + " elements:");

        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int choice;

        do {
            System.out.println("\n--- Array Puzzle Solver Menu ---");
            System.out.println("1. Missing Number (User enters range n)");
            System.out.println("2. Largest & Smallest");
            System.out.println("3. Sort Array");
            System.out.println("4. Search Element");
            System.out.println("5. Sum Pair Puzzle");
            System.out.println("6. Reverse Array");
            System.out.println("7. Merge Two Arrays");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter range n (enter numbers 1 to n): ");
                    int n = sc.nextInt();
                    System.out.println("Enter " + (n - 1) + " numbers in any order:");
                    int[] missingArr = new int[n - 1];
                    for (int i = 0; i < n - 1; i++)
                        missingArr[i] = sc.nextInt();

                    findMissingNumber(missingArr, n);
                    break;

                case 2:
                    findLargestSmallest(arr);
                    break;

                case 3:
                    sortArray(arr);
                    break;

                case 4:
                    System.out.print("Enter element to search: ");
                    searchElement(arr, sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter sum: ");
                    findSumPairs(arr, sc.nextInt());
                    break;

                case 6:
                    reverseArray(arr);
                    break;

                case 7:
                    System.out.print("Enter size of second array: ");
                    int size2 = sc.nextInt();

                    int[] arr2 = new int[size2];
                    System.out.println("Enter " + size2 + " elements:");
                    for (int i = 0; i < size2; i++)
                        arr2[i] = sc.nextInt();

                    int[] merged = mergeArrays(arr, arr2);
                    System.out.print("Merged Array: ");
                    printArray(merged);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
