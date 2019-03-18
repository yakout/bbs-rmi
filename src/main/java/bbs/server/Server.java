package bbs.server;

import java.util.concurrent.atomic.AtomicInteger;
public class Server {
    private AtomicInteger serverObj = null;
    public Server(){
        serverObj = new AtomicInteger(-1);
    }
    public int updateServerObj(int newVal){
        return serverObj.addAndGet(newVal - serverObj.get());
    }
}
