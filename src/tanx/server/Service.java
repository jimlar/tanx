package tanx.server;

import tanx.model.*;

import java.net.Socket;
import java.io.*;

public class Service extends Thread implements MapChangeListener {
    private GameMap map;
    private Socket socket;
    private ObjectOutputStream output;

    private Tank clientTank;

    public Service(GameMap map, Socket socket) throws IOException {
        this.map = map;
        this.socket = socket;
        this.output = new ObjectOutputStream(socket.getOutputStream());

        this.map.addMapChangeListener(this);
        this.clientTank = new Tank(new MapPosition(1, 1));
        this.map.add(clientTank);
    }

    public void mapChanged(MapChangedEvent event) {
        try {
            output.writeObject(event);
            output.flush();
            output.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Command command = (Command) input.readObject();

                if (command instanceof MoveCommand) {
                    MoveCommand moveCommand = (MoveCommand) command;
                    moveCommand.setTarget(clientTank);
                }
                map.execute(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
