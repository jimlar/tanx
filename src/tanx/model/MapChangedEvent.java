package tanx.model;

import java.io.Serializable;

public class MapChangedEvent implements Serializable {
    public static final int TYPE_MOVED = 1;
    public static final int TYPE_ADDED = 2;
    public static final int TYPE_REMOVED = 3;

    private int type;
    private Integer targetId;
    private GameObject target;
    private int direction;
    private MapPosition newPosition;

    public static MapChangedEvent createAddedEvent(GameObject addedObject) {
        return new MapChangedEvent(TYPE_ADDED, null, addedObject, -1, null);
    }

    public static MapChangedEvent createRemovedEvent(GameObject removedObject) {
        return new MapChangedEvent(TYPE_REMOVED, removedObject.getId(), null, -1, null);
    }

    public static MapChangedEvent createMovedEvent(GameObject movedObject, int direction) {
        return new MapChangedEvent(TYPE_MOVED, movedObject.getId(), null, direction, movedObject.getMapPosition());
    }

    public MapChangedEvent(int type, Integer targetId, GameObject target, int direction, MapPosition newPosition) {
        this.type = type;
        this.targetId = targetId;
        this.target = target;
        this.direction = direction;
        this.newPosition = newPosition;
    }

    public int getType() {
        return type;
    }

    public GameObject getTarget(GameMap map) {
        if (targetId != null) {
            return map.getObjectById(targetId);
        } else {
            return target;
        }
    }

    public int getDirection() {
        return direction;
    }

    public MapPosition getNewPosition() {
        return newPosition;
    }
}
