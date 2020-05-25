package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Sort implements Command, Serializable {
    public  Command_Sort(){}
    @Override
    public ArrayList<String> execute() {
       return Reciver.sort(A_Command.getSet());

    }
}
