import java.util.*;

class Student {
    private String name;
    private int studentID;
    private int[] marks;

    public void setStudentName(String name) {
        this.name = name;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double calculateAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return (double) sum / marks.length;
    }

    public void checkResult() {
        double avg = calculateAverage();
        if (avg > 40) {
            System.out.println("Result : PASS");
        } else {
            System.out.println("Result : FAIL");
        }
        System.out.println("Average Score : " + avg);
    }

    public void displayStudentInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("Student ID : " + studentID);
        System.out.println("Name : " + name);
    }
}

public class Tutorial1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int totalStudents = sc.nextInt();
        sc.nextLine(); // consume newline

        Student[] students = new Student[totalStudents];

        for (int i = 0; i < totalStudents; i++) {

            System.out.println("\n--- Enter details for Student " + (i + 1) + " ---");

            Student s = new Student();

            System.out.print("Enter Student ID : ");
            s.setStudentID(sc.nextInt());
            sc.nextLine();

            System.out.print("Enter Student Name : ");
            s.setStudentName(sc.nextLine());

            System.out.print("Enter number of subjects : ");
            int n = sc.nextInt();

            int[] marks = new int[n];
            for (int j = 0; j < n; j++) {
                System.out.print("Enter marks for Subject " + (j + 1) + " : ");
                marks[j] = sc.nextInt();
            }

            s.setMarks(marks);

            // Store student object
            students[i] = s;
        }

        System.out.println("\n===== Student Average Calculator Result =====");

        for (Student s : students) {
            s.displayStudentInfo();
            s.checkResult();
            System.out.println("--------------------------------------");
        }

        sc.close();
    }
}
