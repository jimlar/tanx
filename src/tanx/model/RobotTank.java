package tanx.model;

import java.awt.*;
import java.util.Random;

public class RobotTank extends GameObject implements Runnable {
    private static final int MILLIS_PER_BLOCK = 100;
    private static final int WIDTH = GameMap.MAP_BLOCK_SIZE;
    private static final int HEIGHT = GameMap.MAP_BLOCK_SIZE;
    private static final int CANNON_LENGTH = GameMap.MAP_BLOCK_SIZE;

    private Random random;
    private int facingDirection;

    public RobotTank(MapPosition position) {
        setMapPosition(position);
        this.random = new Random();
        this.facingDirection = DIRECTION_RIGHT;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(MILLIS_PER_BLOCK);
                while (!canMove(facingDirection)) {
                    facingDirection = getNewRandomDirection(facingDirection);
                }

                move(facingDirection);
            }
        } catch (InterruptedException e) {
            System.out.println("Robot tank thread interrupted");
        }
        map.remove(this);
    }

    public void action() {
        map.add(new Shot(position, facingDirection));
    }

    public boolean move(int direction) {
        if (direction == facingDirection) {
            return super.move(direction);
        } else {
            this.facingDirection = direction;
            fireMovedEvent(this, direction, this.position);
            return true;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
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

    private int getNewRandomDirection(int oldDirection) {
        int newDirection = oldDirection;

        while (newDirection == oldDirection) {
            switch (random.nextInt(4)) {
                case 0:
                    newDirection = DIRECTION_DOWN;
                    break;
                case 1:
                    newDirection = DIRECTION_UP;
                    break;
                case 2:
                    newDirection = DIRECTION_LEFT;
                    break;
                case 3:
                    newDirection = DIRECTION_RIGHT;
                    break;
            }
        }
        return newDirection;
    }
}
