package bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBSRemoteImpl extends UnicastRemoteObject implements BBSRemoteInterface {
    private static final long serialVersionUID = 1L;

    private BBS bbs;

    BBSRemoteImpl(BBS bbs) throws RemoteException {
        super();
        this.bbs = bbs;
    }

    @Override
    public ArrayList<String> read(String rid) throws RemoteException {
        ArrayList<String> ret = new ArrayList<>();
        
        Map<String, Integer> reader_info = bbs.read(rid);
        Integer rSeq = reader_info.get("rSeq");
        Integer sSeq = reader_info.get("sSeq");
        Integer oVal = reader_info.get("oVal");
        Integer rNum = reader_info.get("rNum");

        ret.add(rSeq.toString());
        ret.add(sSeq.toString());
        ret.add(oVal.toString());

        try {
            PrintWriter readers_log = Server.readers_log();
            readers_log.printf("%s\t %s \t %s\t %s\n", sSeq, oVal, rid, rNum);
            readers_log.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public ArrayList<String> write(String wid) throws RemoteException {
        ArrayList<String> ret = new ArrayList<>();

        Map<String, Integer> writer_info = bbs.write(wid);

        Integer rSeq = writer_info.get("rSeq");
        Integer sSeq = writer_info.get("sSeq");
        Integer oVal = writer_info.get("oVal");

        ret.add(rSeq.toString());
        ret.add(sSeq.toString());


        try {
            PrintWriter writers_log = Server.writers_log();
            writers_log.printf("%s\t %s\t %s\n", sSeq, oVal, wid);
            writers_log.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
