package controller;

import java.text.ParseException;
import model.SchoolClass;
import view.Menu1;

/**
 *
 * @author Nguyen Dinh Duy
 */

public class SchoolManagement extends Menu1 {

    private static final String title = "\nManage Students";
    private static final String[] arr = {"Add new student", "Print list of student", "Find student" , "Sort list of students", "Statistic of students born before 2000", "Exit"};
    private final SchoolClass school;

    public SchoolManagement() {
        super(title, arr);
        school = new SchoolClass();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                school.addStudent();
                break;
            case 2:
                school.printList();
                break;
            case 3:
                school.findStudent();
                break;
            case 4:
                school.studentSort();
                break;
            case 5:
                school.student_2000_Chars();
                break;
            case 6:
                System.exit(0);
            default:
                System.err.println("Invalid choice. Please try again.");
        }
    }

    public static void main(String[] args) throws ParseException {
        SchoolManagement main = new SchoolManagement();
        main.run();
    }
}
