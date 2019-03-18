package bbs.server;

import java.rmi.Remote;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Remote {
    private AtomicInteger serverObj = null;
    public Server(){
        serverObj = new AtomicInteger(-1);
    }
    public int updateServerObj(int newVal) {
        return serverObj.addAndGet(newVal - serverObj.get());
    }


    public Integer readServerObject(String params) throws java.rmi
            .RemoteException {

        return null;
    }

    public Integer writeServerObject(String params) throws java.rmi
            .RemoteException {

        return null;
    }
}
