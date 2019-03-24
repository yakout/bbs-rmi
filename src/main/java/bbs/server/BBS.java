package bbs.server;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBS {
    private AtomicInteger rSeq;
    private AtomicInteger sSeq;
    private AtomicInteger oVal;
    private AtomicInteger rNum;

    BBS() {
        rSeq = new AtomicInteger(0);
        sSeq = new AtomicInteger(0);
        rNum = new AtomicInteger(0);
        oVal = new AtomicInteger(-1);
    }


    public Integer generate_rSeqNum() {
        return rSeq.incrementAndGet();
    }

    public Integer generate_sSeqNum() {
        return sSeq.incrementAndGet();
    }

    public Integer get_oVal() {
        return oVal.incrementAndGet();
    }

    public Integer get_rNum() {
        return rNum.incrementAndGet();
    }
}
