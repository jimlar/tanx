package tanx.model;

import java.util.*;

public class GameMap {
    private static final MapBlock r = new MapBlock(MapBlock.TYPE_ROAD);
    private static final MapBlock w = new MapBlock(MapBlock.TYPE_WALL);

    private static final MapBlock BLOCKS[][] = {
        {w, w, w, w, w, w, w, w, w, w, w, w, w},
        {w, r, w, w, w, w, w, r, r, r, r, r, w},
        {w, r, w, w, w, r, r, r, w, w, w, r, w},
        {w, r, w, w, w, r, w, r, w, w, w, r, w},
        {w, r, w, w, w, r, w, r, w, w, w, r, w},
        {w, r, w, w, w, r, w, r, w, w, w, r, w},
        {w, r, w, w, w, r, w, r, w, w, w, r, w},
        {w, r, w, w, w, r, w, r, w, w, w, r, w},
        {w, r, r, r, r, r, w, r, w, w, w, r, w},
        {w, r, w, r, w, w, w, r, w, w, w, r, w},
        {w, r, w, r, w, w, w, r, w, w, w, r, w},
        {w, r, w, r, w, w, w, r, w, w, w, r, w},
        {w, r, r, r, r, r, r, r, r, r, r, r, w},
        {w, w, w, w, w, w, w, w, w, w, w, w, w}};

    private Set mapChangeListeners = new HashSet();

    private Map objectsById = new HashMap();

    private int nextObjectId = 1;

    public void addMapChangeListener(MapChangeListener listener) {
        mapChangeListeners.add(listener);
    }

    public void add(GameObject object) {
        object.setId(getNextObjectId());
        objectsById.put(object.getId(), object);
        object.setMap(this);
        fireAddedEvent(object);
    }

    public void remove(GameObject object) {
        objectsById.remove(object.getId());
        object.setMap(null);
        fireRemovedEvent(object);
    }

    public void execute(Command command) {
        if (command instanceof MoveCommand) {
            MoveCommand moveCommand = (MoveCommand) command;
            boolean result = moveCommand.getTarget(this).move(moveCommand.getDirection());
            if (result) {
                fireMovedEvent(moveCommand.getTarget(this), moveCommand.getDirection());
            }
        } else if (command instanceof ActionCommand) {
            command.getTarget(this).action();
        }
    }

    public int getHeight() {
        return BLOCKS.length;
    }

    public int getWidth() {
        return BLOCKS[0].length;
    }

    public MapBlock getBlockAt(int x, int y) {
        return BLOCKS[y][x];
    }

    private synchronized Integer getNextObjectId() {
        return new Integer(nextObjectId++);
    }

    protected boolean isVisitablePosition(int x, int y) {
        if (x < 0 || y < 0 || y >= BLOCKS.length || x >= BLOCKS[0].length) {
            return false;
        }
        return BLOCKS[y][x].isVisitable();
    }

    private void fireMovedEvent(GameObject object, int direction) {
        for (Iterator i = mapChangeListeners.iterator(); i.hasNext();) {
            MapChangeListener listener = (MapChangeListener) i.next();
            listener.mapChanged(MapChangedEvent.createMovedEvent(object, direction));
        }
    }

    private void fireRemovedEvent(GameObject object) {
        for (Iterator i = mapChangeListeners.iterator(); i.hasNext();) {
            MapChangeListener listener = (MapChangeListener) i.next();
            listener.mapChanged(MapChangedEvent.createRemovedEvent(object));
        }
    }

    private void fireAddedEvent(GameObject object) {
        for (Iterator i = mapChangeListeners.iterator(); i.hasNext();) {
            MapChangeListener listener = (MapChangeListener) i.next();
            listener.mapChanged(MapChangedEvent.createAddedEvent(object));
        }
    }

    public GameObject getObjectById(Integer id) {
        GameObject object = (GameObject) objectsById.get(id);
        if (object == null) {
            throw new IllegalArgumentException("no object with id " + id);
        }
        return object;
    }

    public Collection getObjects() {
        return objectsById.values();
    }
}
