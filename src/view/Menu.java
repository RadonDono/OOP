package view;

import Persons.Person;

import java.util.Scanner;

public abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static Person loggedInPerson = null;

    protected static Scanner getScanner() {
        return Menu.scanner;
    }

    public static Person getLoggedInUser() {
        return Menu.loggedInPerson;
    }

    public static void setLoggedInUser(Person user) {
        Menu.loggedInPerson = user;
    }

    protected String getInput(String message) {
        System.out.println(message + ":");
        return Menu.getScanner().nextLine().trim();
    }

    protected String getChoice() {
        return Menu.getScanner().nextLine().trim().toLowerCase();
    }

    public abstract void run();

    protected abstract void showOptions();
}