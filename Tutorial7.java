import java.io.*;
import java.util.*;

class Student {
    String name;
    int age;
    String grade;

    Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name:" + name + " Age:" + age + " Grade:" + grade;
    }
}

public class Tutorial7 {

    static final String FILE_NAME = "students.txt";
    static List<Student> students = new ArrayList<>();

    // Load from file
    static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");
                String name = parts[0].split(":")[1];
                int age = Integer.parseInt(parts[1].split(":")[1]);
                String grade = parts[2].split(":")[1];

                students.add(new Student(name, age, grade));
            }
        } catch (IOException e) {
            System.out.println("No file found. Starting fresh.");
        }
    }

    // Save to file
    static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Add student
    static void addStudent(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        System.out.print("Enter Grade: ");
        String grade = sc.next();

        students.add(new Student(name, age, grade));
        saveToFile();
        System.out.println("Student added!");
    }

    // View students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No records.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Search by name
    static void searchStudent(Scanner sc) {
        System.out.print("Enter name to search: ");
        String name = sc.next();

        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Not found.");
    }

    // Update student
    static void updateStudent(Scanner sc) {
        System.out.print("Enter name to update: ");
        String name = sc.next();

        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new age: ");
                s.age = sc.nextInt();
                System.out.print("Enter new grade: ");
                s.grade = sc.next();

                saveToFile();
                System.out.println("Updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Delete student
    static void deleteStudent(Scanner sc) {
        System.out.print("Enter name to delete: ");
        String name = sc.next();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.name.equalsIgnoreCase(name)) {
                it.remove();
                saveToFile();
                System.out.println("Deleted!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadFromFile();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add");
            System.out.println("2. View");
            System.out.println("3. Search");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> updateStudent(sc);
                case 5 -> deleteStudent(sc);
                case 6 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}