package bbs.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBSRemoteImpl extends UnicastRemoteObject implements BBSRemoteInterface {
    private BBS bbs;

    BBSRemoteImpl(BBS bbs) throws RemoteException {
        super();
        this.bbs = bbs;
    }

    @Override
    public String read(String rid) throws RemoteException {
        return null;
    }

    @Override
    public String write(String wid, String news) throws RemoteException {
        return null;
    }
}
