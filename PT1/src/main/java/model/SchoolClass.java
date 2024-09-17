package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import view.Utils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import view.Menu1;

/**
 *
 * @author Nguyen Dinh Duy
 */
public class SchoolClass {

    public static ArrayList<Student> List = new ArrayList<>();

    public SchoolClass() {
        List = new ArrayList<>();
        Student sv1 = new Student(1, "Nguyen Van A", parseDate("10/2/2000"), 8.5, 9.0);
        Student sv2 = new Student(2, "Vo Van B", parseDate("11/9/1999"), 7.8, 5.2);
        Student sv3 = new Student(3, "Nguyen Van C", parseDate("2/4/1988"), 9.2, 8.8);
        Student sv4 = new Student(4, "Nguyen Manh D", parseDate("5/4/1990"), 7.4, 7.7);
        Student sv5 = new Student(5, "Nguyen Dinh E", parseDate("11/9/2003"), 5.5, 3.2);
        List.add(sv1);
        List.add(sv2);
        List.add(sv3);
        List.add(sv4);
        List.add(sv5);
    }

    public static ArrayList<Student> getList() {
        return List;
    }

    public static void setList(ArrayList<Student> List) {
        SchoolClass.List = List;
    }

    public boolean isEmptyList() {
        return List.isEmpty();
    }

    public void addNew(Student student) {
        List.add(student);
    }

    public static void printList() {
        printHeaders();
        for (Student student : List) {
            System.out.printf("%4d  %-22s    %-13s %5.1f %6.1f %9.2f",
                    student.getId(), student.getName(), student.getBday(), student.getJava(), student.getHTML(), student.averageMarks());
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    private static final String[] sub_menu = {"Birthday", "Average"};
    public static Menu1 menu = new Menu1("", sub_menu) {
        @Override
        public void execute(int ch) {
            //int choice = Integer.parseInt(Utils.getValue("Enter your choice: "));
            switch (ch) {
                case 1 -> {
                    Date bday = parseDate(Utils.getValue("Enter the birthday: "));
                    for (Student student : List) {
                        if (student.getBday().equals(bday)) {
                            System.out.println("Student with birthday " + bday + ": " + student);
                        } else {
                        }
                    }
                }
                case 2 -> {
                    double avg = Double.parseDouble(Utils.getValue("Enter the average: "));
                    for (Student student : List) {
                        if (student.getAverage() == avg) {
                            System.out.println("Student with average " + avg + ": " + student);
                        }
                    }
                }
                default ->
                    System.out.println("Invalid choice, please choose again between 2 above options");
            }
        }
    };
    
    public static void findStudent() {
        menu.run();
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addStudent() {
        int id = Integer.parseInt(Utils.getValue("Enter ID: "));
        String name = Utils.getValue("Enter your name: ");
        Date bday = parseDate(Utils.getValue("Enter your birth day: "));
        double java = Double.parseDouble(Utils.getValue("Enter java score: "));
        double html = Double.parseDouble(Utils.getValue("Enter html score: "));
        List.add(new Student(id, name, bday, java, html));
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------   

    public static void student_2000_Chars() {
        ArrayList<Student> studentList = new ArrayList<>();
        for (Student student : List) {
            LocalDate birthday = student.getBday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = birthday.getYear();
            if (year < 2000) {
                studentList.add(student);
            }
        }
        if (!studentList.isEmpty()) {
            printHeaders();
            for (Student student : studentList) {
                System.out.printf("%4d  %-22s    %-11s %5.1f %6.1f %9.1f",
                        student.getId(), student.getName(), student.getBday(), student.getJava(), student.getHTML(), student.averageMarks());
                System.out.println(" ");
            }
        } else {
            System.out.println("No student found");
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static class CompareByAverage implements Comparator<Student> {
        @Override
        public int compare(Student stu1, Student stu2) {
            return Double.compare(stu1.averageMarks(), stu2.averageMarks());
        }
    }
    
    public static class CompareByHTML implements Comparator<Student> {
        @Override
        public int compare(Student stu1, Student stu2) {
            return Double.compare(stu1.getHTML(), stu2.getHTML());
        }
    }
    
    public static class CompareByJava implements Comparator<Student> {
        @Override
        public int compare(Student stu1, Student stu2) {
            return Double.compare(stu1.getJava(), stu2.getJava());
        }
    }
    
    //private static final ArrayList<Student> sortedList = List;
    private static final String[] subMenuSort = {"By average", "By HTML", "By Java", "By birthday"};
    public static Menu1 menuSort = new Menu1("", subMenuSort) {
        @Override
        public void execute(int ch) {
            switch(ch) {
                case 1 -> {
                    //by average
                    Collections.sort(List, new CompareByAverage());
                }
                case 2 -> {
                    //by HTML
                    Collections.sort(List, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            return Double.compare(o1.getHTML(), o2.getHTML());
                        }
                    }
                    );
                }
                case 3 -> {
                    //by java
                    //Collections.sort(List, new CompareByJava());
                    List.sort(Comparator.comparing(Student::getJava));
                }
                case 4 -> {
                    Collections.sort(List, (o1, o2) -> {
                        return o1.getBday().compareTo(o2.getBday());
                    });
                }
                default -> System.out.println("Invalid choice, please choose again.");
            }
        }
    };
    
    public static void studentSort() {
        menuSort.run2();
    }
    
    //--------------------------------------------------------------------------------------------------------

    public static void sortStudentAvg() {
        Collections.sort(List, (Student stu1, Student stu2) -> Double.compare(stu2.averageMarks(), stu1.averageMarks()));
        System.out.println("Sort complete");
    }

    public static void printHeaders() {
        System.out.println("|************************* List of Students ************************|");
        System.out.println("| ID |         Name         |         Date of Birth         | Java | HTML | Average |");
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please enter a date in the format dd/MM/yyyy");
        }
    }
}
