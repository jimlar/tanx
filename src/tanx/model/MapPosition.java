package tanx.model;

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
        return this.x * GameMap.MAP_BLOCK_SIZE;
    }

    public int getPixelY() {
        return this.y * GameMap.MAP_BLOCK_SIZE;
    }
}
