package bbs.client;


import bbs.utils.ClientType;
import bbs.utils.Configuration;

public class Reader extends Client {
    public Reader(ClientType type, Configuration.ClientConfig config){
       super(type, config);
    }

    public void sendRequest() {

    }

    public void run() {
//        send_request();
//        sleep_random(0, 10000) // 10000ms
//        send_request();
//        sleep_random(0, 10000) // 10000ms
//        send_request();
    }
}
