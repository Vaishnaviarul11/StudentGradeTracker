git import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        double[] subjectGrades;

        public Student(String name, int subjectCount) {
            this.name = name;
            this.subjectGrades = new double[subjectCount];
        }

        public double getAverage() {
            double sum = 0;
            for (double g : subjectGrades) sum += g;
            return sum / subjectGrades.length;
        }

        public double getTotal() {
            double total = 0;
            for (double g : subjectGrades) total += g;
            return total;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        // Get subject names
        String[] subjectNames = new String[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter name of subject " + (i + 1) + ": ");
            subjectNames[i] = scanner.nextLine();
        }

        double[] subjectSums = new double[numSubjects]; // total scores per subject

        // Input students and their grades
        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter name of student " + (i + 1) + ": ");
            String name = scanner.nextLine();

            Student student = new Student(name, numSubjects);

            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Enter grade for " + subjectNames[j] + ": ");
                student.subjectGrades[j] = scanner.nextDouble();
                subjectSums[j] += student.subjectGrades[j];
            }
            scanner.nextLine(); // consume leftover newline

            students.add(student);
        }

        // Display averages for each student
        System.out.println("\n📋 Student Averages:");
        for (Student s : students) {
            System.out.printf("%-15s : Average = %.2f\n", s.name, s.getAverage());
        }

        // Calculate and display average for each subject
        System.out.println("\n📚 Subject Averages:");
        double highestAvg = subjectSums[0] / numStudents;
        double lowestAvg = subjectSums[0] / numStudents;
        int highestSubjectIndex = 0;
        int lowestSubjectIndex = 0;

        for (int i = 0; i < numSubjects; i++) {
            double avg = subjectSums[i] / numStudents;
            System.out.printf("%-15s Average: %.2f\n", subjectNames[i], avg);

            if (avg > highestAvg) {
                highestAvg = avg;
                highestSubjectIndex = i;
            }

            if (avg < lowestAvg) {
                lowestAvg = avg;
                lowestSubjectIndex = i;
            }
        }

        // Find highest and lowest scoring students
        Student highestStudent = students.get(0);
        Student lowestStudent = students.get(0);
        double totalSum = 0;

        for (Student s : students) {
            double total = s.getTotal();
            totalSum += total;

            if (total > highestStudent.getTotal()) highestStudent = s;
            if (total < lowestStudent.getTotal()) lowestStudent = s;
        }

        double classAverage = totalSum / (numStudents * numSubjects);

        // Display class-wide stats
        System.out.println("\n Overall Statistics:");
        System.out.printf("Highest Total Score: %.2f (%s)\n", highestStudent.getTotal(), highestStudent.name);
        System.out.printf("Lowest Total Score : %.2f (%s)\n", lowestStudent.getTotal(), lowestStudent.name);
        System.out.printf("Class Average Score: %.2f\n", classAverage);
        System.out.printf("Highest Scoring Subject: %s (%.2f)\n", subjectNames[highestSubjectIndex], highestAvg);
        System.out.printf("Lowest Scoring Subject : %s (%.2f)\n", subjectNames[lowestSubjectIndex], lowestAvg);

        scanner.close();
    }
}
