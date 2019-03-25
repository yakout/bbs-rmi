package bbs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Writer{
    private Integer numberOfAccess;
    private Integer id;
    private String serverIP;
    private String rmiPort;
    private BBSLogger lineLogger;
    private String fileName;
    public Writer(Integer numberOfAccess,Integer id,String serverIP,String rmiPort){
        this.numberOfAccess = numberOfAccess;
        this.id = id;
        this.serverIP = serverIP;
        this.rmiPort = rmiPort;
        lineLogger = new BBSLogger();
        fileName = new String("log_"+Integer.toString(id)+".txt");
    }

    public void sendWriteRequests() throws InterruptedException {
        BBSRemoteInterface referenceToRemote = null;
        try {
            Registry reg = LocateRegistry.getRegistry(serverIP, Integer.parseInt(rmiPort));
            referenceToRemote = (BBSRemoteInterface) reg.lookup("BBSRemoteInterface");
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int i = 0 ; i < numberOfAccess ; ++i) {
            ArrayList<String> queryLog = null;
            try {
                System.out.print("request #" + i);
                queryLog = referenceToRemote.write(Integer.toString(id));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                lineLogger.LogInfo(fileName,queryLog);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }
    }
    public static void main(String[] args) {
        Writer client = new Writer(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],args[3]);
        try {
            client.sendWriteRequests();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
