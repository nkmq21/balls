package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu1 {

    private String title;
    private ArrayList<String> choices;

    public Menu1() {
    }

    public Menu1(String title, String[] mchon) {
        this.title = title;
        choices = new ArrayList<>();
        for (String m : mchon) {
            choices.add(m);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
//----------------------------------------------------

    public void display() {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
        System.out.println("-------------------");
    }
//----------------------------------------------------

    public int getSelected() {
        display();
        int choice = Integer.parseInt(Utils.getValue("Enter your choice: "));
        return choice;
    }
//----------------------------------------------------

    public abstract void execute(int ch);
//----------------------------------------------------

    public void run() {
        while (true) {
            int ch = getSelected();
            if (ch <= choices.size()) {
                execute(ch);
            } else {
                break;
            }
        }
    }
    
    public void run2() {
        while (true) {
            int ch = getSelected();
            if (ch <= choices.size()) {
                execute(ch);
                break;
            } else {
                break;
            }
        }
    }
//----------------------------------------------------    
}
