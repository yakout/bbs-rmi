package bbs.utils;


import java.io.*;
import java.util.ArrayList;

public class BBSLogger {
    public BBSLogger(){}

    public void LogInfo(String fileName, ArrayList<Integer> data) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(fileName,true));
        for(Integer number : data){
            output.append(Integer.toString(number) + " ");
        }
        output.append(System.lineSeparator());
        output.close();
    }
}
