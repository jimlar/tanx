package tanx.model;

import java.awt.*;

public class Shot extends GameObject implements Runnable {
    private static final int LENGTH = GameMap.MAP_BLOCK_SIZE / 2;
    private static final int MILLIS_PER_BLOCK = 100;

    private int direction;

    public Shot(MapPosition position, int direction) {
        setMapPosition(position);
        this.direction = direction;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            boolean destroyed = false;
            while (!destroyed) {
                Thread.sleep(MILLIS_PER_BLOCK);
                destroyed = !getMap().execute(new MoveCommand(this, direction));
            }
        } catch (InterruptedException e) {
            System.out.println("Shot thread interrupted");
        }
        getMap().remove(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        int x1 = getMapPosition().getPixelX() + (GameMap.MAP_BLOCK_SIZE / 2);
        int y1 = getMapPosition().getPixelY() + (GameMap.MAP_BLOCK_SIZE / 2);
        int x2 = x1;
        int y2 = y1;

        switch (direction) {
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
