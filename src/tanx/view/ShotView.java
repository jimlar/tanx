package tanx.view;

import tanx.model.MoveCommand;
import tanx.model.Shot;

import java.awt.*;

public class ShotView {
    private static final int LENGTH = MapView.MAP_BLOCK_SIZE / 2;

    public static void paint(Shot shot, Graphics g) {
        g.setColor(Color.red);
        int x1 = shot.getMapPosition().getPixelX() + (MapView.MAP_BLOCK_SIZE / 2);
        int y1 = shot.getMapPosition().getPixelY() + (MapView.MAP_BLOCK_SIZE / 2);
        int x2 = x1;
        int y2 = y1;

        switch (shot.getDirection()) {
            case MoveCommand.DIRECTION_DOWN:
                y2 += LENGTH;
                break;
            case MoveCommand.DIRECTION_UP:
                y2 -= LENGTH;
                break;
            case MoveCommand.DIRECTION_LEFT:
                x2 -= LENGTH;
                break;
            case MoveCommand.DIRECTION_RIGHT:
                x2 += LENGTH;
                break;
        }
        g.drawLine(x1, y1, x2, y2);
    }

}
