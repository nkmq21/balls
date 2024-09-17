
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nguyen Dinh Duy
 */
public class test implements Comparable<test> {
    protected String staffID;
    protected String fullName;
    protected Date dob;
    protected String phone;
    
    // List to store staff members
    private static List<test> staffList = new ArrayList<>();

    public test(String staffID, String fullName, Date dob, String phone) {
        if (!isValidtestID(staffID)) {
            throw new IllegalArgumentException("Invalid staff ID format. Please enter an ID in the format S0000");
        }
        if (!isValidDateOfBirth(dob)) {
            throw new IllegalArgumentException("Invalid date of birth. The staff member must be at least 18 years old.");
        }
        this.staffID = staffID;
        this.fullName = fullName;
        this.dob = dob;
        this.phone = phone;
    }

    public String gettestID() {
        return staffID;
    }

    public void settestID(String staffID) {
        if (!isValidtestID(staffID)) {
            throw new IllegalArgumentException("Invalid staff ID format. Please enter an ID in the format S0000");
        }
        this.staffID = staffID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        if (!isValidDateOfBirth(dob)) {
            throw new IllegalArgumentException("Invalid date of birth. The staff member must be at least 18 years old.");
        }
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDay = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthDay, currentDate).getYears();
    }

    private static boolean isValidtestID(String staffID) {
        return staffID.matches("[Ss][0-9]{4}");
    }

    private static boolean isValidDateOfBirth(Date dob) {
        LocalDate birthDay = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDay, currentDate).getYears() >= 18;
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(dateStr);
            if (!isValidDateOfBirth(date)) {
                throw new IllegalArgumentException("Age must be at least 18.");
            }
            return date;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please enter a date in the format dd/MM/yyyy");
        }
    }

    @Override
    public int compareTo(test other) {
        return this.dob.compareTo(other.dob);
    }

    public static void addNewtest(String staffID, String fullName, String dobStr, String phone) {
        Date dob = parseDate(dobStr);
        test newtest = new test(staffID, fullName, dob, phone);
        staffList.add(newtest);
        System.out.println("New staff added: " + newtest.getFullName());
    }

    public static void main(String[] args) {
        // Example usage
        try {
            addNewtest("S1234", "John Doe", "15/06/2000", "123-456-7890");
            addNewtest("S5678", "Jane Doe", "20/08/1995", "098-765-4321");

            // Display staff details and compare ages
            for (test staff : staffList) {
                System.out.println("test ID: " + staff.gettestID() + ", Name: " + staff.getFullName() + ", Age: " + staff.getAge());
            }

            // Compare two staff members based on date of birth
            test staff1 = staffList.get(0);
            test staff2 = staffList.get(1);
            int comparison = staff1.compareTo(staff2);
            if (comparison < 0) {
                System.out.println(staff1.getFullName() + " is younger than " + staff2.getFullName());
            } else if (comparison > 0) {
                System.out.println(staff1.getFullName() + " is older than " + staff2.getFullName());
            } else {
                System.out.println(staff1.getFullName() + " and " + staff2.getFullName() + " have the same date of birth");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
