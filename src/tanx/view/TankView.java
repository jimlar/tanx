package tanx.view;

import tanx.model.MoveCommand;
import tanx.model.Tank;

import java.awt.*;

public class TankView {
    private static final int WIDTH = MapView.MAP_BLOCK_SIZE;
    private static final int HEIGHT = MapView.MAP_BLOCK_SIZE;
    private static final int CANNON_LENGTH = MapView.MAP_BLOCK_SIZE;

    public static void paint(Tank tank, Graphics g) {
        g.setColor(Color.red);
        g.fillRect(tank.getMapPosition().getPixelX(),
                   tank.getMapPosition().getPixelY(),
                   WIDTH,
                   HEIGHT);

        paintCannon(tank, g);

    }

    private static void paintCannon(Tank tank, Graphics g) {
        int cannonX1 = tank.getMapPosition().getPixelX() + (WIDTH / 2);
        int cannonY1 = tank.getMapPosition().getPixelY() + (HEIGHT / 2);
        int cannonX2 = cannonX1;
        int cannonY2 = cannonY1;

        switch (tank.getFacingDirection()) {
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
