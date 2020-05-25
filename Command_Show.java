package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Show implements Command, Serializable {


    public Command_Show( ){

    }
    @Override
    public ArrayList<String> execute() {
        return Reciver.show(A_Command.getSet());


    }
}
