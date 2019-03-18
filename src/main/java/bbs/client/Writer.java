package bbs.client;


import bbs.utils.ClientType;
import bbs.utils.Configuration;

public class Writer extends Client {

    public Writer(Configuration.ClientConfig config) {
        super(ClientType.WRITER, config);
    }

    public void sendRequest() {
        // TODO
    }
}
