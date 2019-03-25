package bbs;


import java.io.*;
import java.io.Writer;
import java.util.ArrayList;

public class BBSLogger {
    public BBSLogger(){}

    public void LogInfo(String fileName, ArrayList<String> data) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(fileName,true));
        for(String number : data){
            output.append(number + " ");
        }
        output.append(System.lineSeparator());
        output.close();
    }
}
