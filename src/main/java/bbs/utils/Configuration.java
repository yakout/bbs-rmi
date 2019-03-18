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
    private String numOfWritter;
    private Map<Integer, String> readers;
    private Map<Integer, String> writers;

    public Configuration() {
        readers = new HashMap<Integer, String>();
        writers = new HashMap<Integer, String>();
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

    public void setNumOfWritter(String numOfWritter) {
        this.numOfWritter = numOfWritter;
    }

    public void addReader(int id, String reader) {
        readers.put(id, reader);
    }

    public void addWriter(int id, String writer) {
        writers.put(id, writer);
    }

    public ServerConfig getServerConfig() {
        return new ServerConfig();
    }

    public ClientConfig getClientConfig() {
        return new ClientConfig();
    }

    // ******************* Inner configurations classes ************************
    public static class ServerConfig {
        ServerConfig() {

        }
    }

    public static class ClientConfig {
        ClientConfig () {

        }
    }
}
