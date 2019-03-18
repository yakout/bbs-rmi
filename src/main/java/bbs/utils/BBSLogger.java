package bbs.utils;


import java.io.*;
import java.util.ArrayList;

public class BBSLogger {
    public static void LogInfo(String fileName, ArrayList<ArrayList<Integer>> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder logs = new StringBuilder();
            for (ArrayList<Integer> tuple : data) {
                for (Integer number : tuple) {
                    logs.append(number.toString()).append(" ");
                }
                logs.append("\n");
            }
            bw.write(logs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
