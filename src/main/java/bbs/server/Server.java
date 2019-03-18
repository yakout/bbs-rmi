package bbs.server;

import bbs.utils.Configuration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The server does the following things:
 *  - Create an instance of the remote object (BBSRemoteImpl in this case)
 *  - Register the object created with the RMI registry.
 *
 */
public class Server implements Runnable {
    private AtomicInteger serverObj = null;
    private Configuration.ServerConfig config;

    public Server(Configuration.ServerConfig config) {
        serverObj = new AtomicInteger(-1);
        this.config = config;
    }

    public int updateServerObj(int newVal) {
        return serverObj.addAndGet(newVal - serverObj.get());
    }

    @Override
    public void run() {
        try {
            Registry reg = LocateRegistry.getRegistry(config.getRmiRegistryPort());
            BBS bbs = new BBS();
            BBSRemoteInterface bbs_i = new BBSRemoteImpl(bbs);
            reg.rebind("OurAwesomeBBS", bbs_i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
