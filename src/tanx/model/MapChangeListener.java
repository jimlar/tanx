package tanx.model;


public interface MapChangeListener {

    void moved(GameObject object, int direction, MapPosition oldPosition);

    void added(GameObject object);

    void removed(GameObject object);
}
