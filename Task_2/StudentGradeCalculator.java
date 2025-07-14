import java.util.*;

class Student {
    String name;
    int rollNumber;
    List<Integer> marks;
    int totalMarks;
    double percentage;
    String grade;

    Student(String name, int rollNumber, List<Integer> marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        calculateResults();
    }

    private void calculateResults() {
        totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        percentage = totalMarks / (double) marks.size();
        grade = assignGrade(percentage);
    }

    private String assignGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }

    public void displayResult() {
        System.out.println("📘 Student Name: " + name);
        System.out.println("🧾 Roll Number: " + rollNumber);
        System.out.println("📚 Marks: " + marks);
        System.out.println("🧮 Total Marks: " + totalMarks);
        System.out.printf("📊 Percentage: %.2f%%\n", percentage);
        System.out.println("🎖️ Grade: " + grade);
        System.out.println("------------------------------\n");
    }
}

public class StudentGradeCalculator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("🎓 Welcome to Student Grade Calculator!");

        boolean addMore;
        do {
            Student student = addStudent();     // Step 1: Add student
            student.displayResult();            // Step 2: Display their result immediately
            System.out.print("Do you want to enter another student? (yes/no): ");
            addMore = scanner.nextLine().trim().equalsIgnoreCase("yes");
        } while (addMore);

        System.out.println("✅ All records saved. Program Ended.");
    }

    private static Student addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter roll number: ");
        int roll = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter number of subjects: ");
        int subjectCount = Integer.parseInt(scanner.nextLine().trim());

        List<Integer> marks = new ArrayList<>();
        for (int i = 1; i <= subjectCount; i++) {
            System.out.print("Enter marks for Subject " + i + " (0-100): ");
            int mark = getValidMark();
            marks.add(mark);
        }

        Student student = new Student(name, roll, marks);
        studentList.add(student);
        System.out.println("✅ Student data added successfully!\n");
        return student;
    }

    private static int getValidMark() {
        while (true) {
            try {
                int mark = Integer.parseInt(scanner.nextLine().trim());
                if (mark >= 0 && mark <= 100) {
                    return mark;
                } else {
                    System.out.print("⚠️ Enter a valid mark between 0 and 100: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Invalid input! Please enter a number: ");
            }
        }
    }
}
