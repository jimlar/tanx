package tanx.server;

import tanx.model.Command;
import tanx.model.GameMap;

import java.net.Socket;
import java.io.*;

public class Service extends Thread {
    private GameMap map;
    private Socket socket;

    public Service(GameMap map, Socket socket) {
        this.map = map;
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Command command = (Command) input.readObject();
                map.execute(command);
                output.flush();
                output.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
