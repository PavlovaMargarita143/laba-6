package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_AverageOfAgglomeration implements  Command, Serializable {
    public  Command_AverageOfAgglomeration(){}
    @Override
    public ArrayList<String> execute() {
       return Reciver.averageOfAgg(A_Command.getSet());

    }
}
