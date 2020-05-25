package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_RemoveById implements Command, Serializable {
    int id;
    public Command_RemoveById (int id){
        this.id =id;
    }
    @Override
    public ArrayList<String> execute() {
 return  Reciver.removeById(A_Command.getSet(),id);

    }
}
