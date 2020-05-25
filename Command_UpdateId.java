package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_UpdateId implements Command, Serializable {
    int id;
    City newCity;
    public Command_UpdateId(int id, City newCity){
        this.id= id;
        this.newCity = newCity;
    }
    @Override
    public ArrayList<String> execute() {
       return Reciver.update(A_Command.getSet(),this.id, newCity);

    }
}
