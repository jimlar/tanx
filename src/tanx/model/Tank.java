package tanx.model;

import java.awt.*;

public class Tank extends GameObject {
    private static final int WIDTH = GameMap.MAP_BLOCK_SIZE;
    private static final int HEIGHT = GameMap.MAP_BLOCK_SIZE;
    private static final int CANNON_LENGTH = GameMap.MAP_BLOCK_SIZE;

    private int facingDirection = MoveCommand.DIRECTION_RIGHT;

    public Tank(MapPosition position) {
        setMapPosition(position);
    }

    public void action() {
        map.add(new Shot(getMapPosition(), facingDirection));
    }

    public boolean move(int direction) {
        if (direction == facingDirection) {
            return super.move(direction);
        } else {
            this.facingDirection = direction;
            fireMovedEvent(this, direction, getMapPosition());
            return true;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getMapPosition().getPixelX(),
                   getMapPosition().getPixelY(),
                   WIDTH,
                   HEIGHT);

        paintCannon(g);

    }

    private void paintCannon(Graphics g) {
        int cannonX1 = getMapPosition().getPixelX() + (WIDTH / 2);
        int cannonY1 = getMapPosition().getPixelY() + (HEIGHT / 2);
        int cannonX2 = cannonX1;
        int cannonY2 = cannonY1;

        switch (facingDirection) {
            case MoveCommand.DIRECTION_DOWN:
                cannonY2 += CANNON_LENGTH;
                break;
            case MoveCommand.DIRECTION_UP:
                cannonY2 -= CANNON_LENGTH;
                break;
            case MoveCommand.DIRECTION_LEFT:
                cannonX2 -= CANNON_LENGTH;
                break;
            case MoveCommand.DIRECTION_RIGHT:
                cannonX2 += CANNON_LENGTH;
                break;
        }
        g.drawLine(cannonX1,
                   cannonY1,
                   cannonX2,
                   cannonY2);
    }
}
