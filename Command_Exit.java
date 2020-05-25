package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Exit implements Command, Serializable {
    public Command_Exit(){

    }
    @Override
    public ArrayList<String> execute() {
       return Reciver.exit();

    }
}
