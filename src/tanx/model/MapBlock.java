package tanx.model;

import java.io.Serializable;

public class MapBlock implements Serializable {
    public static final int TYPE_ROAD = 0;
    public static final int TYPE_WALL = 1;

    private int type;

    public MapBlock(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isVisitable() {
        return type == TYPE_ROAD;
    }
}
