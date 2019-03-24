package bbs.client;


import bbs.server.BBSRemoteImpl;
import bbs.utils.BBSLogger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
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
        BBSRemoteImpl referenceToRemote = null;
        try {
            referenceToRemote = (BBSRemoteImpl)Naming.lookup("rmi://" + serverIP +":"+ rmiPort);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < numberOfAccess ; ++i){
            ArrayList<String> queryLog = null;
            try {
                queryLog = referenceToRemote.write(Integer.toString(id),Integer.toString(id));
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
        Writer client = new Writer(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],args[3]);
        try {
            client.sendWriteRequests();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

