package tanx.client;

import tanx.model.*;

import java.net.Socket;
import java.io.*;

public class Connection {
    private ClientMap map;
    private Socket socket;
    private InputManager inputManager;
    private ObjectOutputStream out;

    public Connection(ClientMap map, String host, int port) throws IOException {
        this.map = map;
        this.socket = new Socket(host, port);
        this.inputManager = new InputManager();
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
    }

    protected synchronized void send(Command command) throws IOException {
        out.writeObject(command);
        out.flush();
        out.reset();
    }

    private class InputManager extends Thread {
        public InputManager() {
            this.start();
        }

        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                while(true) {
                    MapChangedEvent event = (MapChangedEvent) in.readObject();

                    switch (event.getType()) {
                        case MapChangedEvent.TYPE_ADDED:
                            map.add(event.getTarget(map));
                            break;
                        case MapChangedEvent.TYPE_REMOVED:
                            map.remove(event.getTarget(map));
                            break;
                        case MapChangedEvent.TYPE_MOVED:
                            map.executeLocal(new MoveCommand(event.getTarget(map), event.getDirection()));
                            break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
