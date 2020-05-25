package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Clear implements Command, Serializable {


    public Command_Clear( ){

    }
    @Override
    public ArrayList<String> execute() {
      return   Reciver.clear( A_Command.getSet());


    }
}
