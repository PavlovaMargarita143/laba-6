package com.company;

import java.io.Serializable;
import java.util.ArrayList;

class Command_Add implements Command, Serializable {

    City newCity;
    public Command_Add( City newCity){

        this.newCity=newCity;
    }

    @Override
    public ArrayList<String> execute() {
       return Reciver.add(newCity,A_Command.getSet());

    }
}
