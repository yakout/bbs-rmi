package bbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The server does the following things:
 *  - Create an instance of the remote object (BBSRemoteImpl in this case)
 *  - Register the object created with the RMI registry.
 *
 */
public class Server {
    public final static String READER_LOG_PATH = "server_readers.log";
    public final static String WRITER_LOG_PATH = "sever_writers.log";

    public static PrintWriter readers_log = null;
    public static PrintWriter writers_log = null;


    public static void main(String[] args) {

        // init loggers handlers
        try {
            readers_log = new PrintWriter(new FileOutputStream(new
                    File(READER_LOG_PATH)));
            writers_log = new PrintWriter(new FileOutputStream(new
                    File(WRITER_LOG_PATH)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // inti server config params
        String numOfAccess     = args[0];
        String srvHost         = args[1];
        String srvPort         = args[2];
        String rmiRegistryPort = args[3];


        System.setProperty("java.rmi.server.hostname", srvHost);

        try {
            printLogsHeaders();

            Registry reg = LocateRegistry.getRegistry(rmiRegistryPort);
            BBS bbs = new BBS();
            BBSRemoteInterface bbs_i = new BBSRemoteImpl(bbs);
            reg.rebind("BBS", bbs_i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void printLogsHeaders() {
        readers_log.printf("%s\t %s\t %s\t %s\n", "sSeq", "oVal", "rID",
                "rNum");
        writers_log.printf("%s\t %s\t %s\n", "sSeq", "oVal", "wID");
    }
}
