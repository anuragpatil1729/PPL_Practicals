import java.util.Scanner;
import java.util.Arrays;

public class Tutorial2 {

    // find the missing number between 1 to n
    static void findMissingNumber(int[] arr, int n) {
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;

        for (int num : arr) {
            if (num < 1 || num > n) {
                System.out.println("Invalid input array");
                return;
            }
            actualSum += num;
        }

        if (arr.length != n - 1) {
            System.out.println("Array size must be n-1");
            return;
        }

        System.out.println("Missing number: " + (expectedSum - actualSum));
    }

    // Find largest and Smallest
    static void findLargestSmallest(int[] arr) {
        int max = arr[0], min = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        System.out.println("Largest : " + max);
        System.out.println("Smallest : " + min);
    }

    // sort array
    static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));

            }

        }
    }

    // linear search algorithm
    static void searchElement(int arr[], int key) {

        boolean check = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.print("Element found at position : " + i);
                check = true;
                break;

            }
        }

        if (!check) {
            System.out.print("Element not found ");
        }
    }

    // Sum pair Puzzle
    static void findSumPairs(int[] arr, int sum) {
        boolean check = false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    System.out.print("Pair Found at " + i + " and " + j);
                    check = true;
                }
            }
        }
        if (!check) {
            System.out.print("Sum pair not found ");
        }
    }

    // reverse elements
    static void reverseArray(int[] arr) {
        System.out.print("Reversed Array: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter  the size of the array : ");
        int size = sc.nextInt();

        int arr[] = new int[size];

        System.out.println("Enter the elements in the array : ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int choice;
        do {
            System.out.println("\n--- Array Puzzle Solver Menu ---");
            System.out.println("1. Find Missing Number");
            System.out.println("2. Find Largest & Smallest");
            System.out.println("3. Sort Array");
            System.out.println("4. Search Element");
            System.out.println("5. Sum Pair Puzzle");
            System.out.println("6. Reverse Array");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter n (max number): ");
                    int n = sc.nextInt();
                    findMissingNumber(arr, n);
                    break;

                case 2:
                    findLargestSmallest(arr);
                    break;

                case 3:
                    sortArray(arr);
                    break;

                case 4:
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    searchElement(arr, key);
                    break;

                case 5:
                    System.out.print("Enter sum: ");
                    int sum = sc.nextInt();
                    findSumPairs(arr, sum);
                    break;

                case 6:
                    reverseArray(arr);
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