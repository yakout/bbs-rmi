package bbs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Random;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class BBS {
    private ReentrantReadWriteLock bbs_lock;
    private AtomicInteger rSeq;
    private AtomicInteger sSeq;
    private AtomicInteger rNum;
    private Integer news_id;
    private Random rand;

    public BBS() {
        rand     = new Random(System.currentTimeMillis());
        bbs_lock = new ReentrantReadWriteLock(true);
        rSeq     = new AtomicInteger(0);
        sSeq     = new AtomicInteger(0);
        rNum     = new AtomicInteger(0);
        news_id  = -1;
    }

    private Integer generate_rSeqNum() {
        return rSeq.incrementAndGet();
    }

    private Integer generate_sSeqNum() {
        return sSeq.incrementAndGet();
    }

    private Integer getNewsID() {
        return news_id;
    }

    private Integer setAndGetNewsID(Integer id) {
        news_id = id;
        return id;
    }

    private Integer up_rNum() {
        return rNum.incrementAndGet();
    }

    private void down_rNum() {
        rNum.decrementAndGet();
    }

    public Map<String, Integer> write(String wid) {
        Map<String, Integer> writer_info = new HashMap<>();
        Integer rSeq = generate_rSeqNum();

        // writers cretical section
        bbs_lock.writeLock().lock();
        // start writing.
        random_sleep();
        writer_info.put("rSeq", rSeq);
        writer_info.put("sSeq", generate_sSeqNum());
        writer_info.put("oVal", setAndGetNewsID(Integer.parseInt(wid)));
        bbs_lock.writeLock().unlock();

        return writer_info;
    }

    public Map<String, Integer> read(String rid) {
        Map<String, Integer> reader_info = new HashMap<>();
        // generate rSeq to the client to be served.
        Integer rSeq = generate_rSeqNum();

        // readers cretical section
        bbs_lock.readLock().lock();
        Integer rNum = up_rNum(); // increment number of readers.
        // start reading.
        random_sleep();
        reader_info.put("rSeq", rSeq);
        reader_info.put("sSeq", generate_sSeqNum());
        reader_info.put("oVal", getNewsID());
        reader_info.put("rNum", rNum);
        down_rNum(); // done reading.
        bbs_lock.readLock().unlock();

        return reader_info;
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
