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
        GameMap model = new GameMap();
        final Tank tank = new Tank();
        tank.setBounds(new Rectangle(10, 10, 10, 10));
        model.add(tank);
        final TanxFrame frame = new TanxFrame(model);
        frame.show();

        frame.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                Rectangle bounds = tank.getBounds();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        tank.moveDown();
                        break;
                    case KeyEvent.VK_UP:
                        tank.moveUp();
                        break;
                    case KeyEvent.VK_LEFT:
                        tank.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        tank.moveRight();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
    }
}