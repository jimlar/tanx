package tanx.view;

import tanx.model.*;

import java.awt.*;

public class GameObjectView {
    private static final int TANK_WIDTH = MapView.MAP_BLOCK_SIZE;
    private static final int TANK_HEIGHT = MapView.MAP_BLOCK_SIZE;
    private static final int CANNON_LENGTH = MapView.MAP_BLOCK_SIZE;
    private static final int SHOT_LENGTH = MapView.MAP_BLOCK_SIZE / 2;

    public static void paintObject(GameObject gameObject, Graphics g) {
        if (gameObject instanceof Tank) {
            GameObjectView.paintTank((Tank) gameObject, g);

        } else if (gameObject instanceof Shot) {
            GameObjectView.paintShot((Shot) gameObject, g);

        } else {
            throw new IllegalStateException("unknown type" + gameObject.getClass());
        }
    }

    private static void paintTank(Tank tank, Graphics g) {
        g.setColor(Color.red);
        g.fillRect(tank.getMapPosition().getPixelX(),
                   tank.getMapPosition().getPixelY(),
                   TANK_WIDTH,
                   TANK_HEIGHT);

        paintCannon(tank, g);

    }

    private static void paintCannon(Tank tank, Graphics g) {
        int cannonX1 = tank.getMapPosition().getPixelX() + (TANK_WIDTH / 2);
        int cannonY1 = tank.getMapPosition().getPixelY() + (TANK_HEIGHT / 2);
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

    private static void paintShot(Shot shot, Graphics g) {
        g.setColor(Color.red);
        int x1 = shot.getMapPosition().getPixelX() + (MapView.MAP_BLOCK_SIZE / 2);
        int y1 = shot.getMapPosition().getPixelY() + (MapView.MAP_BLOCK_SIZE / 2);
        int x2 = x1;
        int y2 = y1;

        switch (shot.getDirection()) {
            case MoveCommand.DIRECTION_DOWN:
                y2 += SHOT_LENGTH;
                break;
            case MoveCommand.DIRECTION_UP:
                y2 -= SHOT_LENGTH;
                break;
            case MoveCommand.DIRECTION_LEFT:
                x2 -= SHOT_LENGTH;
                break;
            case MoveCommand.DIRECTION_RIGHT:
                x2 += SHOT_LENGTH;
                break;
        }
        g.drawLine(x1, y1, x2, y2);
    }
}
