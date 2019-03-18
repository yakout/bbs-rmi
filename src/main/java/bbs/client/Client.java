package bbs.client;

import bbs.utils.ClientType;
import bbs.utils.Configuration;

import java.util.Random;

public abstract class Client implements Runnable {
    protected ClientType type = null;
    protected Configuration.ClientConfig config;
    protected Random rand;

    public Client(ClientType type, Configuration.ClientConfig config) {
        this.type = type;
        this.config = config;
        this.rand = new Random(System.currentTimeMillis());
    }

    public abstract void sendRequest();

    @Override
    public void run() {
        try {
            sendRequest();
            Thread.sleep(rand.nextInt(10000) + 1);
            sendRequest();
            Thread.sleep(rand.nextInt(10000) + 1);
            sendRequest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
