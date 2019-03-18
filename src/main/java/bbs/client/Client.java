package bbs.client;

import bbs.utils.ClientType;
import bbs.utils.Configuration;

public abstract class Client implements Runnable {
    protected ClientType type = null;
    protected Configuration.ClientConfig config;

    public Client(ClientType type, Configuration.ClientConfig config) {
        this.type = type;
        this.config = config;
    }

    public abstract void sendRequest();
}
