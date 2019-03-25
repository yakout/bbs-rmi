package bbs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
public class Start {

    private static Configuration fillConfig(Properties prop) {
        Configuration configs = new Configuration();
        prop.forEach((key, value) -> {
            String keyString = key.toString();
            String valueString = value.toString();
            if(keyString.toLowerCase().contains("rmiregistry".toLowerCase())){
                configs.setRmiRegistryPort(valueString);
            }else if(keyString.toLowerCase().contains("port".toLowerCase())){
                configs.setServerPort(valueString);
            }else if(keyString.toLowerCase().contains("server".toLowerCase())){
                configs.setServerIP(valueString);
            }else if(keyString.toLowerCase().contains("readers".toLowerCase())){
                configs.setNumOfReaders(valueString);
            }else if(keyString.toLowerCase().contains("writers".toLowerCase())){
                configs.setNumOfWriters(valueString);
            }else if(keyString.toLowerCase().contains("accesses".toLowerCase())){
                configs.setNumOfAccess(valueString);
            }else{
                StringBuilder number = new StringBuilder();
                int ptr = keyString.length() - 1;
                while(keyString.charAt(ptr) != 'r'){
                    ptr--;
                }
                ptr++;
                while(ptr  < keyString.length()){
                    number.append(keyString.charAt(ptr++));
                }
                if(keyString.toLowerCase().contains("writer".toLowerCase()))
                    configs.addWriter(Integer.parseInt(number.toString()),valueString);
                else
                    configs.addReader(Integer.parseInt(number.toString()),valueString);
            }
        });
        return configs;
    }
    private static Configuration readProperties(String fileName) {
        Properties prop = null;
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            prop = new Properties();

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
        return fillConfig(prop);
    }

    public static void main(String args[]) throws IOException {
       Configuration config = readProperties("./bbs/system.properties");
       String path = System.getProperty("user.dir");
        //initiate server
        Runtime.getRuntime().exec("ssh " + config.getServerConfig().getServerIP() +
                " cd " + path + " ;" + "java bbs/Server " + config.getNumOfAccess()+ " " +config.getNumOfReaders() 
                + " " + config.getNumOfWriters() +" " + config.getServerConfig().getServerIP()
        + " " + config.getServerConfig().getServerPort() + " " + config.getServerConfig().getRmiRegistryPort());
        //initiate writers
        Map<Integer,String> readers = config.getReaders();
        for(Map.Entry<Integer,String> entry : readers.entrySet()) {
            String hostName = entry.getValue();
            Integer hostID = entry.getKey();
            Runtime.getRuntime().exec("ssh " + hostName +
                    " cd " + path + " ;" + "java bbs/Reader " + config.getNumOfAccess() + " " + Integer.toString(hostID)
                    + " " + config.getServerConfig().getServerIP()
                    + " " + config.getServerConfig().getRmiRegistryPort());
        }
        //initiate readers
        Map<Integer,String> writers = config.getWriters();
        for(Map.Entry<Integer,String> entry : writers.entrySet()) {
            String hostName = entry.getValue();
            Integer hostID = entry.getKey();
            Runtime.getRuntime().exec("ssh " + hostName +
                    " cd " + path + " ;" + "java bbs/Writer " + config.getNumOfAccess() + " " + Integer.toString(hostID)
                    + " " + config.getServerConfig().getServerIP()
                    + " " + config.getServerConfig().getRmiRegistryPort());
        }
    }
}