package tanx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.awt.*;

public class GameMap extends GameObject implements MoveListener {
    private List objects = new ArrayList();

    public void add(GameObject object) {
        objects.add(object);
        object.addMoveListener(this);
    }

    public List getObjects() {
        return objects;
    }

    public void paint(Graphics g) {
        for (Iterator i = objects.iterator(); i.hasNext();) {
            GameObject gameObject = (GameObject) i.next();
            gameObject.paint(g);
        }
    }

    public void moved(GameObject object, Rectangle oldBounds, Rectangle newBounds) {
        fireMovedEvent(object, oldBounds, newBounds);
    }
}
