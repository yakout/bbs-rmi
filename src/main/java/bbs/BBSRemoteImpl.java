package bbs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBSRemoteImpl extends UnicastRemoteObject implements BBSRemoteInterface {
    private static final long serialVersionUID = 1L;
    private Random rand;

    private BBS bbs;

    BBSRemoteImpl(BBS bbs) throws RemoteException {
        super();
        this.bbs = bbs;
        this.rand = new Random(System.currentTimeMillis());
    }

    @Override
    public ArrayList<String> read(String rid) throws RemoteException {
        ArrayList<String> ret = new ArrayList<>();

        random_sleep();
        
        Integer rSeq = bbs.generate_rSeqNum();
        Integer sSeq = bbs.generate_sSeqNum();
        Integer oVal = bbs.get_oVal();
        Integer rNum = bbs.get_rNum();

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
    public ArrayList<String> write(String wid, String news) throws RemoteException {
        ArrayList<String> ret = new ArrayList<>();

        random_sleep();

        Integer rSeq = bbs.generate_rSeqNum();
        Integer sSeq = bbs.generate_sSeqNum();
        Integer oVal = bbs.get_oVal();

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

    /**
     * Each reading and writing operation takes a
     * random amount of time (500 to 1000ms)
     */
    private void random_sleep() {
        try {
            int low = 500;
            int high = 1000;
            Thread.sleep(rand.nextInt(high - low) + low);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
