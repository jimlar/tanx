package tanx.ui;

import tanx.model.*;

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
                            map.execute(new MoveCommand(focusedObject, MoveCommand.DIRECTION_DOWN));
                            e.consume();
                            break;
                        case KeyEvent.VK_UP:
                            map.execute(new MoveCommand(focusedObject, MoveCommand.DIRECTION_UP));
                            e.consume();
                            break;
                        case KeyEvent.VK_LEFT:
                            map.execute(new MoveCommand(focusedObject, MoveCommand.DIRECTION_LEFT));
                            e.consume();
                            break;
                        case KeyEvent.VK_RIGHT:
                            map.execute(new MoveCommand(focusedObject, MoveCommand.DIRECTION_RIGHT));
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
