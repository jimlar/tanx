package tanx.model;

import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public abstract class GameObject {
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;

    private Set moveListeners = new HashSet();
    protected MapPosition position = new MapPosition(0, 0);

    public void addMoveListener(MoveListener listener) {
        moveListeners.add(listener);
    }

    public MapPosition getMapPosition() {
        return this.position;
    }

    public void setMapPosition(MapPosition position) {
        this.position = position;
    }

    /**
     * Implements a default move behaviour
     * - It just moves the objects coordinates on the map.
     * - Override it to implement object specific behaviour
     */
    public void move(int direction) {
        MapPosition oldPosition = this.position;
        switch (direction) {
            case DIRECTION_UP:
                this.position = new MapPosition(position.getX(), position.getY() - 1);
                break;
            case DIRECTION_DOWN:
                this.position = new MapPosition(position.getX(), position.getY() + 1);
                break;
            case DIRECTION_LEFT:
                this.position = new MapPosition(position.getX() - 1, position.getY());
                break;
            case DIRECTION_RIGHT:
                this.position = new MapPosition(position.getX() + 1, position.getY());
                break;
            default:
                throw new IllegalArgumentException("invalid direction " + direction);
        }
        fireMovedEvent(this, direction, oldPosition);
    }

    public abstract void paint(Graphics g);

    protected void fireMovedEvent(GameObject gameObject, int direction, MapPosition oldPosition) {
        for (Iterator i = moveListeners.iterator(); i.hasNext();) {
            MoveListener listener = (MoveListener) i.next();
            listener.moved(gameObject, direction, oldPosition);
        }
    }
}
