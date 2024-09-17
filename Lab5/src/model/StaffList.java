/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import static model.Staff.parseDate;
import view.Menu;
import view.Utils;

/**
 *
 * @author Nguyen Dinh Duy
 */
public class StaffList {

    public static ArrayList<Staff> List = new ArrayList<>();

    public StaffList() {
            
    }

    public static String[] staffArray = {
        "S0001:Phan Truong Minh:11/05/2002:0905189234",
        "S0002:Tran Trung Tien:31/08/2003:0913235467",
        "S0003:Minh Nguyen:20/09/2001:037896754",
        "S0004:Phan Thi Minh Thu:09/08/2002:090324565",};

    public static void addNewTrimStaff(String staffID, String fullName, String birthday, String phone) {
        Date dob = Staff.parseDate(birthday);
        Staff newStaff = new Staff(staffID, fullName, dob, phone);
        List.add(newStaff);
        System.out.println("New staff added: " + newStaff.getFullName());
    }

    //put this in main method
    public static void initializeStaffArr(String[] staffArray) {
        List<Staff> list = new ArrayList<>();
        for (String staffData : staffArray) {
            String[] parts = staffData.split(":");
            if (parts.length == 4) {
                String ID = parts[0].trim();
                String name = parts[1].trim();
                String birthday = parts[2].trim();
                String phone = parts[3].trim();
                addNewTrimStaff(ID, name, birthday, phone);
            } else {
                System.out.println("Invalid data format");
            }
        }
    }

    //checking if the ID input 
    private static String getUniqueID() {
        String id;
        while (true) {
            id = Staff.getIDInput();
            boolean idExist = false;
            for (Staff staff : List) {
                if (staff.getStaffID().equals(id)) {
                    idExist = true;
                    break;
                }
            }
            if (idExist) {
                System.out.println("This ID is already exist, please enter another ID");
            } else {
                break;
            }
        }
        return id;
    }

    public static void addNewStaff() {
        String ID = getUniqueID();
        String name = Utils.getValue("Enter your name: ");
        Date birthday = Staff.parseDate(Utils.getValue("Enter your birthday: "));
        String phone = Utils.getValue("Enter your phone number: ");
        List.add(new Staff(ID, name, birthday, phone));
    }

    private static final String[] subMenu = {"By age", "By birthday", "By name", "By phone"};
    public static Menu menuFind = new Menu("Find Options", subMenu) {
        @Override
        public void execute(int ch) {
            switch (ch) {
                case 1 -> {
                    ArrayList<Staff> ageFind = new ArrayList<>();
                    try {
                        int age = Integer.parseInt(Utils.getValue("Enter the age: "));
                        boolean found = false;
                        for (Staff staff : List) {
                            if (staff.getAge() == age) {
                                ageFind.add(staff);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("No staff found with this age");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age, please try again");
                    }
                    printHeaders();
                    for (Staff age : ageFind) {
                        System.out.printf("| %-12s | %-25s | %-25s | %-12s | %3d |\n",
                                age.getStaffID(), age.getFullName(), age.getDob(), age.getPhone(), age.getAge());
                    }
                    System.out.println("+--------------+---------------------------+------------------------------+--------------+-----+");

                }
                case 2 -> {
                    Date dob = null;
                    ArrayList<Staff> dobFind = new ArrayList<>();
                    try {
                        dob = parseDate(Utils.getValue("Enter your birthday: "));
                    } catch (Exception e) {
                        System.out.println("Invalid birthday, please try again");
                    }
                    boolean found = false;
                    for (Staff staff : List) {
                        if (staff.getDob().equals(dob)) {
                            dobFind.add(staff);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No staff found with this birthday");
                    }
                    printHeaders();
                    for (Staff date : dobFind) {
                        System.out.printf("| %-12s | %-25s | %-25s | %-12s | %3d |\n",
                                date.getStaffID(), date.getFullName(), date.getDob(), date.getPhone(), date.getAge());
                    }
                    System.out.println("+--------------+---------------------------+------------------------------+--------------+-----+");

                }
                case 3 -> {
                    String name = Utils.getValue("Enter the name");
                    boolean found = false;
                    ArrayList<Staff> nameFind = new ArrayList<>();
                    for (Staff staff : List) {
                        if (staff.getFullName().equalsIgnoreCase(name)) {
                            nameFind.add(staff);
                            found = true;
                        } 
                    }
                    if(!found) {
                        System.out.println("No staff found with this name");
                    }
                    printHeaders();
                    for (Staff ten : nameFind) {
                        System.out.printf("| %-12s | %-25s | %-25s | %-12s | %3d |\n",
                                ten.getStaffID(), ten.getFullName(), ten.getDob(), ten.getPhone(), ten.getAge());
                    }
                }
                case 4 -> {
                    String phone = Utils.getValue("Enter the phone number: ");
                    boolean found = false;
                    ArrayList<Staff> phoneFind = new ArrayList<>();
                    for (Staff staff : List) {
                        if (staff.getPhone().equals(phone)) {
                            phoneFind.add(staff);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No staff found with this phone number");
                    }
                    printHeaders();
                    for (Staff sdt : phoneFind) {
                        System.out.printf("| %-12s | %-25s | %-25s | %-12s | %3d |\n",
                                sdt.getStaffID(), sdt.getFullName(), sdt.getDob(), sdt.getPhone(), sdt.getAge());
                    }
                }
                default -> {
                    System.out.println("Invalid choice, please choose again");
                }
            }
        }
    };
    
    public static void findStaff() {
        menuFind.run();
    }

    public static void sameAgeStaff() {
        ArrayList<Staff> staff_LIST = new ArrayList<>();
        int age = Integer.parseInt(Utils.getValue("Enter the age: "));
        for (Staff staff : List) {
            if (staff.getAge() == age) {
                staff_LIST.add(staff);
            }
        }
        if (!staff_LIST.isEmpty()) {
            printHeaders();
            for (Staff staff : staff_LIST) {
                System.out.printf("| %-12s | %-25s | %-25s | %-12s | %3d |",
                        staff.getStaffID(), staff.getFullName(), staff.getDob(), staff.getPhone(), staff.getAge());
                System.out.println(" ");
            }
        } else {
            System.out.println("No staff found with this age");
        }
    }

    public static void printHeaders() {
        System.out.println("+--------------+---------------------------+------------------------------+--------------+-----+");
        System.out.println("|  Staff ID    |         Full Name         |         Date of Birth        |     Phone    | Age |");
        System.out.println("+--------------+---------------------------+------------------------------+--------------+-----+");
    }

    public static void printList() {
        printHeaders();
        for (Staff staff : List) {
            System.out.printf("| %-12s | %-25s | %-25s | %-12s | %3d |\n",
                    staff.getStaffID(), staff.getFullName(), staff.getDob(), staff.getPhone(), staff.getAge());
        }
        System.out.println("+--------------+---------------------------+------------------------------+--------------+-----+");
    }

    public static class sortByName implements Comparator<Staff> {

        @Override
        public int compare(Staff o1, Staff o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }

    }

    public static class sortByAge implements Comparator<Staff> {

        @Override
        public int compare(Staff o1, Staff o2) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }

    }

    public static class sortByDob implements Comparator<Staff> {

        @Override
        public int compare(Staff o1, Staff o2) {
            return o1.getDob().compareTo(o2.getDob());
        }

    }
    
    private static final String[] subMenuSort = {"By name", "By age", "By date of birth"};
    public static Menu menuSort = new Menu("SORT MENU", subMenuSort) {
        
        @Override
        public void execute(int ch) {
            switch (ch) {
                case 1 -> {
                    //by name
                    Collections.sort(List, new sortByName());
                }
                case 2 -> {
                    Collections.sort(List, new sortByAge());
                }
                case 3 -> {
                    Collections.sort(List, new sortByDob());
                }
            }
        }
        
    };
    
    public static void staffSort() {
        menuSort.run2();
    }
}
