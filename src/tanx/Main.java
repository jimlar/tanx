package tanx;

import tanx.ui.TanxFrame;
import tanx.model.GameMap;
import tanx.model.Tank;
import tanx.model.MapPosition;
import tanx.model.RobotTank;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Main {
    public static void main(String args[]) throws Exception {
        GameMap map = new GameMap();
        Tank tank = new Tank(new MapPosition(1, 1));
        map.add(tank);
        map.setFocusedObject(tank);

        map.add(new RobotTank(new MapPosition(1, 5)));

        TanxFrame frame = new TanxFrame(map);
        frame.show();
    }
}