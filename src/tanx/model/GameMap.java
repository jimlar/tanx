package tanx.model;

import java.awt.*;
import java.util.*;

public class GameMap {
    /* The pixel size of a map block (both x and y ways) */
    public static final int MAP_BLOCK_SIZE = 10;

    private static final int BACKGROUND[][] = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private Set mapChangeListeners = new HashSet();

    /* this is the object which has input focus */
    private GameObject focusedObject;
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

    public boolean execute(Command command) {
        if (command instanceof MoveCommand) {
            MoveCommand moveCommand = (MoveCommand) command;
            MapPosition oldPos = moveCommand.getTarget(this).getMapPosition();
            boolean result = moveCommand.getTarget(this).move(moveCommand.getDirection());
            fireMovedEvent(moveCommand.getTarget(this), moveCommand.getDirection(), oldPos);
            return result;
        }
        return false;
    }

    public GameObject getFocusedObject() {
        return this.focusedObject;
    }

    public void setFocusedObject(GameObject focusedObject) {
        this.focusedObject = focusedObject;
    }

    public int getHeight() {
        return BACKGROUND.length;
    }

    public int getPixelHeight() {
        return getHeight() * MAP_BLOCK_SIZE;
    }

    public int getPixelWidth() {
        return getWidth() * MAP_BLOCK_SIZE;
    }

    public int getWidth() {
        return BACKGROUND[0].length;
    }

    public void paint(Graphics g) {
        paintBackground(g);
        paintObjects(g);
    }

    private synchronized Integer getNextObjectId() {
        return new Integer(nextObjectId++);
    }

    private void paintBackground(Graphics g) {

        for (int y = 0; y < BACKGROUND.length; y++) {
            for (int x = 0; x < BACKGROUND[y].length; x++) {
                if (BACKGROUND[y][x] == 1) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(Color.white);
                }

                g.fillRect(x * MAP_BLOCK_SIZE,
                           y * MAP_BLOCK_SIZE,
                           (x + 1) * MAP_BLOCK_SIZE,
                           (y + 1) * MAP_BLOCK_SIZE);
            }
        }
    }

    private void paintObjects(Graphics g) {
        for (Iterator i = objectsById.values().iterator(); i.hasNext();) {
            GameObject gameObject = (GameObject) i.next();
            gameObject.paint(g);
        }
    }

    protected boolean isVisitablePosition(int x, int y) {
        if (x < 0 || y < 0 || y >= BACKGROUND.length || x >= BACKGROUND[0].length) {
            return false;
        }
        return BACKGROUND[y][x] == 0;
    }

    private void fireMovedEvent(GameObject object, int direction, MapPosition oldPosition) {
        for (Iterator i = mapChangeListeners.iterator(); i.hasNext();) {
            MapChangeListener listener = (MapChangeListener) i.next();
            listener.moved(object, direction, oldPosition);
        }
    }

    private void fireRemovedEvent(GameObject object) {
        for (Iterator i = mapChangeListeners.iterator(); i.hasNext();) {
            MapChangeListener listener = (MapChangeListener) i.next();
            listener.removed(object);
        }
    }

    private void fireAddedEvent(GameObject object) {
        for (Iterator i = mapChangeListeners.iterator(); i.hasNext();) {
            MapChangeListener listener = (MapChangeListener) i.next();
            listener.added(object);
        }
    }

    public GameObject getObjectById(Integer id) {
        GameObject object = (GameObject) objectsById.get(id);
        if (object == null) {
            throw new IllegalArgumentException("no object with id " + id);
        }
        return object;
    }
}
