package bbs.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmedyakout on 3/18/19.
 */
public class Configuration {
    private String serverPort;
    private String serverIP;
    private String numOfAccess;
    private String numOfReader;
    private String numOfWriters;
    private String rmiRegistryPort;

    private Map<Integer, String> readers;
    private Map<Integer, String> writers;

    public Configuration() {
        readers = new HashMap<>();
        writers = new HashMap<>();
    }

    public void setServerPort(String port) {
        this.serverPort = port;
    }

    public void setServerIP(String ip) {
        this.serverIP = ip;
    }

    public void setNumOfAccess(String numOfAccess) {
        this.numOfAccess = numOfAccess;
    }

    public void setNumOfReader(String numOfReader) {
        this.numOfReader = numOfReader;
    }

    public void setNumOfWriters(String numOfWriters) {
        this.numOfWriters = numOfWriters;
    }

    public void setRmiRegistryPort(String rmiRegistryPort) {
        this.rmiRegistryPort = rmiRegistryPort;
    }

    public void addReader(int id, String reader) {
        readers.put(id, reader);
    }

    public void addWriter(int id, String writer) {
        writers.put(id, writer);
    }

    public ServerConfig getServerConfig() {
        return new ServerConfig(this);
    }

    public ClientConfig getClientConfig() {
        return new ClientConfig(this);
    }


    String getServerPort() {
        return serverPort;
    }

    String getServerIP() {
        return serverIP;
    }

    Map<Integer, String> getReaders() {
        return readers;
    }

    Map<Integer, String> getWriters() {
        return writers;
    }

    String getNumOfAccess() {
        return numOfAccess;
    }

    String getNumOfReader() {
        return numOfReader;
    }

    String getNumOfWriters() {
        return numOfWriters;
    }

    String getRmiRegistryPort() {
        return rmiRegistryPort;
    }

    // ******************* Inner configurations classes ************************
    public static class ServerConfig {
        private Configuration config;

        ServerConfig(Configuration config) {
            this.config = config;
        }

        public String getServerIP() {
            return config.getServerIP();
        }

        public Map<Integer, String> getReaders() {
            return config.getReaders();
        }

        public String getServerPort() {
            return config.getServerPort();
        }

        public Map<Integer, String> getWriters() {
            return config.getWriters();
        }
    }

    /**
     * client only need server port and ip.
     */
    public static class ClientConfig {
        Configuration config;
        ClientConfig (Configuration config) {
            this.config = config;
        }

        public String getServerIP() {
            return config.getServerIP();
        }

        public String getServerPort() {
            return config.getServerPort();
        }

    }
}
