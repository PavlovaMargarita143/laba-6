package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Command_Help implements  Command, Serializable {
    public void Command_Help(){

    }
    @Override
    public ArrayList<String> execute() { return Reciver.help();

    }
}
