public class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;
    private String email;

    public Student(String name, int rollNumber, String grade, int age, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
        this.email = email;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String toFileString() {
        return name + "," + rollNumber + "," + grade + "," + age + "," + email;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), parts[4]);
    }

    @Override
    public String toString() {
        return "Name: " + name +
               "\nRoll No: " + rollNumber +
               "\nGrade: " + grade +
               "\nAge: " + age +
               "\nEmail: " + email +
               "\n---------------------------";
    }
}
