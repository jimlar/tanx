package tanx.model;

import java.awt.*;
import java.util.*;

public abstract class GameObject {
    private Set moveListeners = new HashSet();
    protected GameMap map;
    protected MapPosition position = new MapPosition(0, 0);

    public void addMoveListener(MoveListener listener) {
        moveListeners.add(listener);
    }

    public void removeMoveListener(MoveListener listener) {
        moveListeners.remove(listener);
    }

    public MapPosition getMapPosition() {
        return this.position;
    }

    public void setMapPosition(MapPosition position) {
        this.position = position;
    }

    /**
     * Implements a default action behaviour
     * - Does nothing
     */
    public void action() {
    }

    protected abstract void paint(Graphics g);

    /**
     * Implements a default move behaviour
     * - It just moves the objects coordinates on the map.
     * - Override it to implement object specific behaviour
     *
     * @return true if the move was successful, false otherwise (obstacles)
     */
    protected boolean move(int direction) {
        if (!canMove(direction)) {
            return false;
        }

        MapPosition oldPosition = this.position;
        switch (direction) {
            case MoveCommand.DIRECTION_UP:
                this.position = new MapPosition(position.getX(), position.getY() - 1);
                break;
            case MoveCommand.DIRECTION_DOWN:
                this.position = new MapPosition(position.getX(), position.getY() + 1);
                break;
            case MoveCommand.DIRECTION_LEFT:
                this.position = new MapPosition(position.getX() - 1, position.getY());
                break;
            case MoveCommand.DIRECTION_RIGHT:
                this.position = new MapPosition(position.getX() + 1, position.getY());
                break;
            default:
                throw new IllegalArgumentException("invalid direction " + direction);
        }
        fireMovedEvent(this, direction, oldPosition);
        return true;
    }

    protected void fireMovedEvent(GameObject gameObject, int direction, MapPosition oldPosition) {
        for (Iterator i = moveListeners.iterator(); i.hasNext();) {
            MoveListener listener = (MoveListener) i.next();
            listener.moved(gameObject, direction, oldPosition);
        }
    }

    protected void setMap(GameMap map) {
        this.map = map;
    }

    /**
     * ask if you can go a direction
     */
    private boolean canMove(int direction) {

        int destX = getMapPosition().getX();
        int destY = getMapPosition().getY();

        switch (direction) {
            case MoveCommand.DIRECTION_DOWN:
                destY++;
                break;
            case MoveCommand.DIRECTION_UP:
                destY--;
                break;
            case MoveCommand.DIRECTION_LEFT:
                destX--;
                break;
            case MoveCommand.DIRECTION_RIGHT:
                destX++;
                break;
        }
        return map.isVisitablePosition(destX, destY);
    }
}
