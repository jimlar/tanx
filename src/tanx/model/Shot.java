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
            while(!destroyed) {
                Thread.sleep(MILLIS_PER_BLOCK);
                destroyed = !move(direction);
            }
        } catch (InterruptedException e) {
            System.out.println("Shot thread interrupted");
        }
        map.remove(this);
    }

    public void paint(Graphics g) {
        int x1 = position.getPixelX() + (GameMap.MAP_BLOCK_SIZE / 2);
        int y1 = position.getPixelY() + (GameMap.MAP_BLOCK_SIZE / 2);
        int x2 = x1;
        int y2 = y1;

        switch (direction) {
            case DIRECTION_DOWN:
                y2 += LENGTH;
                break;
            case DIRECTION_UP:
                y2 -= LENGTH;
                break;
            case DIRECTION_LEFT:
                x2 -= LENGTH;
                break;
            case DIRECTION_RIGHT:
                x2 += LENGTH;
                break;
        }
        g.drawLine(x1, y1, x2, y2);
    }
}
