package tanx.client;

import tanx.model.*;

import java.io.IOException;

public class ClientMap extends GameMap {
    private Connection connection;

    public ClientMap(String host, int port) throws IOException {
        this.connection = new Connection(this, host, port);
    }

    public void executeLocal(Command command) {
        super.execute(command);
    }

    public void execute(Command command) {
        try {
            connection.send(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
