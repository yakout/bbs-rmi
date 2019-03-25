package bbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The server does the following things:
 *  - Create an instance of the remote object (BBSRemoteImpl in this case)
 *  - Register the object created with the RMI registry.
 *
 */
public class Server {
    public final static String READER_LOG_PATH = "bbs/server_readers.log";
    public final static String WRITER_LOG_PATH = "bbs/sever_writers.log";

    public static PrintWriter readers_log = null;
    public static PrintWriter writers_log = null;


    public static void main(String[] args) {
        System.out.println("Ahmed and Omar");
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
            // createRegistry is very useful for testing, as you can start and 
            // stop the registry from your test as necessary.
            Registry reg = LocateRegistry.createRegistry(Integer.parseInt(rmiRegistryPort));
            BBS bbs = new BBS();
            BBSRemoteImpl bbs_impl = new BBSRemoteImpl(bbs);
            BBSRemoteInterface stub = (BBSRemoteInterface) UnicastRemoteObject.exportObject(bbs_impl, 0);
            
            reg.rebind("BBSRemoteInterface", stub);
        } catch (RemoteException e) {
            writers_log.print(e.getMessage());
            writers_log.close();
            readers_log.close();
        }
    }

    private static void printLogsHeaders() {
        readers_log.printf("%s\t %s\t %s\t %s\n", "sSeq", "oVal", "rID",
                "rNum");
        writers_log.printf("%s\t %s\t %s\n", "sSeq", "oVal", "wID");
    }
}
