package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Adder implements Runnable {
    private String pathName;

    private int result;

    public Adder(String pathName){
        this.pathName = pathName;
        result = 0;
    }

    @Override
    public void run() {
        doAdd();
    }
    
    public void doAdd(){
        try
        {
            FileReader fileReader = new FileReader(pathName);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            for (String line : bufferReader.lines().collect(Collectors.toList())) {
                //System.out.println(line);
                result++;
            };
        }
        catch( FileNotFoundException e)
        {
            System.out.println(e.toString());
        }
    }

    public int GetResult(){
        return result;
    }
}
