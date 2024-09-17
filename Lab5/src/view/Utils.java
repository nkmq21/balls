/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import java.util.Scanner;
/**
 *
 * @author Nguyen Dinh Duy
 */
public class Utils {
    public static String getValue(String input)  {
    Scanner sc = new Scanner(System.in);
    System.out.println(input);
    return sc.nextLine();
    }
}
