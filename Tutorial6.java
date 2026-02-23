
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Tutorial6 {


    static class Expense {
        private double amount;
        private String category;
        private String description;
        private LocalDate date;

        public Expense(double amount, String category, String description, LocalDate date) {
            this.amount = amount;
            this.category = category;
            this.description = description;
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }

        public LocalDate getDate() {
            return date;
        }

        @Override
        public String toString() {
            return date + " | " + category + " | ₹" + amount + " | " + description;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        while (true) {
            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. Delete Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Monthly Report");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateInput = sc.nextLine();
                    LocalDate date = LocalDate.parse(dateInput);

                    Expense expense = new Expense(amount, category, description, date);
                    expenses.add(expense);

                    System.out.println("Expense added successfully!");
                    System.out.println(" ");
                    break;


                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses to delete.");
                        break;
                    }
                    for (int i = 0; i < expenses.size(); i++) {
                        System.out.println(i + ". " + expenses.get(i));
                    }

                    System.out.print("Enter index to delete: ");
                    int index = sc.nextInt();

                    if (index >= 0 && index < expenses.size()) {
                        expenses.remove(index);
                        System.out.println("Expense deleted successfully!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    System.out.println(" ");
                    break;

                case 3:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        for (int i = 0; i < expenses.size(); i++) {
                            System.out.println(i + ". " + expenses.get(i));
                        }
                    }
                    System.out.println(" ");
                    break;
                    
                case 4:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                        break;
                    }

                    System.out.print("Enter month number (1-12): ");
                    int month = sc.nextInt();

                    double total = 0;
                    HashMap<String, Double> categoryTotal = new HashMap<>();

                    for (Expense e : expenses) {
                        if (e.getDate().getMonthValue() == month) {
                            total += e.getAmount();

                            categoryTotal.put(
                                    e.getCategory(),
                                    categoryTotal.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
                            );
                        }
                    }

                    System.out.println("\n--- Monthly Report ---");
                    System.out.println("Total Expense: ₹" + total);

                    System.out.println("\nCategory-wise Expense:");
                    for (String cat : categoryTotal.keySet()) {
                        System.out.println(cat + " : ₹" + categoryTotal.get(cat));
                    }
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}