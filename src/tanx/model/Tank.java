package tanx.model;


public class Tank extends GameObject {
    private int facingDirection = MoveCommand.DIRECTION_RIGHT;

    public Tank(MapPosition position) {
        setMapPosition(position);
    }

    public void action() {
        getMap().add(new Shot(getMapPosition(), facingDirection));
    }

    public boolean move(int direction) {
        if (direction == facingDirection) {
            return super.move(direction);
        } else {
            this.facingDirection = direction;
            return true;
        }
    }

    public int getFacingDirection() {
        return facingDirection;
    }
}
