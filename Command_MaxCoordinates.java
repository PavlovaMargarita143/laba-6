package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_MaxCoordinates implements Command, Serializable {
    public Command_MaxCoordinates(){}
    @Override
    public ArrayList<String> execute() {
      return   Reciver.maxCoordinates(A_Command.getSet());


    }
}
