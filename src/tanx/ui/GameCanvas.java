package tanx.ui;

import tanx.model.GameMap;
import tanx.model.GameObject;
import tanx.model.MoveListener;
import tanx.model.MapPosition;

import java.awt.*;

public class GameCanvas extends Canvas implements MoveListener {
    private GameMap map;

    public GameCanvas(final GameMap map) {
        this.map = map;
        map.addMoveListener(this);
    }

    public void paint(Graphics g) {
        map.paint(g);
    }

    public void moved(GameObject object, int direction, MapPosition oldPosition) {
        repaint();
    }
}
