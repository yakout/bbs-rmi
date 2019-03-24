package bbs.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
    public ArrayList<String> read(String rid) throws RemoteException {
        ArrayList<String> ret = new ArrayList<>();

        Integer rSeq = bbs.generate_rSeqNum();
        Integer sSeq = bbs.generate_sSeqNum();
        Integer oVal = bbs.get_oVal();
        Integer rNum = bbs.get_rNum();

        ret.add(rSeq.toString());

        Server.readers_log.printf("%s\t %s \t, %s\t %s\n", sSeq, oVal, rid,
                rNum);
        return ret;
    }

    @Override
    public ArrayList<String> write(String wid, String news) throws RemoteException {
        ArrayList<String> ret = new ArrayList<>();
        Integer rSeq = bbs.generate_rSeqNum();
        Integer sSeq = bbs.generate_sSeqNum();
        Integer oVal = bbs.get_oVal();

        ret.add(rSeq.toString());

        Server.readers_log.printf("%s\t %s \t, %s\t %s\n", sSeq, oVal, wid);

        return ret;
    }
}
