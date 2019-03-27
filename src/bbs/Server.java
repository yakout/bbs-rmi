package bbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The server does the following things: - Create an instance of the remote
 * object (BBSRemoteImpl in this case) - Register the object created with the
 * RMI registry.
 *
 */
public class Server {
    public final static String READER_LOG_PATH = "server_readers.txt";
    public final static String WRITER_LOG_PATH = "server_writers.txt";

    public static PrintWriter readers_log = null;
    public static PrintWriter writers_log = null;

    public static PrintWriter readers_log() throws IOException {
        return new PrintWriter(new FileWriter(new File(READER_LOG_PATH), true));
    }

    public static PrintWriter writers_log() throws IOException {
        return new PrintWriter(new FileWriter(new File(WRITER_LOG_PATH), true));
    }

    private static BBS bbs;
    private static Registry reg;
    private static BBSRemoteImpl bbs_impl;

    public static void main(String[] args) {
        // init loggers handlers
        try {
            readers_log = readers_log();
            writers_log = writers_log();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // inti server config params
        String srvHost = args[0];
        String srvPort = args[1];
        Integer rmiRegistryPort = Integer.parseInt(args[2]);

        System.setProperty("java.rmi.server.hostname", srvHost);

        try {
            printLogsHeaders();
            // createRegistry is very useful for testing, as you can start and
            // stop the registry from your test as necessary.
            reg = LocateRegistry.createRegistry(rmiRegistryPort);
            bbs = new BBS();
            bbs_impl = new BBSRemoteImpl(bbs);
            reg.rebind("BBSRemoteInterface", bbs_impl);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void printLogsHeaders() {
        readers_log.printf("%s\t %s\t %s\t %s\n", "sSeq", "oVal", "rID",
                "rNum");
        writers_log.printf("%s\t %s\t %s\n", "sSeq", "oVal", "wID");

        readers_log.close();
        writers_log.close();
    }
}
