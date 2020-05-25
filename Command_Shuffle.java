package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Shuffle implements Command, Serializable {


    public Command_Shuffle( ){

    }
    @Override
    public ArrayList<String> execute() {
      return   Reciver.shuffle(A_Command.getSet());


    }
}
