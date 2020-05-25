package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_SumOfSeaLevel implements Command, Serializable {
    public Command_SumOfSeaLevel(){}
    @Override
    public ArrayList<String> execute() {
       return Reciver.sumOfSeaL(A_Command.getSet());

    }
}
