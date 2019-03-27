package bbs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Reader{
    private Integer numberOfAccess;
    private Integer id;
    private String serverIP;
    private String rmiPort;
    private BBSLogger lineLogger;
    private String fileName;
    public Reader(Integer numberOfAccess,Integer id,String serverIP,String rmiPort){
        this.numberOfAccess = numberOfAccess;
        this.id = id;
        this.serverIP = serverIP;
        this.rmiPort = rmiPort;
        lineLogger = new BBSLogger();
        fileName = new String("log_"+Integer.toString(id)+".txt");
    }

    public void sendReadRequests() throws InterruptedException {
        BBSRemoteInterface referenceToRemote = null;
        try {
            Registry reg = LocateRegistry.getRegistry(serverIP, Integer.parseInt(rmiPort));
            referenceToRemote = (BBSRemoteInterface) reg.lookup("BBSRemoteInterface");
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
        for(int i = 0 ; i < numberOfAccess ; ++i){
            ArrayList<String> queryLog = null;
            try {
                System.out.println("request #" + i);
                queryLog = referenceToRemote.read(Integer.toString(id));
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
    public static void main(String[] args){
        Integer numOfAccess = Integer.parseInt(args[0]);
        Integer rid = Integer.parseInt(args[1]);
        String serverIP = args[2];
        String rmiPort = args[3];

        Reader client = new Reader(numOfAccess, rid, serverIP, rmiPort);
        try {
            client.sendReadRequests();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

