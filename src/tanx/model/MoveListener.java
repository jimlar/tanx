package tanx.model;


public interface MoveListener {
    void moved(GameObject object, int direction, MapPosition oldPosition);
}
