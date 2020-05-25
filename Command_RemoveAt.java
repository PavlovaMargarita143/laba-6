package com.company;

import java.io.Serializable;
import java.util.ArrayList;

class Command_RemoveAt implements Command, Serializable {
    ArrayList<City> set;
    int index;
    Command_RemoveAt(int index){
        this.index=index;
    }

    @Override
    public ArrayList<String> execute() {
        return Reciver.remove(A_Command.getSet(),index);

    }
}

