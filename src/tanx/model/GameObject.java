package tanx.model;

import java.io.Serializable;


public abstract class GameObject implements Serializable {
    /**
     * Id in map
     */
    private Integer id;
    private transient GameMap map;
    private MapPosition position = new MapPosition(0, 0);

    /**
     * Implements a default action behaviour
     * - Does nothing
     */
    public void action() {
    }

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
        return true;
    }

    protected Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public MapPosition getMapPosition() {
        return this.position;
    }

    protected void setMapPosition(MapPosition position) {
        this.position = position;
    }

    protected GameMap getMap() {
        return map;
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
