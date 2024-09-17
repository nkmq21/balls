/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import view.Utils;

/**
 *
 * @author Nguyen Dinh Duy
 */
public class Staff {
    protected String staffID;
    protected String fullName;
    protected Date dob;
    protected String phone;

    public Staff(String staffID, String fullName, Date dob, String phone) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.dob = dob;
        this.phone = phone;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
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
            throw new IllegalArgumentException("Invalid date of birth, the staff must be at least 18");
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
    
    public static boolean isValidDateOfBirth(Date dob) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDay = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthDay, currentDate).getYears() >= 18;
    }
    
    public static String getIDInput() {
        String ID = Utils.getValue("Enter your ID: ");
        if (ID.matches("S[0-9]{4}")) {
            return ID;
        } else {
            throw new IllegalArgumentException("Invalid ID format. Please enter a ID in the format S0000");
        }
    }
    
    public static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(dateStr);
            if (!isValidDateOfBirth(date)) {
                throw new IllegalArgumentException("Age must be at least 18");
            }
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please enter a date in the format dd/MM/yyyy");
        }
        
    }
}
