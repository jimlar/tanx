package tanx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.awt.*;

public class GameMap extends GameObject implements MoveListener {
    /* this is the object which has input focus */
    private GameObject focusedObject;
    private List objects = new ArrayList();

    public void add(GameObject object) {
        objects.add(object);
        object.addMoveListener(this);
    }

    public List getObjects() {
        return objects;
    }

    public GameObject getFocusedObject() {
        return this.focusedObject;
    }

    public void setFocusedObject(GameObject focusedObject) {
        this.focusedObject = focusedObject;
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
