package tanx.model;

import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public abstract class GameObject {
    protected Set moveListeners = new HashSet();
    protected Rectangle bounds;

    public void addMoveListener(MoveListener listener) {
        moveListeners.add(listener);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void moveUp() {
        Rectangle oldBounds = this.bounds;
        this.bounds = new Rectangle(bounds.x, bounds.y - 5, bounds.width, bounds.height);
        fireMovedEvent(this, oldBounds, this.bounds);
    }

    public void moveDown() {
        Rectangle oldBounds = this.bounds;
        this.bounds = new Rectangle(bounds.x, bounds.y + 5, bounds.width, bounds.height);
        fireMovedEvent(this, oldBounds, this.bounds);
    }

    public void moveLeft() {
        Rectangle oldBounds = this.bounds;
        this.bounds = new Rectangle(bounds.x - 5, bounds.y, bounds.width, bounds.height);
        fireMovedEvent(this, oldBounds, this.bounds);
    }

    public void moveRight() {
        Rectangle oldBounds = this.bounds;
        this.bounds = new Rectangle(bounds.x + 5, bounds.y, bounds.width, bounds.height);
        fireMovedEvent(this, oldBounds, this.bounds);
    }

    public abstract void paint(Graphics g);

    protected void fireMovedEvent(GameObject gameObject, Rectangle oldBounds, Rectangle newBounds) {
        for (Iterator i = moveListeners.iterator(); i.hasNext();) {
            MoveListener listener = (MoveListener) i.next();
            listener.moved(gameObject, oldBounds, newBounds);
        }
    }
}
