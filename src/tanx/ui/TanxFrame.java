package tanx.ui;

import tanx.model.GameMap;
import tanx.model.GameObject;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class TanxFrame extends JFrame {
    public TanxFrame(final GameMap map) {
        super("Tanx");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new GameCanvas(map));

        pack();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                GameObject focusedObject = map.getFocusedObject();
                if (focusedObject != null) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN:
                            focusedObject.move(GameObject.DIRECTION_DOWN);
                            e.consume();
                            break;
                        case KeyEvent.VK_UP:
                            focusedObject.move(GameObject.DIRECTION_UP);
                            e.consume();
                            break;
                        case KeyEvent.VK_LEFT:
                            focusedObject.move(GameObject.DIRECTION_LEFT);
                            e.consume();
                            break;
                        case KeyEvent.VK_RIGHT:
                            focusedObject.move(GameObject.DIRECTION_RIGHT);
                            e.consume();
                            break;
                        case KeyEvent.VK_SPACE:
                            focusedObject.action();
                            e.consume();
                            break;
                    }
                }
            }
        });
    }
}
