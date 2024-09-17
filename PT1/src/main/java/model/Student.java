/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Nguyen Dinh Duy
 */
public class Student {

    protected int id;
    protected String name;
    protected Date bday;
    protected double Java;
    protected double HTML;
    protected double average;

    public Student(int id, String name, Date bday, double Java, double HTML) {
        this.id = id;
        this.name = name;
        this.bday = bday;
        this.Java = Java;
        this.HTML = HTML;
    }
    
    public void setAverage(double average) {
        this.average = average;
    }
    
    public double getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public double getJava() {
        return Java;
    }

    public void setJava(double Java) {
        this.Java = Java;
    }

    public double getHTML() {
        return HTML;
    }

    public void setHTML(double HTML) {
        this.HTML = HTML;
    }
    
    

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", bday=" + bday + ", Java=" + Java + ", HTML=" + HTML + '}';
    }
    
    public double averageMarks() {
        average = (getHTML() + getJava()) / 2;
        return average;
    }
}
