package bbs.client;


import bbs.utils.ClientType;
import bbs.utils.Configuration;

public class Reader extends Client {
    public Reader(Configuration.ClientConfig config){
        super(ClientType.READER, config);
    }

    public void sendRequest() {
        // TODO
    }
}
