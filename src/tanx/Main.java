package tanx;

import tanx.ui.TanxFrame;
import tanx.model.GameMap;
import tanx.model.Tank;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Main {
    public static void main(String args[]) throws Exception {
        GameMap map = new GameMap();
        Tank tank = new Tank();
        tank.setBounds(new Rectangle(10, 10, 10, 10));
        map.add(tank);
        map.setFocusedObject(tank);

        TanxFrame frame = new TanxFrame(map);
        frame.show();
    }
}