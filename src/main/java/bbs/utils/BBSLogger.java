package bbs.utils;


import java.io.*;
import java.util.ArrayList;

public class SystemLogger {
    public SystemLogger(){

    }
    public void LogInfo(String fileName, ArrayList<ArrayList<Integer>> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            String logs = new String("");
            for (ArrayList<Integer> tuple : data) {
                for (Integer number : tuple) {
                    logs = logs + number.toString() + " ";
                }
                logs += "\n";
            }
            bw.write(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
