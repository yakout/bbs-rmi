package bbs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBS {
    private AtomicInteger rSeq;
    private AtomicInteger sSeq;
    private AtomicInteger oVal;
    private AtomicInteger rNum;

    public BBS() {
        rSeq = new AtomicInteger(0);
        sSeq = new AtomicInteger(0);
        rNum = new AtomicInteger(0);
        oVal = new AtomicInteger(-1);
    }

    private Integer generate_rSeqNum() {
        return rSeq.incrementAndGet();
    }

    private Integer generate_sSeqNum() {
        return sSeq.incrementAndGet();
    }

    private Integer get_oVal() {
        return oVal.incrementAndGet();
    }

    private Integer get_rNum() {
        return rNum.incrementAndGet();
    }

    public Map<String, Integer> write(String wid) {
        Map<String, Integer> writer_info = new HashMap<>();

        writer_info.put("rSeq", generate_rSeqNum());
        writer_info.put("sSeq", generate_sSeqNum());
        writer_info.put("oVal", get_oVal());

        return writer_info;
    }

    public Map<String, Integer> read(String rid) {
        Map<String, Integer> reader_info = new HashMap<>();

        reader_info.put("rSeq", generate_rSeqNum());
        reader_info.put("sSeq", generate_sSeqNum());
        reader_info.put("oVal", get_oVal());
        reader_info.put("rNum", get_rNum());

        return reader_info;
    }
}
