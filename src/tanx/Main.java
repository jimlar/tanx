package tanx;

import tanx.model.*;
import tanx.view.TanxFrame;
import tanx.server.Server;
import tanx.client.ClientMap;

public class Main {
    public static void main(String args[]) throws Exception {

        if (args.length == 0) {
            printUsageAndExit();
        }

        if (args[0].equalsIgnoreCase("client")) {
            ClientMap clientMap = new ClientMap("localhost", 1234);
            TanxFrame frame = new TanxFrame(clientMap);
            frame.show();
        } else if (args[0].equalsIgnoreCase("server")) {
            GameMap serverMap = new GameMap();
            Server server = new Server(serverMap);
            server.start();
        } else {
            printUsageAndExit();
        }
    }

    private static void printUsageAndExit() {
        System.out.println("Usage:");
        System.out.println("  tanx client [host] [port] - Connect to a server and play");
        System.out.println("  tanx server [port]        - Start a server");
        System.exit(1);
    }
}