package tanx.model;

import java.awt.*;

public class Tank extends GameObject {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int CANNON_LENGTH = 10;

    private int facingDirection = DIRECTION_RIGHT;

    public void move(int direction) {
        if (direction == facingDirection) {
            super.move(direction);
        } else {
            this.facingDirection = direction;
            fireMovedEvent(this, direction, this.position);
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(position.getPixelX(),
                   position.getPixelY(),
                   WIDTH,
                   HEIGHT);

        paintCannon(g);

    }

    private void paintCannon(Graphics g) {
        int cannonX1 = position.getPixelX() + (WIDTH / 2);
        int cannonY1 = position.getPixelY() + (HEIGHT / 2);
        int cannonX2 = cannonX1;
        int cannonY2 = cannonY1;

        switch (facingDirection) {
            case DIRECTION_DOWN:
                cannonY2 += CANNON_LENGTH;
                break;
            case DIRECTION_UP:
                cannonY2 -= CANNON_LENGTH;
                break;
            case DIRECTION_LEFT:
                cannonX2 -= CANNON_LENGTH;
                break;
            case DIRECTION_RIGHT:
                cannonX2 += CANNON_LENGTH;
                break;
        }
        g.drawLine(cannonX1,
                   cannonY1,
                   cannonX2,
                   cannonY2);
    }
}
