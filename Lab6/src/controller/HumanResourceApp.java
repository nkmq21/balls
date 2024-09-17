package controller;

import java.text.ParseException;
import model.StaffList;
import view.Menu;

/**
 *
 * @author Nguyen Dinh Duy
 */

//TODO: check the staff ID input initialization, avoid program stop suddenly 
//DONE: fix print headers 
//DONE: fix sub menu of find options
//TODO: fix find staff printing method
//TODO: fix print method after deploy the same age staff method

public class HumanResourceApp extends Menu {
    private static final String[] arr = {"Display all staff", "Add new staff", "Search staff options", "Sort staff options", "Staff with the same age"};
    private final StaffList staff;

    public HumanResourceApp() {
        super("HUMAN RESOURCE APP", arr);
        staff = new StaffList();
        //StaffList.initializeStaffArr(StaffList.staffArray);
    }

    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1 -> {
                StaffList.printList();
            }
            case 2 -> {
                StaffList.addNewStaff();
            }
            case 3 -> {
                StaffList.findStaff();
            }
            case 4 -> {
                StaffList.staffSort();
            }
            case 5 -> {
                StaffList.sameAgeStaff();
            }
            case 6 -> {
                System.exit(0);
            }
            default -> System.err.println("INVALID CHOICE, PLEASE TRY AGAIN");
        }
    }
    
    public static void main(String[] args) throws ParseException {
        HumanResourceApp main = new HumanResourceApp();
        main.run();
    } 
}
