package tanx;

import tanx.model.*;
import tanx.view.TanxFrame;

public class Main {
    public static void main(String args[]) throws Exception {
        GameMap map = new GameMap();
        Tank tank = new Tank(new MapPosition(1, 1));
        map.add(tank);
        map.setFocusedObject(tank);

        TanxFrame frame = new TanxFrame(map);
        frame.show();
    }
}