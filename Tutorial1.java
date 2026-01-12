import java.util.*;

class Student {
    private String name;
    private int StudentID;
    private int[] marks;

    public void setStudentName(String name) {
        this.name = name;

    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int getStudentID() {
        return StudentID;
    }

    public String getName() {
        return name;
    }

    public double calculateAverage() {
        int sum = 0;
        for (int marks : marks) {
            sum += marks;
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
        System.out.println("Student ID : " + StudentID);
        System.out.println("Name : " + name);
    }

}

public class Tutorial1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Student s1 = new Student();

        System.out.print("Enter Student ID : ");
        s1.setStudentID(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Student Name : ");
        s1.setStudentName(sc.nextLine());

        System.out.print("Enter the number of subjects : ");
        int n = sc.nextInt();

        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the marks for Subject " + (i + 1) + " : ");
            marks[i] = sc.nextInt();

        }

        s1.setMarks(marks);

        System.out.println("Student Average calculator : ");

        s1.calculateAverage();
        s1.checkResult();

        sc.close();
    }

}