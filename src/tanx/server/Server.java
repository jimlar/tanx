package tanx.server;

import tanx.model.GameMap;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server extends Thread {
    private GameMap map;
    private ServerSocket serverSocket;

    public Server(GameMap map) throws IOException {
        this.map = map;
        this.serverSocket = new ServerSocket(1234);
    }

    public void run() {
        try {
            while (true) {
                Socket socket = this.serverSocket.accept();
                Service service = new Service(map, socket);
                service.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        GameMap map = new GameMap();
        Server server = new Server(map);
        server.start();
    }
}
