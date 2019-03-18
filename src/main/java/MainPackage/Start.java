package Main.java.MainPackage;

import Main.java.ServerPackage.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Start {
    private static Properties prop = null;
    private static void readProperties(String fileName){
        if (prop == null) {
            prop = new Properties();
        }
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            prop.load(input);
        } catch (IOException ex) {
            System.out.println("file is not found in the working directory");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        readProperties("src/main/resources/system.properties");
        Server s = new Server();
        s.updateServerObj(1);

    }
}