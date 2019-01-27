package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class AdderCallable implements Callable<Integer> {
    private String pathName;

    private int result;

    public AdderCallable(String pathName){
        this.pathName = pathName;
        result = 0;
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

    @Override
    public Integer call() throws Exception {
        doAdd();
        return result;
    }
}
