package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Command_Save implements Command, Serializable {


    public Command_Save( ){

    }
    @Override
    public ArrayList<String> execute() {
        try {
            Reciver.save(A_Command.getSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
