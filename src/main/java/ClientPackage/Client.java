package Main.java.ClientPackage;

import Main.java.ConstantsAndEnumsPackage.ClientType;
import Main.java.Utils.Configuration;

public abstract class Client implements Runnable {
    protected ClientType type = null;
    protected Configuration.ClientConfig config;

    public Client(ClientType type, Configuration.ClientConfig config) {
        this.type = type;
        this.config = config;
    }

    public abstract void sendRequest();
}
