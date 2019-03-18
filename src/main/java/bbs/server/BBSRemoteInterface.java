package bbs.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public interface BBSRemoteInterface extends Remote {
    public String read(String rid) throws RemoteException;
    public String write(String wid, String news) throws RemoteException;
}
