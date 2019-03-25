package bbs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public interface BBSRemoteInterface extends Remote {
    public ArrayList<String> read(String rid) throws RemoteException;
    public ArrayList<String> write(String wid, String news) throws RemoteException;
}
