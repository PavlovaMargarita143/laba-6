package com.company;

import java.util.ArrayList;

public abstract class A_Command {
    static ArrayList<City> set;
    public static void setSet(ArrayList<City> set) {
        A_Command.set = set;
    }
    public static ArrayList<City> getSet() {
        return set;
    }
}
