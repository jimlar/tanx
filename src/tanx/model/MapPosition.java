package tanx.model;

import tanx.view.MapView;

public class MapPosition {
    private int x;
    private int y;

    public MapPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getPixelX() {
        return this.x * MapView.MAP_BLOCK_SIZE;
    }

    public int getPixelY() {
        return this.y * MapView.MAP_BLOCK_SIZE;
    }
}
