import java.util.*;
public class FMP2 {
   private static HashSet<String> courseCodes = new HashSet<>();
   private static HashSet<String> enrollmentPairs = new HashSet<>();
   private static HashMap<Integer, String> students = new HashMap<>();
   private static Scanner scanner = new Scanner(System.in);
   public static void main(String[] args) {
       int choice = -1;
       do {
           System.out.println("\n--- Course Registration and Unique Enrollment ---");
           System.out.println("1 Add student | 2 Add course | 3 Enroll | 4 List student courses | 5 List courses | 0 Exit");
           System.out.print("Enter choice: ");
           try {
               choice = Integer.parseInt(scanner.nextLine().trim());
           } catch (NumberFormatException e) {
               System.out.println("Invalid menu option.");
               continue;
           }
           switch (choice) {
               case 1:
                   addStudent();
                   break;
               case 2:
                   addCourse();
                   break;
               case 3:
                   enrollStudent();
                   break;
               case 4:
                   listStudentCourses();
                   break;
               case 5:
                   listCourses();
                   break;
               case 0:
                   System.out.println("Exiting application.");
                   break;
               default:
                   System.out.println("Invalid menu option.");
           }
       } while (choice != 0);
   }
   private static void addStudent() {
       System.out.print("Enter student ID: ");
       try {
           int id = Integer.parseInt(scanner.nextLine().trim());
           System.out.print("Enter student name: ");
           String name = scanner.nextLine().trim();
           if (name.isEmpty()) {
               System.out.println("Student name cannot be empty.");
               return;
           }
           if (students.containsKey(id)) {
               System.out.println("Duplicate student ID rejected.");
           } else {
               students.put(id, name);
               System.out.println("Student added"); //[span_1](start_span)[span_1](end_span)
           }
       } catch (NumberFormatException e) {
           System.out.println("Malformed number.");
       }
   }
   private static void addCourse() {
       System.out.print("Enter course code: ");
       String code = scanner.nextLine().trim().toUpperCase();
       if (code.isEmpty()) {
           System.out.println("Course code cannot be empty.");
           return;
       }
       if (courseCodes.contains(code)) {
           System.out.println("Duplicate rejected"); //[span_2](start_span)[span_2](end_span)
       } else {
           courseCodes.add(code);
           System.out.println("Course added"); //[span_3](start_span)[span_3](end_span)
       }
   }
   private static void enrollStudent() {
       System.out.print("Enter student ID: ");
       try {
           int id = Integer.parseInt(scanner.nextLine().trim());
           if (!students.containsKey(id)) {
               System.out.println("Student not found"); //[span_4](start_span)[span_4](end_span)
               return;
           }
           System.out.print("Enter course code: ");
           String code = scanner.nextLine().trim().toUpperCase();
           if (!courseCodes.contains(code)) {
               System.out.println("Course not found");
               return;
           }
           String pair = id + ":" + code; //[span_5](start_span)[span_5](end_span)
           if (enrollmentPairs.contains(pair)) {
               System.out.println("Duplicate enrollment rejected"); //[span_6](start_span)[span_6](end_span)
           } else {
               enrollmentPairs.add(pair);
               System.out.println("Enrollment added"); //[span_7](start_span)[span_7](end_span)
           }
       } catch (NumberFormatException e) {
           System.out.println("Malformed number.");
       }
   }
   private static void listStudentCourses() {
       System.out.print("Enter student ID: ");
       try {
           int id = Integer.parseInt(scanner.nextLine().trim());
           if (!students.containsKey(id)) {
               System.out.println("Student not found");
               return;
           }
           ArrayList<String> enrolled = new ArrayList<>();
           for (String pair : enrollmentPairs) { // Enhanced for traversal[span_8](start_span)[span_8](end_span)
               String[] parts = pair.split(":");
               if (Integer.parseInt(parts[0]) == id) {
                   enrolled.add(parts[1]);
               }
           }
           if (enrolled.isEmpty()) {
               System.out.println("No courses enrolled.");
           } else {
               System.out.println(String.join(" and ", enrolled)); //[span_9](start_span)[span_9](end_span)
           }
       } catch (NumberFormatException e) {
           System.out.println("Malformed number.");
       }
   }
   private static void listCourses() {
       if (courseCodes.isEmpty()) {
           System.out.println("No courses available.");
           return;
       }
       System.out.println(String.join(" and ", courseCodes));
   }
}