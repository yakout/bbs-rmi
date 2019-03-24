package bbs.server;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBS {
    private AtomicInteger rSeq;
    private AtomicInteger sSeq;
    private AtomicInteger oVal;

    BBS() {
        rSeq = new AtomicInteger(0);
        sSeq = new AtomicInteger(0);
    }


    public Integer generate_rSeqNum() {
        return rSeq.addAndGet(1);
    }

    public Integer generate_sSeqNum() {
        return sSeq.addAndGet(1);
    }

    public Integer get_oVal() {
        return oVal.addAndGet(1);
    }
}
