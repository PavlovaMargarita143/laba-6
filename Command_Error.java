package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Error implements Command, Serializable {
    public Command_Error(){}
    @Override
    public ArrayList<String> execute(){
       return Reciver.error();

    }
}
