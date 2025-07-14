import java.util.*;
import java.io.*;

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.txt";
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        loadFromFile();
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("📚 Welcome to Student Management System");
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = getIntInput(sc);

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> removeStudent(sc);
                case 3 -> searchStudent(sc);
                case 4 -> displayAllStudents();
                case 5 -> System.out.println("✅ Exiting... Goodbye!");
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 5);

        saveToFile();
        sc.close();
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter roll number: ");
        int roll = getIntInput(sc);
        System.out.print("Enter grade (A-F): ");
        String grade = sc.nextLine().trim().toUpperCase();
        System.out.print("Enter age: ");
        int age = getIntInput(sc);
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
            System.out.println("❗ Fields cannot be empty.");
            return;
        }

        studentList.add(new Student(name, roll, grade, age, email));
        System.out.println("✅ Student added successfully.");
    }

    private static void removeStudent(Scanner sc) {
        System.out.print("Enter roll number to remove: ");
        int roll = getIntInput(sc);
        boolean removed = studentList.removeIf(s -> s.getRollNumber() == roll);
        if (removed)
            System.out.println("🗑️ Student removed.");
        else
            System.out.println("❌ Student not found.");
    }

    private static void searchStudent(Scanner sc) {
        System.out.print("Enter roll number to search: ");
        int roll = getIntInput(sc);
        for (Student s : studentList) {
            if (s.getRollNumber() == roll) {
                System.out.println("🔎 Student found:\n" + s);
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    private static void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("📭 No student records.");
            return;
        }
        System.out.println("📋 Student List:");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    private static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                studentList.add(Student.fromFileString(line));
            }
        } catch (IOException ignored) {}
    }

    private static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : studentList) {
                bw.write(s.toFileString());
                bw.newLine();
            }
            System.out.println("💾 Data saved to file.");
        } catch (IOException e) {
            System.out.println("❌ Failed to save data.");
        }
    }

    private static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("❗ Invalid input. Enter a number: ");
            }
        }
    }
}
