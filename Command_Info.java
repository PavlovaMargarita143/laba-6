package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Info implements Command, Serializable {
    public  Command_Info(){}

    @Override
    public ArrayList<String> execute() {
     return    Reciver.info(A_Command.getSet());


    }
}
