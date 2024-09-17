/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Dinh Duy
 */
public class Circle extends Point {

    double radius;
    double diameter;
    double circumference;
    double area;

    public Circle(int x, int y) {
        super(0, 0);
        this.radius = 1;
    }

    public Circle(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public double getCircumference() {
        return 2 * radius * Math.PI;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return String.format("Circle{Center(x = %d, y = %d), radius = %.2f, diameter = %.2f, circumference = %.2f, area = %.2f}",
                this.getX(), this.getY(), this.getRadius(), this.getDiameter(), this.getCircumference(), this.getArea());
    }

}
